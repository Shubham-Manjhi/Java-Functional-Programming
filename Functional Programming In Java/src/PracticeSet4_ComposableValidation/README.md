# Practice Set 4: Composable Validation

**Goal:** Build composable, reusable validation pipelines using functional interfaces.

## Tasks
1. Create a `Validator<T>` functional interface returning `ValidationResult` with success/failure and messages.
2. Implement combinators: `and`, `or`, `map`, and `compose` to build larger validators from smaller ones.
3. Build validators for a `User` record: non-empty name, email format, age range, and strong password policy.
4. Implement a higher-order `lift` that converts `Predicate<T>` into `Validator<T>` with a provided message.
5. Demonstrate parallel validation (independent rules) and short-circuiting behavior.

## Requirements
- Favor immutability; results must be immutable objects/records.
- Avoid throwing exceptions for control flow—return rich validation results instead.
- Provide demonstrations in `Solution.java#main` showing both success and failure cases.
- Keep error messages clear and aggregated when multiple rules fail.
