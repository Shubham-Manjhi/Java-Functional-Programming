// Ch5_ParalletStreamsInJava.java
// --------------------------------
// Working with Parallel Streams in Java. Focus: when/why to use, pitfalls (ordering, shared state),
// performance cautions, proper reduce/collect usage, and predictable functional style.
// Theory (What/Why/How/Caution):
// - What: parallelStream splits work across threads using the common ForkJoinPool.
// - Why: speed up CPU-bound, large, independent workloads; not a silver bullet for I/O or small data.
// - How: keep operations stateless, associative, and side-effect-free; use correct identity/combiner in reduce/collect;
//   preserve order only when needed (may cost performance).
// - Caution: avoid shared mutable state, avoid blocking ops on common pool, mind thread count via system property or custom pool.

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Ch5_ParalletStreamsInJava {

    // Example 1: Parallel sum of a range (CPU-bound) using reduce
    public static void parallelSum() {
        long sum = LongStream.rangeClosed(1, 1_000_000)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println("[1] Parallel sum 1..1_000_000: " + sum);
    }

    // Example 2: Ordered vs unordered processing
    // unordered() may allow better parallel performance when order is not required.
    public static void orderedVsUnordered() {
        List<Integer> data = IntStream.rangeClosed(1, 20).boxed().toList();
        List<Integer> ordered = data.parallelStream()
                .filter(n -> n % 2 == 0)
                .toList();
        List<Integer> unordered = data.parallelStream()
                .unordered()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("[2] Ordered evens (encounter order): " + ordered);
        System.out.println("[2] Unordered evens (may differ): " + unordered);
    }

    // Example 3: Parallel reduce with proper combiner (sum of squares)
    public static void parallelReduceSumSquares() {
        int result = IntStream.rangeClosed(1, 10)
                .parallel()
                .reduce(0, (acc, n) -> acc + n * n, Integer::sum);
        System.out.println("[3] Parallel sum of squares 1..10: " + result);
    }

    // Example 4: Parallel collect to thread-safe structure (toConcurrentMap)
    public static void parallelToConcurrentMap() {
        List<String> words = List.of("java", "parallel", "streams", "java", "forkjoin");
        Map<String, Long> freq = words.parallelStream()
                .collect(Collectors.toConcurrentMap(Function.identity(), w -> 1L, Long::sum));
        System.out.println("[4] Parallel concurrent freq: " + freq);
    }

    // Example 5: Functions parameterizing work (functional example)
    // Pass a Function to shape work and keep pipeline pure/stateless for predictability.
    public static void functionalExample() {
        List<String> items = List.of("alpha", "beta", "gamma", "delta");
        Function<String, Integer> work = s -> s.toUpperCase().hashCode();
        List<Integer> hashed = items.parallelStream()
                .map(work)
                .sorted() // keep deterministic output
                .toList();
        System.out.println("[5] Functional hash work (sorted for determinism): " + hashed);
    }

    // Example 6: Predictable/side-effect-free parallel processing
    // Stateless map/filter + terminal collect to immutable list ensures deterministic values (ordering via sorted).
    public static void predictableParallel() {
        List<Integer> data = List.of(5, 1, 4, 2, 8, 3);
        List<Integer> result = data.parallelStream()
                .map(n -> n * 3)
                .filter(n -> n % 2 == 0)
                .sorted() // enforce deterministic order
                .collect(Collectors.collectingAndThen(Collectors.toList(), List::copyOf));
        System.out.println("[6] Predictable parallel doubled-even (immutable, sorted): " + result);
    }

    // Example 7: When NOT to use parallel (I/O-bound demo with artificial sleep)
    // Shows that blocking ops negate benefits and may harm throughput.
    public static void notForIOBound() {
        List<Integer> tiny = List.of(1, 2, 3, 4);
        long start = System.currentTimeMillis();
        tiny.parallelStream().forEach(n -> {
            try { Thread.sleep(50); } catch (InterruptedException ignored) {}
        });
        long duration = System.currentTimeMillis() - start;
        System.out.println("[7] Parallel with blocking took ~" + duration + " ms (not ideal for I/O-bound)");
    }

    public static void main(String[] args) {
        parallelSum();
        orderedVsUnordered();
        parallelReduceSumSquares();
        parallelToConcurrentMap();
        functionalExample();
        predictableParallel();
        notForIOBound();
    }
}

