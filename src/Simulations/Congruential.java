package Simulations;


import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;

public class Congruential extends Simulation {

    int a = (int)Math.pow(7,5);
    int M = (int)Math.pow(2,31)-1;
    int c = 0;
    int lastGeneratedInteger = random.nextInt(M);

    public Congruential(int N) {
        super(N);
    }

    @Override
    public void runSimulation() {
        runSimulationAndPrintResult();
        plotSamples();
        autoCorrelation();
    }

    @Override
    protected double simulateRandomNumber() {
        int generatedInteger = (lastGeneratedInteger*a + c) % M;
        lastGeneratedInteger = generatedInteger;
        return (double)generatedInteger / M;
    }

    @Override
    protected double getTheoreticalMean() {
        return 0;
    }

    @Override
    protected double getTheoreticalSD() {
        return 0;
    }

    @Override
    protected double[] getProbabilityPerIntervals(int numberOfIntervals, double min, double intervalSize) {
        return new double[numberOfIntervals];
    }

    @Override
    protected String getName() {
        return "Congruential";
    }
}
