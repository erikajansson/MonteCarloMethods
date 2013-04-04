package Simulations;

import Tools.Plotter;

import java.util.ArrayList;
import java.util.List;

public class Poisson extends Plotter {

    private double p = 0.3;
    private double T = 150;

    public Poisson(int N) {
        super(N);
    }

    @Override
    public void runSimulation() {
        simulate();
    }

    public void simulate() {
        // The probability that this event has occurred k times over a time span

        // One path of the poisson process with intensity p for a time span.
        // plot time against outcome

        // the number of events in a given time interval

        // plot number of events from 0 to t as t increases.
        // from the Simulations.Exponential distribution we get how
        // long the time is until the next event

        Exponential exponential = new Exponential(N);

        double t = 0; // Current time.
        ArrayList<Double> times = new ArrayList<Double>();
        while (true)  {
            double timeUtilNextEvent = exponential.simulateRandomNumber();

            t += timeUtilNextEvent;
            if (t >= T) {
                break;
            }
            times.add(t);
        }

        double [] timesArray = new double[times.size() + 1];
        double [] numberOfEventsArray = new double[times.size() + 1];

        for (int i = 0; i < times.size(); ++i) {
            timesArray[i] = times.get(i);
            numberOfEventsArray[i] = i;
        }

        timesArray[times.size()] = T;
        numberOfEventsArray[times.size()] = times.size();

        plotLine("Number of event's after time t", numberOfEventsArray, timesArray);
    }

    @Override
    protected String getName() {
        return "Poisson";
    }
}
