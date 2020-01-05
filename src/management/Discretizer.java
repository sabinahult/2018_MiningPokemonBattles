package management;

import data.Combat;
import data.CombatD;
import data.Pokemon;
import data.PokemonD;
import enums.PokemonEnums.*;
import enums.CombatEnums.*;
import java.util.*;

public class Discretizer {
    public static List<PokemonD> dicretizePokemons(List<Pokemon> pokemons) {
        //arrays for all the values of the pokemons
       int[] hps = new int[pokemons.size()];
       int[] attacks = new int[pokemons.size()];
       int[] defence = new int[pokemons.size()];
       int[] specAtt = new int[pokemons.size()];
       int[] specDef = new int[pokemons.size()];
       int[] speed = new int[pokemons.size()];

       // fill the arrays
       for(int i = 0; i < pokemons.size(); i++) {
           hps[i] = pokemons.get(i).getHP();
           attacks[i] = pokemons.get(i).getAttack();
           defence[i] = pokemons.get(i).getDefence();
           specAtt[i] = pokemons.get(i).getSpcAttack();
           specDef[i] = pokemons.get(i).getSpcDefence();
           speed[i] = pokemons.get(i).getSpeed();
       }

        //sort the arrays
        Arrays.sort(hps);
        Arrays.sort(attacks);
        Arrays.sort(defence);
        Arrays.sort(specAtt);
        Arrays.sort(specDef);
        Arrays.sort(speed);

        // find the index of the splitting points
        int extremeLowRank = 5 * 8;
        int lowRank = 35 * 8;
        int mediumRank = 65 * 8;
        int highRank = 95 * 8;

        // identify the splitting values for the attributes
        int extremeLowHP = hps[extremeLowRank];
        int lowHP = hps[lowRank];
        int mediumHP = hps[mediumRank];
        int highHP = hps[highRank];

        int extremeLowAttack = attacks[extremeLowRank];
        int lowAttack = attacks[lowRank];
        int mediumAttack = attacks[mediumRank];
        int highAttack = attacks[highRank];

        int extremeLowDefence = defence[extremeLowRank];
        int lowDefence = defence[lowRank];
        int mediumDefence = defence[mediumRank];
        int highDefence = defence[highRank];

        int extremeLowSpecAttack = specAtt[extremeLowRank];
        int lowSpecAttack = specAtt[lowRank];
        int mediumSpecAttack = specAtt[mediumRank];
        int highSpecAttack = specAtt[highRank];

        int extremeLowSpecDefence = specDef[extremeLowRank];
        int lowSpecDefence = specDef[lowRank];
        int mediumSpecDefence = specDef[mediumRank];
        int highSpecDefence = specDef[highRank];

        int extremeLowSpeed = speed[extremeLowRank];
        int lowSpeed = speed[lowRank];
        int mediumSPeed = speed[mediumRank];
        int highSpeed = speed[highRank];

        List<PokemonD> dicretizedPokemons = new ArrayList<>();

        // create the discretized pokemons
        for(Pokemon p : pokemons) {
            String name = p.getName();
            int number = p.getId();
            Type1 type1 = p.getType1();
            Type2 type2 = p.getType2();
            Generation generation;
            Stage stage;
            Legendary legendary;
            int evolutionChain = p.getEvolutionChain();
            HP hp;
            Attack attack;
            Defence def;
            SpecialDefence specDefe;
            SpecialAttack specAtta;
            Speed speedL;

            if(p.getHP() > highHP) hp = HP.EXTREMEHIGH;
            else if(p.getHP() > mediumHP) hp = HP.HIGH;
            else if(p.getHP() > lowHP) hp = HP.MEDIUM;
            else if(p.getHP() > extremeLowHP) hp = HP.LOW;
            else hp = HP.EXTREMELOW;

            if(p.getAttack() > highAttack) attack = Attack.EXTREMEHIGH;
            else if(p.getAttack() > mediumAttack) attack = Attack.HIGH;
            else if(p.getAttack() > lowAttack) attack = Attack.MEDIUM;
            else if(p.getAttack() > extremeLowAttack) attack = Attack.LOW;
            else attack = Attack.EXTREMELOW;

            if(p.getDefence() > highDefence) def = Defence.EXTREMEHIGH;
            else if(p.getDefence() > mediumDefence) def = Defence.HIGH;
            else if(p.getDefence() > lowDefence) def = Defence.MEDIUM;
            else if(p.getDefence() > extremeLowDefence) def = Defence.LOW;
            else def = Defence.EXTREMELOW;

            if(p.getSpcAttack() > highSpecAttack) specAtta = SpecialAttack.EXTREMEHIGH;
            else if(p.getSpcAttack() > mediumSpecAttack)specAtta = SpecialAttack.HIGH;
            else if(p.getSpcAttack() > lowSpecAttack) specAtta = SpecialAttack.MEDIUM;
            else if(p.getSpcAttack() > extremeLowSpecAttack) specAtta = SpecialAttack.LOW;
            else specAtta = SpecialAttack.EXTREMELOW;

            if(p.getSpcDefence() > highSpecDefence) specDefe = SpecialDefence.EXTREMEHIGH;
            else if(p.getSpcDefence() > mediumSpecDefence) specDefe = SpecialDefence.HIGH;
            else if(p.getSpcDefence() > lowSpecDefence) specDefe = SpecialDefence.MEDIUM;
            else if(p.getSpcDefence() > extremeLowSpecDefence) specDefe = SpecialDefence.LOW;
            else specDefe = SpecialDefence.EXTREMELOW;

            if(p.getSpeed() > highSpeed) speedL = Speed.EXTREMEHIGH;
            else if(p.getSpeed() > mediumSPeed) speedL = Speed.HIGH;
            else if(p.getSpeed() > lowSpeed) speedL = Speed.MEDIUM;
            else if(p.getSpeed() > extremeLowSpeed) speedL = Speed.LOW;
            else speedL = Speed.EXTREMELOW;

            if(p.getGeneration() == 1) generation = Generation.FIRST;
            else if(p.getGeneration() == 2) generation = Generation.SECOND;
            else if(p.getGeneration() == 3) generation = Generation.THIRD;
            else if(p.getGeneration() == 4) generation = Generation.FOURTH;
            else if(p.getGeneration() == 5) generation = Generation.FIFTH;
            else generation = Generation.SIXTH;

            if(p.getStage() == 0) stage = Stage.ONLY;
            else if(p.getStage() == 1) stage = Stage.FIRST;
            else if(p.getStage() == 2) stage = Stage.SECOND;
            else stage = Stage.THIRD;

            if(p.getLegendary()) legendary = Legendary.LEGENDARY;
            else legendary = Legendary.ORDINARY;

            PokemonD discretizedPokemon =
                    new PokemonD(name, number, type1, type2, generation, stage, legendary, evolutionChain, hp,
                            attack, def, specAtta, specDefe, speedL, p.getTypeMap());
            dicretizedPokemons.add(discretizedPokemon);
        }

        return dicretizedPokemons;
    }

    public static List<CombatD> discretizeCombats(List<Combat> combats, List<PokemonD> discretizedPokemons){
        List<CombatD> discretizedCombats = new ArrayList<>();
        for(Combat combat : combats) {
            PokemonD pokemon1 = null;
            PokemonD pokemon2 = null;
            PokemonD winner = null;
            for(PokemonD discPok : discretizedPokemons) {
                if(combat.getPokemon1().getId() == discPok.getId()) pokemon1 = discPok;
                if(combat.getPokemon2().getId() == discPok.getId()) pokemon2 = discPok;
                if(combat.getWinner().getId() == discPok.getId()) winner = discPok;
            }
            discretizedCombats.add(new CombatD(combat.getId(), pokemon1, pokemon2, winner));
        }
        return discretizedCombats;
    }
}
