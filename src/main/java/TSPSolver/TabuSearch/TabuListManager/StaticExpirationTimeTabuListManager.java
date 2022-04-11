package TSPSolver.TabuSearch.TabuListManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import java.util.HashMap;

public class StaticExpirationTimeTabuListManager extends TabuListManager {
    private Integer expirationTime;

    public StaticExpirationTimeTabuListManager(Integer expirationTime) {
        this.tabuList = new HashMap<>();
        this.expirationTime = expirationTime;
    }

    @Override
    public boolean isTabu(TSPSolution solution, Integer iterationNumber) {
        if(tabuList.containsKey(solution)) {
            if(tabuList.get(solution) < iterationNumber) {
                tabuList.remove(solution);

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
    public void addToTabuList(TSPSolution solution, Integer iterationNumber) {
        tabuList.put(solution, iterationNumber + expirationTime);
    }

    @Override
    public void filterTabuList() {

    }

    @Override
    public void reset() {
        tabuList.clear();
    }

    public void setExpirationTime(Integer expirationTime) {
        this.expirationTime = expirationTime;
    }

}
