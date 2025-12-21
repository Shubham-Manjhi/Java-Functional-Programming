# Practice Set 4: Generic Methods & Builders

**Goal:** Practice writing generic methods and fluent builders with proper type inference.

## Tasks
1. Implement a generic `builder` for an immutable `Config` record that uses a self-referential generic builder for fluent chaining.
2. Create a generic `mapKeys` and `mapValues` for `Map<K, V>` returning new maps with transformed keys/values.
3. Write a `zip` method that zips two `List<A>` and `List<B>` into `List<Pair<A,B>>`, truncating to the shorter size.
4. Implement a `flatten` method for `List<? extends List<T>>` to `List<T>`.
5. Build a fluent `Result<T>` type with `map`, `flatMap`, and `orElse` demonstrating monadic style chaining.

## Requirements
- Ensure builder is type-safe; avoid unchecked casts.
- Use records where helpful for brevity and immutability.
- Demonstrate each utility in `Solution.java#main`.
- Keep APIs fluent and readable.
