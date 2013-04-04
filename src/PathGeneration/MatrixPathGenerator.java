package PathGeneration;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

abstract public class MatrixPathGenerator extends PathGenerator {


    RealMatrix Am;

    MatrixPathGenerator() {
        super(1000);

        double [][] A = new double[N][N];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                A[i][j] = getMatrixValue(i+1,j+1);
               // System.out.println("ij:" + i + " " + j + " " + A[i][j]);
            }
        }

        Am = new Array2DRowRealMatrix(A);
    }

    NormalDistribution normalDistribution = new NormalDistribution();

    abstract double getMatrixValue(int i, int j);

    protected double[] simulatePath(int k) {
        double [] Z = new double[N];

        for (int i = 0; i < N; ++i) {
            Z[i] = normalDistribution.sample();
        }
        RealMatrix Zm = new Array2DRowRealMatrix(Z);

        RealMatrix Wm = Am.multiply(Zm);
        double [] W = Wm.getColumn(0);
        return W;
    }
}
