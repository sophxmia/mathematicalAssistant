package com.github.sophxmia;

import com.github.sophxmia.equation.Equation;
import com.github.sophxmia.equation.EquationDatabase;
import com.github.sophxmia.equation.EquationSearch;
import com.github.sophxmia.equation.EquationValidator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("""
                Hello and welcome!\s
                 Вас вітає "Математичний помічник" вчителя\s
                 Оберіть бажану дію зі списку\s
                    1. Введення рівняння\s
                    2. Пошук рівнянь за коренями\s
                    0. Вихід\s""");


        int setChoice = scanner.nextInt();
        scanner.nextLine();

        switch (setChoice) {
            case 1:
                handleEquationInput(scanner);
                break;
            case 2:
                handleEquationSearch(scanner);
                break;
            case 0:
                System.out.println("Дякую за використання програми. До побачення!");
                break;
            default:
                System.out.println("Невірний вибір. Спробуйте ще раз.");
                break;
        }
    }

    private static void handleEquationSearch(Scanner scanner) {
        System.out.println("Введіть корінь для пошуку:");

        double searchRoot = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Рівнянь з заданим коренем не знайдено.");
    }

    private static void handleEquationInput(Scanner scanner) {
        System.out.println("Введіть математичне рівняння:");
        String equationStr = scanner.nextLine();

        if (EquationValidator.isValidParentheses(equationStr) && EquationValidator.isValidExpression(equationStr)){
            Equation equation = new Equation(equationStr);

            System.out.println("Введене рівняння: " + equationStr);
            System.out.println("Рівняння коректне: " + equation.isValid());

            if(equation.isValid()){
                equation.solve();
                System.out.println("Корені рівняння: ");
            }
        }else {
            System.out.println("Введене рівняння некоректне.");
        }

    }
}