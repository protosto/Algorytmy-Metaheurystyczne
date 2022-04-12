package TSPSolver.TabuSearch;

import TSPSolver.SolutionGenerator;
import TSPSolver.TabuSearch.LongTermMemoryManager.LongTermMemoryManager;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.NeighbourhoodGenerator;
import TSPSolver.TabuSearch.StopCondition.StopCondition;
import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuListManager.TabuListManager;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class TabuSearchSolutionGenerator extends SolutionGenerator {
    private SolutionGenerator startingAlgorithm;
    private StopCondition stopCondition;
    private NeighbourhoodGenerator neighbourhoodGenerator;
    private TabuListManager tabuListManager;
    private LongTermMemoryManager longTermMemoryManager;

    public TabuSearchSolutionGenerator(SolutionGenerator startingAlgorithm, StopCondition stopCondition, NeighbourhoodGenerator neighbourhoodGenerator, TabuListManager tabuListManager, LongTermMemoryManager longTermMemoryManager) {
        this.startingAlgorithm = startingAlgorithm;
        this.stopCondition = stopCondition;
        this.neighbourhoodGenerator = neighbourhoodGenerator;
        this.tabuListManager = tabuListManager;
        this.longTermMemoryManager = longTermMemoryManager;
    }

    @Override
    public List<Integer> solve(double[][] initialDistanceMatrix) {
        double[][] currentDistanceMatrix = Arrays.stream(initialDistanceMatrix).map(double[]::clone).toArray(double[][]::new);
        TSPSolution currentSolution = new TSPSolution(startingAlgorithm.solve(initialDistanceMatrix), currentDistanceMatrix);
        TSPSolution bestSolution = currentSolution;

        System.out.println(SolutionGenerator.objectiveFunction(currentSolution.getSolution(), initialDistanceMatrix));

        do {
            TreeSet<TSPSolution> neighbourhood = neighbourhoodGenerator.generateNeighbourhood(currentSolution, currentDistanceMatrix);

            for(TSPSolution neighbourSolution : neighbourhood) {
                if(!tabuListManager.isTabu(neighbourSolution, neighbourhood, stopCondition.getIterationNumber())) {
                    currentSolution = neighbourSolution;

                    break;
                }
            }

            if(SolutionGenerator.objectiveFunction(bestSolution.getSolution(), initialDistanceMatrix) > SolutionGenerator.objectiveFunction(currentSolution.getSolution(), initialDistanceMatrix)) {
                bestSolution = currentSolution;
            }

            longTermMemoryManager.manage(currentSolution, bestSolution, neighbourhood, currentDistanceMatrix, stopCondition.getIterationNumber());
            tabuListManager.addToTabuList(currentSolution, neighbourhood, stopCondition.getIterationNumber());
        }
        while(!stopCondition.isStopped(currentSolution, bestSolution));

        return bestSolution.getSolution();
    }
}
