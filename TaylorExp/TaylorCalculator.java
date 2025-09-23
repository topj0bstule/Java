public class TaylorCalculator {
    private double epsilon;
    private double x;

    public TaylorCalculator(double epsilon, double x) {
        this.epsilon = epsilon;
        this.x = x;
    }

    public double calculateTaylorExp() {
        double term = 1.0;
        double sum = term;
        int n = 1;

        while (Math.abs(term) >= epsilon) {
            term *= x / n;
            sum += term;
            n++;
        }
        return sum;
    }
}
