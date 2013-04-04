package Simulations;

public class UniformFromLogistic extends Logistic {

    public UniformFromLogistic(int N) {
        super(N);
    }

    @Override
    protected double simulateRandomNumber() {
        return 2*Math.asin(Math.sqrt(super.simulateRandomNumber()))/Math.PI;
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
        double [] probabilities = new double[numberOfIntervals];
        for (int i = 0; i < numberOfIntervals; ++i) {
            probabilities[i] = intervalSize;
        }
        return probabilities;
    }

    @Override
    protected String getName() {
        return "UniformFromLogisitc";
    }
}
