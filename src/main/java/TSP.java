import TSPLoader.TSPLoader;
import TSPSolver.ExtendedClosestNeighbourMethod;
import TSPSolver.SolutionGenerator;
import TSPSolver.TabuSearch.LongTermMemoryManager.FrequentEdgePenaltyLongTermMemoryManager;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.AllSwapsNeighbourhoodGenerator;
import TSPSolver.TabuSearch.StopCondition.NIterationsStopCondition;
import TSPSolver.TabuSearch.StopCondition.NIterationsWithoutProgressStopCondition;
import TSPSolver.TabuSearch.TabuListManager.StaticTabuTenureTabuListManager;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;
import TSPSolver.TwoOptSolutionGenerator;

import java.io.IOException;

public class TSP {
    public static void main(String[] args) throws IOException {
        TSPLoader tspLoader = new TSPLoader();
        double[][] distanceMatrix = tspLoader.loadDistanceMatrixFromFile("rd100.tsp");
        TabuSearchSolutionGenerator tabuSearchSolutionGenerator = new TabuSearchSolutionGenerator(new TwoOptSolutionGenerator(new ExtendedClosestNeighbourMethod()), new NIterationsWithoutProgressStopCondition(10000), new AllSwapsNeighbourhoodGenerator(), new StaticTabuTenureTabuListManager(9), new FrequentEdgePenaltyLongTermMemoryManager(true, 1));

        System.out.println(SolutionGenerator.objectiveFunction(tabuSearchSolutionGenerator.solve(distanceMatrix), distanceMatrix));
    }
}
