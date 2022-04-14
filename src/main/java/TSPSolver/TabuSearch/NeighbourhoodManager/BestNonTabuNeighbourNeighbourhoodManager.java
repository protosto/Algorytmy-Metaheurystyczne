package TSPSolver.TabuSearch.NeighbourhoodManager;

import TSPSolver.TabuSearch.TSPSolution.TSPSolution;
import TSPSolver.TabuSearch.TabuListManager.TabuListManager;

import java.util.TreeSet;

public class BestNonTabuNeighbourNeighbourhoodManager implements NeighbourhoodManager {
    @Override
    public TSPSolution chooseNeighbour(TreeSet<TSPSolution> neighbourhood, TabuListManager tabuListManager, Integer iterationNumber) {
        for (TSPSolution neighbourSolution : neighbourhood) {
            if (!tabuListManager.isTabu(neighbourSolution, neighbourhood, iterationNumber)) {
                return neighbourSolution;
            }
        }

        return neighbourhood.first();
    }

    @Override
    public NeighbourhoodManager copy() {
        return new BestNonTabuNeighbourNeighbourhoodManager();
    }


}
