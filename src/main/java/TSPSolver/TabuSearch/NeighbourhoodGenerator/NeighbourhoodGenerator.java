package TSPSolver.TabuSearch.NeighbourhoodGenerator;

import TSPSolver.TSPSolution.TSPSolution;

import java.util.TreeSet;

public interface NeighbourhoodGenerator {
    TreeSet<TSPSolution> generateNeighbourhood(TSPSolution currentSolution, double[][] distanceMatrix);

    NeighbourhoodGenerator copy();
}
