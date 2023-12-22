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

    public void solve() {
        double A = 0.0;
        double B = 0.0;
        double C = 0.0;

        if (equation.contains("x")) {
            String[] parts = equation.split("=");

            String leftSide = parts[0].trim();
            String rightSide = parts[1].trim();

            String[] coefficients = leftSide.split("\\*");
            if (coefficients.length == 1) {
                B = Double.parseDouble(coefficients[0].trim());
            } else if (coefficients.length == 2) {
                A = Double.parseDouble(coefficients[0].trim());
                B = Double.parseDouble(coefficients[1].trim());
            }
            C = Double.parseDouble(rightSide);
        }
        if (A != 0.0) {
            roots.addAll(EquationSolver.solveQuadraticEquation(A, B, C));
        } else if (B != 0.0) {
            roots.addAll(EquationSolver.solveLinearEquation(B, C));
        }
    }

    public String getEquation() {
        return equation;
    }

    public List<Double> getRoots() {
        return roots;
    }
}
