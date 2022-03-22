package TSPGenerator;

import java.util.Random;

public class RandomObjective {

    public static double findObjective( double [][]table ){

        double objective = 0;
        int []tab = new int[table.length];

        Random rand = new Random();
        int x;
        int var;

        for( int i = 0; i < table.length; i++){
            x = rand.nextInt(table.length-i)+i;
            var = tab[i];
            tab[i] = tab[x];
            tab[x] = var;
        }

        for( int i = 0; i < tab.length-1 ; i++){
            objective += table[tab[i]][tab[+1]];
        }
        return objective;
    }
}
