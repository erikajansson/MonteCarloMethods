package SDE;

public interface SDE {
    public double a(double xt);  // dw
    public double ap(double xt); // dw
    public double b(double xt);  // dt
    public double analyticalSolution(double w, double x0);
}
