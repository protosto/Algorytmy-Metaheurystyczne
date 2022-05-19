package TSPSolver.AntColonyOptimization;

import TSPSolver.TSPSolution.TSPSolution;

import java.util.ArrayList;
import java.util.List;

public class Ant {
    public TSPSolution travel(Integer startingPoint, double[][] pheromoneMatrix, double[][] distanceMatrix, double alpha, double beta) {
        List<Integer> solution = new ArrayList<>();
        List<Integer> unvisitedNodes = new ArrayList<>();

        for(int i = 0; i < distanceMatrix.length; i++) {
            unvisitedNodes.add(i);
        }

        unvisitedNodes.remove(startingPoint);
        solution.add(startingPoint);

        while(!unvisitedNodes.isEmpty()) {
            Integer node = chooseNextNode(unvisitedNodes, solution.get(solution.size() - 1), pheromoneMatrix, distanceMatrix, alpha, beta);
            unvisitedNodes.remove(node);
            solution.add(node);
        }

        return new TSPSolution(solution, distanceMatrix);
    }

    private Integer chooseNextNode(List<Integer> unvisitedNodes, Integer firstNode, double[][] pheromoneMatrix, double[][] distanceMatrix, double alpha, double beta) {
        Double sum = 0.0;

        for(Integer node : unvisitedNodes) {
            sum += Math.pow(pheromoneMatrix[firstNode][node], alpha) * Math.pow((1.0 / distanceMatrix[firstNode][node]), beta);
        }

        Integer currentBestNode = -1;
        Double probabilityOfCurrentBestNode = 0.0;

        for(Integer node : unvisitedNodes) {
            Double probabilityOfCurrentNode = calculateProbability(sum, firstNode, node, pheromoneMatrix, distanceMatrix, alpha, beta);

            if(probabilityOfCurrentNode > probabilityOfCurrentBestNode) {
                probabilityOfCurrentBestNode = probabilityOfCurrentNode;
                currentBestNode = node;
            }
        }

        return currentBestNode;
    }

    private Double calculateProbability(Double sum, Integer firstNode, Integer secondNode, double[][] pheromoneMatrix, double[][] distanceMatrix, double alpha, double beta) {
        return Math.pow(pheromoneMatrix[firstNode][secondNode], alpha) * Math.pow((1.0 / distanceMatrix[firstNode][secondNode]), beta) / sum;
    }
}
