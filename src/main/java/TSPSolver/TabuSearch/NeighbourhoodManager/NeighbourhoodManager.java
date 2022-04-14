package TSPSolver.TabuSearch.NeighbourhoodManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuListManager.TabuListManager;

import java.util.TreeSet;

public interface NeighbourhoodManager {
    TSPSolution chooseNeighbour(TreeSet<TSPSolution> neighbourhood, TabuListManager tabuListManager, Integer iterationNumber);

    NeighbourhoodManager copy();
}
