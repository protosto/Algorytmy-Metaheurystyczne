package TSPSolver;

import TSPSolver.TabuSearch.NeighbourhoodGenerator.AllSwapsNeighbourhoodGenerator;
import TSPSolver.TSPSolution.TSPSolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class TwoOptSolutionGenerator extends SolutionGenerator {
    private SolutionGenerator startingAlgorithm = null;
    private TSPSolution startingSolution = null;

    public TwoOptSolutionGenerator(SolutionGenerator startingAlgorithm) {
        this.startingAlgorithm = startingAlgorithm;
    }
    public TwoOptSolutionGenerator(TSPSolution startingSolution) {
        this.startingSolution = startingSolution;
    }

    @Override
    public TSPSolution solve(double[][] distanceMatrix) {
        TSPSolution minTSPSolution = (startingSolution == null ? startingAlgorithm.solve(distanceMatrix) : startingSolution);
        int size = minTSPSolution.getSolution().size();
        boolean progress;
        int t = 0;

        do {
            progress = false;

            for(int i = 0; i < size - 1; i++) {
                for(int j = i + 1; j < size; j++) {
                    List<Integer> solution = minTSPSolution.getSolution();
                    double objectiveFunctionValueDelta = - distanceMatrix[solution.get(i)][solution.get((i + 1) % size)]
                            - distanceMatrix[solution.get(j)][solution.get((j + 1) % size)]
                            + distanceMatrix[solution.get(i)][solution.get(j)]
                            + distanceMatrix[solution.get((i + 1) % size)][solution.get((j + 1) % size)];

                    if(objectiveFunctionValueDelta < 0 && objectiveFunctionValueDelta < -0.00000001) {
                        Collections.reverse(minTSPSolution.getSolution().subList(i + 1, j + 1));
                        minTSPSolution.setObjectiveFunctionValue(minTSPSolution.getObjectiveFunctionValue() + objectiveFunctionValueDelta);
                        progress = true;
                    }
                }
            }
        }
        while(progress);

        return minTSPSolution;
    }
}
