# Practice Set 2: Bounded Types & Constraints

**Goal:** Master upper and lower bounds, ensuring type safety with constraints.

## Tasks
1. Implement `max` for `List<? extends Comparable<? super T>>` that safely returns the maximum element.
2. Create a generic `clamp` method that constrains a value between min/max using a comparator.
3. Design a `NumberBox<T extends Number>` with methods `add(T)` and `sumAsDouble()`; show usage with subclasses of `Number`.
4. Implement `copy` between `List<? extends T>` source and `List<? super T>` target (PECS) with tests.
5. Create a generic `Sorter<T>` interface with a bounded method reference usage demo (e.g., `Comparator<T>`), highlighting inference.

## Requirements
- Avoid raw types; use bounded wildcards properly.
- Demonstrate PECS (Producer Extends, Consumer Super) in examples.
- Show type inference benefits with method references in `Solution.java#main`.
- Keep examples concise but illustrative of bounds usage.
