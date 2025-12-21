// Chapter 2: Core Concepts of Functional Programming in Java
// ===========================================================
// Functional programming is built on several core concepts that make it distinct from other paradigms.
// This chapter focuses on three key concepts: Immutability, Functional Purity, and First-Class Functions.

public class Ch2_CoreConceptsOfFunctionalProgramming {

    // 1. Immutability
    // ----------------
    // Immutability means that once a data structure is created, it cannot be changed.
    // Instead of modifying existing data, new data structures are created.

    // Example 1: Immutable Data Structures
    public static void immutableDataExample() {
        java.util.List<String> immutableList = java.util.List.of("A", "B", "C");
        System.out.println("Immutable List: " + immutableList);
        // immutableList.add("D"); // This will throw UnsupportedOperationException
    }

    // Example 2: Copy-on-Write for Immutability
    public static void copyOnWriteExample() {
        java.util.List<String> originalList = java.util.List.of("X", "Y", "Z");
        java.util.List<String> newList = new java.util.ArrayList<>(originalList);
        newList.add("W");
        System.out.println("Original List: " + originalList);
        System.out.println("New List: " + newList);
    }

    // 2. Functional Purity
    // ---------------------
    // A pure function is a function where the output depends only on the input, and it has no side effects.

    // Example 3: Pure Function
    public static int add(int a, int b) {
        return a + b; // Depends only on inputs
    }

    public static void demonstratePureFunction() {
        System.out.println("Pure Function Result: " + add(5, 10));
    }

    // Example 4: Impure Function
    public static int impureFunction(int a) {
        System.out.println("Side effect: Printing to console");
        return a * 2;
    }

    public static void demonstrateImpureFunction() {
        System.out.println("Impure Function Result: " + impureFunction(5));
    }

    // 3. First-Class Functions
    // -------------------------
    // First-class functions are functions that can be treated as values. They can be passed as arguments,
    // returned from other functions, and assigned to variables.

    // Example 5: Passing Functions as Arguments
    public static void applyFunction(java.util.function.Function<Integer, Integer> func, int value) {
        System.out.println("Result of applying function: " + func.apply(value));
    }

    public static void demonstratePassingFunctions() {
        java.util.function.Function<Integer, Integer> square = x -> x * x;
        applyFunction(square, 4);
    }

    // Example 6: Returning Functions
    public static java.util.function.Function<Integer, Integer> multiplier(int factor) {
        return x -> x * factor;
    }

    public static void demonstrateReturningFunctions() {
        java.util.function.Function<Integer, Integer> timesThree = multiplier(3);
        System.out.println("Result of timesThree: " + timesThree.apply(5));
    }

    // Example 7: Storing Functions in Variables
    public static void demonstrateStoringFunctions() {
        java.util.function.Function<String, String> greet = name -> "Hello, " + name + "!";
        System.out.println(greet.apply("Alice"));
    }

    public static void main(String[] args) {
        // Demonstrating Immutability
        immutableDataExample();
        copyOnWriteExample();

        // Demonstrating Functional Purity
        demonstratePureFunction();
        demonstrateImpureFunction();

        // Demonstrating First-Class Functions
        demonstratePassingFunctions();
        demonstrateReturningFunctions();
        demonstrateStoringFunctions();
    }
}
