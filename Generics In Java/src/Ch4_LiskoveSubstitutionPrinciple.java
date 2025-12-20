// Chapter 3: Liskov Substitution Principle in Java
// ==================================================
// The Liskov Substitution Principle (LSP) is one of the SOLID principles of object-oriented programming.
// It states that objects of a superclass should be replaceable with objects of a subclass without affecting
// the correctness of the program. In simpler terms, a subclass should behave like its superclass.
// This principle ensures that a derived class does not break the functionality of the base class.

public class Ch4_LiskoveSubstitutionPrinciple {

    // Example 1: Basic LSP Compliance
    // A superclass Shape and its subclass Rectangle
    public static class Shape {
        public double area() {
            return 0;
        }
    }

    public static class Rectangle extends Shape {
        private double length;
        private double width;

        public Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }

        @Override
        public double area() {
            return length * width;
        }
    }

    // Example 2: LSP Violation
    // A Square class that violates LSP by overriding behavior incorrectly
    public static class Square extends Rectangle {
        public Square(double side) {
            super(side, side);
        }

        @Override
        public double area() {
            // Incorrect behavior: area calculation is hardcoded
            return super.area() * 2; // Violates LSP
        }
    }

    // Example 3: LSP with Interfaces
    // Using an interface to ensure LSP compliance
    public interface Bird {
        void fly();
    }

    public static class Sparrow implements Bird {
        @Override
        public void fly() {
            System.out.println("Sparrow is flying");
        }
    }

    public static class Penguin implements Bird {
        @Override
        public void fly() {
            // Penguins cannot fly, violating the LSP
            throw new UnsupportedOperationException("Penguins cannot fly");
        }
    }

    // Example 4: LSP with Abstract Classes
    // Abstract class Animal and its subclasses
    public static abstract class Animal {
        public abstract void makeSound();
    }

    public static class Dog extends Animal {
        @Override
        public void makeSound() {
            System.out.println("Woof Woof");
        }
    }

    public static class Cat extends Animal {
        @Override
        public void makeSound() {
            System.out.println("Meow Meow");
        }
    }

    // Example 5: LSP in Collections
    // Demonstrating LSP with collections
    public static void printShapesArea(Shape[] shapes) {
        for (Shape shape : shapes) {
            System.out.println("Area: " + shape.area());
        }
    }

    // Explanation:
    // 1. LSP ensures that subclasses can replace their base classes without breaking the program.
    // 2. Violations of LSP often occur when a subclass overrides behavior in a way that contradicts the base class.
    // 3. Using interfaces and abstract classes can help enforce LSP by defining consistent behavior.
    // 4. Examples demonstrate both compliance and violation of LSP in different scenarios.
}
