// Ch3_FilterInJava.java
// ----------------------
// Working with Stream filtering in Java. Focus: predicates, composition, short-circuiting, takeWhile/dropWhile, map+filter safety.
// Theory (What/Why/How/Caution):
// - What: filter keeps elements matching a predicate; non-matching are dropped, preserving encounter order.
// - Why: express selection declaratively, avoid manual loops/mutation, compose with maps/reductions safely.
// - How: use pure predicates; reuse and compose predicates; prefer filter before expensive operations; leverage takeWhile/dropWhile for ordered streams (Java 9+).
// - Caution: avoid side effects in predicates; be careful with stateful filters in parallel; remember filter doesn’t de-duplicate (use distinct if needed).

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;

public class Ch3_FilterInJava {

    // Example 1: Basic filtering with reusable predicates
    // Shows predicate reuse and composition for clarity and DRY behavior.
    public static void basicFiltering() {
        Predicate<String> nonBlank = s -> s != null && !s.isBlank();
        Predicate<String> longWord = s -> s.length() >= 4;
        Predicate<String> interesting = nonBlank.and(longWord);

        List<String> words = List.of("", "java", "stream", "api", " ", "mapping");
        List<String> kept = words.stream()
                .filter(interesting)
                .toList();
        System.out.println("[1] Filtered words: " + kept);
    }

    // Example 2: Guarding expensive operations with filter first
    // Filter cheap, then map heavy; prevents errors (e.g., parsing) and saves work.
    public static void filterBeforeMap() {
        List<String> raw = List.of("10", "abc", "25", "-5", "x");
        List<Integer> positives = raw.stream()
                .filter(s -> s.matches("-?\\d+"))
                .map(Integer::parseInt)
                .filter(n -> n > 0)
                .toList();
        System.out.println("[2] Parsed positive ints: " + positives);
    }

    // Example 3: Short-circuiting with findFirst and Optional
    // findFirst stops once a match is found; Optional models presence/absence.
    public static void shortCircuitFindFirst() {
        Optional<String> firstLong = Stream.of("hi", "hey", "hello", "hola")
                .filter(s -> s.length() > 3)
                .findFirst();
        System.out.println("[3] First length>3: " + firstLong.orElse("none"));
    }

    // Example 4: takeWhile/dropWhile (Java 9+) on ordered streams
    // takeWhile keeps prefix while predicate holds; dropWhile skips prefix until predicate fails.
    public static void takeDropWhileDemo() {
        List<Integer> numbers = List.of(1, 2, 3, 0, 4, 5, 0, 6);
        List<Integer> prefixPositives = numbers.stream().takeWhile(n -> n > 0).toList();
        List<Integer> afterFirstZero = numbers.stream().dropWhile(n -> n > 0).toList();
        System.out.println("[4] takeWhile >0: " + prefixPositives);
        System.out.println("[4] dropWhile >0: " + afterFirstZero);
    }

    // Example 5: Filtering map entries (by value) and transforming keys
    // Demonstrates filtering on Map streams and post-filter mapping.
    public static void filterMapEntries() {
        Map<String, Integer> scores = Map.of("alice", 90, "bob", 70, "cara", 85, "dan", 60);
        List<String> honors = scores.entrySet().stream()
                .filter(e -> e.getValue() >= 80)
                .map(e -> e.getKey().toUpperCase() + "=" + e.getValue())
                .sorted()
                .toList();
        System.out.println("[5] Honors entries: " + honors);
    }

    // Example 6: Filtering with custom domain predicate and partitioning
    // Combine filter for selection and partitioning for two-way split.
    public static void domainFiltering() {
        record Task(String name, boolean done, int priority) {}
        List<Task> tasks = List.of(
                new Task("Write", false, 2),
                new Task("Review", true, 1),
                new Task("Refactor", false, 3),
                new Task("Deploy", true, 1)
        );
        List<Task> openHighPriority = tasks.stream()
                .filter(t -> !t.done())
                .filter(t -> t.priority() <= 2)
                .toList();
        Map<Boolean, List<Task>> byDone = tasks.stream()
                .collect(Collectors.partitioningBy(Task::done));
        System.out.println("[6] Open high-priority: " + openHighPriority);
        System.out.println("[6] Partition by done: " + byDone);
    }

    // Example 7: Distinct vs filter – complementary tools
    // filter does not deduplicate; show distinct for uniqueness after filtering.
    public static void filterWithDistinct() {
        List<String> data = List.of("a", "b", "a", "c", "b", "d");
        List<String> unique = data.stream()
                .filter(s -> s.compareTo("b") >= 0)
                .distinct()
                .sorted()
                .toList();
        System.out.println("[7] Filter >= b then distinct: " + unique);
    }

    // Example 8: Functional and predictable filtering
    // Pure predicates, no side effects, stable order -> same input yields same output (deterministic and testable).
    public static void functionalPredictableFiltering() {
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> belowTen = n -> n < 10;
        List<Integer> data = List.of(12, 2, 9, 4, 11, 8, 2);
        List<Integer> result = data.stream()
                .filter(isEven.and(belowTen))
                .sorted() // deterministic ordering
                .toList();
        System.out.println("[8] Functional predictable evens<10 sorted: " + result);
    }

    public static void main(String[] args) {
        basicFiltering();
        filterBeforeMap();
        shortCircuitFindFirst();
        takeDropWhileDemo();
        filterMapEntries();
        domainFiltering();
        filterWithDistinct();
        functionalPredictableFiltering();
    }
}
