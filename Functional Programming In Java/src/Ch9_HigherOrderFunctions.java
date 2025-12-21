// Ch9_HigherOrderFunctions.java
// ======================================
// Chapter 9: Higher-Order Functions in Java
// -----------------------------------------
// A higher-order function is a function that takes one or more functions as arguments, or returns a function as its result.
// Higher-order functions enable powerful abstractions, code reuse, and flexible APIs.
// In Java, higher-order functions are implemented using functional interfaces (Function, Predicate, Consumer, Supplier, etc.) and lambdas.
// Below are detailed explanations and examples demonstrating higher-order functions in Java using different classes and interfaces.

import java.util.*;
import java.util.function.*;

public class Ch9_HigherOrderFunctions {
    // Example 1: Function as Argument (Map Operation)
    // ----------------------------------------------
    // Applies a function to each element of a list and returns a new list.
    public static <T, R> List<R> map(List<T> list, Function<T, R> func) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(func.apply(item));
        }
        return result;
    }
    public static void mapExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> squares = map(numbers, x -> x * x);
        System.out.println("[1] Squares: " + squares);
    }

    // Example 2: Function as Argument (Filter Operation)
    // -------------------------------------------------
    // Filters a list using a predicate.
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
    public static void filterExample() {
        List<String> words = Arrays.asList("apple", "banana", "pear", "kiwi");
        List<String> longWords = filter(words, w -> w.length() > 4);
        System.out.println("[2] Long words: " + longWords);
    }

    // Example 3: Returning a Function (Multiplier Factory)
    // ---------------------------------------------------
    // Returns a function that multiplies its input by a given factor.
    public static Function<Integer, Integer> multiplier(int factor) {
        return x -> x * factor;
    }
    public static void returningFunctionExample() {
        Function<Integer, Integer> triple = multiplier(3);
        System.out.println("[3] Triple of 7: " + triple.apply(7));
    }

    // Example 4: Composing Functions
    // ------------------------------
    // Returns a function that composes two functions.
    public static <T, R, V> Function<T, V> compose(Function<T, R> f, Function<R, V> g) {
        return x -> g.apply(f.apply(x));
    }
    public static void composeExample() {
        Function<Integer, Integer> doubleIt = x -> x * 2;
        Function<Integer, String> toString = Object::toString;
        Function<Integer, String> doubleThenString = compose(doubleIt, toString);
        System.out.println("[4] Double then String of 5: " + doubleThenString.apply(5));
    }

    // Example 5: Custom Functional Interface as Higher-Order Function
    // --------------------------------------------------------------
    @FunctionalInterface
    interface StringTransformer {
        String transform(String s);
    }
    // A method that takes a StringTransformer and applies it to a string.
    public static String applyTransformer(String s, StringTransformer transformer) {
        return transformer.transform(s);
    }
    public static void customInterfaceExample() {
        StringTransformer reverse = str -> new StringBuilder(str).reverse().toString();
        System.out.println("[5] Reversed: " + applyTransformer("HigherOrder", reverse));
    }

    // Example 6: Returning a Predicate (Dynamic Filter)
    // ------------------------------------------------
    // Returns a predicate that checks if a string contains a given substring.
    public static Predicate<String> containsSubstring(String substring) {
        return s -> s.contains(substring);
    }
    public static void returningPredicateExample() {
        Predicate<String> hasJava = containsSubstring("Java");
        System.out.println("[6] Contains 'Java': " + hasJava.test("I love Java!"));
    }

    // Example 7: Passing BiFunction as Argument (Combiner)
    // ---------------------------------------------------
    // Combines two values using a BiFunction.
    public static <T, U, R> R combine(T a, U b, BiFunction<T, U, R> combiner) {
        return combiner.apply(a, b);
    }
    public static void biFunctionExample() {
        int sum = combine(10, 20, (x, y) -> x + y);
        System.out.println("[7] Sum: " + sum);
    }

    public static void main(String[] args) {
        mapExample();
        filterExample();
        returningFunctionExample();
        composeExample();
        customInterfaceExample();
        returningPredicateExample();
        biFunctionExample();
    }
}

