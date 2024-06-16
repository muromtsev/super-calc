import java.util.*;
import java.util.Scanner;

public class Main {

    static final List<String> romanNumerals = new ArrayList<>(Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII",
            "VIII", "IX", "X"));

    public static String calc(String input) throws Exception {
        String[] exps = input.split(" ");

        String result = inputCheck(exps);

        if (result.equals("Arabic")) {
            return arabicCalc(exps);
        }
        else {
            return romanCalc(exps);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== Калькулятор ===");
        System.out.println("[INFO] Введите строку с арифметическим выражением между двумя числами");
        System.out.println("[INFO] В формате 2 * 2 или римскими II * V");
        System.out.println("[INFO] Допустимые операции [ + - * / ]");
        System.out.println("[INFO] Вводимые числа доджны быть в диапазоне от 1 до 10 включительно");
        System.out.println("[INFO] либо римскими от I до X");
        System.out.println("Для выхода введите: q");

        Scanner scan = new Scanner(System.in);
        String data;

        while(!(data = scan.nextLine()).equals("q")) {
            System.out.println(calc(data));
        }
        System.out.println("Завершение программы");

    }

    private static String inputCheck(String[] data) throws Exception {

        String operations = "+-/*";
        int operand1 = 0;
        int operand2 = 0;

        if (data.length != 3) {
            throw new Exception("[ERROR] Неверный ввод. ");
        }

        if (!operations.contains(data[1])) {
            throw new Exception("[ERROR] Неверный знак операции, допустимы [ + - / * ]");
        }

        try {
            operand1 = Integer.parseInt(data[0]);
            operand2 = Integer.parseInt(data[2]);
        } catch (Exception e) {
            if (!romanNumerals.contains(data[0]) || !romanNumerals.contains(data[2])) {
                throw new Exception("[ERROR] Неверный ввод. Числа должны быть либо арабские либо римские");
            } else {
                return "Roman";
            }
        }

        if ((operand1 < 1 || operand1 > 10) || (operand2 < 1 || operand2 > 10)) {
            throw new Exception("[ERROR] Необходимо ввести цифры от 1 до 10");
        }

        return "Arabic";
    }

    private static String arabicCalc(String[] data) {
        int number1 = Integer.parseInt(data[0]);
        int number2 = Integer.parseInt(data[2]);

        switch(data[1]) {
            case("+"):
                return String.valueOf(number1 + number2);
            case("-"):
                return String.valueOf(number1 - number2);
            case("*"):
                return String.valueOf(number1 * number2);
            case("/"):
                return String.valueOf(number1 / number2);
            default:
                return null;
        }
    }

    private static String romanCalc(String[] data) throws Exception {
        int romanIndex1 = romanNumerals.indexOf(data[0]) + 1;
        int romanIndex2 = romanNumerals.indexOf(data[2]) + 1;
        int result = 0;

        switch(data[1]) {
            case("+"):
                result = romanIndex1 + romanIndex2;
                break;
            case("-"):
                result = romanIndex1 - romanIndex2;
                break;
            case("*"):
                result = romanIndex1 * romanIndex2;
                break;
            case("/"):
                result = romanIndex1 / romanIndex2;
                break;
        }
        if (result < 1) {
            throw new Exception("[ERROR] Недопустимый ответ");
        }
        else {
            return conversionToArabic(result);
        }

    }

    private static String conversionToArabic(int arabic) {
        String[] oneHundredRomanNumerals = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV",
                "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
                "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
                "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
                "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV",
                "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII",
                "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII",
                "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return oneHundredRomanNumerals[arabic];
    }
}