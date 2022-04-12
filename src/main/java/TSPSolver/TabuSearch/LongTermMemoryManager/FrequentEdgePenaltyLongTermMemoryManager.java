package TSPSolver.TabuSearch.LongTermMemoryManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

import java.util.TreeSet;

public class FrequentEdgePenaltyLongTermMemoryManager implements LongTermMemoryManager {
    boolean isSymmetrical;
    double penaltyValue;

    public FrequentEdgePenaltyLongTermMemoryManager(boolean isSymmetrical, double penaltyValue) {
        this.isSymmetrical = isSymmetrical;
        this.penaltyValue = penaltyValue;
    }

    @Override
    public void manage(TSPSolution currentSolution, TSPSolution localBestSolution, TreeSet neighbourhood, double[][] currentDistanceMatrix, Integer iterationNumber) {
        if(isSymmetrical) {
            for(int i = 0; i < currentSolution.getSolution().size() - 1; i++) {
                currentDistanceMatrix[currentSolution.getSolution().get(i)][currentSolution.getSolution().get(i + 1)] += penaltyValue;
                currentDistanceMatrix[currentSolution.getSolution().get(i + 1)][currentSolution.getSolution().get(i)] += penaltyValue;
            }
        }
        else {
            for(int i = 0; i < currentSolution.getSolution().size() - 1; i++) {
                currentDistanceMatrix[currentSolution.getSolution().get(i)][currentSolution.getSolution().get(i + 1)] += penaltyValue;
            }
        }
    }
}
