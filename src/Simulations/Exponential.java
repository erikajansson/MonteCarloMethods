package Simulations;

import java.util.Scanner;

public class Exponential extends Simulation {

    double p; // The probability that one event occurs in a time interval

    public Exponential(int numberOfIterations) {
        super(numberOfIterations);

        System.out.println("p=");
        Scanner scanner = new Scanner(System.in);
        p = scanner.nextDouble();
    }

    @Override
    protected double simulateRandomNumber() {
        return -Math.log(random.nextDouble()) * 1/p;
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
        return new double[numberOfIntervals];
    }

    @Override
    protected String getName() {
        return "Exponential";
    }
}
