package SDE;

public class Euler extends SDESolver {

    public Euler(int N, double x0, SDE sde) {
        super(N, x0, sde);
    }

    public Euler(int N) {
        super(N);
    }

    public static double getEulerForT(double y, double dt, double dw, SDE sde) {
        return y + sde.b(y) * dt + sde.a(y) * dw;
    }

    @Override
    protected double getNumbericalSolutionForT(double y, int i) {
        return  getEulerForT(y, dt, w[i]-w[i-1], sde);
    }

    @Override
    protected String getName() {
        return "SDE.Euler";
    }
}
