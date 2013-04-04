import Integration.*;
import PathGeneration.*;
import SDE.*;
import Simulations.*;
import Tools.NamedSimulation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        ArrayList<Class> namedSimulations = new ArrayList<Class>();
        namedSimulations.add(Buffon.class);
        namedSimulations.add(Dice.class);
        namedSimulations.add(Bernoulli.class);
        namedSimulations.add(Binominal.class);
        namedSimulations.add(Exponential.class);
        namedSimulations.add(Geometric.class);
        namedSimulations.add(Poisson.class);
        namedSimulations.add(StudentT.class);
        namedSimulations.add(Integration.class);
        namedSimulations.add(CustomDistribution.class);
        namedSimulations.add(Brownian.class);
        namedSimulations.add(BrownianSimulation.class);
        namedSimulations.add(RandomWalk.class);
        namedSimulations.add(Spectral.class);
        namedSimulations.add(IncrementalMatrix.class);
        namedSimulations.add(SpectralMatrix.class);
        namedSimulations.add(BrownianBridge.class);
        namedSimulations.add(Euler.class);
        namedSimulations.add(Milstein.class);
        namedSimulations.add(EulerTester.class);
        namedSimulations.add(EulerPlotter.class);
        namedSimulations.add(MilsteinPlotter.class);
        namedSimulations.add(MilsteinTester.class);
        namedSimulations.add(CorrectorPredictorPlotter.class);
        namedSimulations.add(CorrectorPredictorTester.class);
        namedSimulations.add(Bivariate.class);
        namedSimulations.add(Logistic.class);
        namedSimulations.add(UniformFromLogistic.class);
        namedSimulations.add(Congruential.class);
        namedSimulations.add(Integration2D.class);
        namedSimulations.add(Triangle.class);

        while (true) {
            try {

                for (int i = 0; i < namedSimulations.size(); ++i) {
                    System.out.println("" + i + ": " + namedSimulations.get(i).getName());
                }

                Scanner scanner = new Scanner(System.in);
                int number = scanner.nextInt();
                System.out.println("Number of iterations:");
                int N = scanner.nextInt();

                Class[] type = { int.class };
                NamedSimulation namedSimulation = (NamedSimulation)namedSimulations.get(number).getConstructor(type).newInstance(N);
                namedSimulation.runSimulation();


//                if (c == '1') {
//                    Class[] type = { int.class };
//                    Buffon buffon = (Buffon)namedSimulations.get(0).getConstructor(type).newInstance(N);
//                    buffon.run();
//                } else if (c == '2') {
//                    Dice dice = new Dice(N);
//                    dice.runSimulationAndPrintResult();
//                    dice.plotDistribution(6);
//                    dice.plotAverageConverging();
//                    dice.plotSamples();
//                } else if (c == '3') {
//                    Bernoulli bernoulli = new Bernoulli(N);
//                    bernoulli.runSimulationAndPrintResult();
//                    bernoulli.plotDistribution(2);
//                } else if (c == '4') {
//                    Binominal binominal = new Binominal(N);
//                    binominal.runSimulationAndPrintResult();
//                    binominal.plotDistribution(6);
//                } else if (c == '5') {
//                    Exponential exponential = new Exponential(N);
//                    exponential.runSimulationAndPrintResult();
//                    exponential.plotAverageConverging();
//                } else if (c == '6') {
//                    CentralLimitTheorem centralLimitTheorem = new CentralLimitTheorem(new Dice(N));
//                    centralLimitTheorem.runSimulationAndPrintResult();
//                    centralLimitTheorem.plotAverageConverging();
//                    centralLimitTheorem.plotDistribution(100);
//                    centralLimitTheorem.plotSamples();
//                }  else if (c == '7') {
//                    CentralLimitTheorem2 centralLimitTheorem = new CentralLimitTheorem2(new Dice(N));
//                    centralLimitTheorem.runSimulationAndPrintResult();
//                    centralLimitTheorem.plotAverageConverging();
//                    centralLimitTheorem.plotDistribution(100);
//                    centralLimitTheorem.plotSamples();
//                }  else if (c == '8') {
//                    Geometric geometric = new Geometric(N);
//                    geometric.runSimulationAndPrintResult();
//                    geometric.plotSamples();
//                } else if (c == '9') {
//                    Poisson poisson = new Poisson(N);
//                    poisson.simulate();
//                } else if (c == 'q') {
//                    StudentT studentT = new StudentT(N);
//                    studentT.runSimulationAndPrintResult();
//                    studentT.plotSamples();
//                    studentT.plotDistribution(1000);
//                } else if (c == 'w') {
//                    Integration.Integration integration = new Integration.Integration();
//                    integration.run();
//                } else if (c == 'e') {
//                    CustomDistribution customDistribution = new CustomDistribution();
//                    customDistribution.runSimulationAndPrintResult();
//                    customDistribution.plotAverageConverging();
//                    customDistribution.plotLawOfLargeNumbers();
//
//                    CentralLimitTheorem2 centralLimitTheorem2 = new CentralLimitTheorem2(customDistribution);
//                    centralLimitTheorem2.runSimulationAndPrintResult();
//                    centralLimitTheorem2.plotDistribution(10000000);
//                } else if (c == 'r') {
//                    Brownian brownian = new Brownian(N);
//                    brownian.simulateAndPlotPaths();
//                } else if (c == 'f') {
//                    BrownianSimulation brownianSimulation = new BrownianSimulation(N);
//                    brownianSimulation.runSimulationAndPrintResult();
//                    brownianSimulation.plotDistribution(100);
//                } else if (c == 't') {
//                    RandomWalk randomWalk = new RandomWalk(N);
//                    randomWalk.simulateAndPlotPaths();
//                } else if (c == 'y') {
//                    Spectral spectral = new Spectral(N);
//                    spectral.simulateAndPlotPaths();
//                } else if (c == 'u') {
//                    IncrementalMatrix incrementalMatrix = new IncrementalMatrix();
//                    incrementalMatrix.simulateAndPlotPaths();
//                } else if (c == 'i') {
//                    SpectralMatrix spectralMatrix = new SpectralMatrix();
//                    spectralMatrix.simulateAndPlotPaths();
//                } else if (c == 'o') {
//                    BrownianBridge brownianBridge = new BrownianBridge();
//                    brownianBridge.simulateAndPlotPaths();
//                } else if (c == 'p') {
//                    SDE.Euler euler = new SDE.Euler(N);
//                    euler.simulateAndPlotPaths();
//                } else if (c == 'a') {
//                    SDE.Milstein milstein = new SDE.Milstein(N);
//                    milstein.simulateAndPlotPaths();
//                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

    }
}
