package PathGeneration;

public class IncrementalMatrix extends MatrixPathGenerator {
    @Override
    double getMatrixValue(int i, int j) {
        if (i >= j) {
            return Math.sqrt(1.0/N);
        } else {
            return 0;
        }
    }

    @Override
    protected String getName() {
        return "Incremental Matrix";
    }
}
