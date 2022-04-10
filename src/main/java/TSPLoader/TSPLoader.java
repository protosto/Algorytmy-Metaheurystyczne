package TSPLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TSPLoader {

    public TSPLoader() {
    }

    public double[][] loadDistanceMatrixFromFile(String filePath) throws FileNotFoundException, IllegalArgumentException {
        Scanner scanner = new Scanner(new File(filePath));
        String line = "";

        while(!Pattern.matches("^DIMENSION.*", line)) {
             line = scanner.nextLine();
        }

        int n = Integer.parseInt(line.split(":")[1].trim());

        while(!Pattern.matches("^EDGE_WEIGHT_TYPE.*", line)) {
            line = scanner.nextLine();
        }

        String edgeWeightType = line.split(":")[1].trim();

        if(edgeWeightType.equals("EUC_2D") || edgeWeightType.equals("GEO")) {
            while(!scanner.next().equals("NODE_COORD_SECTION")) {
                scanner.nextLine();
            }

            return new EuclideanCoordinatesDistanceLoader().loadDistances(scanner, n);
        }
        else if(edgeWeightType.equals("EXPLICIT")) {
            while(!Pattern.matches("^EDGE_WEIGHT_FORMAT.*", line)) {
                line = scanner.nextLine();
            }

            String format = line.split(":")[1].trim();

            while(!scanner.next().equals("EDGE_WEIGHT_SECTION")) {
                scanner.nextLine();
            }

            if(format.equals("FULL_MATRIX")) {
                return new FullMatrixDistanceLoader().loadDistances(scanner, n);
            }
            else if(format.equals("UPPER_ROW")) {
                return new UpperRowMatrixDistanceLoader().loadDistances(scanner, n);
            }
            else if(format.equals("UPPER_DIAG_ROW")) {
                return new UpperDiagonalRowMatrixDistanceLoader().loadDistances(scanner, n);
            }
            else if(format.equals("LOWER_ROW")) {
                return new LowerRowMatrixDistanceLoader().loadDistances(scanner, n);
            }
            else if(format.equals("LOWER_DIAG_ROW")) {
                return new LowerDiagonalRowMatrixDistanceLoader().loadDistances(scanner, n);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public double[][] loadCoordinatesMatrixFromFile(String filePath) throws FileNotFoundException, IllegalArgumentException {
        Scanner scanner = new Scanner(new File(filePath));
        String line = "";

        while(!Pattern.matches("^DIMENSION.*", line)) {
            line = scanner.nextLine();
        }

        int n = Integer.parseInt(line.split(":")[1].trim());

        while(!scanner.next().equals("NODE_COORD_SECTION")) {
            scanner.nextLine();
        }

        return new EuclideanCoordinatesLoader().loadCoordinates(scanner, n);
    }
}
