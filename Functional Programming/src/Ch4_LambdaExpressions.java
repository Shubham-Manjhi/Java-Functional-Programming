// Chapter 4: Lambda Expressions in Java
// ======================================
// Lambda expressions are a feature introduced in Java 8 that provide a concise way to represent anonymous functions.
// They enable functional programming in Java by allowing you to pass behavior as arguments to methods.
//
// Syntax of Lambda Expressions:
// -----------------------------
// (parameters) -> expression
// OR
// (parameters) -> { statements }
//
// Key Features:
// 1. Simplifies the use of functional interfaces.
// 2. Reduces boilerplate code.
// 3. Enables functional programming paradigms.

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Ch4_LambdaExpressions {

    // Example 1: Basic Lambda Expression
    // Demonstrates a simple lambda expression to implement a functional interface.
    public static void basicLambdaExample() {
        Runnable runnable = () -> System.out.println("Hello from Lambda!");
        new Thread(runnable).start();
    }

    // Example 2: Lambda with Parameters
    // Demonstrates a lambda expression with parameters.
    public static void lambdaWithParametersExample() {
        BiConsumer<String, Integer> printNameAndAge = (name, age) ->
            System.out.println("Name: " + name + ", Age: " + age);
        printNameAndAge.accept("Alice", 30);
    }

    // Example 3: Lambda with Streams
    // Demonstrates the use of lambda expressions with Java Streams.
    public static void lambdaWithStreamsExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squaredNumbers = numbers.stream()
                                              .map(x -> x * x)
                                              .collect(Collectors.toList());
        System.out.println("Squared Numbers: " + squaredNumbers);
    }

    // Example 4: Lambda for Custom Sorting
    // Demonstrates using a lambda expression for custom sorting.
    public static void lambdaForSortingExample() {
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
        names.sort((a, b) -> a.compareTo(b));
        System.out.println("Sorted Names: " + names);
    }

    // Example 5: Lambda with Functional Interfaces
    // Demonstrates using built-in functional interfaces with lambda expressions.
    public static void lambdaWithFunctionalInterfacesExample() {
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));

        Function<String, Integer> stringLength = str -> str.length();
        System.out.println("Length of 'Lambda': " + stringLength.apply("Lambda"));

        Consumer<String> printMessage = message -> System.out.println("Message: " + message);
        printMessage.accept("Hello, Functional Programming!");
    }

    // Example 6: Capturing Variables in Lambda
    // Demonstrates how lambda expressions can capture variables from the enclosing scope.
    public static void capturingVariablesExample() {
        String greeting = "Hello";
        Consumer<String> greeter = name -> System.out.println(greeting + ", " + name + "!");
        greeter.accept("Alice");
    }

    public static void main(String[] args) {
        // Demonstrating Basic Lambda Expression
        basicLambdaExample();

        // Demonstrating Lambda with Parameters
        lambdaWithParametersExample();

        // Demonstrating Lambda with Streams
        lambdaWithStreamsExample();

        // Demonstrating Lambda for Custom Sorting
        lambdaForSortingExample();

        // Demonstrating Lambda with Functional Interfaces
        lambdaWithFunctionalInterfacesExample();

        // Demonstrating Capturing Variables in Lambda
        capturingVariablesExample();
    }
}
