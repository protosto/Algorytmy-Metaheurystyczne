package TSPSolver.TabuSearch.StopCondition;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

public abstract class StopCondition {
    protected Integer iterationNumber;

    public abstract boolean isStopped(TSPSolution currentSolution, TSPSolution localBestSolution);
    public abstract void reset();
    public abstract StopCondition copy();

    public Integer getIterationNumber() {
        return iterationNumber;
    }

}
