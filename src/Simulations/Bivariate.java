package Simulations;

import Tools.Plotter;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;

public class Bivariate extends Plotter {

    double [] means = new double[]{1.0,2.0};
    double [][] covariances = new double[][]{{1.0,1.0},{-1.0,16.0}};
    MultivariateNormalDistribution multivariateNormalDistribution = new MultivariateNormalDistribution(means, covariances);

    public Bivariate(int N) {
        super(N);
    }

    @Override
    public void runSimulation() {
        double [][] numbers = multivariateNormalDistribution.sample(N);

        double [] variate1 = new double[N];
        double [] variate2 = new double[N];
        double [] variateNumber = new double[N];

        for (int i = 0; i < N; ++i) {
            variate1[i] = numbers[i][0];
            variate2[i] = numbers[i][1];
            variateNumber[i] = (double)i;
        }

        plot("Bivariate 1", variate1, "2", variate2, variateNumber);
    }

    @Override
    protected String getName() {
        return "Bivariate";
    }
}
