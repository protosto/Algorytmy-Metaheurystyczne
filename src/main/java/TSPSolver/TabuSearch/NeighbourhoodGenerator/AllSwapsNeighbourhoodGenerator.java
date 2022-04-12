package TSPSolver.TabuSearch.NeighbourhoodGenerator;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import java.util.Collections;
import java.util.TreeSet;

public class AllSwapsNeighbourhoodGenerator implements NeighbourhoodGenerator {
    @Override
    public TreeSet<TSPSolution> generateNeighbourhood(TSPSolution solution, double[][] distanceMatrix) {
        TreeSet<TSPSolution> neighbourhood = new TreeSet<>();

        for(int i = 0; i < solution.getSolution().size() - 1; i++) {
            for(int j = i + 1; j < solution.getSolution().size(); j++) {
                Collections.swap(solution.getSolution(), i, j);
                TSPSolution neighbour = new TSPSolution(solution.getSolution(), distanceMatrix);
                Collections.swap(solution.getSolution(), i, j);

                neighbourhood.add(neighbour);
            }
        }

        return neighbourhood;
    }
}
