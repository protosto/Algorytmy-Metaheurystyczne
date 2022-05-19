import TSPLoader.TSPLoader;
import TSPSolver.AntColonyOptimization.AntColonySystem;
import TSPSolver.ExtendedClosestNeighbourMethod;
import TSPSolver.TwoOptSolutionGenerator;

import java.io.IOException;

public class TSP {
    public static void main(String[] args) throws IOException {
        double[][] distanceMatrix = new TSPLoader().loadDistanceMatrixFromFile("pr1002.tsp");

        AntColonySystem antColonySystem = new AntColonySystem(100, 0.1, 0.1, 1, 0.9, 1000, 1, 2, 1, true, true);
        TwoOptSolutionGenerator twoOptSolutionGenerator = new TwoOptSolutionGenerator(new ExtendedClosestNeighbourMethod());
        System.out.println(twoOptSolutionGenerator.solve(distanceMatrix).getObjectiveFunctionValue());
        System.out.println(antColonySystem.solve(distanceMatrix).getObjectiveFunctionValue());
    }
}
