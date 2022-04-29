package TSPSolver.TabuSearch.TabuSearchTest;

import TSPGenerator.TSPGenerator;
import TSPSolver.SolutionGenerator;
import TSPSolver.TabuSearch.TabuSearchSolutionGenerator;

import java.util.List;

public class TabuSearchQualityTests {
    private static String path = "C:\\Users\\123w4\\Desktop\\TabuSearch\\";
    private List<SolutionGenerator> testedAlgorithms;
    private Integer pointCountStart;
    private Integer pointCountEnd;
    private Integer pointCountStep;
    private Integer numberOfRepeats;
    private TSPGenerator tspGenerator;

        public TabuSearchQualityTests(List<SolutionGenerator> testedAlgorithms, Integer pointCountStart, Integer pointCountEnd, Integer pointCountStep, Integer numberOfRepeats, TSPGenerator tspGenerator, String fileName) {
        this.testedAlgorithms = testedAlgorithms;
        this.pointCountStart = pointCountStart;
        this.pointCountEnd = pointCountEnd;
        this.pointCountStep = pointCountStep;
        this.numberOfRepeats = numberOfRepeats;
        this.tspGenerator = tspGenerator;
    }
}
