
import TSPTester.KValueTester;
import TSPTester.TimeAndQualityTester;
import java.io.IOException;

public class TSP {
    public static void main(String[] args) throws IOException {
        TimeAndQualityTester timeAndQualityTester = new TimeAndQualityTester();
        KValueTester kValueTester = new KValueTester();
        timeAndQualityTester.test(50);
        kValueTester.test(50);
    }
}
