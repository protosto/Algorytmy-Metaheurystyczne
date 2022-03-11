package TSPLoader;

import java.util.Scanner;

public class UpperRowMatrixDistanceLoader implements DistanceLoader {
    @Override
    public double[][] loadDistances(Scanner scanner, int n) {
        double[][] distances = new double[n][n];

        for(int i = 0; i < n - 1; i++) {
            for(int j = n - 1; j > i; j--) {
                distances[i][j] = scanner.nextDouble();
                distances[j][i] = distances[i][j];
            }
        }

        return distances;
    }
}
