package TSPSolver.AntColonyOptimization;

import TSPSolver.SolutionGenerator;
import TSPSolver.TSPSolution.TSPSolution;
import TSPSolver.TwoOptSolutionGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AntColonySystem extends SolutionGenerator {
    private static final int numberOfThreads = 8;
    private volatile static ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
    private int numberOfAntsPerIteration;
    private double globalPheromoneEvaporationCoefficient;
    private double localPheromoneEvaporationCoefficient;
    private double startPheromoneValue;
    private double exploitationProbability;
    private int numberOfIterations;
    private double alpha;
    private double beta;
    private double Q;
    private boolean isSymmetrical;
    private boolean localSearch;

    public AntColonySystem(int numberOfAntsPerIteration, double globalPheromoneEvaporationCoefficient, double localPheromoneEvaporationCoefficient, double startPheromoneValue, double exploitationProbability, int numberOfIterations, double alpha, double beta, double Q, boolean isSymmetrical, boolean localSearch) {
        this.numberOfAntsPerIteration = numberOfAntsPerIteration;
        this.globalPheromoneEvaporationCoefficient = globalPheromoneEvaporationCoefficient;
        this.localPheromoneEvaporationCoefficient = localPheromoneEvaporationCoefficient;
        this.startPheromoneValue = startPheromoneValue;
        this.exploitationProbability = exploitationProbability;
        this.numberOfIterations = numberOfIterations;
        this.alpha = alpha;
        this.beta = beta;
        this.isSymmetrical = isSymmetrical;
        this.Q = Q;
        this.localSearch = localSearch;
    }

    @Override
    public TSPSolution solve(double[][] distanceMatrix) {
        TSPSolution globalBestSolution = new TSPSolution();
        double[][] pheromoneMatrix = new double[distanceMatrix.length][distanceMatrix.length];

        for(int i = 0; i < distanceMatrix.length; i++) {
            for(int j = 0; j < distanceMatrix.length; j++) {
                pheromoneMatrix[i][j] = startPheromoneValue;
            }
        }

        for(int i = 0; i < numberOfIterations; i++) {
            TSPSolution localBestSolution = sendAnts(distanceMatrix, pheromoneMatrix);
            globalPheromoneUpdate(pheromoneMatrix, localBestSolution);

            if(localBestSolution.getObjectiveFunctionValue() < globalBestSolution.getObjectiveFunctionValue()) {
                globalBestSolution = localBestSolution;
            }

            System.out.println(i + " " + globalBestSolution.getObjectiveFunctionValue());
        }

        executorService.shutdownNow();
        executorService = Executors.newFixedThreadPool(numberOfThreads);

        return globalBestSolution;
    }

    private void globalPheromoneUpdate(double[][] pheromoneMatrix, TSPSolution tspSolution) {
        for(int i = 0; i < tspSolution.getSolution().size(); i++) {
            for(int j = 0; j < tspSolution.getSolution().size(); j++) {
                pheromoneMatrix[i][j] *= (1.0 - globalPheromoneEvaporationCoefficient);
            }
        }

        for(int i = 1; i < tspSolution.getSolution().size(); i++) {
            pheromoneMatrix[i - 1][i] += (Q / tspSolution.getObjectiveFunctionValue());

            if(isSymmetrical) {
                pheromoneMatrix[i][i - 1] += (Q / tspSolution.getObjectiveFunctionValue());
            }
        }

        pheromoneMatrix[tspSolution.getSolution().size() - 1][0] += (Q / tspSolution.getObjectiveFunctionValue());

        if(isSymmetrical) {
            pheromoneMatrix[0][tspSolution.getSolution().size() - 1] += (Q / tspSolution.getObjectiveFunctionValue());
        }
    }

    private TSPSolution sendAnts(double[][] distanceMatrix, double[][] pheromoneMatrix) {
        List<Ant> antList = new ArrayList<>();
        List<Future<?>> futures = new ArrayList<>();
        TSPSolution localBestSolution = new TSPSolution();

        List<Integer> nodesLeft = new ArrayList<>();

        for (int i = 0; i < distanceMatrix.length; i++) {
            nodesLeft.add(i);
        }

        for(int i = 0; i < numberOfAntsPerIteration; i++) {
            int random = (int) (Math.random() * nodesLeft.size());
            Ant ant = new Ant(nodesLeft.get(random), localPheromoneEvaporationCoefficient, pheromoneMatrix, distanceMatrix, startPheromoneValue, exploitationProbability, alpha, beta, isSymmetrical);
            futures.add(executorService.submit(ant));
            antList.add(ant);
            nodesLeft.remove(random);
        }

        try {
            while(!isFinished(futures)) {
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e) {
            throw new RuntimeException(e);
        }

        //checkForExceptions(futures);

        for(Ant ant : antList) {
            TSPSolution tspSolution = (localSearch ? new TwoOptSolutionGenerator(ant.getTspSolution()).solve(distanceMatrix) : ant.getTspSolution());

            if(localBestSolution.getObjectiveFunctionValue() > tspSolution.getObjectiveFunctionValue()) {
                localBestSolution = tspSolution;
            }
        }

        return localBestSolution;
    }

    private boolean isFinished(List<Future<?>> futures) {
        boolean allDone = true;

        for(Future<?> future : futures){
            allDone &= future.isDone();
        }

        return allDone;
    }

    private void checkForExceptions(List<Future<?>> futures) {
        Throwable throwable = null;

        for(Future future : futures) {
            try {
                if(future.isDone()) {
                    future.get();
                }
            }
            catch(CancellationException cancellationException) {
                throwable = cancellationException;
            }
            catch(ExecutionException executionException) {
                throwable = executionException.getCause();
            }
            catch(InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }

            if (throwable != null) {
                System.out.println(throwable);
            }
        }
    }

}
