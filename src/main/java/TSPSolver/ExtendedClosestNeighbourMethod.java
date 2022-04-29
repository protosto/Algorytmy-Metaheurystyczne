package TSPSolver;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

import java.util.ArrayList;
import java.util.List;

public class ExtendedClosestNeighbourMethod extends SolutionGenerator {
    @Override
    public TSPSolution solve(double[][] distanceMatrix) {

        TSPSolution minTSPSolution;
        TSPSolution currentTSPSolution;
        List<Integer> uncheckedPointsList = new ArrayList<>();

        for (int i = 1; i < distanceMatrix.length; i++) {
            uncheckedPointsList.add(i);
        }

        minTSPSolution = ClosestNeighbourMethod.solveFor(distanceMatrix, 0);

        for (Integer point : uncheckedPointsList) {
            currentTSPSolution = ClosestNeighbourMethod.solveFor(distanceMatrix, point);

            if (currentTSPSolution.getObjectiveFunctionValue() < minTSPSolution.getObjectiveFunctionValue()) {
                minTSPSolution = currentTSPSolution;
            }
        }


        return minTSPSolution;
    }
}



