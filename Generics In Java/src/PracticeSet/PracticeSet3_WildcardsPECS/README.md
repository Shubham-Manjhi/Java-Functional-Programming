# Practice Set 3: Wildcards & PECS

**Goal:** Master Producer Extends, Consumer Super (PECS) and wildcard capture techniques.

## Tasks
1. Implement `sum(List<? extends Number>)` and explain why writing to that list is unsafe.
2. Create `addDefaults(List<? super Integer>)` that safely writes integers to a consumer list.
3. Build a `copyIf` method that copies from `List<? extends T>` to `List<? super T>` when a `Predicate<? super T>` holds.
4. Demonstrate wildcard capture with a helper method to swap elements in `List<?>`.
5. Implement `maxBy` that accepts `List<? extends T>` and a `Comparator<? super T>` and returns `Optional<T>`.

## Requirements
- Add concise comments on PECS rules where helpful.
- Provide demonstrations in `Solution.java#main`.
- Avoid raw types; showcase safe read/write patterns.
- Use Optional for potentially empty results.
