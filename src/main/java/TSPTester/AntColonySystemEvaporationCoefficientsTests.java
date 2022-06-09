package TSPTester;

import TSPLoader.TSPLoader;
import TSPSolver.AntColonyOptimization.AntColonySystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AntColonySystemEvaporationCoefficientsTests {
    public static void runTests(List<String> fileNames) throws IOException {
        String path = "C:\\Users\\123w4\\Desktop\\ACS\\";
        List<BufferedWriter> bufferedWriterList = new ArrayList<>();
        List<double[][]> distanceMatrices = new ArrayList<>();

        for (String fileName : fileNames) {
            bufferedWriterList.add(new BufferedWriter(new FileWriter(new File(path + fileName + "EvaporationCoefficients.csv"))));
            distanceMatrices.add(TSPLoader.loadDistanceMatrixFromFile(fileName + ".tsp"));
        }

        for(double i = 0.0; i <= 1.01; i += 0.1) {
            for(double j = 0.0; j <= 1.01; j += 0.1) {
                AntColonySystem antColonySystem = new AntColonySystem(20, i, j, 1, 0.9, 100, 1, 1, 1, true, false, false);

                for(int h = 0; h < fileNames.size(); h++) {
                    List<Double> results = new ArrayList<>();

                    for(int k = 0; k < 25; k++) {
                        System.out.println(fileNames.get(h) + " " + i + " " + j + " " + k);

                        results.add(antColonySystem.solve(distanceMatrices.get(h)).getObjectiveFunctionValue());
                    }

                    bufferedWriterList.get(h).write(results.stream().mapToDouble(x -> x).average().getAsDouble() + ",");
                }
            }

            for(BufferedWriter bufferedWriter : bufferedWriterList) {
                bufferedWriter.write("\n");
            }
        }

        for(BufferedWriter bufferedWriter : bufferedWriterList) {
            bufferedWriter.close();
        }
    }
}
