package PokemonCluster;

import data.PokemonNorm;
import java.util.List;

public class Distance {
    private static double attackMax = -Double.MAX_VALUE;
    private static double attackMin = Double.MAX_VALUE;
    private static double defenceMax = -Double.MAX_VALUE;
    private static double defenceMin = Double.MAX_VALUE;
    private static double speedMax = -Double.MAX_VALUE;
    private static double speedMin = Double.MAX_VALUE;
    private static double specialAttackMax = -Double.MAX_VALUE;
    private static double specialAttackMin = Double.MAX_VALUE;
    private static double specialDefenceMax = -Double.MAX_VALUE;
    private static double specialDefenceMin = Double.MAX_VALUE;
    private static double hpMax = -Double.MAX_VALUE;
    private static double hpMin = Double.MAX_VALUE;

    public static double calculateDistance(PokemonNorm p, PokemonNorm medoid) {

        double distance = 0;
        distance += (Math.abs(p.getAttack() - medoid.getAttack()) / (attackMax - attackMin));
        distance += (Math.abs(p.getDefence() - medoid.getDefence()) / (defenceMax - defenceMin));
        //distance += (Math.abs(p.getSpeed() - medoid.getSpeed()) / (speedMax - speedMin));
        distance += (Math.abs(p.getSpecialAttack() - medoid.getSpecialAttack()) / (specialAttackMax - specialAttackMin));
        distance += (Math.abs(p.getSpecialDefence() - medoid.getSpecialDefence()) / (specialDefenceMax - specialDefenceMin));
        distance += (Math.abs(p.getHp() - medoid.getHp()) / (hpMax - hpMin));
        //if (p.getGeneration() != medoid.getGeneration()) distance++;
        if (p.getStage() != medoid.getStage()) distance++;
        if (!p.getType1().equals(medoid.getType1())) distance++;
        if (!p.getType2().equals(medoid.getType2())) distance++;
        //if (!(p.getLegendary() && medoid.getLegendary())) distance++;
        return distance;
    }

    public static void setExtremes(List<PokemonNorm> data) {
        for(PokemonNorm p : data) {
            if(p.getAttack() < attackMin) attackMin = p.getAttack();
            if(p.getAttack() > attackMax) attackMax = p.getAttack();
            if(p.getDefence() < defenceMin) defenceMin = p.getDefence();
            if(p.getDefence() > defenceMax) defenceMax = p.getDefence();
            if(p.getSpecialAttack() < specialAttackMin) specialAttackMin = p.getSpecialAttack();
            if(p.getSpecialAttack() > specialAttackMax) specialAttackMax = p.getSpecialAttack();
            if(p.getSpecialDefence() < specialDefenceMin) specialDefenceMin = p.getSpecialDefence();
            if(p.getSpecialDefence() > specialDefenceMax) specialDefenceMax = p.getSpecialDefence();
            if(p.getHp() < hpMin) hpMin = p.getHp();
            if(p.getHp()> hpMax) hpMax = p.getHp();
            if(p.getSpeed() < speedMin) speedMin = p.getSpeed();
            if(p.getSpeed() > speedMax) speedMax = p.getSpeed();
        }
    }
}
