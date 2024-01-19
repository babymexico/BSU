package com.example.coursework.test_json;

public class Expressions {
    private String expression;

    public Expressions(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "Expressions{" +
                "expression='" + expression + '}';
    }
}
