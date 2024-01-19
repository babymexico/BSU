package com.example.coursework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExternalCalculatorBuilderTest {

    @Test
    void calculate1() {
        CalculatorBuilderManager calc = new CalculatorBuilderManager();
        calc.setCalc(new ExternalCalculatorBuilder());

        double result = calc.calculate("4 + 3 * 4");

        assertEquals(16.0, result);
    }

    @Test
    void calculate2() {
        CalculatorBuilderManager calc = new CalculatorBuilderManager();
        calc.setCalc(new ExternalCalculatorBuilder());

        double result = calc.calculate("3 + 2 - 7");

        assertEquals(-2.0, result);
    }

    @Test
    void calculate3() {
        CalculatorBuilderManager calc = new CalculatorBuilderManager();
        calc.setCalc(new ExternalCalculatorBuilder());

        double result = calc.calculate("4 * (5 - 6) / 3");

        assertEquals((double) -4 /3, result);
    }

    @Test
    void calculate4() {
        CalculatorBuilderManager calc = new CalculatorBuilderManager();
        calc.setCalc(new ExternalCalculatorBuilder());

        double result = calc.calculate("0.56 + 0.2 * 4");

        assertEquals(1.36, result);
    }
}