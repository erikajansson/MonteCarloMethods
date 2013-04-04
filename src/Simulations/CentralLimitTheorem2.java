package Simulations;

import org.apache.commons.math3.distribution.NormalDistribution;

public class CentralLimitTheorem2 extends Simulation {

    Simulation simulation;

    public CentralLimitTheorem2(Simulation simulation) {
        super(100000);
        this.simulation = simulation;
    }

    @Override
    protected double simulateRandomNumber() {
        double mean = simulation.simulate();
        double sd = simulation.calculateSD();
        return Math.sqrt(simulation.N)*(mean - simulation.getTheoreticalMean())/sd;
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

        NormalDistribution normalDistribution = new NormalDistribution(0, 1);

        for (int i = 0; i < numberOfIntervals; ++i) {
            probabilities[i] = normalDistribution.probability(min + i*intervalSize, min + (i+1)*intervalSize);
        }

        return probabilities;
    }

    @Override
    protected String getName() {
        return "Central Limit Theorem 2";
    }
}
