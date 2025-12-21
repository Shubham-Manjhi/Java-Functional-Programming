import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;

// Practice Set 1: Generic Collections & Utilities
public class Solution {
    // 1) Generic Pair
    public record Pair<L, R>(L left, R right) {
        public static <L, R> Pair<L, R> of(L l, R r) { return new Pair<>(l, r); }
        public <NL> Pair<NL, R> mapLeft(java.util.function.Function<? super L, ? extends NL> fn) { return new Pair<>(fn.apply(left), right); }
        public <NR> Pair<L, NR> mapRight(java.util.function.Function<? super R, ? extends NR> fn) { return new Pair<>(left, fn.apply(right)); }
    }

    // 2) copyOf with covariance
    public static <T> List<T> copyOf(Collection<? extends T> src) {
        return List.copyOf(src);
    }

    // 3) partition with predicate
    public static <T> Pair<List<T>, List<T>> partition(List<T> list, Predicate<? super T> predicate) {
        List<T> yes = new ArrayList<>();
        List<T> no = new ArrayList<>();
        for (T t : list) {
            (predicate.test(t) ? yes : no).add(t);
        }
        return Pair.of(List.copyOf(yes), List.copyOf(no));
    }

    // 4) mergeMaps with resolver
    public static <K, V> Map<K, V> mergeMaps(Map<K, V> a, Map<K, V> b, BiFunction<? super V, ? super V, ? extends V> resolver) {
        Map<K, V> out = new HashMap<>(a);
        b.forEach((k, v) -> out.merge(k, v, resolver));
        return Map.copyOf(out);
    }

    // 5) variance helpers
    public static double sumNumbers(List<? extends Number> nums) {
        return nums.stream().mapToDouble(Number::doubleValue).sum();
    }

    public static void addIntegers(List<? super Integer> nums, Integer... values) {
        for (Integer v : values) nums.add(v);
    }

    public static void main(String[] args) {
        Pair<String, Integer> p = Pair.of("age", 30);
        System.out.println("Pair: " + p);
        System.out.println("mapLeft: " + p.mapLeft(String::toUpperCase));

        List<Integer> src = List.of(1, 2, 3);
        List<Number> copied = copyOf(src);
        System.out.println("copyOf -> " + copied);

        Pair<List<Integer>, List<Integer>> parts = partition(src, n -> n % 2 == 0);
        System.out.println("partition even/odd -> " + parts);

        Map<String, Integer> m1 = Map.of("a", 1, "b", 2);
        Map<String, Integer> m2 = Map.of("b", 3, "c", 4);
        Map<String, Integer> merged = mergeMaps(m1, m2, Integer::sum);
        System.out.println("merged -> " + merged);

        List<? extends Number> nums = List.of(1, 2.5, 3L);
        System.out.println("sumNumbers -> " + sumNumbers(nums));

        List<Number> sink = new ArrayList<>();
        addIntegers(sink, 10, 20, 30);
        System.out.println("after addIntegers -> " + sink);
    }
}

