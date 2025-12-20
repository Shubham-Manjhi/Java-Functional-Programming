// Chapter 2: Generic Methods in Java
// ====================================
// Generic methods are methods that introduce their own type parameters.
// These type parameters are declared before the return type of the method.
// Generic methods allow for type-safe operations and can be used with any type.
// This file explains the concept of generic methods with detailed examples.

public class Ch2_GenericsMethods {

    // A generic method to print an array of any type
    public static <T> void printArray(T[] array) {
        // Iterating through the array and printing each element
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // A generic method to find the maximum element in an array of Comparable types
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            return null; // Return null for empty or null arrays
        }

        T max = array[0]; // Assume the first element is the maximum
        for (T element : array) {
            if (element.compareTo(max) > 0) {
                max = element; // Update max if a larger element is found
            }
        }
        return max;
    }

    // Explanation:
    // 1. The <T> before the return type indicates that the method is generic.
    // 2. The type parameter T can be replaced with any type when the method is called.
    // 3. The findMax method uses a bounded type parameter (T extends Comparable<T>)
    //    to ensure that the elements can be compared.
    // 4. Generic methods improve code reusability and type safety.
}
