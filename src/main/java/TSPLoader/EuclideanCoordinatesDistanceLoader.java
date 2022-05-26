package TSPLoader;

import java.util.Scanner;

public class EuclideanCoordinatesDistanceLoader implements DistanceLoader, DistanceMatrixCalculator {
    @Override
    public double[][] loadDistances(Scanner scanner, int n) {
        return calculateDistances(new EuclideanCoordinatesLoader().loadCoordinates(scanner, n));
    }

    @Override
    public double[][] calculateDistances(double[][] coordinates) {
        int n = coordinates.length;

        double[][] distances = new double[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                distances[i][j] = Math.round(Math.sqrt((coordinates[i][0] - coordinates[j][0]) * (coordinates[i][0] - coordinates[j][0]) + (coordinates[i][1] - coordinates[j][1]) * (coordinates[i][1] - coordinates[j][1])));
                distances[j][i] = distances[i][j];
            }
        }

        return distances;
    }
}
