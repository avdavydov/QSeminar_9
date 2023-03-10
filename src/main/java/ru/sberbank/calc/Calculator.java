package ru.sberbank.calc;

import java.util.*;

public class Calculator {

    public static void main(String[] args) {
        Calculator calc = new Calculator();

        while (true) {
            System.out.println("Введите выражение, состоящее из двух целых чисел от 0 до 10, знака операции(напр. 2+2): ");
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();
            System.out.println(calc.calc(inputString));

        }

    }

    public String calc(String inputString) {

        CalculatorHelper calculatorHelper = new CalculatorHelper();

        String[] arabic = {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1"};
        String[] roman = {"X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};
        String[] allowed_operations = {"+", "-", "*", "/"};
        String result;
        int valueOfIntResult;
        Boolean digitFirstIsArabic = null;
        Boolean digitSecondIsArabic = null;

        int digitFirst, digitSecond;

        //Находим первое и второе число и проверяем, является ли они арабским, а также находим операцию:
        String[] digitArray = inputString.toUpperCase().replaceAll(" ", "").split("[+-/*]");
        if (digitArray.length != 2) {
            throw new IllegalArgumentException();
        }
        char operation = inputString.replaceAll(" ", "").charAt(digitArray[0].length());
        if (!Arrays.asList(allowed_operations).contains(String.valueOf(operation))) {
            throw new IllegalArgumentException();
        }

        if (Arrays.asList(arabic).contains(digitArray[0])) {
            digitFirstIsArabic = true;
        }

        if (Arrays.asList(arabic).contains(digitArray[1])) {
            digitSecondIsArabic = true;
        }

        if (Arrays.asList(roman).contains(digitArray[0])) {
            digitFirstIsArabic = false;
        }

        if (Arrays.asList(roman).contains(digitArray[1])) {
            digitSecondIsArabic = false;
        }


        if ((digitFirstIsArabic != null) && (digitSecondIsArabic != null) && (digitFirstIsArabic && digitSecondIsArabic)) {

            //Если оба числа арабские:
            digitFirst = Integer.parseInt(digitArray[0]);
            digitSecond = Integer.parseInt(digitArray[1]);

            if ((digitFirst > 10) || (digitSecond > 10)) {
                throw new IllegalArgumentException();
            } else {
                result = Integer.toString(calculatorHelper.do_calc(digitFirst, digitSecond, operation));
            }


        } else if ((digitFirstIsArabic != null) && (digitSecondIsArabic != null) && (!digitFirstIsArabic && !digitSecondIsArabic)) {
            //Если оба числа - римские, конвертим их в арабские, считаем результат и конвертим обратно в римские
            digitFirst = calculatorHelper.convertRomanToInteger(digitArray[0]);
            digitSecond = calculatorHelper.convertRomanToInteger(digitArray[1]);
            valueOfIntResult = calculatorHelper.do_calc(digitFirst, digitSecond, operation);
            if ((valueOfIntResult < 1) || (digitFirst > 10) || (digitSecond > 10)) {
                throw new IllegalArgumentException();
            } else {
                result = calculatorHelper.convertIntegerToRoman(valueOfIntResult);
            }


        } else {
            //Если числа из разных систем исчисления:
            throw new IllegalArgumentException();

        }

        return result;
    }
}
