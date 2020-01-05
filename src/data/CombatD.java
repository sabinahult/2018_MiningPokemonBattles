package data;

import enums.CombatEnums.*;
import enums.PokemonEnums.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class to represent discretized combat objects with labels.
 */
public class CombatD {
    int id;
    private PokemonD pokemon1;
    private PokemonD pokemon2;
    private PokemonD winner;

    // FIRSTWIN or SECONDWIN
    private CombatClass classLabel;


    private CombatAttack attack;
    private CombatDefence defence;
    private CombatSpeed speed;
    private CombatSpecAttack specialAttack;
    private CombatSpecDefence specialDefence;
    private CombatHP hp;
    private CombatLegendary legendary;
    private CombatStage stage;
    private CombatType type;
    private ClusterType cluster;

    public CombatD(int id, PokemonD pokemon1, PokemonD pokemon2, PokemonD winner) {
        this.id = id;
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.winner = winner;
        if(pokemon1.getId() == winner.getId()) classLabel = CombatClass.FIRSTWIN;
        else classLabel = CombatClass.SECONDWIN;
        setAttack();
        setDefence();
        setHP();
        setLegendary();
        setSpecAttack();
        setSpecDefence();
        setSpeed();
        setStage();
        setType();
        setCluster();


    }

    public int getId() { return id; }

    public PokemonD getPokemon1() {
        return pokemon1;
    }

    public PokemonD getPokemon2() {
        return pokemon2;
    }

    public PokemonD getWinner() {
        return winner;
    }

    public CombatClass getClassLabel() {
        return classLabel;
    }

    /**
     * Returns the value for the given attribute based on class
     * @param attribute the class of the attribute
     * @return the value for that attribute
     */
    public Object getAttributeValue(Object attribute) {
        if(attribute.equals(CombatAttack.class)) return attack;
        if(attribute.equals(CombatDefence.class)) return defence;
        if(attribute.equals(CombatSpeed.class)) return speed;
        if(attribute.equals(CombatSpecAttack.class)) return specialAttack;
        if(attribute.equals(CombatSpecDefence.class)) return specialDefence;
        if(attribute.equals(CombatHP.class)) return hp;
        if(attribute.equals(CombatLegendary.class)) return legendary;
        if(attribute.equals(CombatStage.class)) return stage;
        if(attribute.equals(CombatType.class)) return type;
        if(attribute.equals(CombatClass.class)) return classLabel;
        if(attribute.equals(ClusterType.class)) return cluster;

        // hopefully this never happens :)
        return null;
    }

    private void setAttack(){
        if(pokemon1.getAttack().equals(Attack.EXTREMELOW) && pokemon2.getAttack().equals(Attack.EXTREMELOW)) attack = CombatAttack.XL_XL;
        if(pokemon1.getAttack().equals(Attack.LOW) && pokemon2.getAttack().equals(Attack.LOW)) attack = CombatAttack.L_L;
        if(pokemon1.getAttack().equals(Attack.MEDIUM) && pokemon2.getAttack().equals(Attack.MEDIUM)) attack = CombatAttack.M_M;
        if(pokemon1.getAttack().equals(Attack.HIGH) && pokemon2.getAttack().equals(Attack.HIGH)) attack = CombatAttack.H_H;
        if(pokemon1.getAttack().equals(Attack.EXTREMEHIGH) && pokemon2.getAttack().equals(Attack.EXTREMEHIGH)) attack = CombatAttack.XH_XH;
        //if(pokemon1.getAttack().equals(pokemon2.getAttack())) attack = CombatAttack.EVEN;

        if(pokemon1.getAttack().equals(Attack.EXTREMELOW) && pokemon2.getAttack().equals(Attack.EXTREMEHIGH)) attack = CombatAttack.XL_XH;
        if(pokemon1.getAttack().equals(Attack.EXTREMELOW) && pokemon2.getAttack().equals(Attack.HIGH)) attack = CombatAttack.XL_H;
        if(pokemon1.getAttack().equals(Attack.EXTREMELOW) && pokemon2.getAttack().equals(Attack.MEDIUM)) attack = CombatAttack.XL_M;
        if(pokemon1.getAttack().equals(Attack.EXTREMELOW) && pokemon2.getAttack().equals(Attack.LOW)) attack = CombatAttack.XL_L;
        if(pokemon1.getAttack().equals(Attack.LOW) && pokemon2.getAttack().equals(Attack.EXTREMEHIGH)) attack = CombatAttack.L_XH;
        if(pokemon1.getAttack().equals(Attack.LOW) && pokemon2.getAttack().equals(Attack.HIGH)) attack = CombatAttack.L_H;
        if(pokemon1.getAttack().equals(Attack.LOW) && pokemon2.getAttack().equals(Attack.MEDIUM)) attack = CombatAttack.L_M;
        if(pokemon1.getAttack().equals(Attack.LOW) && pokemon2.getAttack().equals(Attack.EXTREMELOW)) attack = CombatAttack.L_XL;
        if(pokemon1.getAttack().equals(Attack.MEDIUM) && pokemon2.getAttack().equals(Attack.EXTREMEHIGH)) attack = CombatAttack.M_XH;
        if(pokemon1.getAttack().equals(Attack.MEDIUM) && pokemon2.getAttack().equals(Attack.HIGH)) attack = CombatAttack.M_H;
        if(pokemon1.getAttack().equals(Attack.MEDIUM) && pokemon2.getAttack().equals(Attack.LOW)) attack = CombatAttack.M_L;
        if(pokemon1.getAttack().equals(Attack.MEDIUM) && pokemon2.getAttack().equals(Attack.EXTREMELOW)) attack = CombatAttack.M_XL;
        if(pokemon1.getAttack().equals(Attack.HIGH) && pokemon2.getAttack().equals(Attack.EXTREMEHIGH)) attack = CombatAttack.H_XH;
        if(pokemon1.getAttack().equals(Attack.HIGH) && pokemon2.getAttack().equals(Attack.MEDIUM)) attack = CombatAttack.H_M;
        if(pokemon1.getAttack().equals(Attack.HIGH) && pokemon2.getAttack().equals(Attack.LOW)) attack = CombatAttack.H_L;
        if(pokemon1.getAttack().equals(Attack.HIGH) && pokemon2.getAttack().equals(Attack.EXTREMELOW)) attack = CombatAttack.H_XL;
        if(pokemon1.getAttack().equals(Attack.EXTREMEHIGH) && pokemon2.getAttack().equals(Attack.HIGH)) attack = CombatAttack.XH_H;
        if(pokemon1.getAttack().equals(Attack.EXTREMEHIGH) && pokemon2.getAttack().equals(Attack.MEDIUM)) attack = CombatAttack.XH_M;
        if(pokemon1.getAttack().equals(Attack.EXTREMEHIGH) && pokemon2.getAttack().equals(Attack.LOW)) attack = CombatAttack.XH_L;
        if(pokemon1.getAttack().equals(Attack.EXTREMEHIGH) && pokemon2.getAttack().equals(Attack.EXTREMELOW)) attack = CombatAttack.XH_XL;
    }

    private void setDefence(){
        if(pokemon1.getDefence().equals(Defence.EXTREMELOW) && pokemon2.getDefence().equals(Defence.EXTREMELOW)) defence = CombatDefence.XL_XL;
        if(pokemon1.getDefence().equals(Defence.LOW) && pokemon2.getDefence().equals(Defence.LOW)) defence = CombatDefence.L_L;
        if(pokemon1.getDefence().equals(Defence.MEDIUM) && pokemon2.getDefence().equals(Defence.MEDIUM)) defence = CombatDefence.M_M;
        if(pokemon1.getDefence().equals(Defence.HIGH) && pokemon2.getDefence().equals(Defence.HIGH)) defence = CombatDefence.H_H;
        if(pokemon1.getDefence().equals(Defence.EXTREMEHIGH) && pokemon2.getDefence().equals(Defence.EXTREMEHIGH)) defence = CombatDefence.XH_XH;
        //if(pokemon1.getDefence().equals(pokemon2.getDefence())) defence = CombatDefence.EVEN;

        if(pokemon1.getDefence().equals(Defence.EXTREMELOW) && pokemon2.getDefence().equals(Defence.EXTREMEHIGH)) defence = CombatDefence.XL_XH;
        if(pokemon1.getDefence().equals(Defence.EXTREMELOW) && pokemon2.getDefence().equals(Defence.HIGH)) defence = CombatDefence.XL_H;
        if(pokemon1.getDefence().equals(Defence.EXTREMELOW) && pokemon2.getDefence().equals(Defence.MEDIUM)) defence = CombatDefence.XL_M;
        if(pokemon1.getDefence().equals(Defence.EXTREMELOW) && pokemon2.getDefence().equals(Defence.LOW)) defence = CombatDefence.XL_L;
        if(pokemon1.getDefence().equals(Defence.LOW) && pokemon2.getDefence().equals(Defence.EXTREMEHIGH)) defence = CombatDefence.L_XH;
        if(pokemon1.getDefence().equals(Defence.LOW) && pokemon2.getDefence().equals(Defence.HIGH)) defence = CombatDefence.L_H;
        if(pokemon1.getDefence().equals(Defence.LOW) && pokemon2.getDefence().equals(Defence.MEDIUM)) defence = CombatDefence.L_M;
        if(pokemon1.getDefence().equals(Defence.LOW) && pokemon2.getDefence().equals(Defence.EXTREMELOW)) defence = CombatDefence.L_XL;
        if(pokemon1.getDefence().equals(Defence.MEDIUM) && pokemon2.getDefence().equals(Defence.EXTREMEHIGH)) defence = CombatDefence.M_XH;
        if(pokemon1.getDefence().equals(Defence.MEDIUM) && pokemon2.getDefence().equals(Defence.HIGH)) defence = CombatDefence.M_H;
        if(pokemon1.getDefence().equals(Defence.MEDIUM) && pokemon2.getDefence().equals(Defence.LOW)) defence = CombatDefence.M_L;
        if(pokemon1.getDefence().equals(Defence.MEDIUM) && pokemon2.getDefence().equals(Defence.EXTREMELOW)) defence = CombatDefence.M_XL;
        if(pokemon1.getDefence().equals(Defence.HIGH) && pokemon2.getDefence().equals(Defence.EXTREMEHIGH)) defence = CombatDefence.H_XH;
        if(pokemon1.getDefence().equals(Defence.HIGH) && pokemon2.getDefence().equals(Defence.MEDIUM)) defence = CombatDefence.H_M;
        if(pokemon1.getDefence().equals(Defence.HIGH) && pokemon2.getDefence().equals(Defence.LOW)) defence = CombatDefence.H_L;
        if(pokemon1.getDefence().equals(Defence.HIGH) && pokemon2.getDefence().equals(Defence.EXTREMELOW)) defence = CombatDefence.H_XL;
        if(pokemon1.getDefence().equals(Defence.EXTREMEHIGH) && pokemon2.getDefence().equals(Defence.HIGH)) defence = CombatDefence.XH_H;
        if(pokemon1.getDefence().equals(Defence.EXTREMEHIGH) && pokemon2.getDefence().equals(Defence.MEDIUM)) defence = CombatDefence.XH_M;
        if(pokemon1.getDefence().equals(Defence.EXTREMEHIGH) && pokemon2.getDefence().equals(Defence.LOW)) defence = CombatDefence.XH_L;
        if(pokemon1.getDefence().equals(Defence.EXTREMEHIGH) && pokemon2.getDefence().equals(Defence.EXTREMELOW)) defence = CombatDefence.XH_XL;
    }

    private void setHP(){
        if(pokemon1.getHp().equals(HP.EXTREMELOW) && pokemon2.getHp().equals(HP.EXTREMELOW)) hp = CombatHP.XL_XL;
        if(pokemon1.getHp().equals(HP.LOW) && pokemon2.getHp().equals(HP.LOW)) hp = CombatHP.L_L;
        if(pokemon1.getHp().equals(HP.MEDIUM) && pokemon2.getHp().equals(HP.MEDIUM)) hp = CombatHP.M_M;
        if(pokemon1.getHp().equals(HP.HIGH) && pokemon2.getHp().equals(HP.HIGH)) hp = CombatHP.H_H;
        if(pokemon1.getHp().equals(HP.EXTREMEHIGH) && pokemon2.getHp().equals(HP.EXTREMEHIGH)) hp = CombatHP.XH_XH;
        //if(pokemon1.getHp().equals(pokemon2.getHp())) hp = CombatHP.EVEN;

        if(pokemon1.getHp().equals(HP.EXTREMELOW) && pokemon2.getHp().equals(HP.EXTREMEHIGH)) hp = CombatHP.XL_XH;
        if(pokemon1.getHp().equals(HP.EXTREMELOW) && pokemon2.getHp().equals(HP.HIGH)) hp = CombatHP.XL_H;
        if(pokemon1.getHp().equals(HP.EXTREMELOW) && pokemon2.getHp().equals(HP.MEDIUM)) hp = CombatHP.XL_M;
        if(pokemon1.getHp().equals(HP.EXTREMELOW) && pokemon2.getHp().equals(HP.LOW)) hp = CombatHP.XL_L;
        if(pokemon1.getHp().equals(HP.LOW) && pokemon2.getHp().equals(HP.EXTREMEHIGH)) hp = CombatHP.L_XH;
        if(pokemon1.getHp().equals(HP.LOW) && pokemon2.getHp().equals(HP.HIGH)) hp = CombatHP.L_H;
        if(pokemon1.getHp().equals(HP.LOW) && pokemon2.getHp().equals(HP.MEDIUM)) hp = CombatHP.L_M;
        if(pokemon1.getHp().equals(HP.LOW) && pokemon2.getHp().equals(HP.EXTREMELOW)) hp = CombatHP.L_XL;
        if(pokemon1.getHp().equals(HP.MEDIUM) && pokemon2.getHp().equals(HP.EXTREMEHIGH)) hp = CombatHP.M_XH;
        if(pokemon1.getHp().equals(HP.MEDIUM) && pokemon2.getHp().equals(HP.HIGH)) hp = CombatHP.M_H;
        if(pokemon1.getHp().equals(HP.MEDIUM) && pokemon2.getHp().equals(HP.LOW)) hp = CombatHP.M_L;
        if(pokemon1.getHp().equals(HP.MEDIUM) && pokemon2.getHp().equals(HP.EXTREMELOW)) hp = CombatHP.M_XL;
        if(pokemon1.getHp().equals(HP.HIGH) && pokemon2.getHp().equals(HP.EXTREMEHIGH)) hp = CombatHP.H_XH;
        if(pokemon1.getHp().equals(HP.HIGH) && pokemon2.getHp().equals(HP.MEDIUM)) hp = CombatHP.H_M;
        if(pokemon1.getHp().equals(HP.HIGH) && pokemon2.getHp().equals(HP.LOW)) hp = CombatHP.H_L;
        if(pokemon1.getHp().equals(HP.HIGH) && pokemon2.getHp().equals(HP.EXTREMELOW)) hp = CombatHP.H_XL;
        if(pokemon1.getHp().equals(HP.EXTREMEHIGH) && pokemon2.getHp().equals(HP.HIGH)) hp = CombatHP.XH_H;
        if(pokemon1.getHp().equals(HP.EXTREMEHIGH) && pokemon2.getHp().equals(HP.MEDIUM)) hp = CombatHP.XH_M;
        if(pokemon1.getHp().equals(HP.EXTREMEHIGH) && pokemon2.getHp().equals(HP.LOW)) hp = CombatHP.XH_L;
        if(pokemon1.getHp().equals(HP.EXTREMEHIGH) && pokemon2.getHp().equals(HP.EXTREMELOW)) hp = CombatHP.XH_XL;
    }

    private void setSpeed(){
        if(pokemon1.getSpeed().equals(Speed.EXTREMELOW) && pokemon2.getSpeed().equals(Speed.EXTREMELOW)) speed = CombatSpeed.XL_XL;
        if(pokemon1.getSpeed().equals(Speed.LOW) && pokemon2.getSpeed().equals(Speed.LOW)) speed = CombatSpeed.L_L;
        if(pokemon1.getSpeed().equals(Speed.MEDIUM) && pokemon2.getSpeed().equals(Speed.MEDIUM)) speed = CombatSpeed.M_M;
        if(pokemon1.getSpeed().equals(Speed.HIGH) && pokemon2.getSpeed().equals(Speed.HIGH)) speed = CombatSpeed.H_H;
        if(pokemon1.getSpeed().equals(Speed.EXTREMEHIGH) && pokemon2.getSpeed().equals(Speed.EXTREMEHIGH)) speed = CombatSpeed.XH_XH;
        //if(pokemon1.getSpeed().equals(pokemon2.getSpeed())) speed = CombatSpeed.EVEN;

        if(pokemon1.getSpeed().equals(Speed.EXTREMELOW) && pokemon2.getSpeed().equals(Speed.EXTREMEHIGH)) speed = CombatSpeed.XL_XH;
        if(pokemon1.getSpeed().equals(Speed.EXTREMELOW) && pokemon2.getSpeed().equals(Speed.HIGH)) speed = CombatSpeed.XL_H;
        if(pokemon1.getSpeed().equals(Speed.EXTREMELOW) && pokemon2.getSpeed().equals(Speed.MEDIUM)) speed = CombatSpeed.XL_M;
        if(pokemon1.getSpeed().equals(Speed.EXTREMELOW) && pokemon2.getSpeed().equals(Speed.LOW)) speed = CombatSpeed.XL_L;
        if(pokemon1.getSpeed().equals(Speed.LOW) && pokemon2.getSpeed().equals(Speed.EXTREMEHIGH)) speed = CombatSpeed.L_XH;
        if(pokemon1.getSpeed().equals(Speed.LOW) && pokemon2.getSpeed().equals(Speed.HIGH)) speed = CombatSpeed.L_H;
        if(pokemon1.getSpeed().equals(Speed.LOW) && pokemon2.getSpeed().equals(Speed.MEDIUM)) speed = CombatSpeed.L_M;
        if(pokemon1.getSpeed().equals(Speed.LOW) && pokemon2.getSpeed().equals(Speed.EXTREMELOW)) speed = CombatSpeed.L_XL;
        if(pokemon1.getSpeed().equals(Speed.MEDIUM) && pokemon2.getSpeed().equals(Speed.EXTREMEHIGH)) speed = CombatSpeed.M_XH;
        if(pokemon1.getSpeed().equals(Speed.MEDIUM) && pokemon2.getSpeed().equals(Speed.HIGH)) speed = CombatSpeed.M_H;
        if(pokemon1.getSpeed().equals(Speed.MEDIUM) && pokemon2.getSpeed().equals(Speed.LOW)) speed = CombatSpeed.M_L;
        if(pokemon1.getSpeed().equals(Speed.MEDIUM) && pokemon2.getSpeed().equals(Speed.EXTREMELOW)) speed = CombatSpeed.M_XL;
        if(pokemon1.getSpeed().equals(Speed.HIGH) && pokemon2.getSpeed().equals(Speed.EXTREMEHIGH)) speed = CombatSpeed.H_XH;
        if(pokemon1.getSpeed().equals(Speed.HIGH) && pokemon2.getSpeed().equals(Speed.MEDIUM)) speed = CombatSpeed.H_M;
        if(pokemon1.getSpeed().equals(Speed.HIGH) && pokemon2.getSpeed().equals(Speed.LOW)) speed = CombatSpeed.H_L;
        if(pokemon1.getSpeed().equals(Speed.HIGH) && pokemon2.getSpeed().equals(Speed.EXTREMELOW)) speed = CombatSpeed.H_XL;
        if(pokemon1.getSpeed().equals(Speed.EXTREMEHIGH) && pokemon2.getSpeed().equals(Speed.HIGH)) speed = CombatSpeed.XH_H;
        if(pokemon1.getSpeed().equals(Speed.EXTREMEHIGH) && pokemon2.getSpeed().equals(Speed.MEDIUM)) speed = CombatSpeed.XH_M;
        if(pokemon1.getSpeed().equals(Speed.EXTREMEHIGH) && pokemon2.getSpeed().equals(Speed.LOW)) speed = CombatSpeed.XH_L;
        if(pokemon1.getSpeed().equals(Speed.EXTREMEHIGH) && pokemon2.getSpeed().equals(Speed.EXTREMELOW)) speed = CombatSpeed.XH_XL;
    }

    private void setSpecAttack(){
        if(pokemon1.getSpcAttack().equals(SpecialAttack.EXTREMELOW) && pokemon2.getSpcAttack().equals(SpecialAttack.EXTREMELOW)) specialAttack = CombatSpecAttack.XL_XL;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.LOW) && pokemon2.getSpcAttack().equals(SpecialAttack.LOW)) specialAttack = CombatSpecAttack.L_L;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.MEDIUM) && pokemon2.getSpcAttack().equals(SpecialAttack.MEDIUM)) specialAttack = CombatSpecAttack.M_M;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.HIGH) && pokemon2.getSpcAttack().equals(SpecialAttack.HIGH)) specialAttack = CombatSpecAttack.H_H;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.EXTREMEHIGH) && pokemon2.getSpcAttack().equals(SpecialAttack.EXTREMEHIGH)) specialAttack = CombatSpecAttack.XH_XH;
        //if(pokemon1.getSpcAttack().equals(pokemon2.getSpcAttack())) specialAttack = CombatSpecAttack.EVEN;

        if(pokemon1.getSpcAttack().equals(SpecialAttack.EXTREMELOW) && pokemon2.getSpcAttack().equals(SpecialAttack.EXTREMEHIGH)) specialAttack = CombatSpecAttack.XL_XH;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.EXTREMELOW) && pokemon2.getSpcAttack().equals(SpecialAttack.HIGH)) specialAttack = CombatSpecAttack.XL_H;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.EXTREMELOW) && pokemon2.getSpcAttack().equals(SpecialAttack.MEDIUM)) specialAttack = CombatSpecAttack.XL_M;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.EXTREMELOW) && pokemon2.getSpcAttack().equals(SpecialAttack.LOW)) specialAttack = CombatSpecAttack.XL_L;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.LOW) && pokemon2.getSpcAttack().equals(SpecialAttack.EXTREMEHIGH)) specialAttack = CombatSpecAttack.L_XH;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.LOW) && pokemon2.getSpcAttack().equals(SpecialAttack.HIGH)) specialAttack = CombatSpecAttack.L_H;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.LOW) && pokemon2.getSpcAttack().equals(SpecialAttack.MEDIUM)) specialAttack = CombatSpecAttack.L_M;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.LOW) && pokemon2.getSpcAttack().equals(SpecialAttack.EXTREMELOW)) specialAttack = CombatSpecAttack.L_XL;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.MEDIUM) && pokemon2.getSpcAttack().equals(SpecialAttack.EXTREMEHIGH)) specialAttack = CombatSpecAttack.M_XH;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.MEDIUM) && pokemon2.getSpcAttack().equals(SpecialAttack.HIGH)) specialAttack = CombatSpecAttack.M_H;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.MEDIUM) && pokemon2.getSpcAttack().equals(SpecialAttack.LOW)) specialAttack = CombatSpecAttack.M_L;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.MEDIUM) && pokemon2.getSpcAttack().equals(SpecialAttack.EXTREMELOW)) specialAttack = CombatSpecAttack.M_XL;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.HIGH) && pokemon2.getSpcAttack().equals(SpecialAttack.EXTREMEHIGH)) specialAttack = CombatSpecAttack.H_XH;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.HIGH) && pokemon2.getSpcAttack().equals(SpecialAttack.MEDIUM)) specialAttack = CombatSpecAttack.H_M;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.HIGH) && pokemon2.getSpcAttack().equals(SpecialAttack.LOW)) specialAttack = CombatSpecAttack.H_L;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.HIGH) && pokemon2.getSpcAttack().equals(SpecialAttack.EXTREMELOW)) specialAttack = CombatSpecAttack.H_XL;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.EXTREMEHIGH) && pokemon2.getSpcAttack().equals(SpecialAttack.HIGH)) specialAttack = CombatSpecAttack.XH_H;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.EXTREMEHIGH) && pokemon2.getSpcAttack().equals(SpecialAttack.MEDIUM)) specialAttack = CombatSpecAttack.XH_M;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.EXTREMEHIGH) && pokemon2.getSpcAttack().equals(SpecialAttack.LOW)) specialAttack = CombatSpecAttack.XH_L;
        if(pokemon1.getSpcAttack().equals(SpecialAttack.EXTREMEHIGH) && pokemon2.getSpcAttack().equals(SpecialAttack.EXTREMELOW)) specialAttack = CombatSpecAttack.XH_XL;
    }
    private void setSpecDefence(){
        if(pokemon1.getSpcDefence().equals(SpecialDefence.EXTREMELOW) && pokemon2.getSpcDefence().equals(SpecialDefence.EXTREMELOW)) specialDefence = CombatSpecDefence.XL_XL;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.LOW) && pokemon2.getSpcDefence().equals(SpecialDefence.LOW)) specialDefence = CombatSpecDefence.L_L;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.MEDIUM) && pokemon2.getSpcDefence().equals(SpecialDefence.MEDIUM)) specialDefence = CombatSpecDefence.M_M;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.HIGH) && pokemon2.getSpcDefence().equals(SpecialDefence.HIGH)) specialDefence = CombatSpecDefence.H_H;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.EXTREMEHIGH) && pokemon2.getSpcDefence().equals(SpecialDefence.EXTREMEHIGH)) specialDefence = CombatSpecDefence.XH_XH;
        //if(pokemon1.getSpcDefence().equals(pokemon2.getSpcDefence())) specialDefence = CombatSpecDefence.EVEN;

        if(pokemon1.getSpcDefence().equals(SpecialDefence.EXTREMELOW) && pokemon2.getSpcDefence().equals(SpecialDefence.EXTREMEHIGH)) specialDefence = CombatSpecDefence.XL_XH;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.EXTREMELOW) && pokemon2.getSpcDefence().equals(SpecialDefence.HIGH)) specialDefence = CombatSpecDefence.XL_H;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.EXTREMELOW) && pokemon2.getSpcDefence().equals(SpecialDefence.MEDIUM)) specialDefence = CombatSpecDefence.XL_M;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.EXTREMELOW) && pokemon2.getSpcDefence().equals(SpecialDefence.LOW)) specialDefence = CombatSpecDefence.XL_L;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.LOW) && pokemon2.getSpcDefence().equals(SpecialDefence.EXTREMEHIGH)) specialDefence = CombatSpecDefence.L_XH;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.LOW) && pokemon2.getSpcDefence().equals(SpecialDefence.HIGH)) specialDefence = CombatSpecDefence.L_H;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.LOW) && pokemon2.getSpcDefence().equals(SpecialDefence.MEDIUM)) specialDefence = CombatSpecDefence.L_M;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.LOW) && pokemon2.getSpcDefence().equals(SpecialDefence.EXTREMELOW)) specialDefence = CombatSpecDefence.L_XL;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.MEDIUM) && pokemon2.getSpcDefence().equals(SpecialDefence.EXTREMEHIGH)) specialDefence = CombatSpecDefence.M_XH;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.MEDIUM) && pokemon2.getSpcDefence().equals(SpecialDefence.HIGH)) specialDefence = CombatSpecDefence.M_H;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.MEDIUM) && pokemon2.getSpcDefence().equals(SpecialDefence.LOW)) specialDefence = CombatSpecDefence.M_L;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.MEDIUM) && pokemon2.getSpcDefence().equals(SpecialDefence.EXTREMELOW)) specialDefence = CombatSpecDefence.M_XL;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.HIGH) && pokemon2.getSpcDefence().equals(SpecialDefence.EXTREMEHIGH)) specialDefence = CombatSpecDefence.H_XH;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.HIGH) && pokemon2.getSpcDefence().equals(SpecialDefence.MEDIUM)) specialDefence = CombatSpecDefence.H_M;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.HIGH) && pokemon2.getSpcDefence().equals(SpecialDefence.LOW)) specialDefence = CombatSpecDefence.H_L;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.HIGH) && pokemon2.getSpcDefence().equals(SpecialDefence.EXTREMELOW)) specialDefence = CombatSpecDefence.H_XL;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.EXTREMEHIGH) && pokemon2.getSpcDefence().equals(SpecialDefence.HIGH)) specialDefence = CombatSpecDefence.XH_H;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.EXTREMEHIGH) && pokemon2.getSpcDefence().equals(SpecialDefence.MEDIUM)) specialDefence = CombatSpecDefence.XH_M;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.EXTREMEHIGH) && pokemon2.getSpcDefence().equals(SpecialDefence.LOW)) specialDefence = CombatSpecDefence.XH_L;
        if(pokemon1.getSpcDefence().equals(SpecialDefence.EXTREMEHIGH) && pokemon2.getSpcDefence().equals(SpecialDefence.EXTREMELOW)) specialDefence = CombatSpecDefence.XH_XL;
    }

    private void setLegendary() {
        if(pokemon1.getLegendary().equals(Legendary.LEGENDARY) && pokemon2.getLegendary().equals(Legendary.LEGENDARY)) legendary = CombatLegendary.LEG_LEG;
        if(pokemon1.getLegendary().equals(Legendary.LEGENDARY) && pokemon2.getLegendary().equals(Legendary.ORDINARY)) legendary = CombatLegendary.LEG_ORD;
        if(pokemon1.getLegendary().equals(Legendary.ORDINARY) && pokemon2.getLegendary().equals(Legendary.LEGENDARY)) legendary = CombatLegendary.ORD_LEG;
        if(pokemon1.getLegendary().equals(Legendary.ORDINARY) && pokemon2.getLegendary().equals(Legendary.ORDINARY)) legendary = CombatLegendary.ORD_ORD;
    }

    private void setStage() {
        if(pokemon1.getStage().equals(Stage.ONLY)) {
            if(pokemon2.getStage().equals(Stage.ONLY)) stage = CombatStage.ONLY_ONLY;
            if(pokemon2.getStage().equals(Stage.FIRST)) stage = CombatStage.ONLY_FIR;
            if(pokemon2.getStage().equals(Stage.SECOND)) stage = CombatStage.ONLY_SEC;
            if(pokemon2.getStage().equals(Stage.THIRD)) stage = CombatStage.ONLY_THI;
            return;
        }

        if(pokemon1.getStage().equals(Stage.FIRST)) {
            if(pokemon2.getStage().equals(Stage.ONLY)) stage = CombatStage.FIR_ONLY;
            if(pokemon2.getStage().equals(Stage.FIRST)) stage = CombatStage.FIR_FIR;
            if(pokemon2.getStage().equals(Stage.SECOND)) stage = CombatStage.FIR_SEC;
            if(pokemon2.getStage().equals(Stage.THIRD)) stage = CombatStage.FIR_THI;
            return;
        }

        if(pokemon1.getStage().equals(Stage.SECOND)) {
            if(pokemon2.getStage().equals(Stage.ONLY)) stage = CombatStage.SEC_ONLY;
            if(pokemon2.getStage().equals(Stage.FIRST)) stage = CombatStage.SEC_FIR;
            if(pokemon2.getStage().equals(Stage.SECOND)) stage = CombatStage.SEC_SEC;
            if(pokemon2.getStage().equals(Stage.THIRD)) stage = CombatStage.SEC_THI;
            return;
        }

        if(pokemon1.getStage().equals(Stage.THIRD)) {
            if(pokemon2.getStage().equals(Stage.ONLY)) stage = CombatStage.THI_ONLY;
            if(pokemon2.getStage().equals(Stage.FIRST)) stage = CombatStage.THI_FIR;
            if(pokemon2.getStage().equals(Stage.SECOND)) stage = CombatStage.THI_SEC;
            if(pokemon2.getStage().equals(Stage.THIRD)) stage = CombatStage.THI_THI;
        }
    }

    private void setType() {
        Type1 poke1Type = pokemon1.getType1();
        Map<Type1, Double> pokeOne = pokemon1.getTypeMatching();

        Type1 poke2Type = pokemon2.getType1();
        Map<Type1, Double> pokeTwo = pokemon2.getTypeMatching();

        double poke1Match = pokeOne.get(poke2Type);
        double poke2Match = pokeTwo.get(poke1Type);

        //if(poke1Match == poke2Match) type = CombatType.EVEN;

        if(poke1Match == 0) {
            if(poke2Match == 0) type = CombatType.ZZ;
            if(poke2Match == 0.25) type = CombatType.ZQ;
            if(poke2Match == 0.5) type = CombatType.ZH;
            if(poke2Match == 1) type = CombatType.ZO;
            if(poke2Match == 2) type = CombatType.ZT;
            if(poke2Match == 4) type = CombatType.ZF;
            return;
        }

        if(poke1Match == 0.25) {
            if(poke2Match == 0) type = CombatType.QZ;
            if(poke2Match == 0.25) type = CombatType.QQ;
            if(poke2Match == 0.5) type = CombatType.QH;
            if(poke2Match == 1) type = CombatType.QO;
            if(poke2Match == 2) type = CombatType.QT;
            if(poke2Match == 4) type = CombatType.QF;
            return;
        }

        if(poke1Match == 0.5) {
            if(poke2Match == 0) type = CombatType.HZ;
            if(poke2Match == 0.25) type = CombatType.HQ;
            if(poke2Match == 0.5) type = CombatType.HH;
            if(poke2Match == 1) type = CombatType.HO;
            if(poke2Match == 2) type = CombatType.HT;
            if(poke2Match == 4) type = CombatType.HF;
            return;
        }

        if(poke1Match == 1) {
            if(poke2Match == 0) type = CombatType.OZ;
            if(poke2Match == 0.25) type = CombatType.OQ;
            if(poke2Match == 0.5) type = CombatType.OH;
            if(poke2Match == 1) type = CombatType.OO;
            if(poke2Match == 2) type = CombatType.OT;
            if(poke2Match == 4) type = CombatType.OF;
            return;
        }

        if(poke1Match == 2) {
            if(poke2Match == 0) type = CombatType.TZ;
            if(poke2Match == 0.25) type = CombatType.TQ;
            if(poke2Match == 0.5) type = CombatType.TH;
            if(poke2Match == 1) type = CombatType.TO;
            if(poke2Match == 2) type = CombatType.TT;
            if(poke2Match == 4) type = CombatType.TF;
            return;
        }

        if(poke1Match == 4) {
            if(poke2Match == 0) type = CombatType.FZ;
            if(poke2Match == 0.25) type = CombatType.FQ;
            if(poke2Match == 0.5) type = CombatType.FH;
            if(poke2Match == 1) type = CombatType.FO;
            if(poke2Match == 2) type = CombatType.FT;
            if(poke2Match == 4) type = CombatType.FF;
        }
    }

    private void setCluster(){
        if(pokemon1.getCluster() == 1 && pokemon2.getCluster() == 1) cluster = ClusterType.O_O;
        if(pokemon1.getCluster() == 1 && pokemon2.getCluster() == 2) cluster = ClusterType.O_TW;
        if(pokemon1.getCluster() == 1 && pokemon2.getCluster() == 3) cluster = ClusterType.O_TH;
        if(pokemon1.getCluster() == 1 && pokemon2.getCluster() == 4) cluster = ClusterType.O_FO;
        if(pokemon1.getCluster() == 1 && pokemon2.getCluster() == 5) cluster = ClusterType.O_FI;
        if(pokemon1.getCluster() == 2 && pokemon2.getCluster() == 1) cluster = ClusterType.TW_O;
        if(pokemon1.getCluster() == 2 && pokemon2.getCluster() == 2) cluster = ClusterType.TW_TW;
        if(pokemon1.getCluster() == 2 && pokemon2.getCluster() == 3) cluster = ClusterType.TW_TH;
        if(pokemon1.getCluster() == 2 && pokemon2.getCluster() == 4) cluster = ClusterType.TW_FO;
        if(pokemon1.getCluster() == 2 && pokemon2.getCluster() == 5) cluster = ClusterType.TW_FI;
        if(pokemon1.getCluster() == 3 && pokemon2.getCluster() == 1) cluster = ClusterType.TH_O;
        if(pokemon1.getCluster() == 3 && pokemon2.getCluster() == 2) cluster = ClusterType.TH_TW;
        if(pokemon1.getCluster() == 3 && pokemon2.getCluster() == 3) cluster = ClusterType.TH_TH;
        if(pokemon1.getCluster() == 3 && pokemon2.getCluster() == 4) cluster = ClusterType.TH_FO;
        if(pokemon1.getCluster() == 3 && pokemon2.getCluster() == 5) cluster = ClusterType.TH_FI;
        if(pokemon1.getCluster() == 4 && pokemon2.getCluster() == 1) cluster = ClusterType.FO_O;
        if(pokemon1.getCluster() == 4 && pokemon2.getCluster() == 2) cluster = ClusterType.FO_TW;
        if(pokemon1.getCluster() == 4 && pokemon2.getCluster() == 3) cluster = ClusterType.FO_TH;
        if(pokemon1.getCluster() == 4 && pokemon2.getCluster() == 4) cluster = ClusterType.FO_FO;
        if(pokemon1.getCluster() == 4 && pokemon2.getCluster() == 5) cluster = ClusterType.FO_FI;
        if(pokemon1.getCluster() == 5 && pokemon2.getCluster() == 1) cluster = ClusterType.FI_O;
        if(pokemon1.getCluster() == 5 && pokemon2.getCluster() == 2) cluster = ClusterType.FI_TW;
        if(pokemon1.getCluster() == 5 && pokemon2.getCluster() == 3) cluster = ClusterType.FI_TH;
        if(pokemon1.getCluster() == 5 && pokemon2.getCluster() == 4) cluster = ClusterType.FI_FO;
        if(pokemon1.getCluster() == 5 && pokemon2.getCluster() == 5) cluster = ClusterType.FI_FI;
        else cluster = ClusterType.EVEN;
    }
    public CombatAttack getAttack() {
        return attack;
    }

    public CombatDefence getDefence() {
        return defence;
    }

    public CombatSpeed getSpeed() {
        return speed;
    }

    public CombatSpecAttack getSpecialAttack() {
        return specialAttack;
    }

    public CombatSpecDefence getSpecialDefence() {
        return specialDefence;
    }

    public CombatHP getHp() {
        return hp;
    }

    public CombatLegendary getLegendary() {
        return legendary;
    }

    public CombatStage getStage() {
        return stage;
    }

    public CombatType getType() {
        return type;
    }

    /**
     * Creates the attribute list to be used in generating the decision tree
     * @return a list of attribute classes
     */
    public static List<Object> getAttributeList() {
        List<Object> attributeList = new ArrayList<>();

        attributeList.add(CombatAttack.class);
        attributeList.add(CombatDefence.class);
        attributeList.add(CombatHP.class);
        attributeList.add(CombatSpeed.class);
        attributeList.add(CombatSpecAttack.class);
        attributeList.add(CombatSpecDefence.class);
        attributeList.add(CombatLegendary.class);
        attributeList.add(CombatStage.class);
        attributeList.add(CombatType.class);
        attributeList.add(ClusterType.class);

        return attributeList;
    }
}