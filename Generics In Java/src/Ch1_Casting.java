// Chapter 1: Casting and Generics in Java
// ==========================================
// Generics in Java provide a way to parameterize types, making code more flexible and type-safe.
// However, there are scenarios where casting is necessary, especially when working with raw types
// or converting between incompatible types. This file explains the role of casting in generics
// with theoretical details and practical examples.

public class Ch1_Casting {

    // A generic method that accepts any type and returns it as a String
    public static <T> String castToString(T input) {
        // Casting the input to String
        return (String) input;
    }

    // A generic method that accepts any type and returns it as an Integer
    public static <T> Integer castToInteger(T input) {
        // Casting the input to Integer
        return (Integer) input;
    }

    // A generic method that accepts any type and returns it as a Double
    public static <T> Double castToDouble(T input) {
        // Casting the input to Double
        return (Double) input;
    }

    // Explanation:
    // 1. Generics improve type safety by allowing parameterized types.
    // 2. Casting is required when working with raw types or when converting between incompatible types.
    // 3. The above methods demonstrate how to cast objects to specific types using generics.
    // 4. If the input type is incompatible with the target type, a ClassCastException will be thrown.
}
