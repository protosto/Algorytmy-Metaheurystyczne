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

public class TSPThreadsTests {
    private String path;
    private String fileName;
    private Integer threadNumberStart;
    private Integer threadNumberEnd;
    private Integer threadNumberStep;
    private Integer numberOfRepeats;
    private TSPGenerator tspGenerator;

    public TSPThreadsTests(String path, String fileName, Integer threadNumberStart, Integer threadNumberEnd, Integer threadNumberStep, Integer numberOfRepeats, TSPGenerator tspGenerator) {
        this.path = path;
        this.fileName = fileName;
        this.threadNumberStart = threadNumberStart;
        this.threadNumberEnd = threadNumberEnd;
        this.threadNumberStep = threadNumberStep;
        this.numberOfRepeats = numberOfRepeats;
        this.tspGenerator = tspGenerator;
    }

    public void runTests() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(path + fileName)));
        TabuSearchSolutionGenerator tabuSearchSolutionGenerator = new TabuSearchSolutionGenerator(new KRandomSolutionGenerator(1), new NIterationsWithoutProgressStopCondition(100), new AllSwapsNeighbourhoodGenerator(), new BestNonTabuNeighbourNeighbourhoodManager(), new StaticTabuTenureTabuListManager(9), null, null, 1);

        int testNum = numberOfRepeats * ((threadNumberEnd - threadNumberStart) / threadNumberStep + 1);

        for (int i = 0; i < numberOfRepeats; i++) {
            double[][] distanceMatrix = tspGenerator.generate(50, 50);

            for (int j = threadNumberStart; j <= threadNumberEnd; j += threadNumberStep) {
                tabuSearchSolutionGenerator.setNumberOfStartingThreads(j);
                TSPSolution tspSolution = tabuSearchSolutionGenerator.solve(distanceMatrix);
                bufferedWriter.write(tspSolution.getObjectiveFunctionValue() + ",");

                System.out.println((1 + i * ((threadNumberEnd - threadNumberStart) / threadNumberStep + 1) + (j / threadNumberStep - 1)) + "/" + testNum);
            }

            bufferedWriter.write("\n");
        }

        bufferedWriter.close();
    }
}
