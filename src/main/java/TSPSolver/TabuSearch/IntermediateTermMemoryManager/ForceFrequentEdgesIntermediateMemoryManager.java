package TSPSolver.TabuSearch.IntermediateTermMemoryManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;

import java.util.TreeSet;

public class ForceFrequentEdgesIntermediateMemoryManager implements IntermediateTermMemoryManager{
    @Override
    public void manage(TabuSearchSolutionGenerator tabuSearchSolutionGenerator, TSPSolution currentSolution, TSPSolution localBestSolution, TreeSet<TSPSolution> neighbourhood, double[][] currentDistanceMatrix, Integer iterationNumber) {

    }

    @Override
    public boolean isAllowed(TSPSolution tspSolution) {
        return true;
    }

    @Override
    public IntermediateTermMemoryManager copy() {
        return null;
    }
}
