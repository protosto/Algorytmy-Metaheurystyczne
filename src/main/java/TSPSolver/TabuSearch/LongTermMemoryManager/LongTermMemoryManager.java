package TSPSolver.TabuSearch.LongTermMemoryManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import java.util.TreeSet;


public interface LongTermMemoryManager {
    void manage(TSPSolution currentSolution, TSPSolution localBestSolution, TreeSet<TSPSolution> neighbourhood, double[][] currentDistanceMatrix, Integer iterationNumber);
}
