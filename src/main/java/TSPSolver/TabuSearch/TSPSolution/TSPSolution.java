package TSPSolver.TabuSearch.TSPSolution;

import TSPSolver.SolutionGenerator;

import java.util.ArrayList;
import java.util.List;

public class TSPSolution implements Comparable {
    private List<Integer> solution;
    private double[][] distanceMatrix;
    private double objectiveFunctionValue;

    public TSPSolution() {
        List<Integer> list = new ArrayList<>();
        this.solution = list;
        this.objectiveFunctionValue = Double.MAX_VALUE;
    }

    public TSPSolution(List<Integer> solution, double[][] distanceMatrix) {
        List<Integer> list = new ArrayList<>();
        list.addAll(solution);
        this.solution = list;
        this.distanceMatrix = distanceMatrix;
        this.objectiveFunctionValue = SolutionGenerator.objectiveFunction(this.solution, this.distanceMatrix);
    }

    public TSPSolution(List<Integer> solution, double[][] distanceMatrix, double objectiveFunctionValue) {
        List<Integer> list = new ArrayList<>();
        list.addAll(solution);
        this.solution = list;
        this.distanceMatrix = distanceMatrix;
        this.objectiveFunctionValue = objectiveFunctionValue;
    }

    @Override
    public int compareTo(Object o) {
        if (this.getObjectiveFunctionValue() > ((TSPSolution) o).getObjectiveFunctionValue()) {
            return 1;
        } else if (this.getObjectiveFunctionValue() < ((TSPSolution) o).getObjectiveFunctionValue()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        return this.solution.equals(((TSPSolution) o).getSolution());
    }

    @Override
    public int hashCode() {
        return solution.hashCode();
    }

    public TSPSolution copy() {
        return new TSPSolution(solution, distanceMatrix, objectiveFunctionValue);
    }

    public void updateObjectiveFunctionValue() {
        this.objectiveFunctionValue = SolutionGenerator.objectiveFunction(this.solution, distanceMatrix);
    }

    public List<Integer> getSolution() {
        return solution;
    }

    public void setSolution(List<Integer> solution) {
        this.solution = solution;
    }

    public double[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public void setDistanceMatrix(double[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public double getObjectiveFunctionValue() {
        return objectiveFunctionValue;
    }

    public void setObjectiveFunctionValue(double objectiveFunctionValue) {
        this.objectiveFunctionValue = objectiveFunctionValue;
    }
}
