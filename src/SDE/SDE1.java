package SDE;

public class SDE1 implements SDE {

    // a * dw
    @Override
    public double a(double xt) {
        return 2 * Math.sqrt(Math.abs(xt));
    }

    // ap * dw
    @Override
    public double ap(double xt) {
        return 1/ Math.sqrt(Math.abs(xt));
    }

    // b * dt
    @Override
    public double b(double xt) {
        return 1;
    }

    @Override
    public double analyticalSolution(double w, double x0) {
        return Math.pow(w + Math.sqrt(x0),2);
    }
}
