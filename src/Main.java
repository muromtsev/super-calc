import java.util.*;
import java.util.Scanner;

public class Main {

    static final List<String> romanNumerals = new ArrayList<>(Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII",
            "VIII", "IX", "X"));

    public static String calc(String input) throws Exception {
        String[] exps = input.split(" ");
        for(String elem: exps) {
            System.out.println(elem);
        }

        String result = inputCheck(exps);

        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== Калькулятор ===");
        System.out.println("[INFO] Введите строку с арифметическим выражением между двумя числами");
        System.out.println("[INFO] В формате 2 * 2 или римскими II * V");

        Scanner scan = new Scanner(System.in);
        String data = scan.nextLine();
        calc(data);
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
}