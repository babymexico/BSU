package com.example.coursework;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class ExternalCalculatorBuilder extends CalculatorBuilder{
    @Override
    double calculate(String expression) {
        Expression exp = new ExpressionBuilder(expression).build();
        double result = exp.evaluate();

        return result;
    }
}
