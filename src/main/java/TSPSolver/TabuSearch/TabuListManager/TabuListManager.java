package TSPSolver.TabuSearch.TabuListManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

import java.util.List;

public interface TabuListManager {
    boolean isTabu(TSPSolution solution, Integer iterationNumber);
    void addToTabuList(TSPSolution solution, Integer iterationNumber);
    void filterTabuList();
    void reset();
}
