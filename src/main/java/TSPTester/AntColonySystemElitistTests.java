package TSPTester;

import TSPLoader.TSPLoader;
import TSPSolver.AntColonyOptimization.AntColonySystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AntColonySystemElitistTests {
    public static void runTests(List<String> fileNames) throws IOException {
        String path = "C:\\Users\\123w4\\Desktop\\ACS\\";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(path + "Elitist.csv")));
        List<double[][]> distanceMatrices = new ArrayList<>();

        for (String fileName : fileNames) {
            distanceMatrices.add(TSPLoader.loadDistanceMatrixFromFile(fileName + ".tsp"));
        }

        for(int k = 0; k < 25; k++) {
            AntColonySystem antColonySystem = new AntColonySystem(20, 0.3, 0.3, 1, 0.9, 500, 1, 2, 1, true, false, true);

            for(int h = 0; h < fileNames.size(); h++) {
                System.out.println(fileNames.get(h) + " " + k);
                bufferedWriter.write(antColonySystem.solve(distanceMatrices.get(h)).getObjectiveFunctionValue() + ",");
            }

            bufferedWriter.write("\n");
        }

        bufferedWriter.close();
    }
}
