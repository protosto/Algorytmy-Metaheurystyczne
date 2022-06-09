package TSPTester;

import TSPLoader.TSPLoader;
import TSPSolver.AntColonyOptimization.AntColonySystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AntColonySystemAntNumberTests {
    public static void runTests(List<String> fileNames) throws IOException {
        String path = "C:\\Users\\123w4\\Desktop\\ACS\\";
        List<BufferedWriter> bufferedWriterList = new ArrayList<>();
        List<double[][]> distanceMatrices = new ArrayList<>();

        for (String fileName : fileNames) {
            bufferedWriterList.add(new BufferedWriter(new FileWriter(new File(path + fileName + "AntNumber.csv"))));
            distanceMatrices.add(TSPLoader.loadDistanceMatrixFromFile(fileName + ".tsp"));
        }

        for(int k = 0; k < 25; k++) {
            for(int h = 0; h < fileNames.size(); h++) {
                for(int i = distanceMatrices.get(h).length / 10; i <= distanceMatrices.get(h).length; i += distanceMatrices.get(h).length / 10) {
                    AntColonySystem antColonySystem = new AntColonySystem(i, 0.1, 0.1, 1, 0.9, 100, 1, 1, 1, true, false, false);

                    System.out.println(fileNames.get(h) + " " + i + " " + k);
                    bufferedWriterList.get(h).write(antColonySystem.solve(distanceMatrices.get(h)).getObjectiveFunctionValue() + ",");
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
