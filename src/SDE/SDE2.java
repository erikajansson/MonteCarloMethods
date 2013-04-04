package SDE;

public class SDE2 implements SDE {

    @Override
    public double a(double xt) {
        return 2;
    }

    @Override
    public double ap(double xt) {
        return 0;
    }

    @Override
    public double b(double xt) {
        return 0;

    }

    @Override
    public double analyticalSolution(double w, double x0) {
        double u0 = Math.pow(x0,2) / 4;
        return 2 * (w + Math.sqrt(u0));
    }
}
