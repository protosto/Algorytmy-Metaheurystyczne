package TSPSolver.TabuSearch.StopCondition;

public class NIterationsStopCondition extends StopCondition {
    private Integer N;

    public NIterationsStopCondition(Integer N) {
        this.N = N;
        this.iterationNumber = 0;
    }

    @Override
    public boolean isStopped() {
        if(iterationNumber == N) {
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


}
