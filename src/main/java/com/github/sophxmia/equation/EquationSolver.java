package com.github.sophxmia.equation;

import com.github.sophxmia.Main;

import java.util.ArrayList;
import java.util.List;

public class EquationSolver {

    public static List<Double> solveLinearEquation(double a, double b) {
        List<Double> roots = new ArrayList<>();

        if (a != 0) {
            double root = -b / a;
            roots.add(root);
        }
        return roots;
    }

    public static List<Double> solveQuadraticEquation(double a, double b, double c) {
        List<Double> roots = new ArrayList<>();
        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);

            roots.add(root1);
            roots.add(root2);
        } else if (discriminant == 0) {
            double root = -b / (2*a);

            roots.add(root);
        }
        return roots;
    }
}
