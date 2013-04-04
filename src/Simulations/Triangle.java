package Simulations;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Triangle extends Simulation {

    public Triangle(int N) {
        super(N);
    }

    @Override
    public void runSimulation() {
        super.runSimulation();
        plotDistribution(N);
    }

    @Override
    protected double simulateRandomNumber() {
        return (random.nextDouble() + random.nextDouble())/2.0;
    }

    @Override
    protected double getTheoreticalMean() {
        throw new NotImplementedException();
    }

    @Override
    protected double getTheoreticalSD() {
        throw new NotImplementedException();
    }

    private double cumulativeProbability(double x1, double x2) {

        if (x1 > x2) {
            throw new RuntimeException("x1 must be less than or equal to x2");
        }
        if (x2 < 0) {
            return 0;
        }
        if (x1 > 1) {
            return 0;
        }
        double probability = 0;
        if (x1 < 0.5) {
            double a = Math.max(x1,0.0);
            double b = Math.min(x2,0.5);
            probability += 2*Math.pow(b,2) - 2*Math.pow(a,2);
        }
        if (x2 > 0.5) {
            double a = Math.max(x1, 0.5);
            double b = Math.min(x2, 1.0);
            probability += 4*b - 2*Math.pow(b,2) -4*a + 2*Math.pow(a,2);
        }
        return probability;
    }

    @Override
    protected double[] getProbabilityPerIntervals(int numberOfIntervals, double min, double intervalSize) {
        double [] probabilities = new double[numberOfIntervals];
        for (int i = 0; i < numberOfIntervals; ++i) {
            probabilities[i] = cumulativeProbability(min + i*intervalSize, min + (i+1)*intervalSize);
        }
        return probabilities;
    }
}
