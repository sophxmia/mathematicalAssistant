package com.github.sophxmia;

import com.github.sophxmia.equation.Equation;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EquationTest {

    @Test
    public void testSolveLinearEquation() {
        Equation equation = new Equation("2*x + 5 = 17");
        equation.solve();
        List<Double> roots = equation.getRoots();

        assertEquals(1, roots.size());
        assertEquals(6.0, roots.get(0), 1e-9); // Using delta for double comparison
    }

    @Test
    public void testSolveQuadraticEquation() {
        Equation equation = new Equation("x^2 - 4 = 0");
        equation.solve();
        List<Double> roots = equation.getRoots();

        assertEquals(2, roots.size());
        assertTrue(roots.contains(2.0));
        assertTrue(roots.contains(-2.0));
    }

    @Test
    public void testInvalidEquation() {
        Equation equation = new Equation("invalid_equation");
        assertFalse(equation.isValid());

        equation = new Equation("2*x + 5 = 17");
        assertTrue(equation.isValid());
    }
}