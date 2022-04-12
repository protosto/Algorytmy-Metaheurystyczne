package TSPSolver.TabuSearch;

import TSPSolver.SolutionGenerator;
import TSPSolver.TabuSearch.LongTermMemoryManager.LongTermMemoryManager;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.NeighbourhoodGenerator;
import TSPSolver.TabuSearch.NeighbourhoodManager.NeighbourhoodManager;
import TSPSolver.TabuSearch.StopCondition.StopCondition;
import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuListManager.TabuListManager;

import java.util.Arrays;
import java.util.TreeSet;

public class TabuSearchSolutionGenerator extends Thread {
    private SolutionGenerator startingAlgorithm;
    private StopCondition stopCondition;
    private NeighbourhoodGenerator neighbourhoodGenerator;
    private NeighbourhoodManager neighbourhoodManager;
    private TabuListManager tabuListManager;
    private LongTermMemoryManager longTermMemoryManager;
    private double[][] initialDistanceMatrix;
    private TSPSolution localBestSolution;
    private volatile static TSPSolution globalBestSolution = null;

    public TabuSearchSolutionGenerator(double[][] initialDistanceMatrix, SolutionGenerator startingAlgorithm, StopCondition stopCondition, NeighbourhoodGenerator neighbourhoodGenerator, NeighbourhoodManager neighbourhoodManager, TabuListManager tabuListManager, LongTermMemoryManager longTermMemoryManager) {
        this.initialDistanceMatrix = initialDistanceMatrix;
        this.startingAlgorithm = startingAlgorithm;
        this.stopCondition = stopCondition;
        this.neighbourhoodGenerator = neighbourhoodGenerator;
        this.neighbourhoodManager = neighbourhoodManager;
        this.tabuListManager = tabuListManager;
        this.longTermMemoryManager = longTermMemoryManager;
    }

    public synchronized void solve() {
        double[][] currentDistanceMatrix = Arrays.stream(initialDistanceMatrix).map(double[]::clone).toArray(double[][]::new);
        TSPSolution currentSolution = new TSPSolution(startingAlgorithm.solve(initialDistanceMatrix), currentDistanceMatrix);
        localBestSolution = currentSolution;

        if(globalBestSolution == null) {
            globalBestSolution = currentSolution;
        }
        else if(globalBestSolution.getObjectiveFunctionValue() > currentSolution.getObjectiveFunctionValue()) {
            globalBestSolution = currentSolution;
        }

        System.out.println(SolutionGenerator.objectiveFunction(currentSolution.getSolution(), initialDistanceMatrix));

        do {
            TreeSet<TSPSolution> neighbourhood = neighbourhoodGenerator.generateNeighbourhood(currentSolution, currentDistanceMatrix);

            currentSolution = neighbourhoodManager.chooseNeighbour(neighbourhood, tabuListManager, stopCondition.getIterationNumber());
            currentSolution.setDistanceMatrix(initialDistanceMatrix);
            currentSolution.updateObjectiveFunctionValue();

            if(localBestSolution.getObjectiveFunctionValue() > currentSolution.getObjectiveFunctionValue()) {
                localBestSolution = currentSolution;

                if(globalBestSolution.getObjectiveFunctionValue() > currentSolution.getObjectiveFunctionValue()) {
                    globalBestSolution = currentSolution;
                }
            }

            longTermMemoryManager.manage(currentSolution, localBestSolution, neighbourhood, currentDistanceMatrix, stopCondition.getIterationNumber());
            tabuListManager.addToTabuList(currentSolution, neighbourhood, stopCondition.getIterationNumber());
        }
        while(!stopCondition.isStopped(currentSolution, localBestSolution));
    }

    @Override
    public void run() {
        solve();
    }

    public void reset() {
        globalBestSolution = null;
        stopCondition.reset();
        tabuListManager.reset();
    }

    public SolutionGenerator getStartingAlgorithm() {
        return startingAlgorithm;
    }

    public void setStartingAlgorithm(SolutionGenerator startingAlgorithm) {
        this.startingAlgorithm = startingAlgorithm;
    }

    public StopCondition getStopCondition() {
        return stopCondition;
    }

    public void setStopCondition(StopCondition stopCondition) {
        this.stopCondition = stopCondition;
    }

    public NeighbourhoodGenerator getNeighbourhoodGenerator() {
        return neighbourhoodGenerator;
    }

    public void setNeighbourhoodGenerator(NeighbourhoodGenerator neighbourhoodGenerator) {
        this.neighbourhoodGenerator = neighbourhoodGenerator;
    }

    public TabuListManager getTabuListManager() {
        return tabuListManager;
    }

    public void setTabuListManager(TabuListManager tabuListManager) {
        this.tabuListManager = tabuListManager;
    }

    public LongTermMemoryManager getLongTermMemoryManager() {
        return longTermMemoryManager;
    }

    public void setLongTermMemoryManager(LongTermMemoryManager longTermMemoryManager) {
        this.longTermMemoryManager = longTermMemoryManager;
    }

    public double[][] getInitialDistanceMatrix() {
        return initialDistanceMatrix;
    }

    public void setInitialDistanceMatrix(double[][] initialDistanceMatrix) {
        this.initialDistanceMatrix = initialDistanceMatrix;
    }

    public static TSPSolution getGlobalBestSolution() {
        return globalBestSolution;
    }

    public static void setGlobalBestSolution(TSPSolution globalBestSolution) {
        TabuSearchSolutionGenerator.globalBestSolution = globalBestSolution;
    }

    public TSPSolution getLocalBestSolution() {
        return localBestSolution;
    }

    public void setLocalBestSolution(TSPSolution localBestSolution) {
        this.localBestSolution = localBestSolution;
    }
}
