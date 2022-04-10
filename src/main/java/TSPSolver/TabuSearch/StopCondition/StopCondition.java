package TSPSolver.TabuSearch.StopCondition;

public abstract class StopCondition {
    protected Integer iterationNumber;

    public abstract boolean isStopped();
    public abstract void reset();

    public Integer getIterationNumber() {
        return iterationNumber;
    }
}
