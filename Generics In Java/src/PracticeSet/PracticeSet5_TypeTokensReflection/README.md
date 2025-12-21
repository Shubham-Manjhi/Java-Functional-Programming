# Practice Set 5: Type Tokens & Reflection with Generics

**Goal:** Explore type erasure workarounds using type tokens and reflective utilities.

## Tasks
1. Implement a `TypeToken<T>` capturing generic type information via anonymous subclassing.
2. Create a `JsonMapper` stub that uses `TypeToken<T>` to (mock) deserialize JSON into `List<T>` while preserving element type.
3. Build a `Registry<T>` that stores instances keyed by `Class<? extends T>` and retrieves by type safely.
4. Implement `newInstance(Class<T>)` utility with checked exception handling wrapped in `Optional`.
5. Demonstrate reflective inspection of generic superclass/type parameters for a class `Box<T>` at runtime.

## Requirements
- Keep implementations lightweight—no external JSON libs; simulate behavior where needed.
- Add concise comments explaining how type tokens avoid erasure in certain APIs.
- Provide demonstrations in `Solution.java#main` showing type-safe retrieval and inspection.
- Use `Optional` for safe handling of reflective instantiation failures.
