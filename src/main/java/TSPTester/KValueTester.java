package TSPTester;

import TSPGenerator.SymmetricTableGenerator;
import TSPSolver.KRandomSolutionGenerator;
import TSPSolver.SolutionGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class KValueTester {
    String path = "C:\\Users\\123w4\\Desktop\\TSP\\";
    BufferedWriter kRandomBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "kValueTest.csv")));

    public KValueTester() throws IOException {
    }

    public void test(int k) throws IOException {
        KRandomSolutionGenerator kRandomSolutionGenerator = new KRandomSolutionGenerator(1);
        SymmetricTableGenerator symmetricTableGenerator = new SymmetricTableGenerator();
        double[][] testMatrix;
        List<Integer> solution;

        for(int i = 0; i < k; i++) {
            testMatrix = symmetricTableGenerator.generateSymmetricTable(200,50);

            for(int j = 100; j <= 10000; j += 100) {
                kRandomSolutionGenerator.setK(j);
                solution = kRandomSolutionGenerator.solve(testMatrix);
                kRandomBufferedWriter.write(SolutionGenerator.objectiveFunction(solution, testMatrix) + ",");
            }

            kRandomBufferedWriter.write("\n");
        }

        kRandomBufferedWriter.close();
    }
}
