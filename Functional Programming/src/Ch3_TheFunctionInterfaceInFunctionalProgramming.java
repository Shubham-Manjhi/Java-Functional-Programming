// Chapter 2: The Function Interface in Functional Programming
// ===========================================================
// The `Function` interface in Java is a functional interface provided in the `java.util.function` package.
// It represents a function that takes one argument and produces a result. The `Function` interface is widely
// used in functional programming to create reusable and composable functions.
//
// Functional Interface Definition:
// ---------------------------------
// @FunctionalInterface
// public interface Function<T, R> {
//     R apply(T t);
// }
//
// - `T`: The type of the input to the function.
// - `R`: The type of the result of the function.
//
// The `apply` method is the single abstract method (SAM) that must be implemented.

import java.util.function.Function;
import java.util.List;
import java.util.stream.Collectors;

public class Ch3_TheFunctionInterfaceInFunctionalProgramming {

    // Example 1: Basic Usage of Function Interface
    public static void basicFunctionExample() {
        Function<Integer, String> intToString = i -> "Number: " + i;
        System.out.println(intToString.apply(5)); // Output: Number: 5
    }

    // Example 2: Function Composition
    // Demonstrates the use of `andThen` and `compose` methods for function composition.
    public static void functionCompositionExample() {
        Function<Integer, Integer> multiplyByTwo = x -> x * 2;
        Function<Integer, Integer> addThree = x -> x + 3;

        // Using andThen: multiply first, then add
        Function<Integer, Integer> multiplyThenAdd = multiplyByTwo.andThen(addThree);
        System.out.println("Result of multiplyThenAdd: " + multiplyThenAdd.apply(5)); // Output: 13

        // Using compose: add first, then multiply
        Function<Integer, Integer> addThenMultiply = multiplyByTwo.compose(addThree);
        System.out.println("Result of addThenMultiply: " + addThenMultiply.apply(5)); // Output: 16
    }

    // Example 3: Function with Streams
    // Demonstrates how to use Function with Java Streams.
    public static void functionWithStreamsExample() {
        List<String> names = List.of("Alice", "Bob", "Charlie");
        Function<String, String> toUpperCase = String::toUpperCase;

        List<String> upperCaseNames = names.stream()
                                           .map(toUpperCase)
                                           .collect(Collectors.toList());
        System.out.println("Uppercase Names: " + upperCaseNames);
    }

    // Example 4: Function as a Parameter
    // Demonstrates passing a Function as a parameter to a method.
    public static void applyFunction(Function<Integer, String> func, int value) {
        System.out.println("Result of applying function: " + func.apply(value));
    }

    public static void functionAsParameterExample() {
        Function<Integer, String> intToHex = Integer::toHexString;
        applyFunction(intToHex, 255); // Output: ff
    }

    // Example 5: Custom Function Implementation
    // Demonstrates creating a custom implementation of the Function interface.
    public static void customFunctionExample() {
        Function<String, Integer> stringLength = new Function<>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };

        System.out.println("Length of 'Functional': " + stringLength.apply("Functional")); // Output: 10
    }

    public static void main(String[] args) {
        // Demonstrating Basic Usage of Function Interface
        basicFunctionExample();

        // Demonstrating Function Composition
        functionCompositionExample();

        // Demonstrating Function with Streams
        functionWithStreamsExample();

        // Demonstrating Function as a Parameter
        functionAsParameterExample();

        // Demonstrating Custom Function Implementation
        customFunctionExample();
    }
}
