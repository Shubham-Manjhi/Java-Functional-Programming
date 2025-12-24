# Practice Set 2: Advanced Mapping & Filtering

**Goal:** Go beyond basics with map/flatMap/mapMulti, predicate composition, and guard expensive ops.

## Tasks
1. Use `mapMulti` (or `flatMap`) to emit doubled evens from a mixed list.
2. Tokenize sentences to a distinct, sorted vocabulary with normalization.
3. Guard parsing with filters before mapping to integers; keep only positives.
4. Compose predicates (and/or/negate) to filter domain objects cleanly.
5. Show a stateful-but-documented mapping (prefix sums) and explain why to avoid in parallel.

## Requirements
- Prefer pure functions; document any stateful use.
- Demonstrate deterministic output (sort when needed).
- Provide a runnable `main` in `Solution.java` with printed results.

