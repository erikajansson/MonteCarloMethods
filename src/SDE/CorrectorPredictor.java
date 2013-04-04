package SDE;

public class CorrectorPredictor extends SDESolver {

    double alpha = 0.5;
    double n = 0.5;

    public CorrectorPredictor(int N, double x0, SDE sde) {
        super(N, x0, sde);
    }

    @Override
    protected double getNumbericalSolutionForT(double y, int i) {
        double dw = w[i]-w[i-1];
        double yp = Euler.getEulerForT(y, dt, dw, sde);
        double yn = y +
                    (alpha*ahat(yp)+(1-alpha)*ahat(y))*dt +
                    (n*sde.a(yp)+(1-n)*sde.a(y))* dw;
        return yn;
    }

    private double ahat(double y) {
        return sde.b(y) - n * sde.a(y) * sde.ap(y);
    }

    @Override
    protected String getName() {
        return "Corrector-Predictor";
    }
}
