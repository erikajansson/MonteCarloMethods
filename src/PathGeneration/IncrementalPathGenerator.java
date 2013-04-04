package PathGeneration;

abstract public class IncrementalPathGenerator extends PathGenerator {

    IncrementalPathGenerator(int N) {
        super(N);
    }

    @Override
    public double[] simulatePath(int k) {
        double [] path = new double[N];
        path[0] = 0;
        for (int i = 1; i < N; ++i) {
            path[i] = path[i-1] + simulatePathIncrement(i);
        }
        return path;
    }

    abstract protected double simulatePathIncrement(int k);

}
