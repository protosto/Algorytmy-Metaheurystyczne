package TSPLoader;

import java.util.Scanner;

public class FullMatrixDistanceLoader implements DistanceLoader {
    @Override
    public double[][] loadDistances(Scanner scanner, int n) {
        double[][] distances = new double[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                distances[i][j] = scanner.nextDouble();
            }
        }

        return distances;
    }
}
