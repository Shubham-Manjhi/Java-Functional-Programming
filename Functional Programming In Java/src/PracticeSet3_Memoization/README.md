# Practice Set 3: Memoization and Caching

**Goal:** Implement memoization patterns to optimize expensive functional computations.

## Tasks
1. Create a generic memoizer `memoize(Function<T,R>)` using `ConcurrentHashMap` that is thread-safe.
2. Implement a `memoizeWithTTL(Function<T,R>, Duration ttl)` that expires entries after TTL using timestamped values.
3. Build a recursive Fibonacci with memoization using `Function<Integer, Long>` without global mutable state.
4. Add a memoized parser that counts parsed tokens; ensure idempotency across repeated calls.
5. Demonstrate cache effectiveness by timing memoized vs non-memoized functions.

## Requirements
- Favor immutability of returned results; cache storage can be mutable but thread-safe.
- Use `Supplier`/`Function` where appropriate.
- Include demonstrations in `Solution.java#main` showing speedup and cache hits.
- Avoid external caching libraries.
