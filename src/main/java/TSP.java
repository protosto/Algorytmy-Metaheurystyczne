import TSPLoader.TSPLoader;
import TSPSolver.ExtendedClosestNeighbourMethod;
import TSPSolver.KRandomSolutionGenerator;
import TSPSolver.SolutionGenerator;
import TSPSolver.TabuSearch.IntermediateTermMemoryManager.RestartOnPromisingAreaIntermediateMemoryManager;
import TSPSolver.TabuSearch.LongTermMemoryManager.FrequentEdgePenaltyLongTermMemoryManager;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.AllSwapsNeighbourhoodGenerator;
import TSPSolver.TabuSearch.NeighbourhoodManager.BestNonTabuNeighbourNeighbourhoodManager;
import TSPSolver.TabuSearch.StopCondition.NIterationsWithoutProgressStopCondition;
import TSPSolver.TabuSearch.StopCondition.NMillisecondsStopCondition;
import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuListManager.StaticTabuTenureTabuListManager;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;
import TSPSolver.TwoOptSolutionGenerator;

import java.io.IOException;

public class TSP {
    public static void main(String[] args) throws IOException {
        TSPLoader tspLoader = new TSPLoader();
        double[][] distanceMatrix = tspLoader.loadDistanceMatrixFromFile("rd100.tsp");

        TabuSearchSolutionGenerator tabuSearchSolutionGenerator = new TabuSearchSolutionGenerator(distanceMatrix, new TSPSolution(new KRandomSolutionGenerator(1).solve(distanceMatrix), distanceMatrix), new NIterationsWithoutProgressStopCondition(1000), new AllSwapsNeighbourhoodGenerator(), new BestNonTabuNeighbourNeighbourhoodManager(), new StaticTabuTenureTabuListManager(14), null, new FrequentEdgePenaltyLongTermMemoryManager(true, 0.05), 1);

        System.out.println("Global best: " + SolutionGenerator.objectiveFunction(tabuSearchSolutionGenerator.solve(distanceMatrix), distanceMatrix));
    }
}
