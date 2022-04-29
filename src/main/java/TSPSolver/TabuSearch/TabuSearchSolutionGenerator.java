package TSPSolver.TabuSearch;

import TSPSolver.SolutionGenerator;
import TSPSolver.TabuSearch.IntermediateTermMemoryManager.IntermediateTermMemoryManager;
import TSPSolver.TabuSearch.LongTermMemoryManager.LongTermMemoryManager;
import TSPSolver.TabuSearch.NeighbourhoodGenerator.NeighbourhoodGenerator;
import TSPSolver.TabuSearch.NeighbourhoodManager.NeighbourhoodManager;
import TSPSolver.TabuSearch.StopCondition.StopCondition;
import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuListManager.TabuListManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.*;

public class TabuSearchSolutionGenerator extends SolutionGenerator implements Runnable{
    private volatile static TSPSolution globalBestSolution = null;
    private volatile static ExecutorService executorService = Executors.newCachedThreadPool();
    private volatile static List<Future<?>> futures = new ArrayList<>();
    private TSPSolution initialSolution;
    private StopCondition stopCondition;
    private NeighbourhoodGenerator neighbourhoodGenerator;
    private NeighbourhoodManager neighbourhoodManager;
    private TabuListManager tabuListManager;
    private IntermediateTermMemoryManager intermediateTermMemoryManager;
    private LongTermMemoryManager longTermMemoryManager;
    private double[][] initialDistanceMatrix;
    private TSPSolution localBestSolution;
    private Integer numberOfStartingThreads;

    public TabuSearchSolutionGenerator(TSPSolution initialSolution, StopCondition stopCondition, NeighbourhoodGenerator neighbourhoodGenerator, NeighbourhoodManager neighbourhoodManager, TabuListManager tabuListManager, IntermediateTermMemoryManager intermediateTermMemoryManager, LongTermMemoryManager longTermMemoryManager, Integer numberOfStartingThreads) {
        this.initialSolution = initialSolution;
        this.stopCondition = stopCondition;
        this.neighbourhoodGenerator = neighbourhoodGenerator;
        this.neighbourhoodManager = neighbourhoodManager;
        this.tabuListManager = tabuListManager;
        this.intermediateTermMemoryManager = intermediateTermMemoryManager;
        this.longTermMemoryManager = longTermMemoryManager;
        this.numberOfStartingThreads = numberOfStartingThreads;
    }

    public TabuSearchSolutionGenerator(double[][] initialDistanceMatrix, TSPSolution initialSolution, StopCondition stopCondition, NeighbourhoodGenerator neighbourhoodGenerator, NeighbourhoodManager neighbourhoodManager, TabuListManager tabuListManager, IntermediateTermMemoryManager intermediateTermMemoryManager, LongTermMemoryManager longTermMemoryManager, Integer numberOfStartingThreads) {
        this.initialDistanceMatrix = initialDistanceMatrix;
        this.initialSolution = initialSolution;
        this.stopCondition = stopCondition;
        this.neighbourhoodGenerator = neighbourhoodGenerator;
        this.neighbourhoodManager = neighbourhoodManager;
        this.tabuListManager = tabuListManager;
        this.intermediateTermMemoryManager = intermediateTermMemoryManager;
        this.longTermMemoryManager = longTermMemoryManager;
        this.numberOfStartingThreads = numberOfStartingThreads;
    }

    @Override
    public List<Integer> solve(double[][] distanceTable) {
        initialDistanceMatrix = distanceTable;

        futures.add(executorService.submit(this));

        for(int i = 1; i < numberOfStartingThreads; i++) {
            futures.add(executorService.submit(this.copy(initialSolution)));
        }

        try{
            while(!isFinished()) {
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdownNow();

        return globalBestSolution.getSolution();
    }

    private synchronized boolean isFinished() {
        boolean allDone = true;

        for(Future<?> future : futures){
            allDone &= future.isDone();
        }

        return allDone;
    }
    private synchronized void runTabuSearch() {
        double[][] currentDistanceMatrix = Arrays.stream(initialDistanceMatrix).map(double[]::clone).toArray(double[][]::new);
        TSPSolution currentSolution = initialSolution;
        localBestSolution = currentSolution;

        if (globalBestSolution == null || globalBestSolution.getObjectiveFunctionValue() > currentSolution.getObjectiveFunctionValue()) {
            globalBestSolution = currentSolution;
        }

        System.out.println(SolutionGenerator.objectiveFunction(currentSolution.getSolution(), initialDistanceMatrix));

        do {
            TreeSet<TSPSolution> neighbourhood = neighbourhoodGenerator.generateNeighbourhood(currentSolution, currentDistanceMatrix);

            currentSolution = neighbourhoodManager.chooseNeighbour(neighbourhood, tabuListManager, stopCondition.getIterationNumber());
            currentSolution.setDistanceMatrix(initialDistanceMatrix);
            currentSolution.updateObjectiveFunctionValue();

            if (localBestSolution.getObjectiveFunctionValue() > currentSolution.getObjectiveFunctionValue()) {
                localBestSolution = currentSolution;

                if (globalBestSolution.getObjectiveFunctionValue() > currentSolution.getObjectiveFunctionValue()) {
                    globalBestSolution = currentSolution;
                }
            }

            if (intermediateTermMemoryManager != null) {
                intermediateTermMemoryManager.manage(this, localBestSolution);
            }
            if (longTermMemoryManager != null) {
                longTermMemoryManager.manage(currentSolution, localBestSolution, neighbourhood, currentDistanceMatrix, stopCondition.getIterationNumber());
            }

            tabuListManager.addToTabuList(currentSolution, neighbourhood, stopCondition.getIterationNumber());
        }
        while(!stopCondition.isStopped(currentSolution, localBestSolution));
    }

    @Override
    public void run() {
        runTabuSearch();
        System.out.println("Local best: " + this.getLocalBestSolution().getObjectiveFunctionValue());
    }

    public void reset() {
        globalBestSolution = null;
        localBestSolution = null;
        intermediateTermMemoryManager.reset();
        stopCondition.reset();
        tabuListManager.reset();
        executorService = Executors.newCachedThreadPool();
        futures = new ArrayList<>();

    }

    public TabuSearchSolutionGenerator copy(TSPSolution initialSolution) {
        return new TabuSearchSolutionGenerator(initialDistanceMatrix, initialSolution, stopCondition.copy(), neighbourhoodGenerator.copy(), neighbourhoodManager.copy(), tabuListManager.copy(), intermediateTermMemoryManager != null ? intermediateTermMemoryManager.copy() : null, longTermMemoryManager != null ? longTermMemoryManager.copy() : null, 0);
    }
    public void setLongTermMemoryManager(LongTermMemoryManager longTermMemoryManager) {
        this.longTermMemoryManager = longTermMemoryManager;
    }
    public TSPSolution getLocalBestSolution() {
        return localBestSolution;
    }
    public void setIntermediateTermMemoryManager(IntermediateTermMemoryManager intermediateTermMemoryManager) {
        this.intermediateTermMemoryManager = intermediateTermMemoryManager;
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static List<Future<?>> getFutures() {
        return futures;
    }

}
