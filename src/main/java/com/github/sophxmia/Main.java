package com.github.sophxmia;

import com.github.sophxmia.equation.Equation;
import com.github.sophxmia.equation.EquationDatabase;

import java.util.List;
import java.util.Scanner;

/**
 * @author Sofia Maliarenko
 * Застосування повинне надавати такі можливості:
 * 1. Вводити математичні рівняння, що містять числа,
 * а також математичні операції +, -, *, / та круглі дужки, рівень вкладеності дужок – довільний.
 * У всіх рівняннях невідома величина позначається англійською літерою x.
 * 2. Перевіряти введене рівняння на коректність розміщення дужок.
 * 3. Перевіряти коректність введеного виразу
 */
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
        System.out.println();
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
        System.out.println("\nВведіть корінь для пошуку:");

        double searchRoot = scanner.nextDouble();
        scanner.nextLine();

        List<String> equations = EquationDatabase.findEquationsByRoot(searchRoot);

        if (!equations.isEmpty()) {
            System.out.println("Знайдено рівняння(я) з коренем " + searchRoot + ":");
            for (String equation : equations) {
                System.out.println(equation);
            }
        } else {
            System.out.println("Рівнянь з заданим коренем не знайдено.");
        }
    }

    private static void handleEquationInput(Scanner scanner) {
        System.out.println("Введіть математичне рівняння:");
        String equationStr = scanner.nextLine();

        try {
            Equation equation = new Equation(equationStr);

            System.out.println("Введене рівняння: " + equationStr);
            if (equation.isValid()) {
                System.out.println("Рівняння коректне: " + equation.isValid());
            } else {
                System.out.println("Рівняння не коректне");
            }
            if (equation.isValid()) {
                equation.solve();

                List<Double> roots = equation.getRoots();

                if (!roots.isEmpty()) {
                    System.out.println("Корені рівняння: " + roots);
                    System.out.println("Введіть корені рівняння (через пробіл):");
                    String rootsInput = scanner.nextLine();
                    String[] rootsArray = rootsInput.split(" ");

                    for (String root : rootsArray) {
                        double parsedRoot = Double.parseDouble(root);
                        if (Math.abs(parsedRoot - roots.get(0)) < 1e-9) {
                            // Якщо введений корінь збігається з реальним коренем, зберігаю його в БД
                            EquationDatabase.saveRoot(equationStr, parsedRoot);
                            System.out.println(parsedRoot + " є коренем рівняння і був збережений в БД.");
                        } else {
                            System.out.println(parsedRoot + " не є коренем рівняння.");
                        }
                    }
                } else {
                    System.out.println("Рівняння не має реальних коренів.");
                }
            }
            if (equation.isValid()) {
                EquationDatabase.saveEquation(equation.getEquation());
                System.out.println("Рівняння успішно збережено в базі даних.");
            } else {
                System.out.println("Рівняння не коректне, не буде збережено в базі даних.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка при обробці числових значень у рівнянні.");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }

    }
}