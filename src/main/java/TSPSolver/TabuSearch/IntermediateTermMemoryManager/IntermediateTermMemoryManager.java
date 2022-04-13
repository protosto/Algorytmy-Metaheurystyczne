package TSPSolver.TabuSearch.IntermediateTermMemoryManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;

import java.util.TreeSet;

public interface IntermediateTermMemoryManager {
    void manage(TabuSearchSolutionGenerator tabuSearchSolutionGenerator, TSPSolution currentSolution, TSPSolution localBestSolution, TreeSet<TSPSolution> neighbourhood, double[][] currentDistanceMatrix, Integer iterationNumber);
    boolean isAllowed(TSPSolution tspSolution);
    IntermediateTermMemoryManager copy();
}
