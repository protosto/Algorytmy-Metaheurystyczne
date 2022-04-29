import TSPLoader.TSPLoader;
import TSPSolver.*;
import TSPSolver.TabuSearch.IntermediateTermMemoryManager.RestartOnPromisingAreaIntermediateMemoryManager;
import TSPSolver.TabuSearch.LongTermMemoryManager.FrequentEdgePenaltyLongTermMemoryManager;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.AllSwapsNeighbourhoodGenerator;
import TSPSolver.TabuSearch.NeighbourhoodManager.BestNonTabuNeighbourNeighbourhoodManager;
import TSPSolver.TabuSearch.StopCondition.NIterationsWithoutProgressStopCondition;
import TSPSolver.TabuSearch.StopCondition.NMillisecondsStopCondition;
import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuListManager.StaticTabuTenureTabuListManager;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;

import java.io.IOException;

public class TSP {
    public static void main(String[] args) throws IOException {
        TSPLoader tspLoader = new TSPLoader();
        KRandomSolutionGenerator kRandomSolutionGenerator = new KRandomSolutionGenerator(100);
        ClosestNeighbourMethod closestNeighbourMethod = new ClosestNeighbourMethod();
        ExtendedClosestNeighbourMethod extendedClosestNeighbourMethod = new ExtendedClosestNeighbourMethod();
        TwoOptSolutionGenerator twoOptSolutionGenerator = new TwoOptSolutionGenerator(extendedClosestNeighbourMethod);
        double[][] distanceMatrix = tspLoader.loadDistanceMatrixFromFile("rd100.tsp");

        TabuSearchSolutionGenerator tabuSearchSolutionGenerator = new TabuSearchSolutionGenerator(distanceMatrix, twoOptSolutionGenerator.solve(distanceMatrix), new NIterationsWithoutProgressStopCondition(1000), new AllSwapsNeighbourhoodGenerator(), new BestNonTabuNeighbourNeighbourhoodManager(), new StaticTabuTenureTabuListManager(14), new RestartOnPromisingAreaIntermediateMemoryManager(1.05), new FrequentEdgePenaltyLongTermMemoryManager(true, 0.05), 1);
        System.out.println(kRandomSolutionGenerator.solve(distanceMatrix).getObjectiveFunctionValue());
        System.out.println(closestNeighbourMethod.solve(distanceMatrix).getObjectiveFunctionValue());
        System.out.println(extendedClosestNeighbourMethod.solve(distanceMatrix).getObjectiveFunctionValue());
        System.out.println(twoOptSolutionGenerator.solve(distanceMatrix).getObjectiveFunctionValue());
        System.out.println("Global best: " + tabuSearchSolutionGenerator.solve(distanceMatrix).getObjectiveFunctionValue());
    }
}
