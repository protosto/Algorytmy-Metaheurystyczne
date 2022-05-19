package TSPSolver.AntColonyOptimization;

import TSPSolver.SolutionGenerator;
import TSPSolver.TSPSolution.TSPSolution;

import java.util.ArrayList;
import java.util.List;

public class AntSystem extends SolutionGenerator {
    private TSPSolution globalBestSolution;
    private double pheromoneEvaporationCoefficient;
    private double alpha;
    private double beta;
    private double Q;

    public AntSystem(double pheromoneEvaporationCoefficient, double alpha, double beta, double q) {
        this.pheromoneEvaporationCoefficient = pheromoneEvaporationCoefficient;
        this.alpha = alpha;
        this.beta = beta;
        Q = q;
    }

    @Override
    public TSPSolution solve(double[][] distanceMatrix) {
        globalBestSolution = new TSPSolution();
        double[][] pheromoneMatrix = new double[distanceMatrix.length][distanceMatrix.length];

        for(int i = 0; i < distanceMatrix.length; i++) {
            for(int j = 0; j < distanceMatrix.length; j++) {
                pheromoneMatrix[i][j] = 1;
            }
        }

        for(int i = 0; i < 100; i++) {
            List<TSPSolution> tspSolutionList = sendAnts(distanceMatrix, pheromoneMatrix);
            updatePheromoneMatrix(pheromoneMatrix, tspSolutionList);
        }

        return globalBestSolution;
    }

    private void updatePheromoneMatrix(double[][] pheromoneMatrix, List<TSPSolution> tspSolutionList) {
        for(int i = 0; i < tspSolutionList.get(0).getSolution().size(); i++) {
            for(int j = 0; j < tspSolutionList.get(0).getSolution().size(); j++) {
                pheromoneMatrix[i][j] *= (1.0 - pheromoneEvaporationCoefficient);
            }
        }

        for(TSPSolution tspSolution : tspSolutionList) {
            for(int i = 1; i < tspSolution.getSolution().size(); i++) {
                pheromoneMatrix[i - 1][i] += (Q / tspSolution.getObjectiveFunctionValue());
            }

            pheromoneMatrix[0][tspSolution.getSolution().size() - 1] += (Q / tspSolution.getObjectiveFunctionValue());
        }
    }

    private List<TSPSolution> sendAnts(double[][] distanceMatrix, double[][] pheromoneMatrix) {
        List<TSPSolution> tspSolutionList = new ArrayList<>();

        for(int i = 0; i < distanceMatrix.length; i++) {
            TSPSolution tspSolution = new Ant().travel(i, pheromoneMatrix, distanceMatrix, alpha, beta);

            if(tspSolution.getObjectiveFunctionValue() < globalBestSolution.getObjectiveFunctionValue()) {
                globalBestSolution = tspSolution;
            }

            tspSolutionList.add(tspSolution);
        }

        return tspSolutionList;
    }

    public void setPheromoneEvaporationCoefficient(double pheromoneEvaporationCoefficient) {
        this.pheromoneEvaporationCoefficient = pheromoneEvaporationCoefficient;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public void setQ(double q) {
        Q = q;
    }
}
