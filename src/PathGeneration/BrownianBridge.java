package PathGeneration;

import org.apache.commons.math3.distribution.NormalDistribution;

public class BrownianBridge extends PathGenerator {

    NormalDistribution normalDistribution = new NormalDistribution();

    public BrownianBridge() {
        super(1000);
    }

    double [] path;
    int leftIndex = 0;
    int bridgeIndex;
    int rightIndex;
    boolean [] pathSet;

    private double middleValue(int leftIndex, int bridgeIndex, int rightIndex) {
        return  path[leftIndex]*(rightIndex - bridgeIndex)/(rightIndex+1-leftIndex) +
                path[rightIndex]*(bridgeIndex+1-leftIndex)/(rightIndex+1-leftIndex) +
                Math.sqrt((bridgeIndex+1-leftIndex)*(rightIndex-bridgeIndex)/(rightIndex+1-leftIndex))*normalDistribution.sample()/2;
    }

    private boolean doNextBridge() {
        boolean startedAtZero = leftIndex == 0;
        while (pathSet[leftIndex]) {
            ++leftIndex;
            if (leftIndex >= N) {
                // Finished! Start over!!
                if (startedAtZero) {
                    return false;
                }
                leftIndex = 0;
                startedAtZero = true;
            }
        }
        rightIndex = leftIndex;
        --leftIndex;
        while (!pathSet[rightIndex]) {
            ++rightIndex;
        }
        bridgeIndex = (rightIndex - leftIndex)/2+leftIndex;
        path[bridgeIndex] = middleValue(leftIndex,bridgeIndex, rightIndex);
        pathSet[bridgeIndex] = true;
        leftIndex = rightIndex;
        return true;
    }

    @Override
    protected double[] simulatePath(int k) {

        path = new double[N];
        pathSet = new boolean[N];

        path[0] = 0;
        pathSet[0] = true;
        path[N-1] = normalDistribution.sample();
        pathSet[N-1] = true;

        leftIndex = 0;
        while (doNextBridge()) {
        }

        return path;
    }

    @Override
    protected String getName() {
        return "PathGeneration.Brownian Bridge";
    }
}
