package Simulations;

import java.util.Random;

public class Bernoulli extends Simulation {

    double  p = 0.4;

    public Bernoulli(int numberOfIterations) {
        super(numberOfIterations);
    }

    @Override
    protected double simulateRandomNumber() {
        if (random.nextDouble() < p) {
            return 1;
        } else {
           return 0;
        }
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
        probabilities[0] = p;
        probabilities[1] = 1 - p;
        return probabilities;
    }

    @Override
    protected String getName() {
        return "Bernoulli";
    }
}
