import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Solution {
    public static void main(String[] args) {
        // 1. custom collector average
        class Acc { long sum; long count; }
        double avg = Stream.of(10, 20, 30)
                .collect(Collector.of(
                        Acc::new,
                        (a, v) -> { a.sum += v; a.count++; },
                        (a, b) -> { a.sum += b.sum; a.count += b.count; return a; },
                        a -> a.count == 0 ? 0.0 : (double) a.sum / a.count
                ));
        System.out.println("avg: " + avg);

        // 2. topN collector
        Collector<Integer, ?, List<Integer>> top3 = Collector.of(
                () -> new PriorityQueue<>(Comparator.naturalOrder()),
                (pq, v) -> { pq.offer(v); if (pq.size() > 3) pq.poll(); },
                (a, b) -> { b.forEach(v -> { a.offer(v); if (a.size() > 3) a.poll(); }); return a; },
                pq -> {
                    List<Integer> out = new ArrayList<>(pq);
                    out.sort(Comparator.reverseOrder());
                    return List.copyOf(out);
                }
        );
        List<Integer> top = Stream.of(5, 2, 9, 1, 7, 9).collect(top3);
        System.out.println("top3: " + top);

        // 3. grouping names upper by city
        record Person(String name, String city) {}
        List<Person> people = List.of(
                new Person("Alice", "NY"), new Person("Bob", "SF"),
                new Person("Cara", "NY"), new Person("Dan", "SF"), new Person("Eve", "NY")
        );
        Map<String, List<String>> namesByCity = people.stream()
                .collect(Collectors.groupingBy(Person::city,
                        Collectors.mapping(p -> p.name().toUpperCase(), Collectors.toList())));
        System.out.println("namesByCity upper: " + namesByCity);

        // 4. grouping to unmodifiable sets
        Map<String, Set<String>> namesSetByCity = people.stream()
                .collect(Collectors.groupingBy(Person::city,
                        Collectors.collectingAndThen(
                                Collectors.mapping(Person::name, Collectors.toSet()),
                                Collections::unmodifiableSet)));
        System.out.println("namesSetByCity unmodifiable: " + namesSetByCity);

        // 5. teeing min/max
        record MinMax<T>(T min, T max) {}
        MinMax<Integer> mm = Stream.of(5, 2, 9, 1, 7).collect(Collectors.teeing(
                Collectors.minBy(Integer::compareTo),
                Collectors.maxBy(Integer::compareTo),
                (min, max) -> new MinMax<>(min.orElse(null), max.orElse(null))
        ));
        System.out.println("min/max: " + mm);
    }
}

