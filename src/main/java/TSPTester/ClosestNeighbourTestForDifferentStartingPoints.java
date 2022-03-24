package TSPTester;

import TSPSolver.ClosestNeighbourMethod;

import java.util.ArrayList;
import java.util.List;

public class ClosestNeighbourTestForDifferentStartingPoints {

    public List<Double> ClosestNeighbourTest( double[][] points ){

        List<Double> objectives = new ArrayList<>();

        for( int i = 0; i < points.length; i++ ){
            objectives.add( ClosestNeighbourMethod.Objective( points, i));
        }
        return objectives;
    }
}
