package TSPLoader;

import java.util.Scanner;

public class UpperDiagonalRowMatrixDistanceLoader implements DistanceLoader {
    @Override
    public double[][] loadDistances(Scanner scanner, int n) {
        double[][] distances = new double[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                distances[i][j] = scanner.nextDouble();
                distances[j][i] = distances[i][j];
            }
        }

        return distances;
    }
}
