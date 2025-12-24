// Ch2_MappingInJava.java
// -----------------------
// Deep dive into mapping operations in Java Streams with multiple illustrative examples.
// Focus: map, flatMap, mapMulti (Java 16+), primitive mapping, and mapping to custom records/DTOs.

// Theory: What/Why/How of mapping in Streams
// - What: map transforms each element to a new value; flatMap maps to a stream and flattens; mapMulti (Java 16+) emits zero/one/many via a sink without extra temporary collections.
// - Why: separate data shape from behavior, keep pipelines declarative, avoid manual loops and mutation, enable chaining and fusion optimizations.
// - How: prefer pure stateless lambdas; use map for 1:1, flatMap for 1:many, mapMulti for conditional multi-emission; use primitive maps (mapToInt/Double/Long) to avoid boxing.
// - Caution: avoid stateful mapping in parallel streams; if you must accumulate (prefix, window), do it intentionally and document it.
// - DTOs/records: mapping is ideal to project domain models into lighter view/transport shapes.

import java.util.*;
import java.util.stream.*;

public class Ch2_MappingInJava {

    // Example 1: Basic map – transform strings to lengths
    // Demonstrates simple one-to-one transformations and immutability of source.
    public static void basicMap() {
        List<String> words = List.of("apple", "banana", "kiwi");
        List<Integer> lengths = words.stream()
                .map(String::length)
                .toList();
        System.out.println("[1] Word lengths: " + lengths + " (source unchanged: " + words + ")");
    }

    // Example 2: Mapping to custom DTO/record
    // Transforms domain objects into lightweight view models for rendering or transport.
    public static void mapToDto() {
        record Person(String name, int age) {}
        record PersonView(String label, boolean adult) {}

        List<Person> people = List.of(new Person("Alice", 30), new Person("Bob", 16), new Person("Cara", 22));
        List<PersonView> views = people.stream()
                .map(p -> new PersonView(p.name().toUpperCase() + " (" + p.age() + ")", p.age() >= 18))
                .toList();
        System.out.println("[2] Person views: " + views);
    }

    // Example 3: flatMap – expand one-to-many (tokenizing sentences)
    // Flatten a stream of sentences into distinct, sorted tokens.
    public static void flatMapTokens() {
        List<String> sentences = List.of("Streams map data", "Map and flatMap", "Java streams rock");
        List<String> vocab = sentences.stream()
                .flatMap(s -> Arrays.stream(s.toLowerCase().split("\\W+")))
                .filter(token -> !token.isBlank())
                .distinct()
                .sorted()
                .toList();
        System.out.println("[3] Vocab: " + vocab);
    }

    // Example 4: mapMulti (Java 16+) – efficient multi-mapping without intermediate collections
    // Emits zero, one, or many results per input via a sink; useful for conditional expansion.
    public static void mapMultiDemo() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> doubledEvens = numbers.stream()
                .<Integer>mapMulti((n, sink) -> {
                    if (n % 2 == 0) sink.accept(n * 2); // emit only for evens
                })
                .toList();
        System.out.println("[4] mapMulti doubled evens: " + doubledEvens);
    }

    // Example 5: Primitive mapping – avoiding boxing (mapToInt/mapToDouble)
    // Show converting objects to primitives and using primitive ops (sum/average/max).
    public static void primitiveMapping() {
        List<String> data = List.of("10", "20", "7");
        IntStream ints = data.stream().mapToInt(Integer::parseInt);
        int sum = ints.sum();
        double avg = data.stream().mapToInt(Integer::parseInt).average().orElse(0.0);
        int max = data.stream().mapToInt(Integer::parseInt).max().orElse(Integer.MIN_VALUE);
        System.out.println("[5] Sum: " + sum + ", Avg: " + avg + ", Max: " + max);
    }

    // Example 6: Mapping with stateful transform (cumulative prefix) using scan-like approach
    // Illustrates that stateful operations should be used carefully; done here with reduce-like fold.
    public static void cumulativeMapping() {
        List<Integer> nums = List.of(1, 2, 3, 4);
        List<Integer> prefixSums = new ArrayList<>();
        nums.stream().reduce(0, (acc, n) -> {
            int next = acc + n;
            prefixSums.add(next);
            return next;
        });
        System.out.println("[6] Prefix sums (illustrative stateful mapping): " + prefixSums);
    }

    public static void main(String[] args) {
        basicMap();
        mapToDto();
        flatMapTokens();
        mapMultiDemo();
        primitiveMapping();
        cumulativeMapping();
    }
}

