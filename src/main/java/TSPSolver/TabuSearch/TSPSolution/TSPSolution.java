package TSPSolver.TabuSearch.TSPSolution;

import TSPSolver.SolutionGenerator;

import java.util.ArrayList;
import java.util.List;

public class TSPSolution implements Comparable {
    private List<Integer> solution;
    private static double[][] distanceMatrix;

    public TSPSolution(List<Integer> solution) {
        List<Integer> list = new ArrayList<>();
        list.addAll(solution);

        this.solution = list;
    }

    @Override
    public int compareTo(Object o) {
        double a = SolutionGenerator.objectiveFunction(this.solution, distanceMatrix);
        double b = SolutionGenerator.objectiveFunction(((TSPSolution) o).getSolution(), distanceMatrix);

        if(a > b) {
            return 1;
        }
        else if (a < b) {
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
}
