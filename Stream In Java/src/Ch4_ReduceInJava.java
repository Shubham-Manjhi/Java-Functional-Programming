// Ch4_ReduceInJava.java
// ----------------------
// Working with Stream reduction in Java. Focus: reduce overloads, identity/associativity, immutability, and custom reductions.
// Theory (What/Why/How/Caution):
// - What: reduction collapses a stream into a single result (sum, product, string join, aggregates).
// - Why: express aggregation declaratively; avoid manual loops; enable parallel-friendly associative operations.
// - How: use pure, associative accumulators; supply identity carefully; use Optional-returning reduce for empty streams.
// - Caution: prefer immutable or thread-safe accumulations; avoid mutating shared state inside reduce; use collectors for multi-value aggregation.

import java.util.*;
import java.util.stream.*;

public class Ch4_ReduceInJava {

    // Example 1: Sum with identity (int)
    // Shows reduce(identity, accumulator) for primitives via boxed stream.
    public static void sumWithIdentity() {
        int sum = Stream.of(1, 2, 3, 4, 5)
                .reduce(0, Integer::sum);
        System.out.println("[1] Sum 1..5: " + sum);
    }

    // Example 2: Max without identity -> Optional result
    // Demonstrates reduce(BinaryOperator) returning Optional when stream may be empty.
    public static void maxOptional() {
        Optional<Integer> max = Stream.<Integer>empty()
                .reduce(Integer::max);
        System.out.println("[2] Max of empty stream present? " + max.isPresent());
    }

    // Example 3: String join with reduce
    // Use identity+accumulator for joining; note that Collectors.joining is more efficient for large data.
    public static void stringJoin() {
        String joined = Stream.of("java", "streams", "reduce")
                .reduce("", (acc, s) -> acc.isEmpty() ? s : acc + ", " + s);
        System.out.println("[3] Joined: " + joined);
    }

    // Example 4: Custom immutable reduction to aggregate stats (count, sum, min, max)
    // Illustrates reduce with identity + accumulator + combiner for parallel safety.
    public static void customStats() {
        record Stats(long count, long sum, int min, int max) {
            static Stats empty() { return new Stats(0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE); }
        }
        Stats stats = Stream.of(5, 1, 9, 3)
                .reduce(
                        Stats.empty(),
                        (st, v) -> new Stats(st.count + 1, st.sum + v, Math.min(st.min, v), Math.max(st.max, v)),
                        (a, b) -> new Stats(a.count + b.count, a.sum + b.sum, Math.min(a.min, b.min), Math.max(a.max, b.max))
                );
        double avg = stats.count == 0 ? 0.0 : (double) stats.sum / stats.count;
        System.out.println("[4] Stats -> count=" + stats.count + ", sum=" + stats.sum + ", min=" + stats.min + ", max=" + stats.max + ", avg=" + avg);
    }

    // Example 5: Reduce to an immutable List via accumulator/combiner (not recommended vs collect)
    // Demonstrates correct use of identity/combiner; collect is preferred for collections.
    public static void reduceToList() {
        List<String> result = Stream.of("a", "b", "c")
                .reduce(
                        List.<String>of(),
                        (list, s) -> {
                            List<String> copy = new ArrayList<>(list);
                            copy.add(s);
                            return List.copyOf(copy);
                        },
                        (l1, l2) -> {
                            List<String> merged = new ArrayList<>(l1);
                            merged.addAll(l2);
                            return List.copyOf(merged);
                        }
                );
        System.out.println("[5] Reduced to immutable list: " + result);
    }

    // Example 6: Product with overflow caution and identity semantics
    // Shows identity impact: identity=1 is neutral for multiplication; handle empty streams.
    public static void productWithIdentity() {
        int product = Stream.of(2, 3, 4)
                .reduce(1, (a, b) -> a * b);
        int emptyProduct = Stream.<Integer>empty().reduce(1, (a, b) -> a * b);
        System.out.println("[6] Product 2*3*4: " + product + "; Empty with identity returns: " + emptyProduct);
    }

    // Example 7: Functional and predictable reduction
    // Pure, side-effect-free accumulator and combiner ensure deterministic results (same input => same output), even in parallel.
    public static void functionalPredictable() {
        record SumCount(long sum, long count) {}
        SumCount sc = Stream.of(2, 4, 6, 8)
                .reduce(
                        new SumCount(0, 0),
                        (acc, v) -> new SumCount(acc.sum() + v, acc.count() + 1),
                        (a, b) -> new SumCount(a.sum() + b.sum(), a.count() + b.count())
                );
        double avg = sc.count() == 0 ? 0.0 : (double) sc.sum() / sc.count();
        System.out.println("[7] Functional predictable avg: " + avg);
    }

    public static void main(String[] args) {
        sumWithIdentity();
        maxOptional();
        stringJoin();
        customStats();
        reduceToList();
        productWithIdentity();
        functionalPredictable();
    }
}
