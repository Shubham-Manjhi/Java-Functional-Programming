# Practice Set 5: Higher-Order Retry and Resilience

**Goal:** Implement higher-order retry logic and resilience patterns using functional programming.

## Tasks
1. Create a `retry` higher-order function that takes a `Supplier<T>` action, max attempts, and backoff strategy (`Function<Integer, Duration>` for attempt -> delay).
2. Support retries on specified exception types only; propagate others immediately.
3. Add jitter to backoff and demonstrate with exponential backoff.
4. Provide a `circuitBreaker` style wrapper that opens after N consecutive failures and half-opens after a cool-down.
5. Compose retry with circuit breaker to protect an unreliable service mock.

## Requirements
- Use functional interfaces (`Supplier`, `Function`, `Predicate`).
- Avoid busy-waiting; use `Thread.sleep` for backoff in demos.
- Keep state encapsulated; expose immutable results/records describing outcomes.
- Demonstrate success, failure, and circuit open scenarios in `Solution.java#main`.
- Make logs concise, showing attempts and outcomes.
