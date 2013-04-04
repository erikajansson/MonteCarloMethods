package Integration;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.RombergIntegrator;

public class Integration {

    private class Function implements UnivariateFunction {

        @Override
        public double value(double x) {
            return Math.min(x, 1-x);
        }
    }

    private class SquareFunction implements UnivariateFunction {

        @Override
        public double value(double x) {
            return Math.pow(Math.min(x, 1 - x), 2);
        }
    }

    public void run() {
        RombergIntegrator rombergIntegrator = new RombergIntegrator();
        double result = rombergIntegrator.integrate(RombergIntegrator.DEFAULT_MAX_ITERATIONS_COUNT, new Function(), 0, 1);

        System.out.println("Integrated: " + result);

        double sq = rombergIntegrator.integrate(RombergIntegrator.DEFAULT_MAX_ITERATIONS_COUNT, new SquareFunction(), 0, 1);

        double var = sq - Math.pow(result,2);

        System.out.println("Variance: " + var);

    }

}
