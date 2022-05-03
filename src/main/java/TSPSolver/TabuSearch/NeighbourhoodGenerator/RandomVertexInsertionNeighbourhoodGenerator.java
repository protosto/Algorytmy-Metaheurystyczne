package TSPSolver.TabuSearch.NeighbourhoodGenerator;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

import java.util.Collections;
import java.util.TreeSet;

public class RandomVertexInsertionNeighbourhoodGenerator implements NeighbourhoodGenerator {
  private int InsertionDistance = 4;
  @Override
  public TreeSet<TSPSolution> generateNeighbourhood(TSPSolution currentSolution, double[][] distanceMatrix) {
    TreeSet<TSPSolution> neighbourhood = new TreeSet<>();

    for (int i = 0; i < currentSolution.getSolution().size() - 1; i++) {
        int rotate = (int) (Math.random()*InsertionDistance+1);
        Collections.rotate(currentSolution.getSolution().subList(i,i+rotate), rotate);
        TSPSolution neighbour = new TSPSolution(currentSolution.getSolution(), distanceMatrix);
        Collections.rotate(currentSolution.getSolution().subList(i,i+rotate), 1);

        neighbourhood.add(neighbour);
    }

    return neighbourhood;
  }

  @Override
  public NeighbourhoodGenerator copy() {
    return new RandomVertexInsertionNeighbourhoodGenerator();
  }

  public void ChangeInsertionDistance(int NewInsertionDistance){ this.InsertionDistance = NewInsertionDistance;}
}