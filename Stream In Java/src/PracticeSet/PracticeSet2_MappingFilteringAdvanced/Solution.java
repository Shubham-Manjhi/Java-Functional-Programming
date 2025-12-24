import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;

public class Solution {
    public static void main(String[] args) {
        // 1. mapMulti: doubled evens
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> doubledEvens = nums.stream()
                .<Integer>mapMulti((n, sink) -> { if (n % 2 == 0) sink.accept(n * 2); })
                .toList();
        System.out.println("doubled evens: " + doubledEvens);

        // 2. vocab distinct sorted
        List<String> sentences = List.of("Streams map data", "map and flatMap", "Java streams rock");
        List<String> vocab = sentences.stream()
                .flatMap(s -> Arrays.stream(s.toLowerCase().split("\\W+")))
                .filter(t -> !t.isBlank())
                .distinct()
                .sorted()
                .toList();
        System.out.println("vocab: " + vocab);

        // 3. guard parsing
        List<String> raw = List.of("10", "abc", "25", "-5", "x");
        List<Integer> positives = raw.stream()
                .filter(s -> s.matches("-?\\d+"))
                .map(Integer::parseInt)
                .filter(n -> n > 0)
                .toList();
        System.out.println("positives: " + positives);

        // 4. predicate composition on domain
        record Task(String name, boolean done, int priority) {}
        List<Task> tasks = List.of(
                new Task("Write", false, 2),
                new Task("Review", true, 1),
                new Task("Refactor", false, 3),
                new Task("Deploy", true, 1)
        );
        Predicate<Task> open = t -> !t.done();
        Predicate<Task> high = t -> t.priority() <= 2;
        List<Task> selected = tasks.stream().filter(open.and(high)).toList();
        System.out.println("selected tasks: " + selected);

        // 5. stateful prefix sums (illustrative only)
        List<Integer> data = List.of(1, 2, 3, 4);
        List<Integer> prefix = new ArrayList<>();
        data.stream().reduce(0, (acc, n) -> { int next = acc + n; prefix.add(next); return next; });
        System.out.println("prefix sums (avoid in parallel): " + prefix);
    }
}

