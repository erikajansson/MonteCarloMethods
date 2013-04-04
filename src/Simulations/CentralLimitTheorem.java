package Simulations;

import org.apache.commons.math3.distribution.NormalDistribution;

public class CentralLimitTheorem extends Simulation {

    private Simulation simulation;

    public CentralLimitTheorem(Simulation simulation) {
        super(1000);
        this.simulation = simulation;
    }

    @Override
    protected double simulateRandomNumber() {
        return simulation.simulate();
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
        double [] probabilities = new double[numberOfIntervals];

        double sd = calculateSD();
        System.out.println("sd: " + sd);

        NormalDistribution normalDistribution = new NormalDistribution(mean, sd);

        for (int i = 0; i < numberOfIntervals; ++i) {
            probabilities[i] = normalDistribution.probability(min + i*intervalSize, min + (i+1)*intervalSize);
        }

        return probabilities;
    }

    @Override
    protected String getName() {
        return "Central Limit Theorem";
    }
}
