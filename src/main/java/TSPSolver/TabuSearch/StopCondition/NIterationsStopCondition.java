package TSPSolver.TabuSearch.StopCondition;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

public class NIterationsStopCondition extends StopCondition {
    private Integer N;

    public NIterationsStopCondition(Integer N) {
        this.N = N;
        this.iterationNumber = 0;
    }

    @Override
    public boolean isStopped(TSPSolution currentSolution, TSPSolution localBestSolution) {
        if(iterationNumber.equals(N)) {
            reset();

            return true;
        }
        else {
            iterationNumber++;

            return false;
        }
    }

    @Override
    public void reset() {
        iterationNumber = 0;
    }

    public Integer getN() {
        return N;
    }

    public void setN(Integer n) {
        N = n;
    }
}
