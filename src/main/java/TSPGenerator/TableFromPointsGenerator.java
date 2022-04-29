package TSPGenerator;

public class TableFromPointsGenerator implements TSPGenerator{
    @Override
    public double[][] generate(int numberOfPoints, int range) {
        double[][] pointsTable = new PointsGenerator().generatePoints(numberOfPoints, 0, range);
        int length = pointsTable.length;

        double [][]tab = new double[length][length];

        for( int i = 0; i < length; i++ ) {
            for (int j = 0; j < length; j++){
                tab[i][j] = CalculateEuclideanDistance.euclideanDistance(pointsTable[i][0], pointsTable[i][1], pointsTable[j][0], pointsTable[j][1]);
            }
        }
        return tab;
    }
}
