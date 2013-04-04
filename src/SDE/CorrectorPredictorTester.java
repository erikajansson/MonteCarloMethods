package SDE;

public class CorrectorPredictorTester extends SDETester {

    public CorrectorPredictorTester(int N) {
        super(N, new CorrectorPredictor(N,0.1, new SDE1()));
    }

    @Override
    protected String getName() {
        return "CorrectorPredictorTester";
    }
}
