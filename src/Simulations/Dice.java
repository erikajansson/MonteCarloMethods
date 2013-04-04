package Simulations;

import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.util.Random;

public class Dice extends Simulation {

    public Dice(int numberOfIterations) {
        super(numberOfIterations);
    }

    @Override
    protected double simulateRandomNumber() {
        return (double)random.nextInt(6) + 1.0;
    }

    @Override
    protected double getTheoreticalMean() {
        return 3.5;
    }

    @Override
    protected double getTheoreticalSD() {
        return Math.sqrt(35/12);
    }

    @Override
    protected double[] getProbabilityPerIntervals(int numberOfIntervals, double min, double intervalSize) {
        return new double[] {1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6};
    }

    @Override
    protected String getName() {
        return "Dice";
    }
}
