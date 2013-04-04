package Simulations;

public class CustomDistribution extends Simulation {

    public CustomDistribution() {
        super(10000);
    }

    @Override
    protected double simulateRandomNumber() {
        double x = random.nextDouble();
        return Math.min(x, 1-x);
    }

    @Override
    protected double getTheoreticalMean() {
        return 0.25;
    }

    @Override
    protected double getTheoreticalSD() {
        return Math.sqrt(0.028333333333);
    }

    @Override
    protected double[] getProbabilityPerIntervals(int numberOfIntervals, double min, double intervalSize) {
        return new double[numberOfIntervals];
    }

    @Override
    protected String getName() {
        return "Custom distribution";
    }
}
