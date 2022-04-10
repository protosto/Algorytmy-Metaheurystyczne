import TSPLoader.TSPLoader;
import TSPSolver.ClosestNeighbourMethod;
import TSPSolver.SolutionGenerator;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.AllSwapsNeighbourhoodGenerator;
import TSPSolver.TabuSearch.StopCondition.NIterationsStopCondition;
import TSPSolver.TabuSearch.TabuListManager.StaticExpirationTimeTabuListManager;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;

import java.io.IOException;

public class TSP {
    public static void main(String[] args) throws IOException {
        TSPLoader tspLoader = new TSPLoader();
        TabuSearchSolutionGenerator tabuSearchSolutionGenerator = new TabuSearchSolutionGenerator(new ClosestNeighbourMethod(), new NIterationsStopCondition(100), new AllSwapsNeighbourhoodGenerator(), new StaticExpirationTimeTabuListManager(10));

        System.out.println(SolutionGenerator.objectiveFunction(tabuSearchSolutionGenerator.solve(tspLoader.loadDistanceMatrixFromFile("rd100.tsp")) ,tspLoader.loadDistanceMatrixFromFile("rd100.tsp")));
    }
}
