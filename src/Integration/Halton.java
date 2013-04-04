package Integration;


import Tools.NamedSimulation;

public class Halton {

    int dimensionality = 2;
    int [] primeNumbers = new int[] {2, 3};
    int sequenceCounter = 0;

    double [] nextUniformVector() {
        ++sequenceCounter;
        double [] sequenceVector = new double[dimensionality];
        for (int i = 0; i < dimensionality; ++i) {
            long k = sequenceCounter;
            long base = primeNumbers[i];
            double f = 1.0;
            double h = 0.0;
            while (k > 0) {
                f /= base;
                h += (k%base)*f;
                k /= base;
            }
            sequenceVector[i] = h;
        }

        return sequenceVector;
    }
}
