package SDE;

import Tools.Plotter;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.Scanner;

abstract public class SDETester extends Plotter {

    SDESolver sdeSolver;

    public SDETester(int N, SDESolver sdeSolver) {
        super(N);
        this.sdeSolver = sdeSolver;
    }

    @Override
    public void runSimulation() {
        simulateAndPlotPaths();
    }

    public void simulateAndPlotPaths() {

        System.out.println("X0:");
        Scanner scanner = new Scanner(System.in);
        double x0 = scanner.nextDouble();
        double amean = x0;
        double  [] logn = new double[N];
        double [] loge = new double[N];

        SimpleRegression simpleRegression = new SimpleRegression();
        SDE sde;
        System.out.println("SDE 1 or 2?");
        int oneOrTwo = scanner.nextInt();
        if (oneOrTwo == 1) {
            sde = new SDE1();
        } else {
            sde = new SDE2();
        }

        for (int i = 2; i < N; ++i) {
            int iterations = (int)Math.pow(2,i);
            logn[i] = - Math.log(iterations);

            sdeSolver.reset(iterations, x0, sde);
            double a = sdeSolver.getAnalyticalSolutionAndReturnEndTime();
            double n = sdeSolver.getNumericalSolutionAndReturnEndTime();
            double err = a - n;
            if (err != 0.0) {
                loge[i] = Math.log(Math.abs(a - n));
                simpleRegression.addData(logn[i], loge[i]);
            }
        }

        double slope = simpleRegression.getSlope();
        double intercept = simpleRegression.getIntercept();

        System.out.println("Slope: " + slope + " Intercept: " + intercept);


        plot("Error", loge, logn);

    }

}
