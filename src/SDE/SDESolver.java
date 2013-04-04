package SDE;

import PathGeneration.Brownian;
import PathGeneration.PathGenerator;
import Tools.PathPlotter;
import Tools.Plotter;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

abstract public class SDESolver extends PathPlotter {

    double [] w;
    protected SDE sde;

    public SDESolver(int N, double x0, SDE sde) {
        super(N);
        dt = 1.0/N;
        this.x0 = x0;
        this.sde = sde;
        normalDistribution = new NormalDistribution();
        brownian = new Brownian(N);
        w = brownian.simulatePath(0);
    }

    public SDESolver(int N) {
        super(N);
        dt = 1.0/N;
        sde = new SDE1();
        System.out.println("X0:");
        Scanner scanner = new Scanner(System.in);
        x0 = scanner.nextDouble();
        normalDistribution = new NormalDistribution();
        brownian = new Brownian(N);
        w = brownian.simulatePath(0);
    }

    public void reset(int N, double x0, SDE sde) {
        this.N = N;
        dt = 1.0/N;
        this.x0 = x0;
        this.sde = sde;
        normalDistribution = new NormalDistribution();
        brownian = new Brownian(N);
        w = brownian.simulatePath(0);
    }

    double dt;
    double x0;
    NormalDistribution normalDistribution;
    Brownian brownian;

    abstract protected double getNumbericalSolutionForT(double y, int i);

    public double [] getNumericalSolution() {
        double [] y = new double[N];
        y[0] = x0;
        for (int i = 1; i < N; ++i) {
            y[i] = getNumbericalSolutionForT(y[i - 1], i);
        }

        return y;
    }

    public double getNumericalSolutionAndReturnEndTime() {
        double [] s = getNumericalSolution();
        return s[s.length -1];
    }

    public double getAnalyticalSolutionAndReturnEndTime() {
        double [] s = getAnalyticalSolution();
        return s[s.length -1];
    }

    public double [] getAnalyticalSolution() {
        double [] y = new double[N];
        for (int i = 0; i < N; ++i) {
            y[i] =  sde.analyticalSolution(w[i], x0);
        }
        return y;
    }

    @Override
    protected int getNumberOfPaths() {
        return 2;
    }

    @Override
    protected double[] simulatePath(int i) {
        if (i < 1) {
            return getAnalyticalSolution();
        } else {
            return getNumericalSolution();
        }
    }

    @Override
    protected String getPathName(int i) {
        if (i < 1) {
            return "Analytical " + i;
        } else {
            return "SDE.SDESolver " + (i - 5);
        }
    }
}
