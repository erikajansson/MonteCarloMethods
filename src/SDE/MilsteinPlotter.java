package SDE;

public class MilsteinPlotter extends SDEPlotter {

    public MilsteinPlotter(int N) {
        super(N, new Milstein(N));
    }

    public String getName() {
        return "MilsteinPlotter";
    }
}
