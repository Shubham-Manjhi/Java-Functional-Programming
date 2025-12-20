// Chapter 1: Introduction to Functional Programming in Java
// ==========================================================
// Functional programming is a programming paradigm that treats computation as the evaluation of mathematical functions
// and avoids changing state and mutable data. It emphasizes immutability, declarative programming, and higher-order functions.

public class Ch1_IntroductionToFunctionalProgramming {

    // Why Use Functional Programming?
    // --------------------------------
    // 1. **Improved Readability**: Functional code is often more concise and easier to understand.
    // 2. **Easier Debugging**: Immutability and pure functions reduce side effects, making debugging simpler.
    // 3. **Concurrency**: Immutability makes it easier to write concurrent programs without worrying about race conditions.
    // 4. **Reusability**: Functions can be reused across different parts of the program.
    // 5. **Testability**: Pure functions are easier to test as they depend only on their inputs.

    // What is Declarative Programming?
    // ---------------------------------
    // Declarative programming is a style of programming where you specify *what* the program should do,
    // rather than *how* to do it. Functional programming is inherently declarative.

    // Example 1: Imperative vs Declarative Programming
    public static void imperativeExample() {
        int sum = 0;
        for (int i = 1; i <= 5; i++) {
            sum += i;
        }
        System.out.println("Imperative Sum: " + sum);
    }

    public static void declarativeExample() {
        int sum = java.util.stream.IntStream.rangeClosed(1, 5).sum();
        System.out.println("Declarative Sum: " + sum);
    }

    // Immutability in Functional Programming
    // ---------------------------------------
    // Immutability means that once a data structure is created, it cannot be changed.
    // Instead of modifying existing data, new data structures are created.

    // Example 2: Mutable vs Immutable Data
    public static void mutableExample() {
        java.util.List<String> list = new java.util.ArrayList<>();
        list.add("A");
        list.add("B");
        System.out.println("Mutable List: " + list);
        list.add("C"); // Modifies the original list
        System.out.println("Modified List: " + list);
    }

    public static void immutableExample() {
        java.util.List<String> list = java.util.List.of("A", "B");
        System.out.println("Immutable List: " + list);
        // list.add("C"); // This will throw UnsupportedOperationException
    }

    // Example 3: Pure Functions
    // A pure function is a function where the output depends only on the input, and it has no side effects.
    public static int pureFunction(int x, int y) {
        return x + y; // Depends only on inputs
    }

    public static void demonstratePureFunction() {
        System.out.println("Pure Function Result: " + pureFunction(3, 5));
    }

    // Example 4: Higher-Order Functions
    // Higher-order functions are functions that take other functions as arguments or return functions as results.
    public static void higherOrderFunctionExample() {
        java.util.function.Function<Integer, Integer> square = x -> x * x;
        java.util.List<Integer> numbers = java.util.List.of(1, 2, 3, 4);
        java.util.List<Integer> squaredNumbers = numbers.stream().map(square).toList();
        System.out.println("Squared Numbers: " + squaredNumbers);
    }

    // Example 5: Lambda Expressions
    // Lambda expressions are a concise way to represent anonymous functions.
    public static void lambdaExample() {
        java.util.List<String> names = java.util.List.of("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println("Hello, " + name + "!"));
    }

    public static void main(String[] args) {
        // Demonstrating Imperative vs Declarative Programming
        imperativeExample();
        declarativeExample();

        // Demonstrating Mutable vs Immutable Data
        mutableExample();
        immutableExample();

        // Demonstrating Pure Functions
        demonstratePureFunction();

        // Demonstrating Higher-Order Functions
        higherOrderFunctionExample();

        // Demonstrating Lambda Expressions
        lambdaExample();
    }
}
