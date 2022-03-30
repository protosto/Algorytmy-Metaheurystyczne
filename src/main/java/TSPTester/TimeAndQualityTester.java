package TSPTester;

import TSPGenerator.PointsGenerator;
import TSPGenerator.SymmetricTableGenerator;
import TSPGenerator.AsymmetricTableGenerator;
import TSPGenerator.TableFromPointsGenerator;
import TSPSolver.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TimeAndQualityTester {
    String path = "C:\\Users\\123w4\\Desktop\\TSP\\";
    BufferedWriter symmetricKRandomTimeBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "TimeTests\\kRandom.csv")));
    BufferedWriter symmetricClosestNeighbourPathTimeBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "TimeTests\\closestNeighbour.csv")));
    BufferedWriter symmetricExtendedClosestNeighbourPathTimeBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "TimeTests\\extendedClosestNeighbour.csv")));
    BufferedWriter symmetricTwoOptKRandomSolutionGeneratorStartTimeBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "TimeTests\\twoOptKRandomStart.csv")));
    BufferedWriter symmetricTwoOptClosestNeighbourMethodStartTimeBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "TimeTests\\twoOptClosestNeighbourStart.csv")));
    BufferedWriter symmetricTwoOptExtendedClosestNeighbourMethodStartTimeBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "TimeTests\\twoOptExtendedClosestNeighbourStart.csv")));
    BufferedWriter symmetricKRandomQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\symmetric\\kRandom.csv")));
    BufferedWriter symmetricClosestNeighbourPathQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\symmetric\\closestNeighbour.csv")));
    BufferedWriter symmetricExtendedClosestNeighbourPathQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\symmetric\\extendedClosestNeighbour.csv")));
    BufferedWriter symmetricTwoOptKRandomSolutionGeneratorStartQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\symmetric\\twoOptKRandomStart.csv")));
    BufferedWriter symmetricTwoOptClosestNeighbourMethodStartQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\symmetric\\twoOptClosestNeighbourStart.csv")));
    BufferedWriter symmetricTwoOptExtendedClosestNeighbourMethodStartQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\symmetric\\twoOptExtendedClosestNeighbourStart.csv")));
    BufferedWriter asymmetricKRandomQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\asymmetric\\kRandom.csv")));
    BufferedWriter asymmetricClosestNeighbourPathQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\asymmetric\\closestNeighbour.csv")));
    BufferedWriter asymmetricExtendedClosestNeighbourPathQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\asymmetric\\extendedClosestNeighbour.csv")));
    BufferedWriter asymmetricTwoOptKRandomSolutionGeneratorStartQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\asymmetric\\twoOptKRandomStart.csv")));
    BufferedWriter asymmetricTwoOptClosestNeighbourMethodStartQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\asymmetric\\twoOptClosestNeighbourStart.csv")));
    BufferedWriter asymmetricTwoOptExtendedClosestNeighbourMethodStartQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\asymmetric\\twoOptExtendedClosestNeighbourStart.csv")));
    BufferedWriter euclideanKRandomQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\euclidean\\kRandom.csv")));
    BufferedWriter euclideanClosestNeighbourPathQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\euclidean\\closestNeighbour.csv")));
    BufferedWriter euclideanExtendedClosestNeighbourPathQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\euclidean\\extendedClosestNeighbour.csv")));
    BufferedWriter euclideanTwoOptKRandomSolutionGeneratorStartQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\euclidean\\twoOptKRandomStart.csv")));
    BufferedWriter euclideanTwoOptClosestNeighbourMethodStartQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\euclidean\\twoOptClosestNeighbourStart.csv")));
    BufferedWriter euclideanTwoOptExtendedClosestNeighbourMethodStartQualityBufferedWriter = new BufferedWriter(new FileWriter(new File(path + "QualityTests\\euclidean\\twoOptExtendedClosestNeighbourStart.csv")));

    public TimeAndQualityTester() throws IOException {
    }

    public void test(int k) throws IOException {
        KRandomSolutionGenerator kRandomSolutionGenerator = new KRandomSolutionGenerator(5000);
        ClosestNeighbourMethod closestNeighbourMethod = new ClosestNeighbourMethod();
        ExtendedClosestNeighbourMethod extendedClosestNeighbourMethod = new ExtendedClosestNeighbourMethod();
        TwoOptSolutionGenerator twoOptSolutionGeneratorKRandomSolutionGeneratorStart = new TwoOptSolutionGenerator(kRandomSolutionGenerator);
        TwoOptSolutionGenerator twoOptSolutionGeneratorClosestNeighbourMethodStart = new TwoOptSolutionGenerator(closestNeighbourMethod);
        TwoOptSolutionGenerator twoOptSolutionGeneratorExtendedClosestNeighbourMethodStart = new TwoOptSolutionGenerator(extendedClosestNeighbourMethod);
        SymmetricTableGenerator symmetricTableGenerator = new SymmetricTableGenerator();
        AsymmetricTableGenerator asymmetricTableGenerator = new AsymmetricTableGenerator();
        PointsGenerator pointsGenerator = new PointsGenerator();
        TableFromPointsGenerator tableFromPointsGenerator = new TableFromPointsGenerator();

        long start;
        long finish;
        long timeElapsed;
        List<Integer> solution;
        double objectiveFunctionValue;

        double[][] testMatrix;

        for(int i = 0; i < k; i++) {
            for(int j = 10; j <= 200; j += 10) {
                //symmetric
                testMatrix = symmetricTableGenerator.generateSymmetricTable(j, 500);

                start = System.nanoTime();
                solution = closestNeighbourMethod.solve(testMatrix);
                finish = System.nanoTime();

                timeElapsed = finish - start;
                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                symmetricClosestNeighbourPathQualityBufferedWriter.write(objectiveFunctionValue + ",");
                symmetricClosestNeighbourPathTimeBufferedWriter.write(timeElapsed + ",");

                start = System.nanoTime();
                solution = extendedClosestNeighbourMethod.solve(testMatrix);
                finish = System.nanoTime();

                timeElapsed = finish - start;
                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                symmetricExtendedClosestNeighbourPathQualityBufferedWriter.write(objectiveFunctionValue + ",");
                symmetricExtendedClosestNeighbourPathTimeBufferedWriter.write(timeElapsed + ",");

                start = System.nanoTime();
                solution = kRandomSolutionGenerator.solve(testMatrix);
                finish = System.nanoTime();

                timeElapsed = finish - start;
                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                symmetricKRandomQualityBufferedWriter.write(objectiveFunctionValue + ",");
                symmetricKRandomTimeBufferedWriter.write(timeElapsed + ",");

                start = System.nanoTime();
                solution = twoOptSolutionGeneratorKRandomSolutionGeneratorStart.solve(testMatrix);
                finish = System.nanoTime();

                timeElapsed = finish - start;
                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                symmetricTwoOptKRandomSolutionGeneratorStartQualityBufferedWriter.write(objectiveFunctionValue + ",");
                symmetricTwoOptKRandomSolutionGeneratorStartTimeBufferedWriter.write(timeElapsed + ",");

                start = System.nanoTime();
                solution = twoOptSolutionGeneratorClosestNeighbourMethodStart.solve(testMatrix);
                finish = System.nanoTime();

                timeElapsed = finish - start;
                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                symmetricTwoOptClosestNeighbourMethodStartQualityBufferedWriter.write(objectiveFunctionValue + ",");
                symmetricTwoOptClosestNeighbourMethodStartTimeBufferedWriter.write(timeElapsed + ",");

                start = System.nanoTime();
                solution = twoOptSolutionGeneratorExtendedClosestNeighbourMethodStart.solve(testMatrix);
                finish = System.nanoTime();

                timeElapsed = finish - start;
                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                symmetricTwoOptExtendedClosestNeighbourMethodStartQualityBufferedWriter.write(objectiveFunctionValue + ",");
                symmetricTwoOptExtendedClosestNeighbourMethodStartTimeBufferedWriter.write(timeElapsed + ",");

                //asymmetric
                testMatrix = asymmetricTableGenerator.generateAsymmetricTable(j, 500);

                solution = closestNeighbourMethod.solve(testMatrix);

                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                asymmetricClosestNeighbourPathQualityBufferedWriter.write(objectiveFunctionValue + ",");

                solution = extendedClosestNeighbourMethod.solve(testMatrix);

                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                asymmetricExtendedClosestNeighbourPathQualityBufferedWriter.write(objectiveFunctionValue + ",");

                solution = kRandomSolutionGenerator.solve(testMatrix);

                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                asymmetricKRandomQualityBufferedWriter.write(objectiveFunctionValue + ",");

                solution = twoOptSolutionGeneratorKRandomSolutionGeneratorStart.solve(testMatrix);

                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                asymmetricTwoOptKRandomSolutionGeneratorStartQualityBufferedWriter.write(objectiveFunctionValue + ",");

                solution = twoOptSolutionGeneratorClosestNeighbourMethodStart.solve(testMatrix);

                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                asymmetricTwoOptClosestNeighbourMethodStartQualityBufferedWriter.write(objectiveFunctionValue + ",");

                solution = twoOptSolutionGeneratorExtendedClosestNeighbourMethodStart.solve(testMatrix);

                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                asymmetricTwoOptExtendedClosestNeighbourMethodStartQualityBufferedWriter.write(objectiveFunctionValue + ",");

                //euclidean
                testMatrix = tableFromPointsGenerator.generateTableFromPoints(pointsGenerator.generatePoints(j, 0, 1000));

                solution = closestNeighbourMethod.solve(testMatrix);

                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                euclideanClosestNeighbourPathQualityBufferedWriter.write(objectiveFunctionValue + ",");

                solution = extendedClosestNeighbourMethod.solve(testMatrix);

                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                euclideanExtendedClosestNeighbourPathQualityBufferedWriter.write(objectiveFunctionValue + ",");

                solution = kRandomSolutionGenerator.solve(testMatrix);

                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                euclideanKRandomQualityBufferedWriter.write(objectiveFunctionValue + ",");

                solution = twoOptSolutionGeneratorKRandomSolutionGeneratorStart.solve(testMatrix);

                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                euclideanTwoOptKRandomSolutionGeneratorStartQualityBufferedWriter.write(objectiveFunctionValue + ",");

                solution = twoOptSolutionGeneratorClosestNeighbourMethodStart.solve(testMatrix);

                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                euclideanTwoOptClosestNeighbourMethodStartQualityBufferedWriter.write(objectiveFunctionValue + ",");

                solution = twoOptSolutionGeneratorExtendedClosestNeighbourMethodStart.solve(testMatrix);

                objectiveFunctionValue = SolutionGenerator.objectiveFunction(solution, testMatrix);
                euclideanTwoOptExtendedClosestNeighbourMethodStartQualityBufferedWriter.write(objectiveFunctionValue + ",");

                System.out.println("n: " + j);
            }

            symmetricClosestNeighbourPathTimeBufferedWriter.write("\n");
            symmetricExtendedClosestNeighbourPathTimeBufferedWriter.write("\n");
            symmetricKRandomTimeBufferedWriter.write("\n");
            symmetricClosestNeighbourPathQualityBufferedWriter.write("\n");
            symmetricExtendedClosestNeighbourPathQualityBufferedWriter.write("\n");
            symmetricKRandomQualityBufferedWriter.write("\n");
            asymmetricClosestNeighbourPathQualityBufferedWriter.write("\n");
            asymmetricExtendedClosestNeighbourPathQualityBufferedWriter.write("\n");
            asymmetricKRandomQualityBufferedWriter.write("\n");
            asymmetricTwoOptClosestNeighbourMethodStartQualityBufferedWriter.write("\n");
            asymmetricTwoOptExtendedClosestNeighbourMethodStartQualityBufferedWriter.write("\n");
            asymmetricTwoOptKRandomSolutionGeneratorStartQualityBufferedWriter.write("\n");
            symmetricTwoOptExtendedClosestNeighbourMethodStartQualityBufferedWriter.write("\n");
            symmetricTwoOptExtendedClosestNeighbourMethodStartTimeBufferedWriter.write("\n");
            symmetricTwoOptClosestNeighbourMethodStartQualityBufferedWriter.write("\n");
            symmetricTwoOptClosestNeighbourMethodStartTimeBufferedWriter.write("\n");
            symmetricTwoOptKRandomSolutionGeneratorStartQualityBufferedWriter.write("\n");
            symmetricTwoOptKRandomSolutionGeneratorStartTimeBufferedWriter.write("\n");
            euclideanKRandomQualityBufferedWriter.write("\n");
            euclideanClosestNeighbourPathQualityBufferedWriter.write("\n");
            euclideanExtendedClosestNeighbourPathQualityBufferedWriter.write("\n");
            euclideanTwoOptKRandomSolutionGeneratorStartQualityBufferedWriter.write("\n");
            euclideanTwoOptClosestNeighbourMethodStartQualityBufferedWriter.write("\n");
            euclideanTwoOptExtendedClosestNeighbourMethodStartQualityBufferedWriter.write("\n");

            System.out.println("k: " + i);
        }

        symmetricClosestNeighbourPathTimeBufferedWriter.close();
        symmetricExtendedClosestNeighbourPathTimeBufferedWriter.close();
        symmetricKRandomTimeBufferedWriter.close();
        symmetricClosestNeighbourPathQualityBufferedWriter.close();
        symmetricExtendedClosestNeighbourPathQualityBufferedWriter.close();
        symmetricKRandomQualityBufferedWriter.close();
        asymmetricClosestNeighbourPathQualityBufferedWriter.close();
        asymmetricExtendedClosestNeighbourPathQualityBufferedWriter.close();
        asymmetricKRandomQualityBufferedWriter.close();
        asymmetricTwoOptClosestNeighbourMethodStartQualityBufferedWriter.close();
        asymmetricTwoOptExtendedClosestNeighbourMethodStartQualityBufferedWriter.close();
        asymmetricTwoOptKRandomSolutionGeneratorStartQualityBufferedWriter.close();
        symmetricTwoOptExtendedClosestNeighbourMethodStartQualityBufferedWriter.close();
        symmetricTwoOptExtendedClosestNeighbourMethodStartTimeBufferedWriter.close();
        symmetricTwoOptClosestNeighbourMethodStartQualityBufferedWriter.close();
        symmetricTwoOptClosestNeighbourMethodStartTimeBufferedWriter.close();
        symmetricTwoOptKRandomSolutionGeneratorStartQualityBufferedWriter.close();
        symmetricTwoOptKRandomSolutionGeneratorStartTimeBufferedWriter.close();
        euclideanKRandomQualityBufferedWriter.close();
        euclideanClosestNeighbourPathQualityBufferedWriter.close();
        euclideanExtendedClosestNeighbourPathQualityBufferedWriter.close();
        euclideanTwoOptKRandomSolutionGeneratorStartQualityBufferedWriter.close();
        euclideanTwoOptClosestNeighbourMethodStartQualityBufferedWriter.close();
        euclideanTwoOptExtendedClosestNeighbourMethodStartQualityBufferedWriter.close();
    }
}
