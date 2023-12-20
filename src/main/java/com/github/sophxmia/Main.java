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
                    0. Вихід""");


        int setChoice = scanner.nextInt();
        scanner.nextLine();

        switch (setChoice){
            case 1:
                handleEquationInput();
                break;
            case 2:
                handleEquationSearch();
                break;
            case 0:
                System.out.println("Дякую за використання програми. До побачення!");
                break;
            default:
                System.out.println("Невірний вибір. Спробуйте ще раз.");
                break;
        }
    }

    private static void handleEquationSearch() {
        System.out.println("Введіть математичне рівняння:");
    }

    private static void handleEquationInput() {
        System.out.println("Введіть корінь для пошуку:");
    }
}