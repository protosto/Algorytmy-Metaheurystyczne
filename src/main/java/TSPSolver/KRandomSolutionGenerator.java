package TSPSolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KRandomSolutionGenerator extends SolutionGenerator {

    private Integer k;

    public KRandomSolutionGenerator(Integer k) {
        this.k = k;
    }

    @Override
    public List<Integer> solve(double[][] distanceMatrix) {
        List<Integer> pointList = new ArrayList<>();

        for(int i = 0; i < distanceMatrix.length; i++) {
            pointList.add(i);
        }

        Collections.shuffle(pointList);
        List<Integer> temp = new ArrayList<>();
        temp.addAll(pointList);

        double currentMinObjectiveFunctionValue = objectiveFunction(pointList, distanceMatrix);
        double currentObjectiveFunctionValue;

        for(int i = 0; i < k; i++) {
            Collections.shuffle(temp);
            currentObjectiveFunctionValue = objectiveFunction(temp, distanceMatrix);

            if(currentMinObjectiveFunctionValue > currentObjectiveFunctionValue) {
                pointList.clear();
                pointList.addAll(temp);
                currentMinObjectiveFunctionValue = currentObjectiveFunctionValue;
            }
        }

        return pointList;
    }

    public void setK(Integer k) {
        this.k = k;
    }
}
