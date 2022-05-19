import TSPLoader.TSPLoader;
import TSPSolver.AntColonyOptimization.AntSystem;
import TSPSolver.ExtendedClosestNeighbourMethod;
import TSPSolver.TwoOptSolutionGenerator;

import java.io.IOException;

public class TSP {
    public static void main(String[] args) throws IOException {
        double[][] distanceMatrix = new TSPLoader().loadDistanceMatrixFromFile("rd100.tsp");

        AntSystem antSystem = new AntSystem(0.5, 0.5, 1.5, 50);
        ExtendedClosestNeighbourMethod extendedClosestNeighbourMethod = new ExtendedClosestNeighbourMethod();
        System.out.println(extendedClosestNeighbourMethod.solve(distanceMatrix).getObjectiveFunctionValue());
        System.out.println(antSystem.solve(distanceMatrix).getObjectiveFunctionValue());
    }
}
