package PathGeneration;

import PathGeneration.PathGenerator;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.Scanner;

public class Spectral extends PathGenerator {

    private int k;

    public Spectral(int N) {
        super(N);
        System.out.println("K:");
        Scanner scanner = new Scanner(System.in);
        k = scanner.nextInt();
    }

    NormalDistribution normalDistribution = new NormalDistribution();

    private double [] z;

    @Override
    protected double[] simulatePath(int k) {
        double [] path = new double[N];
        path[0] = 0;
        z = new double[this.k];
        for (int i = 0; i < this.k; ++i) {
            z[i] = normalDistribution.sample();
        }
        for (int i = 1; i < N; ++i) {
            path[i] = getW(i);
        }
        return path;
    }

    protected double getW(int i) {
        double t = (double)i/N;
        double sum = 0;
        for (int k = 0; k < this.k; ++k) {
            double w = (k - 0.5)*Math.PI;
            sum += z[k] * Math.sin(w*t)/w;
        }
        return Math.sqrt(2.0)*sum;
    }

    @Override
    protected String getName() {
        return "Spectral";
    }
}
