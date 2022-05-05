package TSPSolver.TabuSearch.NeighbourhoodGenerator;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

import java.io.IOException;
import java.util.Collections;
import java.util.TreeSet;

public class RandomVertexInsertionNeighbourhoodGenerator implements NeighbourhoodGenerator {
  private int insertionDistance;

  public RandomVertexInsertionNeighbourhoodGenerator(int insertionDistance) {
      this.insertionDistance = insertionDistance;
  }
  @Override
  public TreeSet<TSPSolution> generateNeighbourhood(TSPSolution currentSolution, double[][] distanceMatrix) {
    TreeSet<TSPSolution> neighbourhood = new TreeSet<>();

    for (int i = 0; i < currentSolution.getSolution().size() - 1 - insertionDistance; i++) {
        int distance = (int) (Math.random() * insertionDistance + 1);

        Integer temp = currentSolution.getSolution().remove(i);
        currentSolution.getSolution().add((i + distance > currentSolution.getSolution().size() - 1 ? currentSolution.getSolution().size() - 1 : i + distance), temp);
        TSPSolution neighbour = new TSPSolution(currentSolution.getSolution(), distanceMatrix);
        temp = currentSolution.getSolution().remove((i + distance > currentSolution.getSolution().size() - 1 ? currentSolution.getSolution().size() - 1 : i + distance));
        currentSolution.getSolution().add(i, temp);


        neighbourhood.add(neighbour);
    }

    return neighbourhood;
  }

  @Override
  public NeighbourhoodGenerator copy() {
    return new RandomVertexInsertionNeighbourhoodGenerator(insertionDistance);
  }

  public void setInsertionDistance(int insertionDistance){ this.insertionDistance = insertionDistance;}
}