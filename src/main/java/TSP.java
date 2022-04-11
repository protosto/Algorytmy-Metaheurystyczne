import TSPLoader.TSPLoader;
import TSPSolver.ClosestNeighbourMethod;
import TSPSolver.ExtendedClosestNeighbourMethod;
import TSPSolver.SolutionGenerator;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.AllNeighbouringSwapsNeighbourhoodGenerator;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.AllSwapsNeighbourhoodGenerator;
import TSPSolver.TabuSearch.StopCondition.NIterationsStopCondition;
import TSPSolver.TabuSearch.TabuListManager.StaticExpirationTimeTabuListManager;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;
import TSPSolver.TwoOptSolutionGenerator;

import java.io.IOException;

public class TSP {
    public static void main(String[] args) throws IOException {
        TSPLoader tspLoader = new TSPLoader();
        double[][] distanceMatrix = tspLoader.loadDistanceMatrixFromFile("rd100.tsp");
        TabuSearchSolutionGenerator tabuSearchSolutionGenerator = new TabuSearchSolutionGenerator(new TwoOptSolutionGenerator(new ExtendedClosestNeighbourMethod()), new NIterationsStopCondition(1000), new AllSwapsNeighbourhoodGenerator(), new StaticExpirationTimeTabuListManager(10));

        System.out.println(SolutionGenerator.objectiveFunction(tabuSearchSolutionGenerator.solve(distanceMatrix), distanceMatrix));
    }
}
