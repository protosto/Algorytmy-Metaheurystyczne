package TSPSolver.TabuSearch.IntermediateTermMemoryManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;

import java.util.TreeSet;

public interface IntermediateTermMemoryManager {
    void manage(TabuSearchSolutionGenerator tabuSearchSolutionGenerator, TSPSolution localBestSolution);
    void reset();

    IntermediateTermMemoryManager copy();
}
