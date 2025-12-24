# Practice Set 5: Custom Collectors & Grouping

**Goal:** Build advanced collectors, grouping strategies, and post-processing.

## Tasks
1. Implement a custom collector via `Collector.of` to compute average (sum+count) from integers.
2. Build a `topN` collector that keeps the top N elements by comparator (heap-based) and returns an immutable list.
3. Use `groupingBy` with `mapping` to group people by city and collect uppercase names.
4. Use `groupingBy` with a downstream `collectingAndThen` to produce unmodifiable sets.
5. Combine collectors with `teeing` (min/max) into a record.

## Requirements
- Keep accumulators associative and side-effect-free.
- Return immutable results where practical.
- Provide runnable `Solution.java` with printed outputs.
- Document comparator/merge rules where relevant.

