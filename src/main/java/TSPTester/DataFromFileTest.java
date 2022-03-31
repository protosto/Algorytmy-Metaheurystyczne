package TSPTester;

import TSPLoader.TSPLoader;
import TSPSolver.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFromFileTest {
//    String path = "C:\\Users\\123w4\\Desktop\\TSP\\BigFileTest\\";
//    BufferedWriter kRandomBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "kRandom.csv")));
//    BufferedWriter closestNeighbourPathBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "closestNeighbour.csv")));
//    BufferedWriter extendedClosestNeighbourPathBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "extendedClosestNeighbour.csv")));
//    BufferedWriter twoOptKRandomSolutionGeneratorStartBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "twoOptKRandomStart.csv")));
//    BufferedWriter twoOptClosestNeighbourMethodStartBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "twoOptClosestNeighbourStart.csv")));
//    BufferedWriter twoOptExtendedClosestNeighbourMethodStartBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "twoOptExtendedClosestNeighbourStart.csv")));

    public DataFromFileTest() throws IOException {
    }

    public void test() throws IOException {
        KRandomSolutionGenerator kRandomSolutionGenerator = new KRandomSolutionGenerator(5000);
        ClosestNeighbourMethod closestNeighbourMethod = new ClosestNeighbourMethod();
        ExtendedClosestNeighbourMethod extendedClosestNeighbourMethod = new ExtendedClosestNeighbourMethod();
        TwoOptSolutionGenerator twoOptSolutionGeneratorKRandomSolutionGeneratorStart = new TwoOptSolutionGenerator(kRandomSolutionGenerator);
        TwoOptSolutionGenerator twoOptSolutionGeneratorClosestNeighbourMethodStart = new TwoOptSolutionGenerator(closestNeighbourMethod);
        TwoOptSolutionGenerator twoOptSolutionGeneratorExtendedClosestNeighbourMethodStart = new TwoOptSolutionGenerator(extendedClosestNeighbourMethod);
        TSPLoader tspLoader = new TSPLoader();

        double[][] distanceMatrix = tspLoader.loadDistanceMatrixFromFile("pr1002.tsp");

        List<Integer> solution = new ArrayList<>();

        solution = kRandomSolutionGenerator.solve(distanceMatrix);
        System.out.println(SolutionGenerator.objectiveFunction(solution, distanceMatrix));

        solution = closestNeighbourMethod.solve(distanceMatrix);
        System.out.println(SolutionGenerator.objectiveFunction(solution, distanceMatrix));

        solution = extendedClosestNeighbourMethod.solve(distanceMatrix);
        System.out.println(SolutionGenerator.objectiveFunction(solution, distanceMatrix));

        solution = twoOptSolutionGeneratorKRandomSolutionGeneratorStart.solve(distanceMatrix);
        System.out.println(SolutionGenerator.objectiveFunction(solution, distanceMatrix));

        solution = twoOptSolutionGeneratorClosestNeighbourMethodStart.solve(distanceMatrix);
        System.out.println(SolutionGenerator.objectiveFunction(solution, distanceMatrix));

        solution = twoOptSolutionGeneratorExtendedClosestNeighbourMethodStart.solve(distanceMatrix);
        System.out.println(SolutionGenerator.objectiveFunction(solution, distanceMatrix));
    }
}
