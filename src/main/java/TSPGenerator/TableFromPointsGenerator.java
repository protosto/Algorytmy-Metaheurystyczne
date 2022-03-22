package TSPGenerator;

public class TableFromPointsGenerator {

    public static double[][] generateTableFromPoints( double [][]table ){

        int length = table.length;
        double [][]tab = new double[length][length];

        for( int i = 0; i < length; i++ ) {
            for (int j = 0; j < length-i; j++){
                tab[i][j] = CalculateEuclideanDistance.euclideanDistance(table[i][0], table[i][1], table[j][0], table[j][1]);
            }
        }
        return tab;
    }
}
