package management;

import data.Pokemon;
import enums.PokemonEnums.Type1;
import enums.PokemonEnums.Type2;

import java.util.HashMap;
import java.util.List;

//In order to create Histograms
public class Frequency {

    //The frequency of types in the pokemon Lim set, containing all pokemons
    public static void frequencyTypeAllPokemon(List<Pokemon> pokemons){

        //Overall count
        HashMap<String, Integer> frequency = new HashMap<>();
        //Count of type 1
        HashMap<String, Integer> frequencyType1 = new HashMap<>();
        //Count of type 2
        HashMap<String, Integer> frequencyType2 = new HashMap<>();
        //Count of how many legendary each type contains
        HashMap<String, Integer> frequencyLegendary = new HashMap<>();
        //Count how many legendary there are in total
        int legendaryCounter = 0;

        for(Pokemon p: pokemons){
            String type1 = p.getType1().name();
            String type2 = p.getType2().name();
            boolean legendary = p.getLegendary();
            if(legendary){
                legendaryCounter++;
            }

            if(!frequency.containsKey(type1)){
                frequency.put(type1, 1);
                if(legendary){
                    if(!frequencyLegendary.containsKey(type1)){
                        frequencyLegendary.put(type1, 1);
                    }
                    else{
                        int count = frequencyLegendary.get(type1);
                        frequencyLegendary.put(type1, count+1);
                    }
                }
            }
            else {
                int count = frequency.get(type1);
                frequency.put(type1, count + 1);
                if (legendary) {
                    if (!frequencyLegendary.containsKey(type1)) {
                        frequencyLegendary.put(type1, 1);
                    } else {
                        int count1 = frequencyLegendary.get(type1);
                        frequencyLegendary.put(type1, count1 + 1);
                    }
                }
            }
            if(!frequency.containsKey(type2)) {
                frequency.put(type2, 1);
                if (legendary) {
                    if (!frequencyLegendary.containsKey(type2)) {
                        frequencyLegendary.put(type2, 1);
                    } else {
                        int count = frequencyLegendary.get(type2);
                        frequencyLegendary.put(type2, count + 1);
                    }
                }
            }
            else {
                    int count = frequency.get(type2);
                    frequency.put(type2, count + 1);
                    if (legendary) {
                        if (!frequencyLegendary.containsKey(type2)) {
                            frequencyLegendary.put(type2, 1);
                        } else {
                            int count1 = frequencyLegendary.get(type2);
                            frequencyLegendary.put(type2, count1 + 1);
                        }
                    }
                }
            if(!frequencyType1.containsKey(type1)){
                frequencyType1.put(type1, 1);
            }
            else{
                int count = frequencyType1.get(type1);
                frequencyType1.put(type1, count+1);
            }
            if(!frequencyType2.containsKey(type2)){
                frequencyType2.put(type2, 1);
            }
            else{
                int count = frequencyType2.get(type2);
                frequencyType2.put(type2, count+1);
            }

        }

        System.out.println("***Frequency Type Counter for Histograms***");
        System.out.println("Overall frequency for both type 1 and type 2 in the Pokemon Set: ");
        for(String key: frequency.keySet()){
            int counter = frequency.get(key);
            System.out.println(key + " " + counter);
        }
        System.out.println("\nFrequency of Type 1 in the Pokemon Set: ");
        for(String key1: frequencyType1.keySet()){
            int counter1 = frequencyType1.get(key1);
            System.out.println(key1 + " " + counter1);
        }
        System.out.println("\nFrequency of Type 2 in the Pokemon Set: ");
        for(String key2: frequencyType2.keySet()){
            int counter2 = frequencyType2.get(key2);
            System.out.println(key2 + " " + counter2);
        }

        System.out.println("\nFrequency of Legendary and Type in the Pokemon Set: ");
        for(String key3: frequencyLegendary.keySet()){
            int counter3 = frequencyLegendary.get(key3);
            System.out.println(key3 + " " + counter3);
        }
        System.out.println("Total number of legendary pokomons: " + legendaryCounter);

    }
}
