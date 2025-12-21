import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

// Practice Set 3: Memoization and Caching
public class Solution {
    // Generic memoizer
    public static <T, R> Function<T, R> memoize(Function<T, R> fn) {
        Map<T, R> cache = new ConcurrentHashMap<>();
        return t -> cache.computeIfAbsent(t, fn);
    }

    // Memoizer with TTL
    private record Entry<R>(R value, long expiresAtMillis) {}

    public static <T, R> Function<T, R> memoizeWithTTL(Function<T, R> fn, Duration ttl) {
        Map<T, Entry<R>> cache = new ConcurrentHashMap<>();
        return t -> {
            long now = System.currentTimeMillis();
            Entry<R> entry = cache.get(t);
            if (entry != null && entry.expiresAtMillis > now) {
                return entry.value();
            }
            R value = fn.apply(t);
            cache.put(t, new Entry<>(value, now + ttl.toMillis()));
            return value;
        };
    }

    // Recursive Fibonacci with memoization
    public static Function<Integer, Long> memoizedFib() {
        Map<Integer, Long> cache = new ConcurrentHashMap<>();
        Function<Integer, Long>[] fibRef = new Function[1];
        fibRef[0] = n -> cache.computeIfAbsent(n, k -> {
            if (k <= 1) return (long) k;
            return fibRef[0].apply(k - 1) + fibRef[0].apply(k - 2);
        });
        return fibRef[0];
    }

    // Memoized parser that counts tokens
    public static Function<String, Integer> memoizedTokenCount() {
        Map<String, Integer> cache = new ConcurrentHashMap<>();
        return s -> cache.computeIfAbsent(s, str -> str.split("\\s+").length);
    }

    private static <T, R> void time(String label, Function<T, R> fn, T input) {
        Instant start = Instant.now();
        R result = fn.apply(input);
        long ms = Duration.between(start, Instant.now()).toMillis();
        System.out.println(label + " -> result=" + result + ", ms=" + ms);
    }

    public static void main(String[] args) {
        // Demo 1: basic memoize
        Function<Integer, Integer> slowSquare = n -> { try { Thread.sleep(50); } catch (InterruptedException ignored) {} return n * n; };
        Function<Integer, Integer> memoSquare = memoize(slowSquare);
        time("First slow square 10", memoSquare, 10);
        time("Cached square 10", memoSquare, 10);

        // Demo 2: memoizeWithTTL
        Function<Integer, Integer> memoTtl = memoizeWithTTL(slowSquare, Duration.ofMillis(100));
        time("TTL first", memoTtl, 5);
        time("TTL cached", memoTtl, 5);
        try { Thread.sleep(120); } catch (InterruptedException ignored) {}
        time("TTL expired", memoTtl, 5);

        // Demo 3: memoized Fibonacci
        Function<Integer, Long> fib = memoizedFib();
        time("Fib 35", fib, 35);
        time("Fib 35 cached", fib, 35);

        // Demo 4: memoized token count
        Function<String, Integer> tokenCount = memoizedTokenCount();
        String text = "Functional programming favors pure functions and immutability";
        time("Token count", tokenCount, text);
        time("Token count cached", tokenCount, text);
    }
}

