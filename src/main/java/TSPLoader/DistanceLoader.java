package TSPLoader;

import java.util.Scanner;

public interface DistanceLoader {
    double[][] loadDistances(Scanner scanner, int n);
}
