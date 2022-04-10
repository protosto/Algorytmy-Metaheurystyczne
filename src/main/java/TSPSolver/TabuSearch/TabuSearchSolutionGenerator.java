package TSPSolver.TabuSearch;

import TSPSolver.SolutionGenerator;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.NeighbourhoodGenerator;
import TSPSolver.TabuSearch.StopCondition.StopCondition;
import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuListManager.TabuListManager;

import java.util.List;
import java.util.TreeSet;

public class TabuSearchSolutionGenerator extends SolutionGenerator {
    private SolutionGenerator startingAlgorithm;
    private StopCondition stopCondition;
    private NeighbourhoodGenerator neighbourhoodGenerator;
    private TabuListManager tabuListManager;

    public TabuSearchSolutionGenerator(SolutionGenerator startingAlgorithm, StopCondition stopCondition, NeighbourhoodGenerator neighbourhoodGenerator, TabuListManager tabuListManager) {
        this.startingAlgorithm = startingAlgorithm;
        this.stopCondition = stopCondition;
        this.neighbourhoodGenerator = neighbourhoodGenerator;
        this.tabuListManager = tabuListManager;
    }

    @Override
    public List<Integer> solve(double[][] distanceTable) {
        TSPSolution.setDistanceMatrix(distanceTable);
        TSPSolution solution = new TSPSolution(startingAlgorithm.solve(distanceTable));

        System.out.println(SolutionGenerator.objectiveFunction(solution.getSolution(), distanceTable));

        do {
            TreeSet<TSPSolution> neighbourhood = neighbourhoodGenerator.generateNeighbourhood(solution);

            for(TSPSolution neighbourSolution : neighbourhood) {
                if(!tabuListManager.isTabu(neighbourSolution, stopCondition.getIterationNumber())) {
                    solution = neighbourSolution;

                    break;
                }

                tabuListManager.addToTabuList(solution, stopCondition.getIterationNumber());
            }
        }
        while(!stopCondition.isStopped());

        return solution.getSolution();
    }
}
