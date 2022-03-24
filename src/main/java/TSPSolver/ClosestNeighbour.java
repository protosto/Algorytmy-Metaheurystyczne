package TSPSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosestNeighbourMethod extends SolutionGenerator{
    @Override
    public List<Integer> solve(double[][] distanceMatrix) {

        return solveFor(distanceMatrix, 0);
    }

    public static List <Integer> solveFor(double[][] distanceMatrix, int startingPoint) {

        List<Integer> pointList = new ArrayList<>();
        Boolean []temp = new Boolean[distanceMatrix.length];
        double minValue = 0;
        int min = 0;
        boolean done = false;


        Arrays.fill(temp, true);
        temp[startingPoint] = false;

        for( int  i = 0; i < distanceMatrix.length; i++ ){
            if( i != startingPoint && !done ){
                min = i;
                minValue = distanceMatrix[startingPoint][i];
                done = true;
            }
            if( distanceMatrix[startingPoint][i] < minValue){
                min = i;
                minValue = distanceMatrix[startingPoint][i];
            }
        }

        pointList.add(min);

        for(int i = 0; i < distanceMatrix.length; i++) {
            done = false;
            for(int x = 0; x < distanceMatrix.length; x++){
                if( i != startingPoint ) {
                    while (!temp[x] && !done) {
                        x++;
                    }
                    if (!done) {
                        minValue = distanceMatrix[i][x];
                        min = x;
                        done = true;
                    }
                    if (temp[x] && distanceMatrix[i][x] < minValue) {
                        minValue = distanceMatrix[i][x];
                        min = x;
                    }
                }
            }
            if( i != startingPoint ) {
                temp[min] = false;
                pointList.add(min);
            }
        }

        return pointList;
    }

    public static double Objective(double[][] distanceMatrix, int startingPoint) {
        Boolean []temp = new Boolean[distanceMatrix.length];
        double objective = 0;
        double minValue = 0;
        int min = 0;
        boolean done = false;


        Arrays.fill(temp, true);
        temp[startingPoint] = false;

        for( int  i = 0; i < distanceMatrix.length; i++ ){
            if( i != startingPoint && !done ){
                min = i;
                minValue = distanceMatrix[startingPoint][i];
                done = true;
            }
            if( distanceMatrix[startingPoint][i] < minValue){
                min = i;
                minValue = distanceMatrix[startingPoint][i];
            }
        }

        for(int i = 0; i < distanceMatrix.length; i++) {
            done = false;
            for(int x = 0; x < distanceMatrix.length; x++){
                if( i != startingPoint ) {
                    while (!temp[x] && !done) {
                        x++;
                    }
                    if (!done) {
                        minValue = distanceMatrix[i][x];
                        min = x;
                        done = true;
                    }
                    if (temp[x] && distanceMatrix[i][x] < minValue) {
                        minValue = distanceMatrix[i][x];
                        min = x;
                    }
                    objective += minValue;
                }
            }
            if( i != startingPoint ) {
                temp[min] = false;
            }
        }

        return objective;
    }

}