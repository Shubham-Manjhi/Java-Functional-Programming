import java.util.*;
import java.util.function.*;
import java.util.stream.*;

// Practice Set 2: Custom Collectors
public class Solution {
    public record Stats(long count, long sum, int min, int max, double average) {}
    public record MinMax<T>(T min, T max) {}

    // 1) toImmutableList collector
    public static <T> Collector<T, ?, List<T>> toImmutableList() {
        return Collector.of(
                ArrayList::new,
                List::add,
                (left, right) -> { left.addAll(right); return left; },
                list -> List.copyOf(list),
                Collector.Characteristics.UNORDERED
        );
    }

    // 2) toStats collector for ints
    public static Collector<Integer, ?, Stats> toStats() {
        class Acc { long count; long sum; int min = Integer.MAX_VALUE; int max = Integer.MIN_VALUE; }
        return Collector.of(
                Acc::new,
                (acc, v) -> { acc.count++; acc.sum += v; acc.min = Math.min(acc.min, v); acc.max = Math.max(acc.max, v); },
                (a, b) -> { a.count += b.count; a.sum += b.sum; a.min = Math.min(a.min, b.min); a.max = Math.max(a.max, b.max); return a; },
                acc -> new Stats(acc.count, acc.sum, acc.min, acc.max, acc.count == 0 ? 0 : (double) acc.sum / acc.count)
        );
    }

    // 3) groupingAndTransforming: group by key, map value, then aggregate to list
    public static <T, K, V> Collector<T, ?, Map<K, List<V>>> groupingAndTransforming(Function<T, K> keyFn, Function<T, V> valFn) {
        return Collectors.groupingBy(keyFn, Collectors.mapping(valFn, Collectors.toList()));
    }

    // 4) topN collector using a priority queue (min-heap)
    public static <T> Collector<T, ?, List<T>> topN(int n, Comparator<T> comparator) {
        return Collector.of(
                () -> new PriorityQueue<>(n, comparator),
                (pq, v) -> { pq.offer(v); if (pq.size() > n) pq.poll(); },
                (a, b) -> { b.forEach(v -> { a.offer(v); if (a.size() > n) a.poll(); }); return a; },
                pq -> {
                    List<T> out = new ArrayList<>(pq);
                    out.sort(comparator.reversed()); // highest first
                    return List.copyOf(out);
                }
        );
    }

    public static void main(String[] args) {
        // Demo: toImmutableList
        List<Integer> imm = Stream.of(1, 2, 3).collect(toImmutableList());
        System.out.println("Immutable list: " + imm);

        // Demo: toStats
        Stats stats = IntStream.of(5, 1, 9, 3).boxed().collect(toStats());
        System.out.println("Stats: " + stats);

        // Demo: groupingAndTransforming
        record Person(String city, String name) {}
        List<Person> people = List.of(new Person("NY", "Alice"), new Person("NY", "Bob"), new Person("SF", "Cara"));
        Map<String, List<String>> byCity = people.stream().collect(groupingAndTransforming(Person::city, Person::name));
        System.out.println("Grouped names: " + byCity);

        // Demo: topN
        List<Integer> top = Stream.of(5, 2, 9, 1, 7, 9).collect(topN(3, Comparator.naturalOrder()));
        System.out.println("Top 3: " + top);

        // Demo: teeing for MinMax
        MinMax<Integer> minMax = Stream.of(5, 2, 9, 1, 7).collect(Collectors.teeing(
                Collectors.minBy(Integer::compareTo),
                Collectors.maxBy(Integer::compareTo),
                (min, max) -> new MinMax<>(min.orElse(null), max.orElse(null))
        ));
        System.out.println("MinMax: " + minMax);
    }
}

