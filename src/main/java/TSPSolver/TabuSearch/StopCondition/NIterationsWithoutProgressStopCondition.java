package TSPSolver.TabuSearch.StopCondition;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

public class NIterationsWithoutProgressStopCondition extends StopCondition {
    private final Integer N;
    private Integer iterationsWithoutProgress;
    private TSPSolution bestSolution;

    public NIterationsWithoutProgressStopCondition(Integer N) {
        this.N = N;
        this.iterationsWithoutProgress = 0;
        this.iterationNumber = 0;
        bestSolution = new TSPSolution();
    }

    @Override
    public boolean isStopped(TSPSolution currentSolution, TSPSolution localBestSolution) {
        iterationNumber++;

        if (!this.bestSolution.equals(localBestSolution)) {
            this.bestSolution = localBestSolution;

            iterationsWithoutProgress = 0;
        } else {
            iterationsWithoutProgress++;
        }

        return iterationsWithoutProgress.equals(N);
    }

    @Override
    public void reset() {
        iterationNumber = 0;
        iterationsWithoutProgress = 0;
    }

    @Override
    public StopCondition copy() {
        return new NIterationsWithoutProgressStopCondition(N);
    }
}
