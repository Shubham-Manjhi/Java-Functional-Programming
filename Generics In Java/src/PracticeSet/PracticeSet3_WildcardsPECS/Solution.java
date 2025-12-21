import java.util.*;
import java.util.function.Predicate;

// Practice Set 3: Wildcards & PECS
public class Solution {
    // 1) sum producer list: safe to read, not to write
    public static double sum(List<? extends Number> nums) {
        return nums.stream().mapToDouble(Number::doubleValue).sum();
    }

    // 2) addDefaults consumer list: safe to write integers
    public static void addDefaults(List<? super Integer> nums) {
        nums.add(1);
        nums.add(2);
        nums.add(3);
    }

    // 3) copyIf with PECS
    public static <T> void copyIf(List<? extends T> src, List<? super T> dst, Predicate<? super T> predicate) {
        for (T t : src) {
            if (predicate.test(t)) dst.add(t);
        }
    }

    // 4) wildcard capture swap
    public static void swap(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }
    private static <T> void swapHelper(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // 5) maxBy with comparator
    public static <T> Optional<T> maxBy(List<? extends T> list, Comparator<? super T> comparator) {
        if (list.isEmpty()) return Optional.empty();
        T best = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            T cand = list.get(i);
            if (comparator.compare(cand, best) > 0) best = cand;
        }
        return Optional.of(best);
    }

    public static void main(String[] args) {
        List<Number> nums = new ArrayList<>(List.of(1, 2.5, 3L));
        System.out.println("sum -> " + sum(nums));

        addDefaults(nums);
        System.out.println("after addDefaults -> " + nums);

        List<Integer> src = List.of(5, 6, 7, 8);
        List<Number> dst = new ArrayList<>();
        copyIf(src, dst, n -> n % 2 == 0);
        System.out.println("copyIf evens -> " + dst);

        swap(dst, 0, dst.size() - 1);
        System.out.println("after swap -> " + dst);

        System.out.println("maxBy -> " + maxBy(dst, (a, b) -> Double.compare(a.doubleValue(), b.doubleValue())));
    }
}

