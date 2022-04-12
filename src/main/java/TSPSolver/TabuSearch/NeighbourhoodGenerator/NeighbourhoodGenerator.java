package TSPSolver.TabuSearch.NeighbourhoodGenerator;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import java.util.TreeSet;

public interface NeighbourhoodGenerator {
    TreeSet<TSPSolution> generateNeighbourhood(TSPSolution solution, double[][] distanceMatrix);
}
