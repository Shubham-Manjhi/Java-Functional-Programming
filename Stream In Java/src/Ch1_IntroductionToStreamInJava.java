// Ch1_IntroductionToStreamInJava
// -----------------------------------
// Intro to Java Streams with concise theory and multiple runnable examples.
// Streams provide declarative, lazy pipelines for transforming and aggregating data.

import java.util.*;
import java.util.stream.*;

public class Ch1_IntroductionToStreamInJava {

    // Example 1: Basic filter + map + collect
    // Demonstrates declarative pipelines: filter (predicate) -> map (transform) -> terminal collect.
    public static void basicPipeline() {
        List<String> names = List.of("alice", "bob", "carol", "dave");
        List<String> upperLong = names.stream()
                .filter(n -> n.length() >= 4)
                .map(String::toUpperCase)
                .toList();
        System.out.println("[1] Uppercase length>=4: " + upperLong);
    }

    // Example 2: Reduction (sum) and summary statistics
    // Shows terminal reductions: summaryStatistics gives count/min/max/sum/avg; reduce combines values.
    public static void reductionAndStats() {
        IntSummaryStatistics stats = IntStream.rangeClosed(1, 10).summaryStatistics();
        int sumSquares = IntStream.rangeClosed(1, 5)
                .map(n -> n * n)
                .reduce(0, Integer::sum);
        System.out.println("[2] Stats 1..10 -> " + stats + "; sum of squares 1..5 -> " + sumSquares);
    }

    // Example 3: flatMap for nested data + distinct + sorted
    // Flatten nested lists, normalize case, remove duplicates, and sort for a canonical vocabulary.
    public static void flatMapDistinctSorted() {
        List<List<String>> phrases = List.of(
                List.of("functional", "streams"),
                List.of("java", "streams"),
                List.of("java", "functional")
        );
        List<String> vocab = phrases.stream()
                .flatMap(List::stream)
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .toList();
        System.out.println("[3] Distinct sorted vocab: " + vocab);
    }

    // Example 4: Collectors grouping and partitioning
    // groupingBy: multi-bucket aggregation by key; partitioningBy: boolean split (2 buckets).
    public static void groupingAndPartitioning() {
        record Person(String name, int age, String city) {}
        List<Person> people = List.of(
                new Person("Alice", 30, "NY"),
                new Person("Bob", 17, "SF"),
                new Person("Cara", 22, "NY"),
                new Person("Dan", 16, "LA"),
                new Person("Eve", 40, "SF")
        );

        Map<String, List<Person>> byCity = people.stream()
                .collect(Collectors.groupingBy(Person::city));

        Map<Boolean, List<Person>> adultsVsMinors = people.stream()
                .collect(Collectors.partitioningBy(p -> p.age() >= 18));

        System.out.println("[4] Grouped by city: " + byCity);
        System.out.println("[4] Partition adults/minors: " + adultsVsMinors);
    }

    // Example 5: Primitive streams and boxing avoidance
    // IntStream/DoubleStream avoid boxing overhead; terminal ops like max/average stay primitive.
    public static void primitiveStreams() {
        int maxEven = IntStream.range(1, 20)
                .filter(n -> n % 2 == 0)
                .max()
                .orElse(-1);
        double avg = DoubleStream.of(1.5, 2.5, 3.5).average().orElse(0.0);
        System.out.println("[5] Max even <20: " + maxEven + "; Average doubles: " + avg);
    }

    // Example 6: Short-circuiting and Optionals
    // findFirst stops early; Optional models presence/absence safely without nulls.
    public static void shortCircuiting() {
        Optional<String> firstLong = Stream.of("a", "bb", "ccc", "dddd")
                .filter(s -> s.length() > 3)
                .findFirst();
        System.out.println("[6] First length>3: " + firstLong.orElse("none"));
    }

    // Example 7: Parallel stream caution (order, shared state)
    // Parallelism can speed CPU-bound, stateless pipelines; avoid shared mutable state, mind ordering.
    public static void parallelDemo() {
        List<Integer> data = IntStream.rangeClosed(1, 10).boxed().toList();
        int sum = data.parallelStream().reduce(0, Integer::sum);
        System.out.println("[7] Parallel sum 1..10: " + sum + " (use only for suitable workloads)");
    }

    public static void main(String[] args) {
        basicPipeline();
        reductionAndStats();
        flatMapDistinctSorted();
        groupingAndPartitioning();
        primitiveStreams();
        shortCircuiting();
        parallelDemo();
    }
}
