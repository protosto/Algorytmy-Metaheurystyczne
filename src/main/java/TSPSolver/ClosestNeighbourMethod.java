package TSPSolver;

import java.util.ArrayList;
import java.util.List;

public class ClosestNeighbourMethod extends SolutionGenerator {
    public static List<Integer> solveFor(double[][] distanceMatrix, Integer startingPoint) {

        List<Integer> pointList = new ArrayList<>();
        List<Integer> unvisitedPointsList = new ArrayList<>();
        Double currentDistance;
        Double minDistance;
        Integer minPoint = null;

        for (int i = 0; i < distanceMatrix.length; i++) {
            unvisitedPointsList.add(i);
        }

        pointList.add(startingPoint);
        unvisitedPointsList.remove(startingPoint);

        while (!unvisitedPointsList.isEmpty()) {
            minDistance = Double.MAX_VALUE;

            for (Integer point : unvisitedPointsList) {
                currentDistance = distanceMatrix[startingPoint][point];

                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    minPoint = point;
                }
            }

            startingPoint = minPoint;
            pointList.add(startingPoint);
            unvisitedPointsList.remove(startingPoint);
        }

        return pointList;
    }

    @Override
    public List<Integer> solve(double[][] distanceMatrix) {

        return solveFor(distanceMatrix, 0);
    }
}