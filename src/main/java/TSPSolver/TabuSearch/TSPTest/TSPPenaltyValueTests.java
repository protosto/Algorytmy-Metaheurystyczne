package TSPSolver.TabuSearch.TSPTest;

import TSPGenerator.TSPGenerator;
import TSPSolver.KRandomSolutionGenerator;
import TSPSolver.TabuSearch.IntermediateTermMemoryManager.RestartOnPromisingAreaIntermediateMemoryManager;
import TSPSolver.TabuSearch.LongTermMemoryManager.FrequentEdgePenaltyLongTermMemoryManager;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.AllSwapsNeighbourhoodGenerator;
import TSPSolver.TabuSearch.NeighbourhoodManager.BestNonTabuNeighbourNeighbourhoodManager;
import TSPSolver.TabuSearch.StopCondition.NIterationsWithoutProgressStopCondition;
import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuListManager.StaticTabuTenureTabuListManager;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TSPPenaltyValueTests {
    private String path;
    private String fileName;
    private Double penaltyStart;
    private Double penaltyEnd;
    private Double penaltyStep;
    private Integer numberOfRepeats;
    private TSPGenerator tspGenerator;
    private boolean isSymmetrical;

    public TSPPenaltyValueTests(String path, String fileName, Double penaltyStart, Double penaltyEnd, Double penaltyStep, Integer numberOfRepeats, TSPGenerator tspGenerator, boolean isSymmetrical) {
        this.path = path;
        this.fileName = fileName;
        this.penaltyStart = penaltyStart;
        this.penaltyEnd = penaltyEnd;
        this.penaltyStep = penaltyStep;
        this.numberOfRepeats = numberOfRepeats;
        this.tspGenerator = tspGenerator;
        this.isSymmetrical = isSymmetrical;
    }

    public void runTests() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(path + fileName)));
        TabuSearchSolutionGenerator tabuSearchSolutionGenerator = new TabuSearchSolutionGenerator(new NIterationsWithoutProgressStopCondition(100), new AllSwapsNeighbourhoodGenerator(), new BestNonTabuNeighbourNeighbourhoodManager(), new StaticTabuTenureTabuListManager(9), new RestartOnPromisingAreaIntermediateMemoryManager(1.1), new FrequentEdgePenaltyLongTermMemoryManager(isSymmetrical, 1), 1);

        int testNum = numberOfRepeats * (int) ((penaltyEnd - penaltyStart) / penaltyStep + 1);

        for (int i = 0; i < numberOfRepeats; i++) {
            double[][] distanceMatrix = tspGenerator.generate(50, 50);
            KRandomSolutionGenerator kRandomSolutionGenerator = new KRandomSolutionGenerator(1);
            tabuSearchSolutionGenerator.setInitialSolution(kRandomSolutionGenerator.solve(distanceMatrix));

            for (double j = penaltyStart; j <= penaltyEnd + 0.01; j += penaltyStep) {
                ((FrequentEdgePenaltyLongTermMemoryManager) tabuSearchSolutionGenerator.getLongTermMemoryManager()).setPenaltyValue(j);
                TSPSolution tspSolution = tabuSearchSolutionGenerator.solve(distanceMatrix);
                bufferedWriter.write(tspSolution.getObjectiveFunctionValue() + ",");

                System.out.println((1 + i * (int) ((penaltyEnd - penaltyStart) / penaltyStep + 1) + (int) (j / penaltyStep - 1)) + "/" + testNum);
            }

            bufferedWriter.write("\n");
        }

        bufferedWriter.close();
    }
}
