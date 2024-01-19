package com.example.coursework;

import java.util.Stack;

public class RegexCalculatorBuilder extends CalculatorBuilder {

    private static final String INVALID_CHAR_MESSAGE = "Invalid character encountered: ";

    @Override
    double calculate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            String ch = Character.toString(expression.charAt(i));
            if (ch.equals(" ")) {
                i++;
                continue;
            } else if (ch.matches("\\d+(\\.\\d+)?") || ch.equals(".")) {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                double number = Double.parseDouble(numBuilder.toString());
                numbers.push(number);
            } else if (ch.matches("[+\\-*/]")) {
                while (!operators.isEmpty() && !hasPrecedence(ch.charAt(0), operators.peek().charAt(0)) && !operators.peek().equals("(")) {
                    double operand2 = numbers.pop();
                    double operand1 = numbers.pop();
                    String op = operators.pop();
                    double result = performOperation(op.charAt(0), operand1, operand2);
                    numbers.push(result);
                }
                operators.push(ch);
                i++;
            } else if (ch.equals("(")) {
                operators.push(ch);
                i++;
            } else if (ch.equals(")")) {
                while (!operators.isEmpty() && operators.peek().charAt(0) != '(') {
                    double operand2 = numbers.pop();
                    double operand1 = numbers.pop();
                    String op = operators.pop();
                    double result = performOperation(op.charAt(0), operand1, operand2);
                    numbers.push(result);
                }
                operators.pop();
                i++;
            } else {
                throw new IllegalArgumentException(INVALID_CHAR_MESSAGE + ch);
            }
        }

        while (!operators.isEmpty()) {
            double operand2 = numbers.pop();
            double operand1 = numbers.pop();
            String op = operators.pop();
            double result = performOperation(op.charAt(0), operand1, operand2);
            numbers.push(result);
        }

        return numbers.pop();
    }
}
