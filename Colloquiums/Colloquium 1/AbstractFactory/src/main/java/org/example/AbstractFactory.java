package org.example;

// Abstract product
interface Shape {
    void draw();
}

// Concrete products
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle: draw()");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square: draw()");
    }
}

// Abstract factory
interface ShapeFactory {
    Shape createShape();
}

// Concrete factories
class CircleFactory implements ShapeFactory {
    @Override
    public Shape createShape() {
        return new Circle();
    }
}

class SquareFactory implements ShapeFactory {
    @Override
    public Shape createShape() {
        return new Square();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        // Create factories
        ShapeFactory circleFactory = new CircleFactory();
        ShapeFactory squareFactory = new SquareFactory();

        // Create shapes
        Shape circle = circleFactory.createShape();
        Shape square = squareFactory.createShape();

        // Draw shapes
        circle.draw(); // Output: Circle: draw()
        square.draw(); // Output: Square: draw()
    }
}
