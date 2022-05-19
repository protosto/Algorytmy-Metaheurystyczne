package TSPSolver.TabuSearch.TabuListManager;

import TSPSolver.TSPSolution.TSPSolution;

import java.util.HashMap;
import java.util.TreeSet;

public class RandomTabuTenureTabuListManager extends TabuListManager {

  private Integer tabuTenureMinValue;
  private Integer tabuTenureMaxValue;

  public RandomTabuTenureTabuListManager(Integer tabuTenureMinValue, Integer  tabuTenureMaxValue) {
    this.tabuList = new HashMap<>();
    this.tabuTenureMinValue = tabuTenureMinValue;
    this.tabuTenureMaxValue = tabuTenureMaxValue;
  }

  @Override
  public boolean isTabu(TSPSolution currentSolution, TreeSet<TSPSolution> neighbourhood, Integer iterationNumber) {
    if (tabuList.containsKey(currentSolution)) {
      if (tabuList.get(currentSolution) < iterationNumber) {
        tabuList.remove(currentSolution);

        return false;
      } else {
        return true;
      }
    } else {
      return false;
    }
  }

  @Override
  public void addToTabuList(TSPSolution currentSolution, TreeSet<TSPSolution> neighbourhood, Integer iterationNumber) {
    int tabuTenure = (int) (Math.random()*(tabuTenureMaxValue - tabuTenureMinValue)+tabuTenureMinValue+1);
    tabuList.put(currentSolution, iterationNumber + tabuTenure);
  }

  @Override
  public void filterTabuList(TSPSolution currentSolution, TreeSet<TSPSolution> neighbourhood, Integer iterationNumber) {

  }

  @Override
  public void reset() {
    tabuList.clear();
  }

  @Override
  public TabuListManager copy() {
    return new RandomTabuTenureTabuListManager(tabuTenureMinValue, tabuTenureMaxValue);
  }

  public void setTabuTenure(Integer tabuTenureMinValue, Integer tabuTenureMaxValue) {
    this.tabuTenureMinValue = tabuTenureMinValue;
    this.tabuTenureMaxValue = tabuTenureMaxValue;
  }

}