package TSPSolver.TabuSearch.TabuListManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import java.util.HashMap;
import java.util.TreeSet;

public class StaticTabuTenureTabuListManager extends TabuListManager {
    private Integer tabuTenure;

    public StaticTabuTenureTabuListManager(Integer tabuTenure) {
        this.tabuList = new HashMap<>();
        this.tabuTenure = tabuTenure;
    }

    @Override
    public boolean isTabu(TSPSolution currentSolution, TreeSet neighbourhood, Integer iterationNumber) {
        if(tabuList.containsKey(currentSolution)) {
            if(tabuList.get(currentSolution) < iterationNumber) {
                tabuList.remove(currentSolution);

                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    @Override
    public void addToTabuList(TSPSolution currentSolution, TreeSet neighbourhood, Integer iterationNumber) {
        tabuList.put(currentSolution, iterationNumber + tabuTenure);
    }

    @Override
    public void filterTabuList(TSPSolution currentSolution, TreeSet neighbourhood, Integer iterationNumber) {

    }

    @Override
    public void reset() {
        tabuList.clear();
    }

    public void setTabuTenure(Integer tabuTenure) {
        this.tabuTenure = tabuTenure;
    }

}
