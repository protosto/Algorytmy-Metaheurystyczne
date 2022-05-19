package TSPSolver.TabuSearch.IntermediateTermMemoryManager;

import TSPSolver.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;

public interface IntermediateTermMemoryManager {
    void manage(TabuSearchSolutionGenerator tabuSearchSolutionGenerator, TSPSolution localBestSolution);
    void reset();

    IntermediateTermMemoryManager copy();
}
