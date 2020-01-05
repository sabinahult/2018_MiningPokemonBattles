package management;

import data.Combat;
import data.Pokemon;

import java.util.List;
import java.util.TreeMap;

//To compute the winning ratio of a pokemon (how many battles the pokemon has won out of all battles the pokemon has been engaged in)
//against its attack, speed ect.
public class Correlation {
    public static TreeMap<Integer, Double[]> getWinRatio(List<Combat> combats) {

        //The collection to be returned, the id matched to a win ratio in percent.
        TreeMap<Integer, Double[]> winRatio = new TreeMap<>();

        //The id of a pokemon and a List of 2 numbers, the first one being how many battles the pokemon has been in,
        // in total and the second how many battles the pokemon has won.
        TreeMap<Integer, int[]> idAndCounter = new TreeMap<>();

        for(Combat c : combats){

            //Getting the ID of the pokemon in the battle and the winner
            Pokemon p1 = c.getPokemon1();
            Pokemon p2 = c.getPokemon2();
            int p1ID = p1.getId();
            int p2ID = p2.getId();
            Pokemon winner = c.getWinner();
            int winnerID = winner.getId();

            if(!idAndCounter.containsKey(p1ID) && !idAndCounter.containsKey(p2ID)){

                //add one
                idAndCounter.put(p1ID, new int[8]);
                int[] i = idAndCounter.get(p1ID);
                i[0] = i[0]+1;
                i[2] = p1.getHP();
                i[3] = p1.getAttack();
                i[4] = p1.getDefence();
                i[5] = p1.getSpcAttack();
                i[6] = p1.getSpcDefence();
                i[7] = p1.getSpeed();

                //add the other
                idAndCounter.put(p2ID, new int[8]);
                int[] m = idAndCounter.get(p2ID);
                m[0] = m[0]+1;
                m[2] = p2.getHP();
                m[3] = p2.getAttack();
                m[4] = p2.getDefence();
                m[5] = p2.getSpcAttack();
                m[6] = p2.getSpcDefence();
                m[7] = p2.getSpeed();

                //add the winner count
                int[] w = idAndCounter.get(winnerID);
                w[1] = w[1]+1;
            }
            else if(!idAndCounter.containsKey(p1ID)){
                //add one
                idAndCounter.put(p1ID, new int[8]);
                int[] i = idAndCounter.get(p1ID);
                i[0] = i[0]+1;
                i[2] = p1.getHP();
                i[3] = p1.getAttack();
                i[4] = p1.getDefence();
                i[5] = p1.getSpcAttack();
                i[6] = p1.getSpcDefence();
                i[7] = p1.getSpeed();

                //increment the other
                int[] m = idAndCounter.get(p2ID);
                m[0] = m[0]+1;

                //add the winner count
                int[] w = idAndCounter.get(winnerID);
                w[1] = w[1]+1;
            }
            else if(!idAndCounter.containsKey(p2ID)){

                int[] i = idAndCounter.get(p1ID);
                i[0] = i[0]+1;

                idAndCounter.put(p2ID, new int[8]);
                int[] m = idAndCounter.get(p2ID);
                m[0] = m[0]+1;
                m[2] = p2.getHP();
                m[3] = p2.getAttack();
                m[4] = p2.getDefence();
                m[5] = p2.getSpcAttack();
                m[6] = p2.getSpcDefence();
                m[7] = p2.getSpeed();

                //add the winner count
                int[] w = idAndCounter.get(winnerID);
                w[1] = w[1]+1;
            }
            else{
                int[] i = idAndCounter.get(p1ID);
                i[0] = i[0]+1;

                int[] m = idAndCounter.get(p2ID);
                m[0] = m[0]+1;

                //add the winner count
                int[] w = idAndCounter.get(winnerID);
                w[1] = w[1]+1;
            }
        }

        for(Integer i: idAndCounter.keySet()){

            int[] x = idAndCounter.get(i);
            int totalBattles = x[0];
            int battlesWon = x[1];
            double winRate = ((double) battlesWon/ (double) totalBattles) * 100;
            winRatio.put(i,new Double[7]);
            Double[] winCorrelationSet = winRatio.get(i);
            winCorrelationSet[0] = winRate;
            //HP
            winCorrelationSet[1] = (double) x[2];
            //Attack
            winCorrelationSet[2] = (double) x[3];
            //Defence
            winCorrelationSet[3] = (double) x[4];
            //Spc Attack
            winCorrelationSet[4] = (double) x[5];
            //Spc Defence
            winCorrelationSet[5] = (double) x[6];
            //Speed
            winCorrelationSet[6] = (double) x[7];
        }

        //Print outs
        //for(Integer j: winRatio.keySet()){
          //  Double[] b = winRatio.get(j);
            //String n = String.valueOf(b[0]).replace(".", ",");
            //String l = String.valueOf(b[1]).replace(".", ",");

          //  System.out.println(l);
        //}

        return  winRatio;
    }
}

