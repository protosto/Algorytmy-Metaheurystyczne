package TSPSolver.AntColonyOptimization;

import TSPSolver.TSPSolution.TSPSolution;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Ant extends Thread {
    private static volatile SecureRandom secureRandom = new SecureRandom();
    private Integer startingPoint;
    private double localPheromoneEvaporationCoefficient;
    private volatile double[][] pheromoneMatrix;
    private volatile double[][] distanceMatrix;
    private double startPheromoneValue;
    private double exploitationProbability;
    private double alpha;
    private double beta;
    private boolean isSymmetrical;
    private TSPSolution tspSolution;

    public Ant(Integer startingPoint, double localPheromoneEvaporationCoefficient, double[][] pheromoneMatrix, double[][] distanceMatrix, double startPheromoneValue, double exploitationProbability, double alpha, double beta, boolean isSymmetrical) {
        this.startingPoint = startingPoint;
        this.localPheromoneEvaporationCoefficient = localPheromoneEvaporationCoefficient;
        this.pheromoneMatrix = pheromoneMatrix;
        this.distanceMatrix = distanceMatrix;
        this.startPheromoneValue = startPheromoneValue;
        this.exploitationProbability = exploitationProbability;
        this.alpha = alpha;
        this.beta = beta;
        this.isSymmetrical = isSymmetrical;
    }

    @Override
    public void run() {
        travel();
    }

    private synchronized void travel() {
        List<Integer> solution = new ArrayList<>();
        List<Integer> unvisitedNodes = new ArrayList<>();

        for(int i = 0; i < distanceMatrix.length; i++) {
            unvisitedNodes.add(i);
        }

        unvisitedNodes.remove(startingPoint);
        solution.add(startingPoint);

        while(!unvisitedNodes.isEmpty()) {
            Integer node = chooseNextNode(unvisitedNodes, solution.get(solution.size() - 1));

            localPheromoneUpdate(solution.get(solution.size() - 1), node);
            unvisitedNodes.remove(node);
            solution.add(node);
        }

        localPheromoneUpdate(solution.get(solution.size() - 1), solution.get(0));

        tspSolution = new TSPSolution(solution, distanceMatrix);
    }

    private synchronized void localPheromoneUpdate(Integer firstNode, Integer secondNode) {
        pheromoneMatrix[firstNode][secondNode] *= (1.0 - localPheromoneEvaporationCoefficient);
        pheromoneMatrix[firstNode][secondNode] += localPheromoneEvaporationCoefficient * startPheromoneValue;

        if(isSymmetrical) {
            pheromoneMatrix[secondNode][firstNode] *= (1.0 - localPheromoneEvaporationCoefficient);
            pheromoneMatrix[secondNode][firstNode] += localPheromoneEvaporationCoefficient * startPheromoneValue;
        }
    }
    private synchronized Integer chooseNextNode(List<Integer> unvisitedNodes, Integer firstNode) {
        Double sum = 0.0;
        Double random = secureRandom.nextDouble();
        Double cumulativeDistributionFunction = 0.0;

        for(Integer node : unvisitedNodes) {
            sum += Math.pow(pheromoneMatrix[firstNode][node], alpha) * Math.pow((1.0 / distanceMatrix[firstNode][node]), beta);
        }

        Integer exploitationNode = unvisitedNodes.get(0);
        Integer biasedExplorationNode = unvisitedNodes.get(0);
        Double probabilityOfExploitationNode = 0.0;

        for(Integer node : unvisitedNodes) {
            Double probabilityOfCurrentNode = calculateProbability(sum, firstNode, node, pheromoneMatrix, distanceMatrix, alpha, beta);
            cumulativeDistributionFunction += probabilityOfCurrentNode;

            if(biasedExplorationNode.equals(-1) && random <= cumulativeDistributionFunction) {
                biasedExplorationNode = node;
            }

            if(probabilityOfCurrentNode > probabilityOfExploitationNode) {
                probabilityOfExploitationNode = probabilityOfCurrentNode;
                exploitationNode = node;
            }
        }

        return (secureRandom.nextDouble() <= exploitationProbability ? exploitationNode : biasedExplorationNode);
    }

    private synchronized Double calculateProbability(Double sum, Integer firstNode, Integer secondNode, double[][] pheromoneMatrix, double[][] distanceMatrix, double alpha, double beta) {
        return Math.pow(pheromoneMatrix[firstNode][secondNode], alpha) * Math.pow((1.0 / distanceMatrix[firstNode][secondNode]), beta) / sum;
    }

    public TSPSolution getTspSolution() {
        return tspSolution;
    }
}
