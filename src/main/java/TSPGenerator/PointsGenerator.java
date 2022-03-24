package TSPGenerator;

import java.util.Random;

public class PointsGenerator {

    public static double[][] generatePoints( int nr_of_points, double min, double range ){

        double [][]tab = new double[nr_of_points][2];

        Random rand = new Random();

        for( int i = 0; i < nr_of_points; i++ ){
            tab[i][0] =  rand.nextDouble(range-min)+min;
            tab[i][1] =  rand.nextDouble(range-min)+min;
        }
        return tab;
    }
}
