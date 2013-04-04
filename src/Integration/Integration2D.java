package Integration;

import Tools.NamedSimulation;
import Tools.Plotter;
import org.apache.commons.math3.analysis.MultivariateFunction;

import java.util.Random;

// http://math.fullerton.edu/mathews/n2003/simpsonsrule2dmod.html

public class Integration2D extends Plotter {

    public Integration2D(int N) {
        super(N);
    }

    @Override
    public void runSimulation() {
        Function2D function2D = new Function2D(0.5);

        SimpsonIntegration2D simpsonIntegration2D = new SimpsonIntegration2D(function2D, 0, 1, 0, 1, N/2, N/2);
        double result = simpsonIntegration2D.integrate();

        System.out.println("Result from Simpson's integration: " + result);

        RandomNumberIntegrator2D randomNumberIntegrator2D = new RandomNumberIntegrator2D(function2D, N);
        double randomResult = randomNumberIntegrator2D.integrate();

        System.out.println("Result from random number integration: " + randomResult);

        HaltonIntegrator2D haltonIntegrator2D = new HaltonIntegrator2D(N, function2D);
        double haltonResult = haltonIntegrator2D.integrate();

        System.out.println("Result from halton integration: " + haltonResult);

        plot("Random", randomNumberIntegrator2D.resultConverging, "Halton", haltonIntegrator2D.resultConverging);

    }

    @Override
    protected String getName() {
        return "Integration2D";
    }

    private class Function2D implements MultivariateFunction {

        Function2D(double corr) {
            this.corr = corr;
        }

        double corr;

        @Override
        public double value(double[] point) {
            if (point.length != 2) {
                throw new RuntimeException("Only 2D functions supported.");
            }
            if (Math.abs(corr) > 1.0) {
                throw new RuntimeException("Correlation should be between -1 and 1.");
            }
            double x1 = point[0];
            double x2 = point[1];

            return Math.exp(-(Math.pow(x1, 2) + Math.pow(x2,2) - 2 * corr * x1 * x2)/(2*(1 - Math.pow(corr, 2))))
                    /(2* Math.PI * Math.sqrt(1 - Math.pow(corr, 2)));
        }
    }

    private class SimpsonIntegration2D {

        SimpsonIntegration2D(MultivariateFunction multivariateFunction, double a, double b, double c, double d, double m, double n) {
            this.multivariateFunction = multivariateFunction;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.m = m;
            this.n = n;
            h = (b -a)/(2*m);
            k = (d -c)/(2*n);
        }

        MultivariateFunction multivariateFunction;
        double a; // lower bound for x
        double b; // upper bound for x
        double c; // lower bound for y
        double d; // lower bound for y
        double m; // number of intervals for x
        double n; // number of intervals for y

        double h; // interval size for x
        double k; // interval size for y

        private double function(double x, double y) {
            double [] values = new double[] {x, y};
            return multivariateFunction.value(values);
        }


        private double getOddYTerm(double x) {
            double t = 0;
            for (int j = 1; j <= n; ++j) {
                t += function(x, c + (2*j-1)*k);
            }
            return t*4;
        }

        private double getEvenYTerm(double x) {
            double t = 0;
            for (int j = 1; j < n; ++j) {
                t += function(x, c + 2*j*k);
            }
            return t*2;
        }

        private double getOddXTerm(double y) {
            double t = 0;
            for (int i = 1; i <= m; ++i) {
                t += function(a + (2*i-1)*h, y);
            }
            return t*4;
        }

        private double getEvenXTerm(double y) {
            double t = 0;
            for (int i = 1; i < m; ++i) {
                t += function(a + 2*i*h,y);
            }
            return t*2;
        }

        private double getOddXOddYTerm() {
            double t = 0;
            for (int j = 1; j <= n; ++j) {
                getOddXTerm(c + (2 * j - 1) * k);
            }
            return t*4;
        }

        private double getOddXEvenYTerm() {
            double t = 0;
            for (int j = 1; j < n; ++j) {
                t += getOddXTerm(c + 2 * j * k);
            }
            return t*2;
        }

        private double getEvenXOddYTerm() {
            double t = 0;
            for (int j = 1; j <= n; ++j) {
                t += getEvenXTerm(c + (2 * j - 1) * k);
            }
            return t*4;
        }

        private double getEvenXEvenYTerm() {
            double t = 0;
            for (int j = 1; j < n; ++j) {
                t += getEvenXTerm(c + 2 * j * k);
            }
            return t*2;
        }

        public double integrate() {

            double v = function(a,c) + function(a,d) + function(b,c) + function(b,d)
                    + getOddYTerm(a) + getEvenYTerm(a) + getOddYTerm(b) + getEvenYTerm(b)
                    + getOddXTerm(c) + getEvenXTerm(c) + getOddXTerm(d) + getEvenXTerm(d)
                    + getOddXOddYTerm() + getEvenXOddYTerm() + getOddXEvenYTerm() + getEvenXEvenYTerm();

            return v * h * k / 9;
        }

    }

    private class RandomNumberIntegrator2D {

        RandomNumberIntegrator2D(MultivariateFunction multivariateFunction, int N) {
            this.multivariateFunction = multivariateFunction;
            this.N = N;
            resultConverging = new double[N];
        }

        MultivariateFunction multivariateFunction;
        int N;
        Random random = new Random();

        public double [] resultConverging;

        public double integrate() {

            double sum = 0;
            for (int i = 0; i < N; ++i) {
                sum += multivariateFunction.value(new double[]{random.nextDouble(), random.nextDouble()});
                resultConverging[i] = sum / (i + 1);
            }
            return sum / N;
        }
    }

    private class HaltonIntegrator2D {

        HaltonIntegrator2D(int N, MultivariateFunction multivariateFunction) {
            this.N = N;
            this.multivariateFunction = multivariateFunction;
            resultConverging = new double[N];
        }

        Halton halton = new Halton();
        int N;
        MultivariateFunction multivariateFunction;
        public double [] resultConverging;

        public double integrate() {
            double sum = 0;
            for (int i = 0; i < N; ++i) {
                sum += multivariateFunction.value(halton.nextUniformVector());
                resultConverging[i] = sum / (i + 1);
            }
            return sum / N;
        }
    }
}


