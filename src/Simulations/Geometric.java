package Simulations;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Geometric extends Simulation {

    double  p = 0.4;

    public Geometric(int numberOfIterations) {
        super(numberOfIterations);
    }

    @Override
    protected double simulateRandomNumber() {
        double variate = 0;
        while (true) {
            variate += 1.0;
            if (random.nextDouble() < p) {
                break;
            }
        }

        return variate;
    }

    @Override
    protected double getTheoreticalMean() {
        throw new NotImplementedException();
    }

    @Override
    protected double getTheoreticalSD() {
        throw new NotImplementedException();
    }

    @Override
    protected double[] getProbabilityPerIntervals(int numberOfIntervals, double min, double intervalSize) {
        return new double[numberOfIntervals];
    }

    @Override
    protected String getName() {
        return "Geometric";
    }
}
