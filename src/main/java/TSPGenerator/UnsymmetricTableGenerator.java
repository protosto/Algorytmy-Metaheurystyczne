package TSPGenerator;

import java.util.Random;

public class UnsymmetricTableGenerator {

    public static double[][] generateUnsymmetricTable( int nr_of_points, double range ){

        double [][]tab = new double[nr_of_points][nr_of_points];

        Random rand = new Random();

        for( int i = 0; i < nr_of_points; i++ ){
            for( int j = 0; j < nr_of_points; j++ ){
                if( i != j ) tab[i][j] =  rand.nextDouble(range);
            }
            tab[i][i] = 0;
        }
        return tab;
    }
}
