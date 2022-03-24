package TSPTester;

import TSPGenerator.PointsGenerator;
import TSPSolver.ClosestNeighbourMethod;
import TSPSolver.ExtendedClosestNeighbourMethod;

import java.util.ArrayList;
import java.util.List;

public class ExtendedClosestNeighbourTestForDifferentSizes {

    public List<Double> ExtendedClosestNeighbourTest(){

        List<Double> times = new ArrayList<>();
        double time;

        //TODO mierzenie czasu

        for( int i = 0; i < 1000; i++ ){

            ExtendedClosestNeighbourMethod.solve(PointsGenerator.generatePoints(i, 0, 1000));
            times.add(time);
        }
        return times;
    }
}
