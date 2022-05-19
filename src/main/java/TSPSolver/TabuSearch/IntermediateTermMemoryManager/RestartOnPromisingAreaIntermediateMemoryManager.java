package TSPSolver.TabuSearch.IntermediateTermMemoryManager;

import TSPSolver.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;

public class RestartOnPromisingAreaIntermediateMemoryManager implements IntermediateTermMemoryManager {
    private final double requiredImprovement;
    private TSPSolution localBestSolution;

    public RestartOnPromisingAreaIntermediateMemoryManager(double requiredImprovement) {
        this.requiredImprovement = requiredImprovement;
        this.localBestSolution = new TSPSolution();
    }

    @Override
    public synchronized void manage(TabuSearchSolutionGenerator tabuSearchSolutionGenerator, TSPSolution localBestSolution) {
        if (this.localBestSolution.getObjectiveFunctionValue() >= localBestSolution.getObjectiveFunctionValue() * requiredImprovement) {
            TabuSearchSolutionGenerator copy = tabuSearchSolutionGenerator.copy(localBestSolution.copy());
            copy.setIntermediateTermMemoryManager(null);
            copy.setLongTermMemoryManager(null);

            TabuSearchSolutionGenerator.getFutures().add(TabuSearchSolutionGenerator.getExecutorService().submit(copy));

            this.localBestSolution = localBestSolution;
        }
    }

    @Override
    public void reset() {
        localBestSolution = new TSPSolution();
    }

    @Override
    public IntermediateTermMemoryManager copy() {
        return new RestartOnPromisingAreaIntermediateMemoryManager(requiredImprovement);
    }
}
