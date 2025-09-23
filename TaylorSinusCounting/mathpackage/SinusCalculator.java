package mathpackage;

public class SinusCalculator {
    private double epsilon;
    private double x;

    public SinusCalculator(double epsilon, double x) {
        this.epsilon = epsilon;
        this.x = x;
    }

    public double calculateTaylorSinus() {
        x = x % (2 * Math.PI);
        double term = x;
        double sum = term;
        int n = 1;

        while (Math.abs(term) >= epsilon) {
            term *= ((-1) * x * x) / ((2 * n) * (2 * n + 1));
            sum += term;
            n++;
        }
        return sum;
    }
}