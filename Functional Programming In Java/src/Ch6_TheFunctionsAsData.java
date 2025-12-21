// Ch6_TheFunctionsAsData.java
// ======================================
// Chapter 6: Functions as Data in Java
// --------------------------------------
// In Java, functions can be treated as data by using functional interfaces such as Function, BiFunction, Consumer, Supplier, etc.
// This allows you to pass functions as arguments, return them from methods, and store them in variables or collections.
// This approach enables higher-order programming and flexible code design.
//
// Below are detailed explanations and examples demonstrating how functions can be used as data in Java.

import java.util.*;
import java.util.function.*;

public class Ch6_TheFunctionsAsData {
    // Example 1: Storing Functions in Variables
    // -----------------------------------------
    // You can assign a lambda expression to a variable of a functional interface type.
    public static void functionAsVariableExample() {
        Function<Integer, Integer> square = x -> x * x;
        System.out.println("[1] Square of 5: " + square.apply(5));
    }

    // Example 2: Passing Functions as Arguments
    // -----------------------------------------
    // Functions can be passed as parameters to other methods.
    public static void processNumber(int number, Function<Integer, Integer> processor) {
        System.out.println("[2] Processed number: " + processor.apply(number));
    }

    public static void passingFunctionAsArgumentExample() {
        processNumber(10, x -> x + 100); // Adds 100 to the number
    }

    // Example 3: Returning Functions from Methods
    // -------------------------------------------
    // Methods can return functions, enabling dynamic behavior.
    public static Function<String, String> getFormatter(boolean upper) {
        if (upper) {
            return s -> s.toUpperCase();
        } else {
            return s -> s.toLowerCase();
        }
    }

    public static void returningFunctionFromMethodExample() {
        Function<String, String> formatter = getFormatter(true);
        System.out.println("[3] Formatted: " + formatter.apply("Hello World"));
    }

    // Example 4: Storing Functions in Collections
    // -------------------------------------------
    // Functions can be stored in lists or maps for dynamic invocation.
    public static void functionsInCollectionExample() {
        List<Function<Integer, Integer>> operations = new ArrayList<>();
        operations.add(x -> x + 1);
        operations.add(x -> x * 2);
        operations.add(x -> x * x);

        int value = 3;
        int i = 1;
        for (Function<Integer, Integer> op : operations) {
            System.out.println("[4." + i + "] Result: " + op.apply(value));
            i++;
        }
    }

    // Example 5: Using Custom Functional Interface
    // -------------------------------------------
    // You can define your own functional interface to represent a function.
    @FunctionalInterface
    interface StringTransformer {
        String transform(String input);
    }

    public static void customFunctionalInterfaceExample() {
        StringTransformer reverse = s -> new StringBuilder(s).reverse().toString();
        System.out.println("[5] Reversed: " + reverse.transform("Java"));
    }

    // Example 6: Using BiFunction as Data
    // -----------------------------------
    // BiFunction can be used to represent operations on two inputs.
    public static void biFunctionAsDataExample() {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("[6] Sum: " + add.apply(7, 8));
    }

    // Example 7: Higher-Order Function Example
    // ----------------------------------------
    // A method that takes a function and returns a new function.
    public static Function<Integer, Integer> makeMultiplier(int factor) {
        return x -> x * factor;
    }

    public static void higherOrderFunctionExample() {
        Function<Integer, Integer> triple = makeMultiplier(3);
        System.out.println("[7] Triple of 4: " + triple.apply(4));
    }

    // Example 8: Using Consumer and Supplier
    // --------------------------------------
    // Consumer represents an operation that takes a single input and returns no result.
    // Supplier represents a function that supplies a result of a given type.
    public static void consumerAndSupplierExample() {
        Consumer<String> printer = s -> System.out.println("[8] Printing: " + s);
        Supplier<Double> randomSupplier = () -> Math.random();
        printer.accept("Hello from Consumer!");
        System.out.println("[8] Random number from Supplier: " + randomSupplier.get());
    }

    public static void main(String[] args) {
        functionAsVariableExample();
        passingFunctionAsArgumentExample();
        returningFunctionFromMethodExample();
        functionsInCollectionExample();
        customFunctionalInterfaceExample();
        biFunctionAsDataExample();
        higherOrderFunctionExample();
        consumerAndSupplierExample();
    }
}

