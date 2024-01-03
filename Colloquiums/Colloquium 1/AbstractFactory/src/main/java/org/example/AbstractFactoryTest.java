package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractFactoryTest {
    @Test
    void testCircleFactory() {
        ShapeFactory circleFactory = new CircleFactory();
        Shape circle = circleFactory.createShape();
        assertNotNull(circle);
        assertTrue(circle instanceof Circle);
    }

    @Test
    void testSquareFactory() {
        ShapeFactory squareFactory = new SquareFactory();
        Shape square = squareFactory.createShape();
        assertNotNull(square);
        assertTrue(square instanceof Square);
    }
}
