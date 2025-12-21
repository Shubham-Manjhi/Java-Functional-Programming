// Chapter 5: BiFunctions and Beyond in Java
// ==========================================
// The `BiFunction` interface in Java is a functional interface provided in the `java.util.function` package.
// It represents a function that takes two arguments and produces a result. The `BiFunction` interface is widely
// used in functional programming to create reusable and composable functions that operate on two inputs.
//
// Functional Interface Definition:
// ---------------------------------
// @FunctionalInterface
// public interface BiFunction<T, U, R> {
//     R apply(T t, U u);
// }
//
// - `T`: The type of the first input to the function.
// - `U`: The type of the second input to the function.
// - `R`: The type of the result of the function.
//
// The `apply` method is the single abstract method (SAM) that must be implemented.

import java.util.*;
import java.util.function.*;

public class Ch5_BiFunctionsAndBeyonds {

    // Example 1: Basic Usage of BiFunction
    // Demonstrates a simple BiFunction to concatenate two strings.
    public static void basicBiFunctionExample() {
        BiFunction<String, String, String> concatenate = (a, b) -> a + b;
        System.out.println("Concatenated Result: " + concatenate.apply("Hello, ", "World!"));
    }

    // Example 2: BiFunction with Custom Logic
    // Demonstrates a BiFunction to calculate the area of a rectangle.
    public static void biFunctionForAreaExample() {
        BiFunction<Integer, Integer, Integer> calculateArea = (length, width) -> length * width;
        System.out.println("Area of Rectangle: " + calculateArea.apply(5, 10));
    }

    // Example 3: BiFunction with Streams
    // Demonstrates using BiFunction with Java Streams to merge two lists.
    public static void biFunctionWithStreamsExample() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);

        BiFunction<Integer, Integer, Integer> sum = Integer::sum;
        List<Integer> mergedList = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++) {
            mergedList.add(sum.apply(list1.get(i), list2.get(i)));
        }

        System.out.println("Merged List: " + mergedList);
    }

    // Example 4: Chaining BiFunctions
    // Demonstrates chaining BiFunctions using `andThen`.
    public static void chainingBiFunctionsExample() {
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        Function<Integer, String> toString = result -> "Result: " + result;

        BiFunction<Integer, Integer, String> multiplyAndConvert = multiply.andThen(toString);
        System.out.println(multiplyAndConvert.apply(3, 4)); // Output: Result: 12
    }

    // Example 5: Using BiConsumer
    // Demonstrates the use of BiConsumer to process two inputs without returning a result.
    public static void biConsumerExample() {
        BiConsumer<String, Integer> printNameAndAge = (name, age) ->
            System.out.println("Name: " + name + ", Age: " + age);
        printNameAndAge.accept("Alice", 30);
    }

    // Example 6: Using BinaryOperator
    // Demonstrates the use of BinaryOperator, a specialization of BiFunction.
    public static void binaryOperatorExample() {
        BinaryOperator<Integer> max = Integer::max;
        System.out.println("Max of 10 and 20: " + max.apply(10, 20));
    }

    // Example 7: Using BiFunction Interface
    // Demonstrates a BiFunction to calculate the product of two numbers.
    public static void biFunctionProductExample() {
        BiFunction<Integer, Integer, Integer> product = (a, b) -> a * b;
        System.out.println("Product of 6 and 7: " + product.apply(6, 7)); // Output: 42
    }

    // Example 8: Implementing BiFunction Interface
    // Demonstrates creating a custom implementation of the BiFunction interface.
    public static void customBiFunctionImplementationExample() {
        BiFunction<Integer, Integer, String> customBiFunction = new BiFunction<>() {
            @Override
            public String apply(Integer a, Integer b) {
                return "Sum: " + (a + b);
            }
        };

        System.out.println(customBiFunction.apply(8, 12)); // Output: Sum: 20
    }

    public static void main(String[] args) {
        // Demonstrating Basic Usage of BiFunction
        basicBiFunctionExample();

        // Demonstrating BiFunction for Custom Logic
        biFunctionForAreaExample();

        // Demonstrating BiFunction with Streams
        biFunctionWithStreamsExample();

        // Demonstrating Chaining BiFunctions
        chainingBiFunctionsExample();

        // Demonstrating BiConsumer
        biConsumerExample();

        // Demonstrating BinaryOperator
        binaryOperatorExample();

        // Demonstrating BiFunction Product Example
        biFunctionProductExample();

        // Demonstrating Custom BiFunction Implementation
        customBiFunctionImplementationExample();
    }
}
