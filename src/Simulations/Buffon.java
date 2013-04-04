package Simulations;

import Tools.NamedSimulation;

import java.util.Random;

public class Buffon extends NamedSimulation implements Runnable {

    public Buffon(int N) {
        super(N);
    }

    @Override
    public void runSimulation() {
        run();
    }

    public double estimatePi() {
        int runningSum = 0;
        for (int i = 0; i < N; ++i) {
            if (throwNeedle()) {
                ++runningSum;
            }
        }

        return (double)N / (double)runningSum;
    }

    private boolean throwNeedle() {
        Random random = new Random();
        double angle = random.nextDouble() * Math.PI / 2;
        double distance = random.nextDouble();
        return distance <= Math.sin(angle) / 2;
    }

    @Override
    public void run() {
        double pi = estimatePi();
        System.out.println("PI was estimated to " + pi);
    }

    @Override
    protected String getName() {
        return "Buffon";
    }
}
