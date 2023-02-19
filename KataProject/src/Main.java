import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int num1, num2, result;
    static char operation;
    static char[] operations = {'+', '-', '*', '/'};
    static int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Используйте цифры от 1 до 10 или от I до X (рим. цифры) и логические выражения '+', '-', '*', '/'."
                + "\n" + "Введите выражение в одну строку: ");
        String str = scanner.nextLine().toUpperCase();
        char[] chars = str.toCharArray();

        for (int i = 1; i < chars.length - 1; i++) {
            switch (chars[i]) {
                case '+' -> operation = '+';
                case '-' -> operation = '-';
                case '*' -> operation = '*';
                case '/' -> operation = '/';
            }
        }

        String str_operation = "\\" + operation;
        String[] strings = str.split(str_operation);
        String str1 = " ";
        String str2 = " ";
        try {
            str1 = strings[0].trim();
            str2 = strings[1].trim();
        } catch (Exception e) {
            throw new ArrayIndexOutOfBoundsException("Вы ввели неправильное выражение. Правильное одно из " + Arrays.toString(operations));
        }

        num1 = romanToArab(str1);
        num2 = romanToArab(str2);
        if (num1 < 1 || num2 < 1) {
            result = 0;
        } else {
            result = toCalc(num1, num2, operation);
            String resultRom = arabicToRoman(result);
            System.out.println(str1 + " " + operation  + " " + str2 + " = " + resultRom);
        }

        num1 = parse(str1);
        num2 = parse(str2);
        if (num1 == 0) {
            result = 0;
        } else {
            result = toCalc(num1, num2, operation);
            System.out.println(str1 + " " + operation + " " + str2 + " = " + result);
        }
    }

    private static int parse(String str) {
        int dig = Integer.parseInt(str);
        if (dig > 10) {
            System.out.println("Используйте цифры от 1 до 10 или от I до X (рим. цифры) и логические выражения '+', '-', '*', '/'");
            dig = 0;
        }
        return dig;
    }

    private  static int toCalc(int num1, int num2, char operation) {
        int result;
        if (operation == '+') {
            result = num1 + num2;
        } else if (operation == '-') {
            result = num1 - num2;
        } else if (operation == '*') {
            result = num1 * num2;
        } else if (operation == '/' && num1 > 0 && num2 > 0) {
            result = num1 / num2;
        } else {
            result = 0;
        }
        return result;
    }

    private static int romanToArab(String str) {
        try {
            switch (str) {
                case "I" -> {
                    return 1;
                }
                case "II" -> {
                    return 2;
                }
                case "III" -> {
                    return 3;
                }
                case "IV" -> {
                    return 4;
                }
                case "V" -> {
                    return 5;
                }
                case "VI" -> {
                    return 6;
                }
                case "VII" -> {
                    return 7;
                }
                case "VIII" -> {
                    return 8;
                }
                case "VIV" -> {
                    return 9;
                }
                case "X" -> {
                    return 10;
                }
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException ("Неверно введенные данные");
        }
        return -1;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 100)) {
            throw new IllegalArgumentException(number + " Неверно введенные данные");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}