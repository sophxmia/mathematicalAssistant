package com.github.sophxmia.equation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sofia Maliarenko
 * Sets the equation, splits it both sides, decides the type of the equation, solves the equation
 */

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

        if (leftSide.contains("x")) {
            switchSidesOfEquation(rightSide, leftSide);
        } else if (rightSide.contains("x")) {
            switchSidesOfEquation(leftSide, rightSide);
        }
    }

    private void switchSidesOfEquation(String leftSide, String rightSide) {
        double A = 0.0;
        double B = 0.0;
        double C = -Double.parseDouble(leftSide); // При перенесенні термінів вправо, ліва сторона стає від'ємною

        String[] rightTerms = rightSide.split("(?=[-+])");
        for (String term : rightTerms) {
            term = term.trim();
            if (term.endsWith("*x")) {
                term = term.substring(0, term.length() - 2).trim();
                if (term.isEmpty() || term.equals("+")) {
                    A += 1.0;
                } else if (term.equals("-")) {
                    B -= 1.0;
                } else {
                    B += Double.parseDouble(term);
                }
            } else if (term.equals("x")) {
                B += 1.0;
            } else {
                C += Double.parseDouble(term);
            }
        }
        // маємо квадратичне рівняння (A*x^2 + B*x + C)
        if (A != 0.0) {
            roots.addAll(EquationSolver.solveQuadraticEquation(A, B, C));
        } else {
            // Якщо A = 0, то маємо лінійне рівняння (B*x + C)
            if (B != 0.0) {
                roots.addAll(EquationSolver.solveLinearEquation(B, C));
            } else {
                // Якщо B = 0, то маємо константне рівняння (C)
                if (C == 0.0) {
                    // Невизначене рівняння (0*x = 0)
                    roots.add(Double.POSITIVE_INFINITY);
                } else {
                    // Додаємо розв'язки для раціонального рівняння (C = 0)
                    roots.addAll(EquationSolver.solveRationalEquation(A, B, C));
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
