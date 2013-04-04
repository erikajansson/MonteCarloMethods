package Tools;

import org.math.plot.Plot2DPanel;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Erika
 * Date: 2/16/13
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
abstract public class Plotter extends NamedSimulation {

    public Plotter(int N) {
        super(N);
    }

    protected void plot(String name1, double[] data1) {
        Plot2DPanel plot = new Plot2DPanel();
        plot.addScatterPlot(name1, data1);
        JFrame frame = new JFrame(name1);
        frame.setContentPane(plot);
        frame.setVisible(true);
        System.out.println("Finished plotting.");
    }

    protected void plot(String name1, double[] data1, double [] data3) {
        Plot2DPanel plot = new Plot2DPanel();
        plot.addScatterPlot(name1, data3, data1);
        JFrame frame = new JFrame(name1);
        frame.setContentPane(plot);
        frame.setVisible(true);
        System.out.println("Finished plotting.");
    }

    protected void plotLine(String name1, double[] data1, double [] data3) {
        Plot2DPanel plot = new Plot2DPanel();
        plot.addLinePlot(name1, data3, data1);
        JFrame frame = new JFrame(name1);
        frame.setContentPane(plot);
        frame.setVisible(true);
        System.out.println("Finished plotting.");
    }

    protected void plot(String name1, double[] data1, String name2, double[] data2,double [] data3) {
        Plot2DPanel plot = new Plot2DPanel();
        plot.addScatterPlot(name1, data3, data1);
        plot.addScatterPlot(name2, data3, data2);
        JFrame frame = new JFrame(name1);
        frame.setContentPane(plot);
        frame.setVisible(true);
        System.out.println("Finished plotting.");
    }

    protected void plot(String name1, double[] data1, String name2, double[] data2) {
        Plot2DPanel plot = new Plot2DPanel();
        plot.addScatterPlot(name1, data1);
        plot.addScatterPlot(name2, data2);
        JFrame frame = new JFrame(name1);
        frame.setContentPane(plot);
        frame.setVisible(true);
        System.out.println("Finished plotting.");
    }

}
