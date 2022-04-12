package TSPSolver.TabuSearch.NeighbourhoodGenerator;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

import java.util.Collections;
import java.util.TreeSet;

public class AllNeighbouringSwapsNeighbourhoodGenerator implements NeighbourhoodGenerator{
    @Override
    public TreeSet<TSPSolution> generateNeighbourhood(TSPSolution solution, double[][] distanceMatrix) {
        TreeSet<TSPSolution> neighbourhood = new TreeSet<>();

        for(int i = 0; i < solution.getSolution().size() - 1; i++) {
            Collections.swap(solution.getSolution(), i, i + 1);
            TSPSolution neighbour = new TSPSolution(solution.getSolution(), distanceMatrix);
            Collections.swap(solution.getSolution(), i, i + 1);

            neighbourhood.add(neighbour);
        }

        return neighbourhood;
    }
}
