package SDE;

public class Milstein extends SDESolver {

    public Milstein(int N) {
        super(N);
    }

    public Milstein(int N, double x0, SDE sde) {
        super(N, x0, sde);
    }

    // ad * dw
    private double ad(double x) {
        return 1/Math.sqrt(x);
    }

    @Override
    protected double getNumbericalSolutionForT(double y, int i) {
        double dw = w[i]-w[i-1];
        return y + sde.b(y)*dt+sde.a(y)*dw+0.5*sde.a(y)*ad(y)*(Math.pow(dw,2)-dt);
    }

    @Override
    protected String getName() {
        return "Milstein";
    }
}
