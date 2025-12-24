# Practice Set 3: Reduction & Collect

**Goal:** Practice reduce overloads, identity/combiner rules, and common collectors (toMap, grouping, partitioning, collectingAndThen).

## Tasks
1. Use reduce with identity/accumulator/combiner to sum squares in parallel safely.
2. Compute max with Optional-returning reduce on a possibly empty stream.
3. Use toMap with a merge function to build frequency counts (handle duplicates).
4. Group people by city and collect names, plus count per city.
5. Partition scores into pass/fail with averaging downstream.
6. Use collectingAndThen to return an immutable, sorted distinct list.

## Requirements
- Keep accumulators associative and side-effect-free.
- Prefer immutable outputs (List.copyOf, unmodifiable collectors).
- Provide runnable `Solution.java` with printed outputs.
- Note: reduce-to-collection is educational; collect is preferred for collections.

