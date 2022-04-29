package TSPSolver.TabuSearch.IntermediateTermMemoryManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;

import java.util.TreeSet;

public class RestartOnPromisingAreaIntermediateMemoryManager implements IntermediateTermMemoryManager {
    private final double requiredImprovement;
    private TSPSolution localBestSolution;

    public RestartOnPromisingAreaIntermediateMemoryManager(double requiredImprovement) {
        this.requiredImprovement = requiredImprovement;
    }

    @Override
    public synchronized void manage(TabuSearchSolutionGenerator tabuSearchSolutionGenerator, TSPSolution localBestSolution) {
        if (this.localBestSolution == null) {
            this.localBestSolution = localBestSolution;
        } else if (this.localBestSolution.getObjectiveFunctionValue() >= localBestSolution.getObjectiveFunctionValue() * requiredImprovement) {
            TabuSearchSolutionGenerator copy = tabuSearchSolutionGenerator.copy(localBestSolution);
            copy.setIntermediateTermMemoryManager(null);
            copy.setLongTermMemoryManager(null);

            TabuSearchSolutionGenerator.getFutures().add(TabuSearchSolutionGenerator.getExecutorService().submit(copy));

            this.localBestSolution = localBestSolution;
        }
    }

    @Override
    public void reset() {
        localBestSolution = null;
    }

    @Override
    public IntermediateTermMemoryManager copy() {
        return new RestartOnPromisingAreaIntermediateMemoryManager(requiredImprovement);
    }
}
