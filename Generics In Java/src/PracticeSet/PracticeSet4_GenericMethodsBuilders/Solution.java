import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

// Practice Set 4: Generic Methods & Builders
public class Solution {
    // 1) Immutable Config with builder
    public record Config(String name, int retries, boolean enabled) {
        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String name = "default";
            private int retries = 3;
            private boolean enabled = true;
            public Builder name(String n) { this.name = n; return this; }
            public Builder retries(int r) { this.retries = r; return this; }
            public Builder enabled(boolean e) { this.enabled = e; return this; }
            public Config build() { return new Config(name, retries, enabled); }
        }
    }

    // Helper Pair
    public record Pair<A, B>(A first, B second) {}

    // 2) mapKeys and mapValues
    public static <K, V, K2> Map<K2, V> mapKeys(Map<K, V> map, Function<? super K, ? extends K2> fn) {
        Map<K2, V> out = new LinkedHashMap<>();
        map.forEach((k, v) -> out.put(fn.apply(k), v));
        return Map.copyOf(out);
    }
    public static <K, V, V2> Map<K, V2> mapValues(Map<K, V> map, Function<? super V, ? extends V2> fn) {
        Map<K, V2> out = new LinkedHashMap<>();
        map.forEach((k, v) -> out.put(k, fn.apply(v)));
        return Map.copyOf(out);
    }

    // 3) zip lists
    public static <A, B> List<Pair<A, B>> zip(List<A> a, List<B> b) {
        int size = Math.min(a.size(), b.size());
        List<Pair<A, B>> out = new ArrayList<>(size);
        for (int i = 0; i < size; i++) out.add(new Pair<>(a.get(i), b.get(i)));
        return List.copyOf(out);
    }

    // 4) flatten list of lists
    public static <T> List<T> flatten(List<? extends List<? extends T>> lists) {
        List<T> out = new ArrayList<>();
        for (List<? extends T> list : lists) out.addAll(list);
        return List.copyOf(out);
    }

    // 5) Result type
    public static final class Result<T> {
        private final T value;
        private final Exception error;
        private Result(T value, Exception error) { this.value = value; this.error = error; }
        public static <T> Result<T> ok(T value) { return new Result<>(value, null); }
        public static <T> Result<T> fail(Exception e) { return new Result<>(null, e); }
        public <U> Result<U> map(Function<? super T, ? extends U> fn) {
            if (error != null) return fail(error);
            try { return ok(fn.apply(value)); } catch (Exception e) { return fail(e); }
        }
        public <U> Result<U> flatMap(Function<? super T, Result<U>> fn) {
            if (error != null) return fail(error);
            try { return fn.apply(value); } catch (Exception e) { return fail(e); }
        }
        public T orElse(T fallback) { return error == null ? value : fallback; }
        @Override public String toString() { return error == null ? "Ok(" + value + ")" : "Err(" + error.getMessage() + ")"; }
    }

    public static void main(String[] args) {
        Config cfg = Config.builder().name("service").retries(5).enabled(false).build();
        System.out.println("Config -> " + cfg);

        Map<String, Integer> scores = Map.of("alice", 10, "bob", 12);
        System.out.println("mapKeys -> " + mapKeys(scores, String::toUpperCase));
        System.out.println("mapValues -> " + mapValues(scores, v -> v * 10));

        List<Pair<String, Integer>> zipped = zip(List.of("a", "b", "c"), List.of(1, 2));
        System.out.println("zip -> " + zipped);

        List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4, 5));
        System.out.println("flatten -> " + flatten(nested));

        Result<Integer> res = Result.ok(5)
                .map(x -> x * 2)
                .flatMap(x -> x > 5 ? Result.ok(x + 1) : Result.fail(new IllegalArgumentException("too small")));
        System.out.println("Result -> " + res + ", orElse= " + res.orElse(-1));
    }
}

