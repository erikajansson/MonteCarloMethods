package SDE;

public class MilsteinTester extends SDETester {

    public MilsteinTester(int N) {
        super(N, new Milstein(N, 0.1, new SDE1()));
    }

    @Override
    protected String getName() {
        return "MilsteinTester";
    }
}
