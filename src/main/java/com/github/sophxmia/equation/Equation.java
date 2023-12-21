package com.github.sophxmia.equation;

import java.util.ArrayList;
import java.util.List;
public class Equation {
    private String equation;
    private List<Double> roots;

    public Equation(String equation) {
        this.equation = equation;
        this.roots = new ArrayList<>();
    }

    public boolean isValid() {
        return EquationValidator.isValidParentheses(equation) && EquationValidator.isValidExpression(equation);
    }

    public void solve() {}

    public String getEquation() {
        return equation;
    }

    public List<Double> getRoots() {
        return roots;
    }
}
