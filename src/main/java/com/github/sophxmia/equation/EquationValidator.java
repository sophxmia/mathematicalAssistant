package com.github.sophxmia.equation;

public class EquationValidator {

    public static boolean isValidExpression(String expression) {
        for (int i = 0; i < expression.length() - 1; i++) {
            char currentChar = expression.charAt(i);
            char nextChar = expression.charAt(i + 1);

            if (isMathOperator(currentChar) && isMathOperator(nextChar) && isValidMultiplicationCase(currentChar, nextChar)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidMultiplicationCase(char currentChar, char nextChar) {
        return currentChar == '*' && (Character.isDigit(nextChar) || nextChar == '-');
    }


    public static boolean isValidParentheses(String equation) {
        int openParenthesesCount = 0;

        for (char character : equation.toCharArray()) {
            if (character == '(') {
                openParenthesesCount++;
            } else if (character == ')') {
                if (openParenthesesCount == 0) {
                    return false; // More closing parentheses than opening ones
                }
                openParenthesesCount--;
            }
        }
        return openParenthesesCount == 0; // All opened parentheses are closed
    }

    private static boolean isMathOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
