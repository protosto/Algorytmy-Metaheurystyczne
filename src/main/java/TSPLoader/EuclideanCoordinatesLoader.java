package TSPLoader;

import java.util.Scanner;

public class EuclideanCoordinatesLoader implements CoordinatesLoader {
    @Override
    public double[][] loadCoordinates(Scanner scanner, int n) {
        double[][] coordinates = new double[n][2];

        for(int i = 0; i < n; i++) {
            scanner.nextInt();
            coordinates[i][0] = scanner.nextDouble();
            coordinates[i][1] = scanner.nextDouble();
        }

        return coordinates;
    }
}
