package TSPSolver.TabuSearch.StopCondition;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

import java.util.ArrayList;

public class NIterationsWithoutProgressStopCondition extends StopCondition {
    private Integer N;
    private Integer iterationsWithoutProgress;
    private TSPSolution bestSolution;

    public NIterationsWithoutProgressStopCondition(Integer N) {
        this.N = N;
        this.iterationsWithoutProgress = 0;
        this.iterationNumber = 0;
        bestSolution = new TSPSolution();
    }

    @Override
    public boolean isStopped(TSPSolution currentSolution, TSPSolution bestSolution) {
        iterationNumber++;

        if(!this.bestSolution.equals(bestSolution)) {
            this.bestSolution = bestSolution;

            iterationsWithoutProgress = 0;
        }
        else {
            iterationsWithoutProgress++;
        }

        if(iterationsWithoutProgress.equals(N)) {
            return true;
        }

        return false;
    }

    @Override
    public void reset() {
        iterationNumber = 0;
        iterationsWithoutProgress = 0;
    }
}
