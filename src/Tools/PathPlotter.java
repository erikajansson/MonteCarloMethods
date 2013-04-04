package Tools;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;

abstract public class PathPlotter extends NamedSimulation {

    public PathPlotter(int N) {
        super(N);
    }

    abstract protected int getNumberOfPaths();
    abstract protected double[] simulatePath(int i);
    abstract protected String getPathName(int i);
    protected Color getPathColor(int i) {
       return null;
    }

    protected abstract String getName();

    public double simulatePathAndReturnEndTime() {
        double [] path = simulatePath(0);
        return path[path.length -1];
    }

    @Override
    public void runSimulation() {
        simulateAndPlotPaths();
    }

    public double [] getTimes() {
        double [] times = new double[N];
        for (int i = 0; i < N; ++i) {
            times[i] = (double)i/(N-1);
        }
        return times;
    }

    public void simulateAndPlotPaths() {

        double [] times = getTimes();

        Plot2DPanel plot = new Plot2DPanel();
        for (int i = 0; i < getNumberOfPaths(); ++i) {
            double [] path = simulatePath(i);
            if (getPathColor(i) == null) {
                plot.addLinePlot(getPathName(i), times, path);
            } else {
                plot.addLinePlot(getPathName(i), getPathColor(i), times, path);
            }
        }
        JFrame frame = new JFrame(getName());
        frame.setContentPane(plot);
        frame.setVisible(true);
        System.out.println("Finished plotting for " + N + " iterations.");
    }

}
