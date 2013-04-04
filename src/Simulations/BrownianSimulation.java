package Simulations;

import PathGeneration.Brownian;
import org.apache.commons.math3.distribution.NormalDistribution;

public class BrownianSimulation extends PathSimulation {

    public BrownianSimulation(int numberOfIterations) {
        super(new Brownian(1000), numberOfIterations);
    }

    @Override
    protected String getName() {
        return "Brownian Simulation";
    }
}
