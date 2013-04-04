package PathGeneration;

import org.apache.commons.math3.distribution.NormalDistribution;


public class Brownian extends IncrementalPathGenerator {

    public Brownian(int N) {
        super(N);
        dt = 1.0/N;
    }

    private double dt;
    NormalDistribution normalDistribution = new NormalDistribution();

    @Override
    protected double simulatePathIncrement(int k) {
        return Math.sqrt(dt)*normalDistribution.sample();
    }

    @Override
    protected String getName() {
        return "Incremental";
    }
}
