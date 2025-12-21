# Practice Set 1: Higher-Order Pipelines

**Goal:** Design expressive stream pipelines using higher-order functions to transform and summarize data.

## Tasks
1. Build a reusable higher-order method `withLogging(Stream<T>, Function<Stream<T>, R>)` that logs pipeline stages and final result.
2. Create a pipeline that:
   - Filters odd numbers.
   - Maps to their squares.
   - Batches into chunks of size 3.
   - Reduces each batch to a sum.
3. Add an operation that measures and returns the latency of any `Supplier<R>` as a tuple `(result, durationMillis)` using a custom record/class.
4. Compose a pipeline that takes a list of sentences and produces a frequency map of lowercase words, skipping stop-words.
5. Extend the pipeline to support parallel execution, ensuring thread-safe aggregation.

## Requirements
- Use Java Streams and functional interfaces (`Function`, `Supplier`, `UnaryOperator`, `Collector`).
- Favor immutability; avoid shared mutable state. If needed, use thread-safe structures.
- Include unit-like demonstrations in `Solution.java#main` showing the outputs.
- Keep logging minimal but clear (stage start/end and result).
