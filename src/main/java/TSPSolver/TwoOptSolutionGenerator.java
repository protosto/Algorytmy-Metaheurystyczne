package TSPSolver;

import TSPSolver.TabuSearch.NeighbourhoodGenerator.AllSwapsNeighbourhoodGenerator;
import TSPSolver.TSPSolution.TSPSolution;

import java.util.TreeSet;

public class TwoOptSolutionGenerator extends SolutionGenerator {
    private final SolutionGenerator startingAlgorithm;

    public TwoOptSolutionGenerator(SolutionGenerator startingAlgorithm) {
        this.startingAlgorithm = startingAlgorithm;
    }

    @Override
    public TSPSolution solve(double[][] distanceMatrix) {
        TSPSolution minTSPSolution = startingAlgorithm.solve(distanceMatrix);
        AllSwapsNeighbourhoodGenerator allSwapsNeighbourhoodGenerator = new AllSwapsNeighbourhoodGenerator();

        do {
            TreeSet<TSPSolution> neighbourhood = allSwapsNeighbourhoodGenerator.generateNeighbourhood(minTSPSolution, distanceMatrix);

            if(neighbourhood.first().getObjectiveFunctionValue() < minTSPSolution.getObjectiveFunctionValue()) {
                minTSPSolution = neighbourhood.first();
            }
            else {
                break;
            }

        }
        while(true);

        return minTSPSolution;
    }
}
