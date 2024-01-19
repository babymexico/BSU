package com.example.coursework;

public abstract class CalculatorBuilder {
    abstract double calculate(String expression);

    protected static boolean hasPrecedence(char operator1, char operator2) {
        final char ADDITION = '+';
        final char SUBTRACTION = '-';
        final char MULTIPLICATION = '*';
        final char DIVISION = '/';

        return ((operator1 == MULTIPLICATION || operator1 == DIVISION) &&
                (operator2 == ADDITION || operator2 == SUBTRACTION));
    }

    protected static double performOperation(char operator, double operand1, double operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero!");
                }
                return operand1 / operand2;
        }
        throw new IllegalArgumentException("Unknown operator: " + operator);
    }
}
