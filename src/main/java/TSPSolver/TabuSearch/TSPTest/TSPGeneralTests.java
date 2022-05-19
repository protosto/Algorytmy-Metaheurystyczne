package TSPSolver.TabuSearch.TSPTest;

import TSPGenerator.TSPGenerator;
import TSPSolver.SolutionGenerator;
import TSPSolver.TSPSolution.TSPSolution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TSPGeneralTests {
    private String path;
    private List<SolutionGenerator> testedAlgorithms;
    private List<String> fileNames;
    private Integer pointCountStart;
    private Integer pointCountEnd;
    private Integer pointCountStep;
    private Integer numberOfRepeats;
    private TSPGenerator tspGenerator;

    public TSPGeneralTests(String path, List<SolutionGenerator> testedAlgorithms, List<String> fileNames, Integer pointCountStart, Integer pointCountEnd, Integer pointCountStep, Integer numberOfRepeats, TSPGenerator tspGenerator) {
        this.path = path;
        this.testedAlgorithms = testedAlgorithms;
        this.fileNames = fileNames;
        this.pointCountStart = pointCountStart;
        this.pointCountEnd = pointCountEnd;
        this.pointCountStep = pointCountStep;
        this.numberOfRepeats = numberOfRepeats;
        this.tspGenerator = tspGenerator;
    }

    public void runTests() throws IOException {
        List<BufferedWriter> bufferedWriterListObjectiveFunctionValue = new ArrayList<>();
        List<BufferedWriter> bufferedWriterListTime = new ArrayList<>();
        int testNum = numberOfRepeats * ((pointCountEnd - pointCountStart) / pointCountStep + 1) * testedAlgorithms.size();

        for (int i = 0; i < fileNames.size(); i++) {
            bufferedWriterListObjectiveFunctionValue.add(new BufferedWriter(new FileWriter(new File(path + "ofv" + fileNames.get(i)))));
            bufferedWriterListTime.add(new BufferedWriter(new FileWriter(new File(path + "time" + fileNames.get(i)))));
        }

        for (int i = 0; i < numberOfRepeats; i++) {
            for (int j = pointCountStart; j <= pointCountEnd; j += pointCountStep) {
                double[][] distanceMatrix = tspGenerator.generate(j, 50);

                for (int k = 0; k < testedAlgorithms.size(); k++) {
                    long startTime = System.nanoTime();
                    TSPSolution tspSolution = testedAlgorithms.get(k).solve(distanceMatrix);
                    long finishTime = System.nanoTime() - startTime;

                    bufferedWriterListObjectiveFunctionValue.get(k).write(tspSolution.getObjectiveFunctionValue() + ",");
                    bufferedWriterListTime.get(k).write(finishTime + ",");

                    System.out.println((1 + i * ((pointCountEnd - pointCountStart) / pointCountStep + 1) * testedAlgorithms.size() + (j / pointCountStep - 1) * testedAlgorithms.size() + k) + "/" + testNum);
                }
            }

            for (int j = 0; j < fileNames.size(); j++) {
                bufferedWriterListObjectiveFunctionValue.get(j).write("\n");
                bufferedWriterListTime.get(j).write("\n");
            }
        }

        for (int i = 0; i < fileNames.size(); i++) {
            bufferedWriterListObjectiveFunctionValue.get(i).close();
            bufferedWriterListTime.get(i).close();
        }
    }
}