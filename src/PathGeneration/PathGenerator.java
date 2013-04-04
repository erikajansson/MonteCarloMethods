package PathGeneration;

import Tools.PathPlotter;

abstract public class PathGenerator extends PathPlotter {

    PathGenerator(int N) {
        super(N);
    }

    @Override
    protected int getNumberOfPaths() {
        return 5;
    }

    @Override
    protected String getPathName(int i) {
        return "Path " + i;
    }
}
