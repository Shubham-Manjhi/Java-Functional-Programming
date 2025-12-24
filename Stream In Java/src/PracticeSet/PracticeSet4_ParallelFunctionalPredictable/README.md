# Practice Set 4: Parallel Streams — Functional & Predictable

**Goal:** Practice safe, deterministic parallel stream patterns with functional purity and predictable outputs.

## Tasks
1. Parallel sum over a large range with correct identity/combiner.
2. Parallel reduce computing sum of squares with associativity preserved.
3. Parallel grouping to a concurrent map (frequency) safely.
4. Use `unordered()` to relax ordering and observe differences vs ordered.
5. Build a predictable parallel pipeline: pure map/filter, then `sorted` and immutable collect.
6. Show a "when not to use parallel" case with a blocking operation.

## Requirements
- No shared mutable state inside stream operations.
- Ensure deterministic final outputs by sorting when order matters.
- Use thread-safe collectors (`toConcurrentMap`) where applicable.
- Provide runnable `Solution.java` with printed outputs.

