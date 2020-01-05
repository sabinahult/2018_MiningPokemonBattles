package data;

import enums.PokemonEnums.*;
import java.util.HashMap;
import java.util.Map;

public class Pokemon {
    private String name;
    private int id;
    private Type1 type1;
    private Type2 type2;
    private int HP;
    private int attack;
    private int defence;
    private int spcAttack;
    private int spcDefence;
    private int speed;

    private int generation;
    private boolean legendary;

    private int parentID = 0; // which pokemon did this one evolve from
    private int evolutionChain; // id of the evolution chain
    private int stage; // evolution stage (0, 1, 2, 3)

    private Map<Type1, Double> typeMap = new HashMap<>(); // a map of types to the pokemons efficiency against them
    private Map<String, Double> normValues;

    private double winRatio = -1;

    public void setType1(Type1 type1) {
        this.type1 = type1;
    }

    public void setType2(Type2 type2) {
        this.type2 = type2;
    }

    public void setName(String name) { this.name = name; }
    public String getName() {
        return name;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public Type1 getType1() { return type1; }
    public Type2 getType2() { return type2; }

    public void setHP(int HP) { this.HP = HP; }
    public int getHP() { return HP; }

    public void setAttack(int attack) { this.attack = attack; }
    public int getAttack() {
        return attack;
    }

    public void setDefence(int defence) { this.defence = defence; }
    public int getDefence() {
        return defence;
    }

    public void setSpcAttack(int spcAttack) { this.spcAttack = spcAttack; }
    public int getSpcAttack() {
        return spcAttack;
    }

    public void setSpcDefence(int spcDefence) { this.spcDefence = spcDefence; }
    public int getSpcDefence() {
        return spcDefence;
    }

    public void setSpeed(int speed) { this.speed = speed; }
    public int getSpeed() {
        return speed;
    }

    public void setGeneration(int generation) { this.generation = generation; }
    public int getGeneration() {
        return generation;
    }

    public void setLegendary(boolean legendary) { this.legendary = legendary; }
    public boolean getLegendary() { return legendary; }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }
    public int getParentID() {
        return parentID;
    }

    public void setEvolutionChain(int evolutionChain) {
        this.evolutionChain = evolutionChain;
    }
    public int getEvolutionChain() { return evolutionChain; }

    public void setStage(int stage) {
        this.stage = stage;
    }
    public int getStage() {
        return stage;
    }

    public void addTypeMap(Map<Type1, Double> typeMatchingMap) { typeMap = typeMatchingMap; }
    public Map<Type1,Double> getTypeMap() { return typeMap; }

    public void addNormValues(Map<String, Double> normValues) {
        this.normValues = normValues;
    }
    public Map<String,Double> getNormValues() {
        return normValues;
    }

    public void setWinRatio(double winRatio) { this.winRatio = winRatio; }
    public double getWinRatio() { return winRatio; }

}
