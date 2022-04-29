package TSPSolver.TabuSearch.StopCondition;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

public class NMillisecondsStopCondition extends StopCondition {
    private Long N;
    private Long currentTime;
    private Long finishTime;

    public NMillisecondsStopCondition(Long N) {
        iterationNumber = 0;
        this.N = N;
        currentTime = System.currentTimeMillis();
        finishTime = currentTime + N;
    }

    public NMillisecondsStopCondition(Long N, Long currentTime, Long finishTime) {
        iterationNumber = 0;
        this.N = N;
        this.currentTime = currentTime;
        this.finishTime = finishTime;

    }
    @Override
    public boolean isStopped(TSPSolution currentSolution, TSPSolution localBestSolution) {
        iterationNumber++;
        currentTime = System.currentTimeMillis();
        return currentTime >= finishTime;
    }

    @Override
    public void reset() {
        iterationNumber = 0;
        currentTime = System.currentTimeMillis();
        finishTime = currentTime + N;
    }

    @Override
    public StopCondition copy() {
        return new NMillisecondsStopCondition(N, currentTime, finishTime);
    }
}
