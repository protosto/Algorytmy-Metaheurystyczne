package TSPGenerator;

import java.util.Random;

public class AsymmetricTableGenerator implements TSPGenerator{
    @Override
    public double[][] generate(int numberOfPoints, int range) {
        double [][]tab = new double[numberOfPoints][numberOfPoints];

        Random rand = new Random();

        for( int i = 0; i < numberOfPoints; i++ ){
            for( int j = 0; j < numberOfPoints; j++ ){
                if( i != j ) tab[i][j] =  rand.nextDouble(range) + 1;
            }
            tab[i][i] = 0;
        }
        return tab;
    }
}
