import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Main {

    public static String calc(String input) {
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        String result = "";
        List<String> operations = new ArrayList<>();
        operations.add("+");
        operations.add("-");
        operations.add("*");
        operations.add("/");
        char op = ' ';
        String firstNum = "";
        String secondNum = "";
        char[] char_operation = new char[10];

        if (input.length() == 1) {
            throw new IllegalArgumentException("Должны быть введены два числа");
        }

        for (int i = 0; i < input.length(); i++) {
            char_operation[i] = input.charAt(i);
            if (i == input.length() - 1) {
                throw new IllegalArgumentException("Не верный знак операции");
            } else if (char_operation[i] == '+') {
                op = '+';
                break;
            } else if (char_operation[i] == '-') {
                op = '-';
                break;
            } else if (char_operation[i] == '*') {
                op = '*';
                break;
            } else if (char_operation[i] == '/') {
                op = '/';
                break;
            }

        }

        for (String a : operations) {
            if (input.contains(a)) {
                firstNum = input.substring(0, input.indexOf(a));
                secondNum = input.substring(input.indexOf(a) + 1);
            }
        }
        if (secondNum.contains("+") || secondNum.contains("-") || secondNum.contains("*") || secondNum.contains("/")){
            throw new IllegalArgumentException("введено больше одного операнда");
        }

        //Roman
        num1 = romanToNumber(firstNum);
        num2 = romanToNumber(secondNum);
        if ((num1 < 0 && num2 > 0) || (num1 > 0 && num2 < 0)) {
            throw new IllegalArgumentException("Введены несовместимые типы чисел");
        }
        if (num1 == -1 && num2 == -1) {
            num1 = Integer.parseInt(firstNum);
            num2 = Integer.parseInt(secondNum);
            res = calculated(num1, num2, op);
            result = String.valueOf(res);
            System.out.println(firstNum + " " + op + " " + secondNum + " " + " = " + res);
        } else {
            //Arabian
            res = calculated(num1, num2, op);
            if (res < 1) {
                throw new IllegalArgumentException("Результат должен быть выше нуля");
            }
            String resultRoman = convertArabToRoman(res);
            result = String.valueOf(res);
            System.out.println(firstNum + " " + op + " " + secondNum + " " + " = " + resultRoman);
        }
        return result;
    }

    static String convertArabToRoman(int arab_result) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII",
                "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV",
                "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII",
                "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return roman[arab_result];
    }

    static int romanToNumber(String roman) {
        try {
            switch (roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Неверный формат данных");
        }
        return -1;
    }

    static int calculated(int num1, int num2, char op) {
        int res = 0;
        if ((num1 <= 0 || num1 > 10) || (num2 <= 0 || num2 > 10)) {
            throw new IllegalArgumentException("Введено неверное число");
        } else {
            switch (op) {
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num1 - num2;
                    break;
                case '*':
                    res = num1 * num2;
                    break;
                case '/':
                    try {
                        res = num1 / num2;
                    } catch (ArithmeticException e) {
                        System.out.println("You have some exception : " + e);
                        break;
                    }
                    break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        calc("1+2+3");
    }
}

