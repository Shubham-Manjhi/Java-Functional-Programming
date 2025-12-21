import java.util.*;
import java.util.function.Function;

// Practice Set 2: Bounded Types & Constraints
public class Solution {
    // 1) max with bounded comparable
    public static <T> Optional<T> max(List<? extends Comparable<? super T>> list) {
        if (list.isEmpty()) return Optional.empty();
        T best = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            T candidate = list.get(i);
            if (((Comparable<? super T>) candidate).compareTo(best) > 0) best = candidate;
        }
        return Optional.of(best);
    }

    // 2) clamp using comparator
    public static <T> T clamp(T value, T min, T max, Comparator<? super T> comparator) {
        if (comparator.compare(value, min) < 0) return min;
        if (comparator.compare(value, max) > 0) return max;
        return value;
    }

    // 3) NumberBox
    public static class NumberBox<T extends Number> {
        private final List<T> values = new ArrayList<>();
        public void add(T value) { values.add(value); }
        public double sumAsDouble() { return values.stream().mapToDouble(Number::doubleValue).sum(); }
        public List<T> values() { return List.copyOf(values); }
    }

    // 4) copy with PECS
    public static <T> void copy(List<? extends T> src, List<? super T> dst) {
        dst.addAll(src);
    }

    // 5) Sorter interface with bounded method reference
    @FunctionalInterface
    public interface Sorter<T> {
        List<T> sort(List<T> items, Comparator<? super T> comparator);
    }

    public static void main(String[] args) {
        List<Integer> ints = List.of(1, 5, 3, 9, 2);
        System.out.println("max ints -> " + max(ints));

        System.out.println("clamp 15 into [0,10] -> " + clamp(15, 0, 10, Integer::compare));

        NumberBox<Double> box = new NumberBox<>();
        box.add(1.5); box.add(2.5);
        System.out.println("NumberBox sum -> " + box.sumAsDouble() + ", values=" + box.values());

        List<Integer> target = new ArrayList<>();
        copy(ints, target);
        System.out.println("copy -> " + target);

        // Sorter demo
        Sorter<Integer> sorter = (items, comp) -> {
            List<Integer> copy = new ArrayList<>(items);
            copy.sort(comp);
            return copy;
        };
        List<Integer> sorted = sorter.sort(ints, Integer::compareTo);
        System.out.println("sorted -> " + sorted);
    }
}

