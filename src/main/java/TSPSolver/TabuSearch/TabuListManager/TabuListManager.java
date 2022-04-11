package TSPSolver.TabuSearch.TabuListManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;

import java.util.HashMap;

public abstract class TabuListManager {
    protected HashMap<TSPSolution, Integer> tabuList;

    public abstract boolean isTabu(TSPSolution solution, Integer iterationNumber);
    public abstract void addToTabuList(TSPSolution solution, Integer iterationNumber);
    public abstract void filterTabuList();
    public abstract void reset();
}
