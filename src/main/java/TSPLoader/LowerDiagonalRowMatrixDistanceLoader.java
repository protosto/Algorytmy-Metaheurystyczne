package TSPLoader;

import java.util.Scanner;

public class LowerDiagonalRowMatrixDistanceLoader implements DistanceLoader {
    @Override
    public double[][] loadDistances(Scanner scanner, int n) {
        double[][] distances = new double[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i + 1; j++) {
                distances[i][j] = scanner.nextDouble();
                distances[j][i] = distances[i][j];
            }
        }

        return distances;
    }
}
