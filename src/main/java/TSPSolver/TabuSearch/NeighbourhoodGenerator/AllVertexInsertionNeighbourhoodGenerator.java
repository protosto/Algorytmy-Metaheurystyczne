package TSPSolver.TabuSearch.NeighbourhoodGenerator;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

import java.util.Collections;
import java.util.TreeSet;

public class AllVertexInsertionNeighbourhoodGenerator implements NeighbourhoodGenerator {
  @Override
  public TreeSet<TSPSolution> generateNeighbourhood(TSPSolution currentSolution, double[][] distanceMatrix) {
    TreeSet<TSPSolution> neighbourhood = new TreeSet<>();

    for (int i = 0; i < currentSolution.getSolution().size() - 1; i++) {
      for(int j = 0; j < currentSolution.getSolution().size() - 1; j++ ){
        if(i == j) {
          continue;
        }

        Integer temp = currentSolution.getSolution().remove(i);
        currentSolution.getSolution().add(j, temp);
        TSPSolution neighbour = new TSPSolution(currentSolution.getSolution(), distanceMatrix);
        temp = currentSolution.getSolution().remove(j);
        currentSolution.getSolution().add(i, temp);

        neighbourhood.add(neighbour);
      }
    }

    return neighbourhood;
  }

  @Override
  public NeighbourhoodGenerator copy() {
    return new AllVertexInsertionNeighbourhoodGenerator();
  }

}