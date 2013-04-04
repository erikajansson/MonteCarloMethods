package SDE;

import Tools.NamedSimulation;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.util.Scanner;

abstract public class SDEPlotter extends NamedSimulation {


    SDESolver sdeSolver;

    public SDEPlotter(int N, SDESolver sdeSolver) {
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

        Plot2DPanel plot = new Plot2DPanel();
        SDE sde;
        System.out.println("SDE 1 or 2?");
        int oneOrTwo = scanner.nextInt();
        if (oneOrTwo == 1) {
            sde = new SDE1();
        } else {
            sde = new SDE2();
        }
        for (int i = 2; i < N; ++i) {
            int numberOfIterations = (int)Math.pow(2,i);
            sdeSolver.reset(numberOfIterations, x0, sde);
            double [] t = sdeSolver.getTimes();
            double [] a =  sdeSolver.getAnalyticalSolution();
            plot.addLinePlot("Analytical, N = " + numberOfIterations, t, a);
            double [] n =  sdeSolver.getNumericalSolution();
            plot.addLinePlot("Numerical, N = " + numberOfIterations, t, n);
        }

        JFrame frame = new JFrame(getName());
        frame.setContentPane(plot);
        frame.setVisible(true);
        System.out.println("Finished plotting for " + N + " iterations.");
    }

}
