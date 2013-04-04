package PathGeneration;

import PathGeneration.IncrementalPathGenerator;

import java.util.Random;

public class RandomWalk extends IncrementalPathGenerator {

    public RandomWalk(int N) {
        super(N);
    }

    private Random random = new Random();
    private double sqrdt = Math.sqrt(1.0/N);

    @Override
    protected double simulatePathIncrement(int k) {
        if (random.nextBoolean()) {
            return -sqrdt;
        } else {
            return sqrdt;
        }
    }

    @Override
    protected String getName() {
        return "Random Walk";
    }
}
