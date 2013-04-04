package SDE;

import Tools.Plotter;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.Scanner;

public class EulerTester extends SDETester {

    public EulerTester(int N) {
        super(N, new Euler(N));
    }

    @Override
    protected String getName() {
        return "EulerTester";
    }
}
