package TSPSolver.TabuSearch.TSPSolution;

import TSPSolver.SolutionGenerator;

import java.util.ArrayList;
import java.util.List;

public class TSPSolution implements Comparable {
    private List<Integer> solution;
    private static double[][] distanceMatrix;
    private double objectiveFunctionValue;

    public TSPSolution(List<Integer> solution, double objectiveFunctionValue) {
        List<Integer> list = new ArrayList<>();
        list.addAll(solution);
        this.solution = list;
        this.objectiveFunctionValue = objectiveFunctionValue;
    }

    public TSPSolution(List<Integer> solution) {
        List<Integer> list = new ArrayList<>();
        list.addAll(solution);
        this.solution = list;
        this.objectiveFunctionValue = SolutionGenerator.objectiveFunction(this.solution, distanceMatrix);
    }

    @Override
    public int compareTo(Object o) {
        if(this.getObjectiveFunctionValue() > ((TSPSolution) o).getObjectiveFunctionValue()) {
            return 1;
        }
        else if (this.getObjectiveFunctionValue() < ((TSPSolution) o).getObjectiveFunctionValue()) {
            return -1;
        }
        else {
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

    public List<Integer> getSolution() {
        return solution;
    }

    public void setSolution(List<Integer> solution) {
        this.solution = solution;
    }

    public static double[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public static void setDistanceMatrix(double[][] distanceMatrix) {
        TSPSolution.distanceMatrix = distanceMatrix;
    }

    public double getObjectiveFunctionValue() {
        return objectiveFunctionValue;
    }

    public void setObjectiveFunctionValue(double objectiveFunctionValue) {
        this.objectiveFunctionValue = objectiveFunctionValue;
    }
}
