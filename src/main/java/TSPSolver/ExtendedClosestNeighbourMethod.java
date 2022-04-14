package TSPSolver;

import java.util.ArrayList;
import java.util.List;

public class ExtendedClosestNeighbourMethod extends SolutionGenerator {
    @Override
    public List<Integer> solve(double[][] distanceMatrix) {

        List<Integer> minPointList = new ArrayList<>();
        List<Integer> currentPointList = new ArrayList<>();
        List<Integer> uncheckedPointsList = new ArrayList<>();

        for (int i = 1; i < distanceMatrix.length; i++) {
            uncheckedPointsList.add(i);
        }

        minPointList = ClosestNeighbourMethod.solveFor(distanceMatrix, 0);
        Double currentDistance;
        Double minDistance = objectiveFunction(minPointList, distanceMatrix);

        for (Integer point : uncheckedPointsList) {
            currentPointList = ClosestNeighbourMethod.solveFor(distanceMatrix, point);
            currentDistance = objectiveFunction(currentPointList, distanceMatrix);

            if (currentDistance < minDistance) {
                minPointList = currentPointList;
                minDistance = currentDistance;
            }
        }

        return minPointList;
    }
}



