// Chapter 3: Bounded Generics in Java
// ====================================
// Bounded generics in Java allow you to restrict the types that can be used as type arguments
// in a parameterized type. This is achieved using bounds, which can be upper bounds or lower bounds.
// Bounded generics improve type safety and provide more flexibility in generic programming.

import java.util.List;

public class Ch3_BoundedGenerics {

    // A generic method with an upper bound
    // This method accepts an array of elements that extend the Number class
    public static <T extends Number> double calculateSum(T[] numbers) {
        double sum = 0.0;
        for (T number : numbers) {
            sum += number.doubleValue(); // Using the doubleValue() method from the Number class
        }
        return sum;
    }

    // A generic method with multiple bounds
    // This method accepts a type that extends Number and implements Comparable
    public static <T extends Number & Comparable<T>> T findMax(T[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return null; // Return null for empty or null arrays
        }

        T max = numbers[0]; // Assume the first element is the maximum
        for (T number : numbers) {
            if (number.compareTo(max) > 0) {
                max = number; // Update max if a larger element is found
            }
        }
        return max;
    }

    // Explanation:
    // 1. Upper Bounds: The <T extends Number> syntax restricts T to be a subclass of Number.
    //    This ensures that the method can use methods from the Number class, such as doubleValue().
    // 2. Multiple Bounds: The <T extends Number & Comparable<T>> syntax restricts T to be a subclass
    //    of Number and also implement the Comparable interface. This allows the method to compare elements.
    // 3. Bounded generics improve type safety by ensuring that only compatible types are used.
    // 4. These methods demonstrate how to use bounded generics to perform operations on specific types.

    // Example 1: Upper Bound with List
    // This method accepts a list of elements that extend the Number class
    public static <T extends Number> void printNumbers(List<T> numbers) {
        for (T number : numbers) {
            System.out.println("Number: " + number);
        }
    }

    // Example 2: Lower Bound
    // This method accepts a list of elements that are superclasses of Integer
    public static void addIntegers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
        System.out.println("List after adding integers: " + list);
    }

    // Example 3: Wildcard with Upper Bound
    // This method calculates the sum of a list of numbers
    public static double calculateSumWildcard(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }

    // Example 4: Generic Class with Bounded Type
    // A generic class that accepts types extending Number
    public static class Box<T extends Number> {
        private T value;

        public Box(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void displayValue() {
            System.out.println("Box contains: " + value);
        }
    }

    // Example 5: Multiple Bounds with Method
    // This method accepts a type that extends Number and implements Comparable
    public static <T extends Number & Comparable<T>> T findMin(T[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return null; // Return null for empty or null arrays
        }

        T min = numbers[0]; // Assume the first element is the minimum
        for (T number : numbers) {
            if (number.compareTo(min) < 0) {
                min = number; // Update min if a smaller element is found
            }
        }
        return min;
    }
}
