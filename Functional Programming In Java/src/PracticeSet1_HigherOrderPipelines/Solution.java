import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.stream.*;

// Practice Set 1: Higher-Order Pipelines
// Demonstrates higher-order functions to build expressive pipelines with logging, batching, and aggregation.
public class Solution {
    // Utility record to hold a result with its duration
    public record TimedResult<R>(R result, long durationMillis) {}

    // Higher-order helper that logs stages around a pipeline
    public static <T, R> R withLogging(Stream<T> stream, Function<Stream<T>, R> pipeline) {
        System.out.println("[withLogging] start");
        R result = pipeline.apply(stream);
        System.out.println("[withLogging] end -> " + result);
        return result;
    }

    // Measure a Supplier and return result plus duration
    public static <R> TimedResult<R> timed(Supplier<R> supplier) {
        Instant start = Instant.now();
        R result = supplier.get();
        long duration = Duration.between(start, Instant.now()).toMillis();
        return new TimedResult<>(result, duration);
    }

    // Chunk a stream into batches of given size
    public static <T> Stream<List<T>> chunk(Stream<T> stream, int size) {
        Iterator<T> it = stream.iterator();
        List<List<T>> chunks = new ArrayList<>();
        while (it.hasNext()) {
            List<T> batch = new ArrayList<>(size);
            for (int i = 0; i < size && it.hasNext(); i++) {
                batch.add(it.next());
            }
            chunks.add(batch);
        }
        return chunks.stream();
    }

    // Frequency map with stop-word filtering; thread-safe for parallel streams
    public static Map<String, Long> wordFreq(List<String> sentences, Set<String> stopWords, boolean parallel) {
        Stream<String> stream = sentences.stream();
        if (parallel) stream = stream.parallel();

        return stream
                .flatMap(s -> Arrays.stream(s.toLowerCase().split("\\W+")))
                .filter(w -> !w.isEmpty())
                .filter(w -> !stopWords.contains(w))
                .collect(Collectors.groupingBy(Function.identity(), parallel ? ConcurrentHashMap::new : HashMap::new, Collectors.counting()));
    }

    public static void main(String[] args) {
        // Task 2: pipeline with logging, filtering, mapping, batching, reducing
        List<Integer> nums = IntStream.rangeClosed(1, 12).boxed().toList();
        withLogging(nums.stream(), s ->
                chunk(s.filter(n -> n % 2 == 1) // odds
                        .map(n -> n * n),      // squares
                        3)
                        .map(batch -> batch.stream().reduce(0, Integer::sum))
                        .toList()
        );

        // Task 3: timed supplier
        TimedResult<Integer> timedSum = timed(() -> IntStream.rangeClosed(1, 1_000_000).sum());
        System.out.println("Timed sum -> result=" + timedSum.result() + ", ms=" + timedSum.durationMillis());

        // Task 4 & 5: word frequency with stop-words and optional parallelism
        List<String> sentences = List.of(
                "Java streams make functional pipelines clear",
                "Functional pipelines benefit from immutability",
                "Immutability reduces side effects and bugs",
                "Pipelines can be parallel when thread-safe"
        );
        Set<String> stop = Set.of("and", "the", "a", "be", "can", "from");

        Map<String, Long> freqSequential = wordFreq(sentences, stop, false);
        System.out.println("Sequential freq: " + freqSequential);

        Map<String, Long> freqParallel = wordFreq(sentences, stop, true);
        System.out.println("Parallel freq:   " + freqParallel);
    }
}

