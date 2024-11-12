package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите математическое выражение (используйте числа, +, -, *, /, и скобки):");
        String inputExpression = scanner.nextLine();

        // Создаем объект Calculate и передаем введенное выражение
        Calculate calculator = new Calculate(inputExpression);

        // Вычисляем результат и выводим его на экран
        String result = calculator.compute();
        System.out.println("Результат: " + result);

        scanner.close();
    }
}
