// Ch7_PassingFunctionAsArguments.java
// ======================================
// Chapter 7: Passing Functions as Arguments in Java
// -------------------------------------------------
// In Java, you can pass functions as arguments using functional interfaces such as Function, BiFunction, Predicate, Consumer, etc.
// This enables higher-order programming, where methods can accept behavior as parameters, making code more flexible and reusable.
// Below are detailed explanations and examples demonstrating how to pass functions as arguments in Java.

import java.util.*;
import java.util.function.*;

public class Ch7_PassingFunctionAsArguments {
    // Example 1: Using Function<T, R> as Argument
    // -------------------------------------------
    // A method that takes a Function and applies it to a value.
    public static <T, R> R applyFunction(T value, Function<T, R> func) {
        return func.apply(value);
    }

    public static void functionArgumentExample() {
        int result = applyFunction(5, x -> x * x);
        System.out.println("[1] Square of 5: " + result);
    }

    // Example 2: Using Predicate<T> as Argument
    // -----------------------------------------
    // A method that filters a list using a Predicate.
    public static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void predicateArgumentExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> evens = filterList(numbers, n -> n % 2 == 0);
        System.out.println("[2] Even numbers: " + evens);
    }

    // Example 3: Using Consumer<T> as Argument
    // ----------------------------------------
    // A method that processes each element of a list using a Consumer.
    public static <T> void processList(List<T> list, Consumer<T> consumer) {
        for (T item : list) {
            consumer.accept(item);
        }
    }

    public static void consumerArgumentExample() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        System.out.print("[3] Names: ");
        processList(names, name -> System.out.print(name + " "));
        System.out.println();
    }

    // Example 4: Using BiFunction<T, U, R> as Argument
    // ------------------------------------------------
    // A method that combines two values using a BiFunction.
    public static <T, U, R> R combine(T a, U b, BiFunction<T, U, R> combiner) {
        return combiner.apply(a, b);
    }

    public static void biFunctionArgumentExample() {
        String combined = combine("Hello", 123, (s, n) -> s + n);
        System.out.println("[4] Combined: " + combined);
    }

    // Example 5: Using Custom Functional Interface as Argument
    // -------------------------------------------------------
    @FunctionalInterface
    interface StringOperation {
        String operate(String s);
    }

    public static String processString(String s, StringOperation op) {
        return op.operate(s);
    }

    public static void customInterfaceArgumentExample() {
        String reversed = processString("Java", str -> new StringBuilder(str).reverse().toString());
        System.out.println("[5] Reversed: " + reversed);
    }

    // Example 6: Passing Method References as Arguments
    // -------------------------------------------------
    public static String toUpper(String s) {
        return s.toUpperCase();
    }

    public static void methodReferenceArgumentExample() {
        String result = processString("functional", Ch7_PassingFunctionAsArguments::toUpper);
        System.out.println("[6] Uppercase: " + result);
    }

    public static void main(String[] args) {
        functionArgumentExample();
        predicateArgumentExample();
        consumerArgumentExample();
        biFunctionArgumentExample();
        customInterfaceArgumentExample();
        methodReferenceArgumentExample();
    }
}

