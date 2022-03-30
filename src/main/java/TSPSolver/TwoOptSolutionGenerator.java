package TSPSolver;

import java.util.Collections;
import java.util.List;

public class TwoOptSolutionGenerator extends SolutionGenerator {
    private SolutionGenerator startingAlgorithm;

    public TwoOptSolutionGenerator(SolutionGenerator startingAlgorithm) {
        this.startingAlgorithm = startingAlgorithm;
    }

    @Override
    public List<Integer> solve(double[][] distanceMatrix) {
        List<Integer> pointList = startingAlgorithm.solve(distanceMatrix);

        double currentMinObjectiveFunctionValue = objectiveFunction(pointList, distanceMatrix);
        int firstPoint = 0, secondPoint = 0;
        double currentObjectiveFunctionValue;

        do {
            Collections.swap(pointList, firstPoint, secondPoint);
            firstPoint = 0;
            secondPoint = 0;

            for(int i = 0; i < pointList.size() - 1; i++) {
                for(int j = i + 1; j < pointList.size(); j++) {
                    Collections.swap(pointList, i, j);
                    currentObjectiveFunctionValue = objectiveFunction(pointList, distanceMatrix);
                    Collections.swap(pointList, i, j);

                    if(currentObjectiveFunctionValue < currentMinObjectiveFunctionValue) {
                        firstPoint = i;
                        secondPoint = j;
                        currentMinObjectiveFunctionValue = currentObjectiveFunctionValue;
                    }
                }
            }
        }
        while(firstPoint != 0 || secondPoint != 0);

        return pointList;
    }
}
