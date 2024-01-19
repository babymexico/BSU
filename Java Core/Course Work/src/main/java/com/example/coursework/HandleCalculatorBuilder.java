package com.example.coursework;

import java.util.Stack;

public class HandleCalculatorBuilder extends CalculatorBuilder{
    @Override
    double calculate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int i = 0;

        while (i < expression.length()) {
            char ch = expression.charAt(i);
            if (ch == ' ') {
                i++;
                continue;
            } else if (Character.isDigit(ch) || ch == '.') {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                double number = Double.parseDouble(numBuilder.toString());
                numbers.push(number);
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.isEmpty() && !hasPrecedence(ch, operators.peek()) && !operators.peek().equals('(')) {
                    double operand2 = numbers.pop();
                    double operand1 = numbers.pop();
                    char op = operators.pop();
                    double result = performOperation(op, operand1, operand2);
                    numbers.push(result);
                }
                operators.push(ch);
                i++;
            } else if (ch == '(') {
                operators.push(ch);
                i++;
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    double operand2 = numbers.pop();
                    double operand1 = numbers.pop();
                    char op = operators.pop();
                    double result = performOperation(op, operand1, operand2);
                    numbers.push(result);
                }
                operators.pop();
                i++;
            } else {
                throw new IllegalArgumentException("Недопустимый символ: " + ch);
            }
        }

        while (!operators.isEmpty()) {
            double operand2 = numbers.pop();
            double operand1 = numbers.pop();
            char op = operators.pop();
            double result = performOperation(op, operand1, operand2);
            numbers.push(result);
        }

        return numbers.pop();
    }
}
