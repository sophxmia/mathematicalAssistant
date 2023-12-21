package com.github.sophxmia.equation;

public class EquationValidator {

    public static boolean isValidExpression(String expression) {return true;}
    public static boolean isValidParentheses(String equation) {return true;}

    private static boolean isMathOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
