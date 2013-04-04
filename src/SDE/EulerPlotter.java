package SDE;

import Tools.NamedSimulation;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.util.Scanner;

public class EulerPlotter extends SDEPlotter {

    public EulerPlotter(int N) {
        super(N, new Euler(N));
    }

    public String getName() {
        return "EulerPlotter";
    }
}
