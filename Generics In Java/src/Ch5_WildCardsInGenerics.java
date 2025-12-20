// Chapter 5: Wildcards in Generics in Java
// ==========================================
// Wildcards in generics provide flexibility in specifying the type of a generic parameter.
// The wildcard `?` represents an unknown type. It is often used with bounds to restrict the types
// that can be passed as arguments. Wildcards are especially useful when working with collections.
//
// Types of Wildcards:
// 1. `? extends Class`: Upper bounded wildcard. Restricts the unknown type to be a subclass of a specific class.
// 2. `? super Class`: Lower bounded wildcard. Restricts the unknown type to be a superclass of a specific class.
// 3. Unbounded `?`: Represents any type.

import java.util.List;
import java.util.ArrayList;

public class Ch5_WildCardsInGenerics {

    // Example 1: Upper Bounded Wildcard
    // This method calculates the sum of a list of numbers
    public static double calculateSum(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }

    // Example 2: Lower Bounded Wildcard
    // This method adds integers to a list of numbers
    public static void addIntegers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
        System.out.println("List after adding integers: " + list);
    }

    // Example 3: Unbounded Wildcard
    // This method prints elements of any type of list
    public static void printList(List<?> list) {
        for (Object element : list) {
            System.out.println("Element: " + element);
        }
    }

    // Example 4: Wildcards with Generic Classes
    // A generic class that accepts any type
    public static class Box<T> {
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

    // Example 5: Resolving Problems with <? extends Class>
    // This method demonstrates how `? extends Class` resolves problems
    public static void copyList(List<? extends Number> source, List<Number> destination) {
        for (Number number : source) {
            destination.add(number);
        }
        System.out.println("Destination list after copying: " + destination);
    }

    // Explanation:
    // 1. `? extends Class` allows you to pass a list of any subclass of the specified class.
    //    For example, a List<Integer> or List<Double> can be passed to a method expecting List<? extends Number>.
    // 2. This resolves the problem of type mismatch when working with collections of different types.
    // 3. `? super Class` is useful when you want to add elements to a collection, ensuring type safety.
    // 4. Unbounded `?` is used when the type is irrelevant, and you only need to perform operations applicable to all objects.

    // Example 6: Combining Wildcards
    // This method demonstrates combining upper and lower bounds
    public static void processNumbers(List<? extends Number> source, List<? super Number> destination) {
        for (Number number : source) {
            destination.add(number);
        }
        System.out.println("Processed destination list: " + destination);
    }
}
