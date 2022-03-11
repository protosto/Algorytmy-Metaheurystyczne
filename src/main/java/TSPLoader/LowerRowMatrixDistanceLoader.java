package TSPLoader;

import java.util.Scanner;

public class LowerRowMatrixDistanceLoader implements DistanceLoader {
    @Override
    public double[][] loadDistances(Scanner scanner, int n) {
        double[][] distances = new double[n][n];

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                distances[i][j] = scanner.nextDouble();
                distances[j][i] = distances[i][j];
            }
        }

        return distances;
    }
}
