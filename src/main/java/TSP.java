import TSPLoader.TSPLoader;
import TSPSolver.ExtendedClosestNeighbourMethod;
import TSPSolver.KRandomSolutionGenerator;
import TSPSolver.SolutionGenerator;
import TSPSolver.TabuSearch.IntermediateTermMemoryManager.RestartOnPromisingAreaIntermediateMemoryManager;
import TSPSolver.TabuSearch.LongTermMemoryManager.FrequentEdgePenaltyLongTermMemoryManager;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.AllSwapsNeighbourhoodGenerator;
import TSPSolver.TabuSearch.NeighbourhoodManager.BestNonTabuNeighbourNeighbourhoodManager;
import TSPSolver.TabuSearch.StopCondition.NIterationsWithoutProgressStopCondition;
import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuListManager.StaticTabuTenureTabuListManager;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;
import TSPSolver.TwoOptSolutionGenerator;

import java.io.IOException;

public class TSP {
    public static void main(String[] args) throws IOException {
        TSPLoader tspLoader = new TSPLoader();
        double[][] distanceMatrix = tspLoader.loadDistanceMatrixFromFile("rd100.tsp");

        TabuSearchSolutionGenerator tabuSearchSolutionGenerator1 = new TabuSearchSolutionGenerator(distanceMatrix, new TSPSolution(new KRandomSolutionGenerator(1).solve(distanceMatrix), distanceMatrix), new NIterationsWithoutProgressStopCondition(1000), new AllSwapsNeighbourhoodGenerator(), new BestNonTabuNeighbourNeighbourhoodManager(), new StaticTabuTenureTabuListManager(14), new RestartOnPromisingAreaIntermediateMemoryManager(1.3), new FrequentEdgePenaltyLongTermMemoryManager(true, 10));
        Thread thread1 = new Thread(tabuSearchSolutionGenerator1);
        TabuSearchSolutionGenerator tabuSearchSolutionGenerator2 = new TabuSearchSolutionGenerator(distanceMatrix, new TSPSolution(new KRandomSolutionGenerator(1).solve(distanceMatrix), distanceMatrix), new NIterationsWithoutProgressStopCondition(1000), new AllSwapsNeighbourhoodGenerator(), new BestNonTabuNeighbourNeighbourhoodManager(), new StaticTabuTenureTabuListManager(14), new RestartOnPromisingAreaIntermediateMemoryManager(1.3), new FrequentEdgePenaltyLongTermMemoryManager(true, 10));
        Thread thread2 = new Thread(tabuSearchSolutionGenerator2);
        TabuSearchSolutionGenerator tabuSearchSolutionGenerator3 = new TabuSearchSolutionGenerator(distanceMatrix, new TSPSolution(new KRandomSolutionGenerator(1).solve(distanceMatrix), distanceMatrix), new NIterationsWithoutProgressStopCondition(1000), new AllSwapsNeighbourhoodGenerator(), new BestNonTabuNeighbourNeighbourhoodManager(), new StaticTabuTenureTabuListManager(14), new RestartOnPromisingAreaIntermediateMemoryManager(1.3), new FrequentEdgePenaltyLongTermMemoryManager(true, 10));
        Thread thread3 = new Thread(tabuSearchSolutionGenerator3);
        TabuSearchSolutionGenerator tabuSearchSolutionGenerator4 = new TabuSearchSolutionGenerator(distanceMatrix, new TSPSolution(new KRandomSolutionGenerator(1).solve(distanceMatrix), distanceMatrix), new NIterationsWithoutProgressStopCondition(1000), new AllSwapsNeighbourhoodGenerator(), new BestNonTabuNeighbourNeighbourhoodManager(), new StaticTabuTenureTabuListManager(14), new RestartOnPromisingAreaIntermediateMemoryManager(1.3), new FrequentEdgePenaltyLongTermMemoryManager(true, 10));
        Thread thread4 = new Thread(tabuSearchSolutionGenerator4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        while(thread1.isAlive() || thread2.isAlive() || thread3.isAlive() || thread4.isAlive()) {

        }

        System.out.println("Global best: " + TabuSearchSolutionGenerator.getGlobalBestSolution().getObjectiveFunctionValue());
    }
}
