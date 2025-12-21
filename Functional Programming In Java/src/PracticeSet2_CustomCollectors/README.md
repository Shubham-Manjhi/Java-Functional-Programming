# Practice Set 2: Custom Collectors

**Goal:** Build advanced, reusable collectors for complex stream reductions.

## Tasks
1. Implement a custom collector `toImmutableList()` using `Collector.of` that returns an unmodifiable list.
2. Create a `toStats()` collector that computes count, sum, min, max, average for `int` streams without using `IntSummaryStatistics`.
3. Build a collector `groupingAndTransforming` that groups elements by key and applies a downstream mapping before aggregation.
4. Implement `topN` collector that keeps only the top N elements by a comparator (heap-based, stable for ties).
5. Demonstrate combining collectors via `teeing` to produce `(min, max)` in a custom record.

## Requirements
- Use `Collector` building blocks: supplier, accumulator, combiner, finisher, characteristics.
- Ensure thread-safety where needed for parallel streams.
- Provide thorough demonstrations in `Solution.java#main` with diverse inputs.
- Favor immutability in final results.
