import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Solution {
    public static void main(String[] args) {
        // 1. Parallel sum range
        long sum = LongStream.rangeClosed(1, 1_000_000)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println("sum 1..1_000_000: " + sum);

        // 2. Parallel sum of squares with combiner
        int sumSquares = IntStream.rangeClosed(1, 10)
                .parallel()
                .reduce(0, (acc, n) -> acc + n * n, Integer::sum);
        System.out.println("sumSquares 1..10: " + sumSquares);

        // 3. Parallel concurrent frequency map
        List<String> words = List.of("java", "parallel", "streams", "java", "forkjoin");
        Map<String, Long> freq = words.parallelStream()
                .collect(Collectors.toConcurrentMap(Function.identity(), w -> 1L, Long::sum));
        System.out.println("freq: " + freq);

        // 4. unordered vs ordered
        List<Integer> data = IntStream.rangeClosed(1, 10).boxed().toList();
        List<Integer> ordered = data.parallelStream().filter(n -> n % 2 == 0).toList();
        List<Integer> unordered = data.parallelStream().unordered().filter(n -> n % 2 == 0).toList();
        System.out.println("ordered evens: " + ordered);
        System.out.println("unordered evens: " + unordered);

        // 5. predictable parallel: pure, sorted, immutable
        List<Integer> predictable = data.parallelStream()
                .map(n -> n * 3)
                .filter(n -> n % 2 == 0)
                .sorted()
                .collect(Collectors.collectingAndThen(Collectors.toList(), List::copyOf));
        System.out.println("predictable: " + predictable);

        // 6. Not for blocking demo
        List<Integer> tiny = List.of(1, 2, 3, 4);
        long start = System.currentTimeMillis();
        tiny.parallelStream().forEach(n -> {
            try { Thread.sleep(50); } catch (InterruptedException ignored) {}
        });
        long duration = System.currentTimeMillis() - start;
        System.out.println("blocking parallel took ~" + duration + " ms (shows inefficiency for I/O)");
    }
}

