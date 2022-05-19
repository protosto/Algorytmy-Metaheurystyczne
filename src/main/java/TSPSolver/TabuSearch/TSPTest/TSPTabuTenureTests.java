package TSPSolver.TabuSearch.TSPTest;

import TSPGenerator.TSPGenerator;
import TSPSolver.KRandomSolutionGenerator;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.AllSwapsNeighbourhoodGenerator;
import TSPSolver.TabuSearch.NeighbourhoodManager.BestNonTabuNeighbourNeighbourhoodManager;
import TSPSolver.TabuSearch.StopCondition.NIterationsWithoutProgressStopCondition;
import TSPSolver.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuListManager.StaticTabuTenureTabuListManager;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TSPTabuTenureTests {
    private String path;
    private String fileName;
    private Integer tabuTenureStart;
    private Integer tabuTenureEnd;
    private Integer tabuTenureStep;
    private Integer numberOfRepeats;
    private TSPGenerator tspGenerator;

    public TSPTabuTenureTests(String path, String fileName, Integer tabuTenureStart, Integer tabuTenureEnd, Integer tabuTenureStep, Integer numberOfRepeats, TSPGenerator tspGenerator) {
        this.path = path;
        this.fileName = fileName;
        this.tabuTenureStart = tabuTenureStart;
        this.tabuTenureEnd = tabuTenureEnd;
        this.tabuTenureStep = tabuTenureStep;
        this.numberOfRepeats = numberOfRepeats;
        this.tspGenerator = tspGenerator;
    }

    public void runTests() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(path + fileName)));
        TabuSearchSolutionGenerator tabuSearchSolutionGenerator = new TabuSearchSolutionGenerator(new NIterationsWithoutProgressStopCondition(100), new AllSwapsNeighbourhoodGenerator(), new BestNonTabuNeighbourNeighbourhoodManager(), new StaticTabuTenureTabuListManager(9), null, null, 1);

        int testNum = numberOfRepeats * ((tabuTenureEnd - tabuTenureStart) / tabuTenureStep + 1);

        for (int i = 0; i < numberOfRepeats; i++) {
            double[][] distanceMatrix = tspGenerator.generate(50, 50);
            KRandomSolutionGenerator kRandomSolutionGenerator = new KRandomSolutionGenerator(1);
            tabuSearchSolutionGenerator.setInitialSolution(kRandomSolutionGenerator.solve(distanceMatrix));

            for (int j = tabuTenureStart; j <= tabuTenureEnd; j += tabuTenureStep) {
                ((StaticTabuTenureTabuListManager) tabuSearchSolutionGenerator.getTabuListManager()).setTabuTenure(j);
                TSPSolution tspSolution = tabuSearchSolutionGenerator.solve(distanceMatrix);
                bufferedWriter.write(tspSolution.getObjectiveFunctionValue() + ",");

                System.out.println((1 + i * ((tabuTenureEnd - tabuTenureStart) / tabuTenureStep + 1) + (j / tabuTenureStep - 1)) + "/" + testNum);
            }

            bufferedWriter.write("\n");
        }

        bufferedWriter.close();
    }
}
