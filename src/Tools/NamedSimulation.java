package Tools;

abstract public class NamedSimulation {

    public int N;

    public NamedSimulation(int N) {
        this.N = N;
    }

    abstract public void runSimulation();

    protected String getName() {
        return getClass().getName();
    }
}
