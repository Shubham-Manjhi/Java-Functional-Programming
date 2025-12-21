import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// Practice Set 5: Higher-Order Retry and Resilience
public class Solution {
    public record RetryResult<T>(boolean success, T value, Exception exception, int attempts) {}
    public record CircuitState(boolean open, int failures) {}

    public static <T> RetryResult<T> retry(Supplier<T> action,
                                          int maxAttempts,
                                          Function<Integer, Duration> backoff,
                                          Predicate<Exception> retryOn,
                                          Random jitter) {
        Exception lastEx = null;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                T value = action.get();
                return new RetryResult<>(true, value, null, attempt);
            } catch (Exception ex) {
                lastEx = ex;
                if (!retryOn.test(ex)) return new RetryResult<>(false, null, ex, attempt);
                long delay = backoff.apply(attempt).toMillis();
                long jitterMs = jitter.nextLong(0, Math.max(1, delay / 2));
                long sleepFor = delay + jitterMs;
                System.out.println("Attempt " + attempt + " failed: " + ex.getMessage() + ", sleep " + sleepFor + "ms");
                try { Thread.sleep(sleepFor); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); break; }
            }
        }
        return new RetryResult<>(false, null, lastEx, maxAttempts);
    }

    // Simple circuit breaker
    public static class CircuitBreaker {
        private final int failureThreshold;
        private final Duration coolDown;
        private long openedAt = -1;
        private final AtomicInteger recentFailures = new AtomicInteger();

        public CircuitBreaker(int failureThreshold, Duration coolDown) {
            this.failureThreshold = failureThreshold;
            this.coolDown = coolDown;
        }

        public synchronized <T> T execute(Supplier<T> action) {
            long now = System.currentTimeMillis();
            if (isOpen(now)) {
                throw new IllegalStateException("Circuit open");
            }
            try {
                T result = action.get();
                recentFailures.set(0);
                return result;
            } catch (Exception ex) {
                int fails = recentFailures.incrementAndGet();
                if (fails >= failureThreshold) {
                    openedAt = now;
                    System.out.println("Circuit opened after " + fails + " failures");
                }
                throw ex;
            }
        }

        private boolean isOpen(long now) {
            if (openedAt < 0) return false;
            long elapsed = now - openedAt;
            if (elapsed >= coolDown.toMillis()) {
                // Half-open: reset and allow a trial
                openedAt = -1;
                recentFailures.set(0);
                return false;
            }
            return true;
        }
    }

    // Unreliable service mock
    public static Supplier<String> flakyService(int failTimes, String successValue) {
        AtomicInteger counter = new AtomicInteger();
        return () -> {
            int c = counter.incrementAndGet();
            if (c <= failTimes) throw new RuntimeException("flaky failure #" + c);
            return successValue + " (call " + c + ")";
        };
    }

    public static void main(String[] args) {
        Random jitter = new Random(42);
        Function<Integer, Duration> expBackoff = attempt -> Duration.ofMillis((long) (100 * Math.pow(2, attempt - 1)));
        Predicate<Exception> retryOnRuntime = ex -> ex instanceof RuntimeException;

        // Demo 1: retry success after failures
        Supplier<String> flaky = flakyService(2, "OK");
        RetryResult<String> res = retry(flaky, 5, expBackoff, retryOnRuntime, jitter);
        System.out.println("Retry result: " + res);

        // Demo 2: circuit breaker opening
        CircuitBreaker cb = new CircuitBreaker(2, Duration.ofMillis(300));
        Supplier<String> alwaysFail = () -> { throw new RuntimeException("boom"); };
        for (int i = 1; i <= 3; i++) {
            try {
                cb.execute(alwaysFail);
            } catch (Exception ex) {
                System.out.println("CB call " + i + " -> " + ex.getMessage());
            }
        }

        // Wait for cool-down
        try { Thread.sleep(350); } catch (InterruptedException ignored) {}
        try {
            System.out.println("CB after cool-down -> " + cb.execute(() -> "recovered"));
        } catch (Exception ex) {
            System.out.println("CB still open -> " + ex.getMessage());
        }

        // Demo 3: compose retry with circuit breaker on a flaky service
        CircuitBreaker cb2 = new CircuitBreaker(3, Duration.ofMillis(200));
        Supplier<String> flaky2 = flakyService(4, "Eventually OK");
        Supplier<String> guarded = () -> cb2.execute(() -> flaky2.get());
        RetryResult<String> res2 = retry(guarded, 6, expBackoff, retryOnRuntime, jitter);
        System.out.println("Retry+CB result: " + res2);
    }
}

