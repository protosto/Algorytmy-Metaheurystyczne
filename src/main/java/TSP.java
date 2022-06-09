import TSPLoader.TSPLoader;
import TSPSolver.AntColonyOptimization.AntColonySystem;
import TSPSolver.ExtendedClosestNeighbourMethod;
import TSPSolver.SolutionGenerator;
import TSPSolver.TabuSearch.IntermediateTermMemoryManager.RestartOnPromisingAreaIntermediateMemoryManager;
import TSPSolver.TabuSearch.LongTermMemoryManager.FrequentEdgePenaltyLongTermMemoryManager;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.AllVertexInsertionNeighbourhoodGenerator;
import TSPSolver.TabuSearch.NeighbourhoodManager.BestNonTabuNeighbourNeighbourhoodManager;
import TSPSolver.TabuSearch.StopCondition.NIterationsWithoutProgressStopCondition;
import TSPSolver.TabuSearch.TabuListManager.StaticTabuTenureTabuListManager;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;
import TSPSolver.TwoOptSolutionGenerator;
import TSPTester.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TSP {
    public static void main(String[] args) throws IOException {
        AntColonySystem antColonySystem = new AntColonySystem(20, 0.3, 0.3, 1, 0.9, 10000, 1, 2, 1, true, true, false);

        List<String> testedFiles = new ArrayList<>();
        testedFiles.add("gr24");
        testedFiles.add("bayg29");
        testedFiles.add("berlin52");
        testedFiles.add("pr76");
        testedFiles.add("rd100");
        testedFiles.add("lin105");
        testedFiles.add("pr107");
        testedFiles.add("bier127");
        testedFiles.add("ch130");
        testedFiles.add("ch150");

        double[][] distanceMatrix = TSPLoader.loadDistanceMatrixFromFile("rd100.tsp");

        antColonySystem.solve(distanceMatrix);

//        AntColonySystemBasicTests.runTests(testedFiles);
//        AntColonySystemLocalSearchTests.runTests(testedFiles);
//        AntColonySystemElitistTests.runTests(testedFiles);
//        AntColonySystemLocalSearchElitistTests.runTests(testedFiles);
//        AntColonySystemAntNumberTests.runTests(testedFiles);
//        AntColonySystemExploitationProbabilityTests.runTests(testedFiles);
//        AntColonySystemEvaporationCoefficientsTests.runTests(testedFiles);
//        AntColonySystemAlphaBetaTests.runTests(testedFiles);
    }
}
