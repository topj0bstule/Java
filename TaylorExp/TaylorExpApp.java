import java.util.Scanner;

public class TaylorExpApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите степень числа 10: ");
        int k = scanner.nextInt();
        double epsilon = Math.pow(10, -k);

        System.out.print("Введите число x (для подсчёта ряда Тейлора): ");
        double x = scanner.nextDouble();

        double standardResult = Math.exp(x);
        System.out.printf("Результат стандартной функции: %.7f%n", standardResult);

        TaylorCalculator calculator = new TaylorCalculator(epsilon, x);
        double taylorResult = calculator.calculateTaylorExp();

        System.out.printf("Результат нашей функции: %.7f%n", taylorResult);
        System.out.printf("Разность: %.7f%n", Math.abs(standardResult - taylorResult));
    }
}
