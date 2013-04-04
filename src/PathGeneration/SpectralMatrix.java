package PathGeneration;

import PathGeneration.MatrixPathGenerator;

public class SpectralMatrix extends MatrixPathGenerator {

    double getW(int k) {
        return (k-0.5)*Math.PI;
    }

    double getSin(double t, int k) {
        return Math.sin(getW(k)*t)/(2.0*k)-1.0;
    }

    @Override
    double getMatrixValue(int i, int k) {
        double t = (double)i/N;
        double sum = 0;
        for (int l = 1; l <= N; ++l) {
            sum += Math.pow(getSin(t,l),2.0);
        }
        double v = Math.sqrt(t)*getSin(t,k)/Math.sqrt(sum);
        return v;
    }

    @Override
    protected String getName() {
        return "PathGeneration.Spectral Matrix";
    }
}
