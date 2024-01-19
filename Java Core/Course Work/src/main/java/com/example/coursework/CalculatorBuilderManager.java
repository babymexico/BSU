package com.example.coursework;

public class CalculatorBuilderManager {

    private CalculatorBuilder calc;

    public void setCalc(CalculatorBuilder calc) {
        if (calc == null) {
            throw new IllegalArgumentException("CalculatorBuilder cannot be null.");
        }
        this.calc = calc;
    }

    public double calculate(String expression) {
        if (calc == null) {
            throw new IllegalStateException("CalculatorBuilder is not set.");
        }
        return calc.calculate(expression);
    }
}
