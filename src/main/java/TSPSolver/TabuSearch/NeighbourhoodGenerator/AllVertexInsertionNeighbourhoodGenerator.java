package TSPSolver.TabuSearch.NeighbourhoodGenerator;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

import java.util.Collections;
import java.util.TreeSet;

public class AllVertexInsertionNeighbourhoodGenerator implements NeighbourhoodGenerator {
  @Override
  public TreeSet<TSPSolution> generateNeighbourhood(TSPSolution currentSolution, double[][] distanceMatrix) {
    TreeSet<TSPSolution> neighbourhood = new TreeSet<>();

    for (int i = 0; i < currentSolution.getSolution().size() - 1; i++) {
      for( int j = i+1; j < currentSolution.getSolution().size() - 1; j++ ){

        Collections.rotate(currentSolution.getSolution().subList(i,i+j), j);
        TSPSolution neighbour = new TSPSolution(currentSolution.getSolution(), distanceMatrix);
        Collections.rotate(currentSolution.getSolution().subList(i,i+j), 1);

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