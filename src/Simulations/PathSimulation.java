package Simulations;

import PathGeneration.Brownian;
import Tools.NamedSimulation;
import org.apache.commons.math3.distribution.NormalDistribution;

public class PathSimulation extends Simulation {

    Brownian brownian = new Brownian(1000);

    public PathSimulation(NamedSimulation namedSimulation, int N) {
        super(N);
        this.namedSimulation = namedSimulation;
    }

    NamedSimulation namedSimulation;

    public PathSimulation(int numberOfIterations) {
        super(numberOfIterations);
    }

    @Override
    protected double simulateRandomNumber() {
        return brownian.simulatePathAndReturnEndTime();
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
            probabilities[i] = normalDistribution.probability((min + i*intervalSize), (min + (i+1)*intervalSize));
        }

        return probabilities;
    }

    @Override
    protected String getName() {
        return "Path Simulation";
    }
}
