package org.example;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculatorTest {

    @Test
    void testCalculateFactorialsForValidInput() {
        FactorialCalculator calculator = new FactorialCalculator();
        int n = 5;
        List<BigInteger> factorials = calculator.calculateFactorials(n);
        assertNotNull(factorials);
        assertEquals(5, factorials.size());
        assertEquals(BigInteger.ONE, factorials.get(0));
        assertEquals(BigInteger.valueOf(2), factorials.get(1));
        assertEquals(BigInteger.valueOf(6), factorials.get(2));
        assertEquals(BigInteger.valueOf(24), factorials.get(3));
        assertEquals(BigInteger.valueOf(120), factorials.get(4));
    }

    @Test
    void testCalculateFactorialsForZero() {
        FactorialCalculator calculator = new FactorialCalculator();
        int n = 0;
        List<BigInteger> factorials = calculator.calculateFactorials(n);
        assertNotNull(factorials);
        assertEquals(0, factorials.size());
    }

    @Test
    void testCalculateFactorialsForNegativeInput() {
        FactorialCalculator calculator = new FactorialCalculator();
        int n = -5;
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateFactorials(n);
        });
    }
}

