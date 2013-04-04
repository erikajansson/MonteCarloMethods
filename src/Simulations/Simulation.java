package Simulations;

import Tools.Plotter;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;
import weka.estimators.KernelEstimator;

import java.util.Random;

public abstract class Simulation extends Plotter {

    public Simulation(int N) {
        super(N);
        simulations = new double[N];
    }

    protected Random random = new Random();

    private double sum = 0;
    private double[] simulations;
    public double mean = 0;

    protected abstract double simulateRandomNumber();

    private double autoCovariance(int h, double mean) {
        double sum = 0;
        for (int i = 0; i < simulations.length - h; ++i) {
            sum += (simulations[i] - mean)*(simulations[i+h] - mean);
        }
        return sum / simulations.length;

    }

    protected void autoCorrelation()  {
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(simulations);
        double mean = descriptiveStatistics.getMean();
        double variance = descriptiveStatistics.getVariance();
        int numberOfAutocorrelations = simulations.length/2;
        double[] autoCorrelations = new double[numberOfAutocorrelations];
        for (int i = 1; i <= numberOfAutocorrelations; ++i) {
            autoCorrelations[i-1] = autoCovariance(i, mean) / variance;
        }

        plot("Auto Correlations", autoCorrelations);
    }

    public void plotDistribution(int numberOfIntervals) {
        double max = simulations[0];
        double min = simulations[0];
        for (int i = 1; i < N; ++i) {
            if (max < simulations[i]) {
                max = simulations[i];
            } else if (min > simulations[i]) {
                min = simulations[i];
            }
        }

        double intervalSize = (max - min) / (numberOfIntervals - 1);

        double [] samplesPerInterval = new double[numberOfIntervals];
        double [] probabilityPerInterval = getProbabilityPerIntervals(numberOfIntervals, min, intervalSize);
        double [] scaledProbabilityPerInterval = new double[numberOfIntervals];
        double [] intervals = new double[numberOfIntervals];
        for (int i = 0; i < numberOfIntervals; ++i) {
            scaledProbabilityPerInterval[i] = probabilityPerInterval[i]* N;
            intervals[i] = min + i*intervalSize;
        }
        System.out.println("min: " + min + " max:" + max  + "size: " + intervalSize);
        for (int i = 0; i < N; ++i) {

            int interval = (int)Math.floor((simulations[i] - min)/ intervalSize);

            if (interval >= 0 && interval < numberOfIntervals) {
                samplesPerInterval[interval] += 1.0;
            } else {
                System.out.println("bad interval: " + interval + "s: " + simulations[i]);
            }
        }

        plot("Distribution", samplesPerInterval, "Probabilities", scaledProbabilityPerInterval, intervals);
        plot("Probabilities", scaledProbabilityPerInterval, intervals);
        KernelEstimator kernelEstimator = new KernelEstimator(intervalSize);
        for (int i = 0; i < N; ++i) {
            kernelEstimator.addValue(simulations[i], 1.0);
        }

        double [] kernelEstimation = new double[numberOfIntervals];

        for (int i = 0; i < numberOfIntervals; ++i) {
            kernelEstimation[i] = kernelEstimator.getProbability(intervals[i]);
        }

        plot("Kernel Estimation", kernelEstimation, "Probabilities", probabilityPerInterval, intervals);

    }

    public void plotAverageConverging() {
        double [] averages = new double[N /10];
        double [] numberOfSamples = new double[N /10];
        double sum = 0;
        for (int i = 0; i < N; i += 10) {
            for (int j = i; j < i + 10; ++j) {
                sum += simulations[i];
            }
            averages[i/10] = sum/i;
            numberOfSamples[i/10] = i;
        }

        plot("Average", averages, numberOfSamples);
    }

    public void plotLawOfLargeNumbers() {
        double [] averages = new double[N /10];
        double [] numberOfSamples = new double[N /10];
        
        double mean = getTheoreticalMean();
        double sd = getTheoreticalSD();
        
        double sum = 0;
        for (int i = 0; i < N; i += 10) {
            for (int j = i; j < i + 10; ++j) {
                sum += simulations[i];
            }
            averages[i/10] = (sum/i - mean)/sd;
            numberOfSamples[i/10] = i;
        }

        plot("Law of Large Numbers", averages, numberOfSamples);
    }

    protected abstract double getTheoreticalMean();

    protected abstract double getTheoreticalSD();

    public void plotSamples() {
        plot("Samples", simulations);
    }

    protected abstract double[] getProbabilityPerIntervals(int numberOfIntervals, double min, double intervalSize);

    @Override
    public void runSimulation() {
        runSimulationAndPrintResult();
        plotSamples();
    }

    public void runSimulationAndPrintResult() {

        double average = simulate();
        System.out.println("Average: " + average);

        double s = calculateSD();
        System.out.println("s: " + s);

    }

    public double simulate() {
        sum = 0;
        for (int i = 0; i < N; ++i) {
            simulations[i] = simulateRandomNumber();
            sum += simulations[i];
        }

        mean = sum / N;

        return mean;
    }

    public double calculateSD() {
        double sum = 0;
        for (int i = 0; i < N; ++i) {
            sum += Math.pow(simulations[i] - mean, 2);
        }
        return Math.sqrt(sum / (N - 1));
    }

}
