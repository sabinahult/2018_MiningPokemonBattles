package management;

import data.*;
import enums.PokemonEnums.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {
    public static List<Pokemon> readPokemons(String csvFile) throws IOException {
        List<Pokemon> pokemons = new ArrayList<>();
        BufferedReader buff = new BufferedReader(new FileReader(new File(csvFile)));

        String line = buff.readLine(); // don't need headers
        while ((line = buff.readLine()) != null) {
            Pokemon pokemon = new Pokemon();
            String[] data = line.split(",");
            for (int i = 0; i < data.length; i++) {
                if (i == 0) pokemon.setId(Integer.parseInt(data[i]));
                if (i == 1) pokemon.setName(data[i]);
                if (i == 2) pokemon.setType1(getType1(data[i].toLowerCase()));
                if (i == 3) pokemon.setType2(getType2(data[i].toLowerCase()));
                if (i == 4) pokemon.setHP(Integer.parseInt(data[i]));
                if (i == 5) pokemon.setAttack(Integer.parseInt(data[i]));
                if (i == 6) pokemon.setDefence(Integer.parseInt(data[i]));
                if (i == 7) pokemon.setSpcAttack(Integer.parseInt(data[i]));
                if (i == 8) pokemon.setSpcDefence(Integer.parseInt(data[i]));
                if (i == 9) pokemon.setSpeed(Integer.parseInt(data[i]));
                if (i == 10) pokemon.setGeneration(Integer.parseInt(data[i]));
                if (i == 11) pokemon.setLegendary(Boolean.parseBoolean(data[i]));
            }
            pokemons.add(pokemon);
        }
        return pokemons;
    }

    // from another file with another format
    public static List<Pokemon> addEvolution(String csvFile, List<Pokemon> pokemons) throws IOException {
        BufferedReader buff = new BufferedReader(new FileReader(new File(csvFile)));
        String line = buff.readLine();
        String[] headers = line.split(",");

        // a map of pokemons to their ID's in current dataset
        Map<Integer, Pokemon> tempPoke = new HashMap<>();

        // a map of evolutionchainID to the members of the chain
        Map<Integer, List<Pokemon>> evolutionChain = new HashMap<>();

        while ((line = buff.readLine()) != null) {
            String[] pokemon = line.split(",");
            int pokelength = pokemon.length;
            int pokeIndex = Integer.parseInt(pokemon[0]);
            // locate the pokemons in the already loaded data
            for (Pokemon p : pokemons) {
                if (p.getName().toLowerCase().equals(pokemon[1])) {
                    // place them in the map with their temporary ID
                    tempPoke.put(pokeIndex, p);
                    break;
                }
            }

            // if this is a pokemon that appears in both datasets
            if (tempPoke.containsKey(pokeIndex)) {
                // if it is a member of an evolution chain, add it to the map
                if (!pokemon[pokelength - 3].equals("")) {
                    int chainID = Integer.parseInt(pokemon[pokelength - 3]);
                    tempPoke.get(pokeIndex).setEvolutionChain(chainID);
                    if (evolutionChain.containsKey(chainID)) {
                        evolutionChain.get(chainID).add(tempPoke.get(pokeIndex));
                    } else {
                        List<Pokemon> evolution = new ArrayList<>();
                        evolution.add(tempPoke.get(pokeIndex));
                        evolutionChain.put(chainID, evolution);
                    }
                }
                // if it has a parent, set the parent ID
                if (!pokemon[pokelength - 4].equals("")) {
                    int parentID = Integer.parseInt(pokemon[pokelength - 4]);
                    tempPoke.get(pokeIndex).setParentID(parentID);
                }
            }
        }
        // for each "family" of pokemons
        for (Map.Entry<Integer, List<Pokemon>> chain : evolutionChain.entrySet()) {
            int stagesToSet = chain.getValue().size();
            List<Pokemon> family = chain.getValue();
            // for each pokemon in the family
            for (Pokemon p : family) {
                // count the length of the evolution chain
                int count = 1;
                if (p.getParentID() == 0) {
                    p.setStage(count);
                } else {
                    Pokemon pokemonToExamine = p;
                    int lastInLine = pokemonToExamine.getParentID();
                    while (pokemonToExamine.getParentID() != 0) {
                        lastInLine = pokemonToExamine.getParentID();
                        pokemonToExamine = tempPoke.get(lastInLine);
                        count++;
                    }
                    // set the evolution stage to be the length of the evolution chain from the pokemon and back
                    p.setStage(count);
                }
            }
        }
        return pokemons;
    }


    // adding type matching data from yet another differently formatted csv file
    public static List<Pokemon> addData(String csvFile, List<Pokemon> pokemons) throws IOException {
        BufferedReader buff = new BufferedReader(new FileReader(new File(csvFile)));
        String line = buff.readLine();
        String[] headers = line.split(",");

        int count = 1;
        while ((line = buff.readLine()) != null) {
            String[] pok = line.split(",");
            Pokemon pokemonToAlter = null;
            count++;

            // match the pokemons from the new dataset with the ones from the old dataset
            // TODO SH: Kunne dette gøres med en binary search eller en anden hurtig måde :)?
            for(Pokemon p : pokemons) {
                if (p.getName().startsWith(pok[pok.length - 11]) || pok[pok.length - 11].startsWith(p.getName())) {
                    pokemonToAlter = p;
                    break;
                }
            }
            // to avoid null pointer
            if (pokemonToAlter == null) pokemonToAlter = new Pokemon();

            // a map of types to the pokemons efficiency against them
            Map<Type1, Double> typeMatchingMap = new HashMap<>();
            int index = 1;
            for (int i = 0; i < pok.length; i++) {
                // jumping over the abilities to get to the right data
                while (!pok[i].matches("[0-9].*") && i < 6) i++;

                // limiting the search to 18, which is the number of types
                if (index < 19) {
                    // retrieving the types from the headers and converting them to Type enums
                    String typeHeader = headers[index];
                    String typeString = typeHeader.split("_")[1];
                    Type1 type = getType1(typeString);
                    typeMatchingMap.put(type, Double.parseDouble(pok[i]));
                    index++;
                }
                // add the map to the pokemons info
                pokemonToAlter.addTypeMap(typeMatchingMap);
            }
        }
        for(Pokemon p : pokemons) {
            if(p.getTypeMap().size() == 0) {
                for(Pokemon q : pokemons) {
                    if(p.getType1().equals(q.getType1())) {
                        p.addTypeMap(q.getTypeMap());
                        break;
                    }
                }
            }
        }
        return pokemons;
    }

    // create combat objects from csv file
    public static List<Combat> readCombats(String csvFile, List<Pokemon> pokemons) throws IOException {
        BufferedReader buff = new BufferedReader(new FileReader(new File(csvFile)));
        List<Combat> combats = new ArrayList<>();

        int id = 0;
        String line = buff.readLine(); // don't need the headers
        while ((line = buff.readLine()) != null) {
            String[] data = line.split(",");
            Pokemon pokemon1 = null;
            Pokemon pokemon2 = null;
            Pokemon winner = null;

            for (Pokemon p : pokemons) {
                if (Integer.parseInt(data[0]) == p.getId()) pokemon1 = p;
                if (Integer.parseInt(data[1]) == p.getId()) pokemon2 = p;
                if (Integer.parseInt(data[2]) == p.getId()) winner = p;
            }
            combats.add(new Combat(id++, pokemon1, pokemon2, winner));
        }
        return combats;
    }

    // convert string to enum Type1
        public static Type1 getType1(String type) {
            Type1 typeToAdd = null;
            if (type.equals("grass")) typeToAdd = Type1.Grass;
            if (type.equals("water")) typeToAdd = Type1.Water;
            if (type.equals("fire")) typeToAdd = Type1.Fire;
            if (type.equals("poison")) typeToAdd = Type1.Poison;
            if (type.equals("rock")) typeToAdd = Type1.Rock;
            if (type.equals("dark")) typeToAdd = Type1.Dark;
            if (type.equals("psychic")) typeToAdd = Type1.Psychic;
            if (type.equals("ghost")) typeToAdd = Type1.Ghost;
            if (type.equals("fairy")) typeToAdd = Type1.Fairy;
            if (type.equals("dragon")) typeToAdd = Type1.Dragon;
            if (type.equals("flying")) typeToAdd = Type1.Flying;
            if (type.equals("ground")) typeToAdd = Type1.Ground;
            if (type.startsWith("fight")) typeToAdd = Type1.Fighting;
            if (type.equals("electric")) typeToAdd = Type1.Electric;
            if (type.equals("steel")) typeToAdd = Type1.Steel;
            if (type.equals("ice")) typeToAdd = Type1.Ice;
            if (type.equals("bug")) typeToAdd = Type1.Bug;
            if (type.equals("normal")) typeToAdd = Type1.Normal;
            return typeToAdd;
        }

    // convert string to enum Type
    public static Type2 getType2(String type) {
        Type2 typeToAdd = null;
        if (type.equals("grass")) typeToAdd = Type2.Grass;
        if (type.equals("water")) typeToAdd = Type2.Water;
        if (type.equals("fire")) typeToAdd = Type2.Fire;
        if (type.equals("poison")) typeToAdd = Type2.Poison;
        if (type.equals("rock")) typeToAdd = Type2.Rock;
        if (type.equals("dark")) typeToAdd = Type2.Dark;
        if (type.equals("psychic")) typeToAdd = Type2.Psychic;
        if (type.equals("ghost")) typeToAdd = Type2.Ghost;
        if (type.equals("fairy")) typeToAdd = Type2.Fairy;
        if (type.equals("dragon")) typeToAdd = Type2.Dragon;
        if (type.equals("flying")) typeToAdd = Type2.Flying;
        if (type.equals("ground")) typeToAdd = Type2.Ground;
        if (type.startsWith("fight")) typeToAdd = Type2.Fighting;
        if (type.equals("electric")) typeToAdd = Type2.Electric;
        if (type.equals("steel")) typeToAdd = Type2.Steel;
        if (type.equals("ice")) typeToAdd = Type2.Ice;
        if (type.equals("bug")) typeToAdd = Type2.Bug;
        if (type.equals("normal")) typeToAdd = Type2.Normal;
        if (type.equals("")) typeToAdd = Type2.None;
        return typeToAdd;
    }

}



