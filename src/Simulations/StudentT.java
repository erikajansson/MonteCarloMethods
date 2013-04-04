package Simulations;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.TDistribution;

public class StudentT extends Simulation {

    private NormalDistribution normalDistribution = new NormalDistribution();
    private TDistribution studentT = new TDistribution(2);

    public StudentT(int N) {
        super(N);
    }

    @Override
    protected double simulateRandomNumber() {
        return normalDistribution.sample() / Math.sqrt(Math.abs(normalDistribution.sample()*normalDistribution.sample()/2));
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

        for (int i = 0; i < numberOfIntervals; ++i) {
            probabilities[i] = studentT.probability(min + i*intervalSize, min + (i+1)*intervalSize);
        }

        return probabilities;
    }

    @Override
    protected String getName() {
        return "Student's t";
    }
}
