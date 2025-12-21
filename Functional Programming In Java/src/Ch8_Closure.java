// Ch8_Closure.java
// ======================================
// Chapter 8: Closures in Java
// ---------------------------
// A closure is a function that captures variables from its surrounding scope, allowing it to access those variables even after the scope has exited.
// In Java, closures are typically implemented using lambda expressions or anonymous inner classes.
// Closures are powerful for creating flexible, reusable, and stateful functions.
// Below are detailed explanations and examples demonstrating closures in Java using different classes and interfaces.

import java.util.*;
import java.util.function.*;

public class Ch8_Closure {
    // Example 1: Simple Closure with Lambda
    // -------------------------------------
    // A lambda captures a local variable from its enclosing scope.
    public static void simpleClosureExample() {
        int base = 10;
        Function<Integer, Integer> adder = x -> x + base; // 'base' is captured
        System.out.println("[1] 5 + base (10): " + adder.apply(5));
    }

    // Example 2: Closure with Effectively Final Variable
    // --------------------------------------------------
    // Java requires captured variables to be final or effectively final.
    public static void effectivelyFinalClosureExample() {
        String suffix = "!";
        Function<String, String> exclaim = s -> s + suffix;
        System.out.println("[2] Hello" + suffix + ": " + exclaim.apply("Hello"));
    }

    // Example 3: Closure with Mutable State via Array
    // ----------------------------------------------
    // To mutate captured state, use a mutable object (like an array or custom class).
    public static void mutableClosureExample() {
        int[] counter = {0};
        Runnable increment = () -> counter[0]++;
        increment.run(); increment.run();
        System.out.println("[3] Counter after 2 increments: " + counter[0]);
    }

    // Example 4: Closure in a Custom Functional Interface
    // --------------------------------------------------
    @FunctionalInterface
    interface StringAppender {
        String append(String s);
    }
    public static void customInterfaceClosureExample() {
        String appendText = "_Java";
        StringAppender appender = s -> s + appendText;
        System.out.println("[4] Appended: " + appender.append("Generics"));
    }

    // Example 5: Closure with BiFunction and Outer Variable
    // ----------------------------------------------------
    public static void biFunctionClosureExample() {
        int multiplier = 3;
        BiFunction<Integer, Integer, Integer> multiplyAndAdd = (a, b) -> a * multiplier + b;
        System.out.println("[5] (2 * 3) + 4: " + multiplyAndAdd.apply(2, 4));
    }

    // Example 6: Closure with Supplier and Local State
    // ------------------------------------------------
    public static void supplierClosureExample() {
        String greeting = "Hello, ";
        Supplier<String> greeter = () -> greeting + "World!";
        System.out.println("[6] " + greeter.get());
    }

    // Example 7: Closure with Anonymous Inner Class
    // ---------------------------------------------
    public static void anonymousClassClosureExample() {
        final int offset = 100;
        Function<Integer, Integer> addOffset = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer value) {
                return value + offset;
            }
        };
        System.out.println("[7] 50 + offset (100): " + addOffset.apply(50));
    }

    public static void main(String[] args) {
        simpleClosureExample();
        effectivelyFinalClosureExample();
        mutableClosureExample();
        customInterfaceClosureExample();
        biFunctionClosureExample();
        supplierClosureExample();
        anonymousClassClosureExample();
    }
}

