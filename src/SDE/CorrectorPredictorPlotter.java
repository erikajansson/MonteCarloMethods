package SDE;

public class CorrectorPredictorPlotter extends SDEPlotter {
    public CorrectorPredictorPlotter(int N) {
        super(N, new CorrectorPredictor(N, 0.1, new SDE1()));
    }

    @Override
    protected String getName() {
        return "CorrectorPredictor Plotter";
    }
}
