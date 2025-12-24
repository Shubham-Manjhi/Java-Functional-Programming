import java.util.*;
import java.util.stream.*;

public class Solution {
    public static void main(String[] args) {
        // 1. Stream creation + lazy logging
        Stream<Integer> fromList = List.of(1, 2, 3, 4, 5).stream();
        Stream<String> fromArray = Arrays.stream(new String[]{"a", "b", "c"});
        IntStream range = IntStream.rangeClosed(1, 5);
        System.out.println("fromList class=" + fromList.getClass());
        System.out.println("fromArray class=" + fromArray.getClass());
        System.out.println("range sum=" + range.sum());

        // 2. Filter even, square, limit 5
        List<Integer> evensSquared = IntStream.rangeClosed(1, 20)
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .limit(5)
                .boxed()
                .toList();
        System.out.println("evensSquared first 5: " + evensSquared);

        // 3. findFirst vs findAny
        Optional<Integer> first = IntStream.rangeClosed(1, 10).findFirst();
        Optional<Integer> anyUnordered = IntStream.rangeClosed(1, 10)
                .unordered()
                .parallel()
                .findAny();
        System.out.println("findFirst ordered: " + first.orElse(-1));
        System.out.println("findAny unordered parallel: " + anyUnordered.orElse(-1));

        // 4. distinct + sorted vocab
        List<String> sentences = List.of("Hello stream", "Stream hello Java", "Java streams are cool");
        List<String> vocab = sentences.stream()
                .flatMap(s -> Arrays.stream(s.toLowerCase().split("\\W+")))
                .filter(t -> !t.isBlank())
                .distinct()
                .sorted()
                .toList();
        System.out.println("vocab: " + vocab);

        // 5. skip/limit vs takeWhile/dropWhile
        List<Integer> data = List.of(1, 2, 3, 0, 4, 5, 0, 6);
        System.out.println("skip2 limit3: " + data.stream().skip(2).limit(3).toList());
        System.out.println("takeWhile>0: " + data.stream().takeWhile(n -> n > 0).toList());
        System.out.println("dropWhile>0: " + data.stream().dropWhile(n -> n > 0).toList());
    }
}

