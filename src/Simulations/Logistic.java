package Simulations;

import java.util.Scanner;

public class Logistic extends Simulation {

    double lastGeneratedRandomNumber;
    double my;

    public Logistic(int N) {
        super(N);

        lastGeneratedRandomNumber = random.nextDouble();
        System.out.println("My=");
        Scanner scanner = new Scanner(System.in);
        my = scanner.nextDouble();
    }

    @Override
    protected double simulateRandomNumber() {
        double newRandomNumber = my*lastGeneratedRandomNumber*(1-lastGeneratedRandomNumber);
        lastGeneratedRandomNumber = newRandomNumber;
        return newRandomNumber;
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
    public void runSimulation() {
        runSimulationAndPrintResult();
        plotSamples();
        plotDistribution(N*2);
    }

    private double getA(double x) {
        return 2 / Math.PI * Math.asin(Math.sqrt(x));
    }

    @Override
    protected double[] getProbabilityPerIntervals(int numberOfIntervals, double min, double intervalSize) {
        double [] probabilities = new double[numberOfIntervals];
        for (int i = 0; i < numberOfIntervals; ++i) {
            double x1 = min + i*intervalSize;
            double a2 = getA(x1 + intervalSize);
            double a1 = getA(x1);
            probabilities[i] = a2 - a1;
        }
        return probabilities;
    }

    @Override
    protected String getName() {
        return "Logistic";
    }
}
