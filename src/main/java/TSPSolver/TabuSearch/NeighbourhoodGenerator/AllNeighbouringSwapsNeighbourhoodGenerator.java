package TSPSolver.TabuSearch.NeighbourhoodGenerator;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

import java.util.Collections;
import java.util.TreeSet;

public class AllNeighbouringSwapsNeighbourhoodGenerator implements NeighbourhoodGenerator {
    @Override
    public TreeSet<TSPSolution> generateNeighbourhood(TSPSolution currentSolution, double[][] distanceMatrix) {
        TreeSet<TSPSolution> neighbourhood = new TreeSet<>();

        for (int i = 0; i < currentSolution.getSolution().size() - 1; i++) {
            Collections.swap(currentSolution.getSolution(), i, i + 1);
            TSPSolution neighbour = new TSPSolution(currentSolution.getSolution(), distanceMatrix);
            Collections.swap(currentSolution.getSolution(), i, i + 1);

            neighbourhood.add(neighbour);
        }

        return neighbourhood;
    }

    public NeighbourhoodGenerator copy() {
        return new AllNeighbouringSwapsNeighbourhoodGenerator();
    }
}
