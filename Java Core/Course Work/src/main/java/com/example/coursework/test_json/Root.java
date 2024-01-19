package com.example.coursework.test_json;

import java.util.List;

public class Root {
    private String name;
    private List<Expressions> expressions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Expressions> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expressions> expressions) {
        this.expressions = expressions;
    }

    @Override
    public String toString() {
        return "Root{" +
                "name='" + name + '\'' +
                ", expressions=" + expressions +
                '}';
    }
}
