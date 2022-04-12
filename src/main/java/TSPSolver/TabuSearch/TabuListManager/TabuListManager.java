package TSPSolver.TabuSearch.TabuListManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import java.util.Map;
import java.util.TreeSet;

public abstract class TabuListManager {
    protected Map<TSPSolution, Integer> tabuList;

    public abstract boolean isTabu(TSPSolution solution, TreeSet neighbourhood, Integer iterationNumber);
    public abstract void addToTabuList(TSPSolution solution, TreeSet neighbourhood, Integer iterationNumber);
    public abstract void filterTabuList(TSPSolution solution, TreeSet neighbourhood, Integer iterationNumber);
    public abstract void reset();
}
