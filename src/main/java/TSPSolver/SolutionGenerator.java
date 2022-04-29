package TSPSolver;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

import java.util.List;

public abstract class SolutionGenerator {

    public static double objectiveFunction(List<Integer> pointList, double[][] distanceMatrix) {
        double objectiveFunctionValue = 0.0;

        int size = pointList.size() - 1;

        for (int i = 0; i < size; i++) {
            objectiveFunctionValue += distanceMatrix[pointList.get(i)][pointList.get(i + 1)];
        }

        objectiveFunctionValue += distanceMatrix[pointList.get(size)][pointList.get(0)];

        return objectiveFunctionValue;
    }

    public abstract TSPSolution solve(double[][] distanceTable);
}

