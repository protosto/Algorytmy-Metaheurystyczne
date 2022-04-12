package TSPSolver.TabuSearch.TabuListManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import java.util.Map;
import java.util.TreeSet;

public abstract class TabuListManager {
    protected Map<TSPSolution, Integer> tabuList;

    public abstract boolean isTabu(TSPSolution currentSolution, TreeSet neighbourhood, Integer iterationNumber);
    public abstract void addToTabuList(TSPSolution currentSolution, TreeSet neighbourhood, Integer iterationNumber);
    public abstract void filterTabuList(TSPSolution currentSolution, TreeSet neighbourhood, Integer iterationNumber);
    public abstract void reset();
}
