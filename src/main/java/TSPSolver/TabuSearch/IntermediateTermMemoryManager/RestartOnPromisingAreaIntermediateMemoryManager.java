package TSPSolver.TabuSearch.IntermediateTermMemoryManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;

import java.util.TreeSet;

public class RestartOnPromisingAreaIntermediateMemoryManager implements IntermediateTermMemoryManager {
    private double requiredImprovement;
    private TSPSolution localBestSolution;

    public RestartOnPromisingAreaIntermediateMemoryManager(double requiredImprovement) {
        this.requiredImprovement = requiredImprovement;
    }

    @Override
    public void manage(TabuSearchSolutionGenerator tabuSearchSolutionGenerator, TSPSolution currentSolution, TSPSolution localBestSolution, TreeSet<TSPSolution> neighbourhood, double[][] currentDistanceMatrix, Integer iterationNumber) {
        if(this.localBestSolution == null) {
            this.localBestSolution = localBestSolution;
        }
        else if(this.localBestSolution.getObjectiveFunctionValue() >= localBestSolution.getObjectiveFunctionValue() * requiredImprovement) {
            TabuSearchSolutionGenerator copy = tabuSearchSolutionGenerator.copy(localBestSolution);
            copy.setIntermediateTermMemoryManager(null);
            copy.setLongTermMemoryManager(null);
            Thread thread = new Thread(copy);
            this.localBestSolution = localBestSolution;
            thread.start();
        }
    }

    @Override
    public boolean isAllowed(TSPSolution tspSolution) {
        return true;
    }

    @Override
    public IntermediateTermMemoryManager copy() {
        return new RestartOnPromisingAreaIntermediateMemoryManager(requiredImprovement);
    }
}
