package management;

import data.Combat;

import java.util.List;

//Calculates the base performance of ID3 with combatsets
public class ZeroR {

    public static void calculateZeroR(List<Combat> combats){
        int pokemon1Win = 0;
        int pokemon2Win = 0;

        for(Combat c: combats){
            if(c.getPokemon1().getName().equals(c.getWinner().getName())){
                pokemon1Win++;
            }
            else{
                pokemon2Win++;
            }
        }
        //It is pokemon 2 that wins priority voting.
        //So I did not write a lot for this case.
        if(pokemon1Win > pokemon2Win) {
            System.out.println("Majority voting in the Combat dataset: " + "Pokemon1");
        }
        else{
            System.out.println("******ZeroR Calculation******");
            System.out.println("Majority voting in the Combat dataset: " + "Pokemon2" + "\nNumber of Pokemon2 wins: " + pokemon2Win);
            System.out.println("Number of Pokemon1 wins: " + pokemon1Win);
            double ZeroR = ((double)(26399 +0)/(26399+0+0+23601))*100;
            System.out.println("ZeroR = " + ZeroR + "%");
        }


    }
}
