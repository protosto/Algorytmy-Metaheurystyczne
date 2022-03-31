
import TSPGenerator.SymmetricTableGenerator;
import TSPSolver.KRandomSolutionGenerator;
import TSPTester.DataFromFileTest;
import TSPTester.KValueTester;
import TSPTester.TimeAndQualityTester;
import java.io.IOException;
import java.util.List;

public class TSP {
    public static void main(String[] args) throws IOException {
        //TimeAndQualityTester timeAndQualityTester = new TimeAndQualityTester();
        //KValueTester kValueTester = new KValueTester();
        //DataFromFileTest dataFromFileTest = new DataFromFileTest();
        //timeAndQualityTester.test(50);
        //kValueTester.test(50);
        //dataFromFileTest.test();
        KRandomSolutionGenerator kRandomSolutionGenerator = new KRandomSolutionGenerator(50);
        SymmetricTableGenerator symmetricTableGenerator = new SymmetricTableGenerator();

        List<Integer> solution = kRandomSolutionGenerator.solve(symmetricTableGenerator.generateSymmetricTable(20, 50));

        for(Integer i : solution) {
            System.out.print(i + " w");
        }
    }
}
