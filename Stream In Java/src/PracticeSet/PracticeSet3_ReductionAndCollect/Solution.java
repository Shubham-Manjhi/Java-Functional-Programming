import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Solution {
    public static void main(String[] args) {
        // 1. reduce with combiner for parallel sum of squares
        int sumSquares = IntStream.rangeClosed(1, 10)
                .parallel()
                .reduce(0, (acc, n) -> acc + n * n, Integer::sum);
        System.out.println("sumSquares 1..10: " + sumSquares);

        // 2. max with Optional reduce
        Optional<Integer> max = Stream.<Integer>empty().reduce(Integer::max);
        System.out.println("max present? " + max.isPresent());

        // 3. toMap frequency with merge
        List<String> words = List.of("java", "stream", "java", "map");
        Map<String, Integer> freq = words.stream()
                .collect(Collectors.toMap(Function.identity(), w -> 1, Integer::sum, LinkedHashMap::new));
        System.out.println("freq: " + freq);

        // 4. grouping names by city + counts
        record Person(String name, String city) {}
        List<Person> people = List.of(
                new Person("Alice", "NY"), new Person("Bob", "SF"),
                new Person("Cara", "NY"), new Person("Dan", "SF"), new Person("Eve", "NY")
        );
        Map<String, List<String>> namesByCity = people.stream()
                .collect(Collectors.groupingBy(Person::city, Collectors.mapping(Person::name, Collectors.toList())));
        Map<String, Long> countByCity = people.stream()
                .collect(Collectors.groupingBy(Person::city, Collectors.counting()));
        System.out.println("namesByCity: " + namesByCity);
        System.out.println("countByCity: " + countByCity);

        // 5. partition pass/fail avg
        List<Integer> scores = List.of(90, 55, 72, 88, 40, 95);
        Map<Boolean, Double> avgByPass = scores.stream()
                .collect(Collectors.partitioningBy(s -> s >= 60, Collectors.averagingInt(Integer::intValue)));
        System.out.println("avgByPass: " + avgByPass);

        // 6. collectingAndThen immutable sorted distinct
        List<String> data = List.of("b", "c", "a", "b");
        List<String> sortedDistinct = data.stream()
                .distinct()
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    list.sort(String::compareTo);
                    return List.copyOf(list);
                }));
        System.out.println("sortedDistinct immutable: " + sortedDistinct);
    }
}

