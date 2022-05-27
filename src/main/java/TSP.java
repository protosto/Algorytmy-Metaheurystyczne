import TSPLoader.TSPLoader;
import TSPSolver.AntColonyOptimization.AntColonySystem;

import java.io.IOException;

public class TSP {
    public static void main(String[] args) throws IOException {
        double[][] distanceMatrix = new TSPLoader().loadDistanceMatrixFromFile("rd100.tsp");

        AntColonySystem antColonySystem = new AntColonySystem(10, 0.1, 0.1, 1, 0.9, 500, 1, 2, 1, true, true, true);
        antColonySystem.solve(distanceMatrix);
    }
}
