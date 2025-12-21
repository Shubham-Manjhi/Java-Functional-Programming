// Ch8_ReturningFunctions.java
// ======================================
// Chapter 8: Returning Functions in Java
// --------------------------------------
// In Java, you can return functions from methods using functional interfaces such as Function, BiFunction, Predicate, Consumer, etc.
// This enables higher-order programming, where methods can generate and return behavior dynamically.
// Below are detailed explanations and examples demonstrating how to return functions in Java.

import java.util.function.*;
import java.util.*;

public class Ch8_ReturningFunctions {
    // Example 1: Returning a Function<T, R> from a Method
    // ---------------------------------------------------
    public static Function<Integer, Integer> getMultiplier(int factor) {
        // Returns a function that multiplies its input by the given factor
        return x -> x * factor;
    }

    public static void functionReturnExample() {
        Function<Integer, Integer> triple = getMultiplier(3);
        System.out.println("[1] Triple of 4: " + triple.apply(4));
    }

    // Example 2: Returning a Predicate<T> from a Method
    // -------------------------------------------------
    public static Predicate<String> getLengthTester(int minLength) {
        // Returns a predicate that tests if a string's length is at least minLength
        return s -> s.length() >= minLength;
    }

    public static void predicateReturnExample() {
        Predicate<String> longEnough = getLengthTester(5);
        System.out.println("[2] 'Hello' long enough? " + longEnough.test("Hello"));
        System.out.println("[2] 'Hi' long enough? " + longEnough.test("Hi"));
    }

    // Example 3: Returning a BiFunction<T, U, R> from a Method
    // --------------------------------------------------------
    public static BiFunction<String, String, String> getConcatenator(String separator) {
        // Returns a bifunction that concatenates two strings with a separator
        return (a, b) -> a + separator + b;
    }

    public static void biFunctionReturnExample() {
        BiFunction<String, String, String> concat = getConcatenator("-");
        System.out.println("[3] Concatenated: " + concat.apply("Java", "Generics"));
    }

    // Example 4: Returning a Custom Functional Interface
    // -------------------------------------------------
    @FunctionalInterface
    interface StringTransformer {
        String transform(String s);
    }

    public static StringTransformer getReverser() {
        // Returns a function that reverses a string
        return s -> new StringBuilder(s).reverse().toString();
    }

    public static void customInterfaceReturnExample() {
        StringTransformer reverser = getReverser();
        System.out.println("[4] Reversed: " + reverser.transform("Functional"));
    }

    // Example 5: Returning a Consumer<T> from a Method
    // ------------------------------------------------
    public static Consumer<String> getPrinter(String prefix) {
        // Returns a consumer that prints a string with a prefix
        return s -> System.out.println(prefix + s);
    }

    public static void consumerReturnExample() {
        Consumer<String> printer = getPrinter("[5] Message: ");
        printer.accept("Hello from Consumer!");
    }

    // Example 6: Returning a Supplier<T> from a Method
    // ------------------------------------------------
    public static Supplier<Double> getRandomSupplier(double min, double max) {
        // Returns a supplier that generates a random double between min and max
        return () -> min + Math.random() * (max - min);
    }

    public static void supplierReturnExample() {
        Supplier<Double> randomSupplier = getRandomSupplier(10, 20);
        System.out.println("[6] Random number: " + randomSupplier.get());
    }

    // Example 7: Returning a Function that Returns Another Function
    // ------------------------------------------------------------
    // This demonstrates higher-order functions: a method returns a function, which itself returns another function.
    // In Java, this can be represented as Supplier<Supplier<String>> or Function<Void, Supplier<String>>.
    public static Supplier<Supplier<String>> getNestedSupplier() {
        // Returns a Supplier that returns another Supplier, which returns a String
        return () -> () -> "[7] Hello from nested Supplier!";
    }

    public static void nestedFunctionReturnExample() {
        Supplier<Supplier<String>> outer = getNestedSupplier();
        Supplier<String> inner = outer.get();
        System.out.println(inner.get());
    }

    public static void main(String[] args) {
        functionReturnExample();
        predicateReturnExample();
        biFunctionReturnExample();
        customInterfaceReturnExample();
        consumerReturnExample();
        supplierReturnExample();
        nestedFunctionReturnExample();
    }
}
