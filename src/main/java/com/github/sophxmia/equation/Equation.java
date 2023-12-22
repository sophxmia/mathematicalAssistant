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
        String[] parts = equation.split("=");

        String leftSide = parts[0].trim();
        String rightSide = parts[1].trim();

        double A = 0.0;
        double B = 0.0;
        double C = -Double.parseDouble(rightSide); // При перенесенні термінів вправо, права сторона стає від'ємною

        String[] leftTerms = leftSide.split("(?=[-+])");

        for (String term : leftTerms) {
            term = term.trim();
            if (term.endsWith("*x")) {
                term = term.substring(0, term.length() - 2).trim();
                if (term.isEmpty() || term.equals("+")) {
                    A += 1.0;
                } else if (term.equals("-")) {
                    A -= 1.0;
                } else {
                    A += Double.parseDouble(term);
                }
            } else if (term.equals("x")) {
                B += 1.0;
            } else {
                B += Double.parseDouble(term);
            }
        }

        // Перевірте, чи маємо квадратичне рівняння (A*x^2 + B*x + C)
        if (A != 0.0) {
            roots.addAll(EquationSolver.solveQuadraticEquation(A, B, C));
        } else {
            // Якщо A = 0, то перевірте, чи маємо лінійне рівняння (B*x + C)
            if (B != 0.0) {
                roots.addAll(EquationSolver.solveLinearEquation(B, C));
            } else {
                // Якщо B = 0, то перевірте, чи маємо константне рівняння (C)
                if (C == 0.0) {
                    // Невизначене рівняння (0*x = 0)
                    roots.add(Double.POSITIVE_INFINITY);
                }
            }
        }
    }

    public String getEquation() {
        return equation;
    }

    public List<Double> getRoots() {
        return roots;
    }
}
