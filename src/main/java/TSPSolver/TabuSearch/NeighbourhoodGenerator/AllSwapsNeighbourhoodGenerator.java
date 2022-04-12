package TSPSolver.TabuSearch.NeighbourhoodGenerator;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import java.util.Collections;
import java.util.TreeSet;

public class AllSwapsNeighbourhoodGenerator implements NeighbourhoodGenerator {
    @Override
    public TreeSet<TSPSolution> generateNeighbourhood(TSPSolution currentSolution, double[][] distanceMatrix) {
        TreeSet<TSPSolution> neighbourhood = new TreeSet<>();

        for(int i = 0; i < currentSolution.getSolution().size() - 1; i++) {
            for(int j = i + 1; j < currentSolution.getSolution().size(); j++) {
                Collections.swap(currentSolution.getSolution(), i, j);
                TSPSolution neighbour = new TSPSolution(currentSolution.getSolution(), distanceMatrix);
                Collections.swap(currentSolution.getSolution(), i, j);

                neighbourhood.add(neighbour);
            }
        }

        return neighbourhood;
    }
}
