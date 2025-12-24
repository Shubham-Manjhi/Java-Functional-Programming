// Ch5_CollectInJava.java
// -----------------------
// Working with Stream collect in Java. Focus: built-in collectors, toMap merge rules, grouping/partitioning,
// collectingAndThen for immutability, custom collectors, and deterministic/functional patterns.
// Theory (What/Why/How/Caution):
// - What: collect is a terminal operation that folds a stream into a container/result (List, Map, String, stats).
// - Why: declarative aggregation with reusable collectors; avoids manual loops and mutable plumbing.
// - How: pick the right collector (toList, toSet, joining, toMap, groupingBy, partitioningBy, collectingAndThen,
//   mapping, summarizing, custom Collector.of). Keep accumulators pure and associative for predictability.
// - Caution: beware duplicate keys with toMap (supply a merge fn), prefer unmodifiable outputs when sharing,
//   avoid mutating shared state during collection; choose concurrent collectors only when safe.

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Ch5_CollectInJava {

    // Example 1: Basic collectors (toList, toSet, joining)
    public static void basicCollectors() {
        List<String> items = List.of("apple", "banana", "apple", "cherry");
        List<String> list = items.stream().collect(Collectors.toList());
        Set<String> set = items.stream().collect(Collectors.toSet());
        String joined = items.stream().collect(Collectors.joining(", "));
        System.out.println("[1] list=" + list + " set=" + set + " joined=" + joined);
    }

    // Example 2: toMap with merge to handle duplicate keys
    public static void toMapWithMerge() {
        List<String> words = List.of("java", "stream", "java", "map");
        Map<String, Integer> freq = words.stream()
                .collect(Collectors.toMap(Function.identity(), w -> 1, Integer::sum, LinkedHashMap::new));
        System.out.println("[2] freq map (LinkedHashMap to preserve order): " + freq);
    }

    // Example 3: groupingBy + mapping (project values) and counting
    public static void groupingAndCounting() {
        record Person(String name, String city) {}
        List<Person> people = List.of(
                new Person("Alice", "NY"), new Person("Bob", "SF"),
                new Person("Cara", "NY"), new Person("Dan", "SF"), new Person("Eve", "NY")
        );
        Map<String, List<String>> namesByCity = people.stream()
                .collect(Collectors.groupingBy(Person::city, Collectors.mapping(Person::name, Collectors.toList())));
        Map<String, Long> countByCity = people.stream()
                .collect(Collectors.groupingBy(Person::city, Collectors.counting()));
        System.out.println("[3] Names by city: " + namesByCity);
        System.out.println("[3] Count by city: " + countByCity);
    }

    // Example 4: partitioningBy with downstream collector (avg of passing scores)
    public static void partitioningWithAvg() {
        List<Integer> scores = List.of(90, 55, 72, 88, 40, 95);
        Map<Boolean, Double> avgByPass = scores.stream()
                .collect(Collectors.partitioningBy(s -> s >= 60,
                        Collectors.averagingInt(Integer::intValue)));
        System.out.println("[4] Avg by pass/fail: " + avgByPass);
    }

    // Example 5: collectingAndThen for immutability + post-processing (sorted)
    public static void collectingAndThenDemo() {
        List<String> data = List.of("b", "c", "a", "b");
        List<String> sortedDistinct = data.stream()
                .distinct()
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    list.sort(String::compareTo);
                    return List.copyOf(list);
                }));
        System.out.println("[5] Sorted distinct immutable: " + sortedDistinct);
    }

    // Example 6: Custom collector via Collector.of (sum + count -> average)
    public static void customCollectorAverage() {
        class Acc { long sum; long count; }
        double avg = Stream.of(10, 20, 30)
                .collect(Collector.of(
                        Acc::new,
                        (a, v) -> { a.sum += v; a.count++; },
                        (a, b) -> { a.sum += b.sum; a.count += b.count; return a; },
                        a -> a.count == 0 ? 0.0 : (double) a.sum / a.count
                ));
        System.out.println("[6] Custom collector average: " + avg);
    }

    // Example 7: Functional and predictable collection
    // Pure mapping + sorting + unmodifiable output ensures deterministic results (same input => same output).
    public static void functionalPredictable() {
        List<Integer> data = List.of(5, 2, 9, 2, 7);
        List<Integer> result = data.stream()
                .map(n -> n * 2)          // pure function
                .sorted()                 // deterministic order
                .collect(Collectors.collectingAndThen(Collectors.toList(), List::copyOf));
        System.out.println("[7] Functional predictable doubled+sorted immutable: " + result);
    }

    // Example 8: Using Function to parameterize collection behavior
    // Demonstrates passing a Function to shape keys before collecting.
    public static void functionsInCollect() {
        List<String> animals = List.of("Ant", "Ape", "cat", "Cougar", "cow");
        Function<String, String> keyFn = s -> s.substring(0, 1).toUpperCase();
        Map<String, List<String>> byInitial = animals.stream()
                .collect(Collectors.groupingBy(keyFn, Collectors.mapping(String::toLowerCase, Collectors.toList())));
        System.out.println("[8] Grouped by initial (Function-based): " + byInitial);
    }

    public static void main(String[] args) {
        basicCollectors();
        toMapWithMerge();
        groupingAndCounting();
        partitioningWithAvg();
        collectingAndThenDemo();
        customCollectorAverage();
        functionalPredictable();
        functionsInCollect();
    }
}

