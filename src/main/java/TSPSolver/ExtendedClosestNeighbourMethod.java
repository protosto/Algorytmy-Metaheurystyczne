package TSPSolver;

import java.util.ArrayList;
import java.util.List;

public class ExtendedClosestNeighbourMethod extends SolutionGenerator{
    @Override
    public List<Integer> solve(double[][] distanceMatrix) {

        List<Integer> pointList = new ArrayList<>();

        double minObjective = objectiveFunction( ClosestNeighbourMethod.solveFor(distanceMatrix, 0), distanceMatrix);
        pointList = ClosestNeighbourMethod.solveFor(distanceMatrix, 0);

        for( int i = 1; i < distanceMatrix.length; i++ ){
            if( objectiveFunction( ClosestNeighbourMethod.solveFor(distanceMatrix, i), distanceMatrix) < minObjective ){
                minObjective = objectiveFunction( ClosestNeighbourMethod.solveFor(distanceMatrix, i), distanceMatrix);
                pointList = ClosestNeighbourMethod.solveFor(distanceMatrix, 0);
            }
        }

        return pointList;
    }
}
