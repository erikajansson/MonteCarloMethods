package Simulations;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.MathUtils;

import java.util.Random;

public class Binominal extends Simulation {


    int n = 5;
    double p = 0.4;

    public Binominal(int numberOfIterations) {
        super(numberOfIterations);
    }

    @Override
    protected double simulateRandomNumber() {
        int variate = 0;
        for (int j = 0; j < n; ++j) {
            if (random.nextDouble() < p) {
                ++variate;
            }
        }
        return variate;
    }

    @Override
    protected double getTheoreticalMean() {
        return 0;
    }

    @Override
    protected double getTheoreticalSD() {
        return 0;
    }

    @Override
    protected double[] getProbabilityPerIntervals(int numberOfIntervals, double min, double intervalSize) {
        double[] probabilities = new double[numberOfIntervals];
        for (int k = 0; k < numberOfIntervals; ++k) {
            probabilities[k] = ArithmeticUtils.factorial(n)*Math.pow(p,k)*Math.pow(1-p, n-k)/
                              (ArithmeticUtils.factorial(k)*ArithmeticUtils.factorial(n-k));
        }

        return probabilities;
    }

    @Override
    protected String getName() {
        return "Binominal";
    }
}
