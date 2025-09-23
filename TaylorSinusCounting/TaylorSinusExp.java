import mathpackage.SinusCalculator;
import java.util.Scanner;

public class TaylorSinusExp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите степень числа 10: ");
        int k = scanner.nextInt();
        double epsilon = Math.pow(10, -k);

        System.out.print("Введите число x (для подсчёта ряда Тейлора): ");
        double x = scanner.nextDouble();

        double standardResult = Math.sin(x);
        System.out.printf("Результат стандартной функции: %.7f%n", standardResult);

        SinusCalculator calculator = new SinusCalculator(epsilon, x);
        double taylorResult = calculator.calculateTaylorSinus();

        System.out.printf("Результат нашей функции: %.7f%n", taylorResult);
        System.out.printf("Разность: %.7f%n", Math.abs(standardResult - taylorResult));
    }
}