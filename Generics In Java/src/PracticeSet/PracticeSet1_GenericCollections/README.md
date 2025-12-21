# Practice Set 1: Generic Collections & Utilities

**Goal:** Deepen understanding of generic collections, variance, and utility patterns.

## Tasks
1. Implement a generic `Pair<L, R>` with static factory methods, equals/hashCode/toString, and a `mapLeft`/`mapRight`.
2. Write a `copyOf` utility that takes `Collection<? extends T>` and returns an immutable `List<T>`.
3. Build a generic `partition` method that splits a `List<T>` into two lists based on a `Predicate<? super T>` and returns a `Pair<List<T>, List<T>>`.
4. Create a type-safe `mergeMaps` that combines two `Map<K, V>` with a `BiFunction<V, V, V>` conflict resolver.
5. Demonstrate covariance/contravariance pitfalls by writing helper methods that accept `List<? extends Number>` and `List<? super Integer>` and show safe reads/writes.

## Requirements
- Favor immutability for returned collections.
- Include demonstrations in `Solution.java#main` with sample inputs and outputs.
- Keep code concise and type-safe; avoid raw types.
- Add brief comments where it aids understanding of variance rules.
