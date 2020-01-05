package management;

import data.Pokemon;
import data.PokemonNorm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Normalizer {
    static double hpMean;
    static double attackMean;
    static double defenceMean;
    static double spcAttMean;
    static double spcDefMean;
    static double speedMean;
    static double hpSTD;
    static double attackSTD;
    static double defenceSTD;
    static double spcAttSTD;
    static double spcDefSTD;
    static double speedSTD;


    public static void means(List<Pokemon> pokemons) {

        for(Pokemon p : pokemons) {
            hpMean += p.getHP();
            attackMean += p.getAttack();
            defenceMean += p.getDefence();
            spcAttMean += p.getSpcAttack();
            spcDefMean += p.getSpcDefence();
            speedMean += p.getSpeed();
        }

        hpMean /= pokemons.size();
        attackMean /= pokemons.size();
        defenceMean /= pokemons.size();
        spcAttMean /= pokemons.size();
        spcDefMean /= pokemons.size();
        speedMean /= pokemons.size();
    }

    public static void standardDev(List<Pokemon> pokemons) {

        for (Pokemon pok : pokemons) {
            hpSTD += Math.pow(pok.getHP() - hpMean, 2) / pokemons.size();
            attackSTD += Math.pow(pok.getAttack() - attackMean, 2) / pokemons.size();
            defenceSTD += Math.pow(pok.getDefence() - defenceMean, 2) / pokemons.size();
            spcAttSTD += Math.pow(pok.getSpcAttack() - spcAttMean, 2) / pokemons.size();
            spcDefSTD += Math.pow(pok.getSpcDefence() - spcDefMean, 2) / pokemons.size();
            speedSTD += Math.pow(pok.getSpeed() - speedMean, 2) / pokemons.size();
        }
    }


    public static List<PokemonNorm> normalize(List<Pokemon> data) {
        means(data);
        standardDev(data);
        List<PokemonNorm> normalized = new ArrayList<>();
        for(Pokemon p : data) {
            PokemonNorm q = new PokemonNorm(p.getId(), p.getName(), p.getType1(), p.getType2(), p.getLegendary(), p.getGeneration(), p.getStage());
            double attack = Math.abs((p.getAttack() - attackMean) / attackSTD);
            q.setAttack(attack);
            double defence = Math.abs((p.getDefence() - defenceMean) / defenceSTD);
            q.setDefence(defence);
            double speed = Math.abs((p.getSpeed() - speedMean) / speedSTD);
            q.setSpeed(speed);
            double specialAttack = Math.abs((p.getSpcAttack() - spcAttMean) / spcAttSTD);
            q.setSpecialAttack(specialAttack);
            double specialDefence = Math.abs((p.getSpcDefence() - spcDefMean) / spcDefSTD);
            q.setSpecialDefence(specialDefence);
            double hp = Math.abs((p.getHP() - hpMean) / hpSTD);
            q.setHp(hp);
            normalized.add(q);
        }
        return normalized;
    }

}
