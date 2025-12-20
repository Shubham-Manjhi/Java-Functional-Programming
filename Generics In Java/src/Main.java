import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		// Chapter 1: Casting and Generics
        System.out.println("\nChapter 1: Casting and Generics");
        try {
            Object strObj = "Hello Generics!";
            String castedString = Ch1_Casting.castToString(strObj);
            System.out.println("Casted to String: " + castedString);
        } catch (ClassCastException e) {
            System.out.println("Failed to cast to String: " + e.getMessage());
        }

        // Example 2: Casting to Integer
        try {
            Object intObj = 42;
            Integer castedInteger = Ch1_Casting.castToInteger(intObj);
            System.out.println("Casted to Integer: " + castedInteger);
        } catch (ClassCastException e) {
            System.out.println("Failed to cast to Integer: " + e.getMessage());
        }

        // Example 3: Casting to Double
        try {
            Object doubleObj = 3.14;
            Double castedDouble = Ch1_Casting.castToDouble(doubleObj);
            System.out.println("Casted to Double: " + castedDouble);
        } catch (ClassCastException e) {
            System.out.println("Failed to cast to Double: " + e.getMessage());
        }

        // Example 4: Invalid casting
        try {
            Object invalidObj = "Not a number";
            Integer invalidCast = Ch1_Casting.castToInteger(invalidObj);
            System.out.println("Casted to Integer: " + invalidCast);
        } catch (ClassCastException e) {
            System.out.println("Failed to cast to Integer: " + e.getMessage());
        }

        // Chapter 2: Generic Methods
        System.out.println("\nChapter 2: Generic Methods");
        Integer[] intArray = {1, 2, 3, 4, 5};
        Double[] doubleArray = {1.1, 2.2, 3.3};
        Ch2_GenericsMethods.printArray(intArray);
        Ch2_GenericsMethods.printArray(doubleArray);
        System.out.println("Max in intArray: " + Ch2_GenericsMethods.findMax(intArray));
        System.out.println("Max in doubleArray: " + Ch2_GenericsMethods.findMax(doubleArray));

        // Chapter 3: Bounded Generics
        System.out.println("\nChapter 3: Bounded Generics");
        Number[] numbers = {1, 2.5, 3};
        System.out.println("Sum of numbers: " + Ch3_BoundedGenerics.calculateSum(numbers));
        System.out.println("Max of numbers: " + Ch3_BoundedGenerics.findMax(numbers));
        Ch3_BoundedGenerics.Box<Integer> intBox = new Ch3_BoundedGenerics.Box<>(10);
        intBox.displayValue();

        // Chapter 4: Liskov Substitution Principle
        System.out.println("\nChapter 4: Liskov Substitution Principle");
        Ch4_LiskoveSubstitutionPrinciple.Shape[] shapes = {
            new Ch4_LiskoveSubstitutionPrinciple.Rectangle(5, 10),
            new Ch4_LiskoveSubstitutionPrinciple.Rectangle(3, 6)
        };
        Ch4_LiskoveSubstitutionPrinciple.printShapesArea(shapes);

        // Chapter 5: Wildcards in Generics
        System.out.println("\nChapter 5: Wildcards in Generics");
        List<Integer> intList = new ArrayList<>();
        Ch5_WildCardsInGenerics.addIntegers(intList);
        List<Double> doubleList = List.of(1.1, 2.2, 3.3);
        System.out.println("Sum of doubleList: " + Ch5_WildCardsInGenerics.calculateSum(doubleList));
        List<Number> destinationList = new ArrayList<>();
        Ch5_WildCardsInGenerics.copyList(doubleList, destinationList);
        Ch5_WildCardsInGenerics.processNumbers(doubleList, destinationList);
	}
}