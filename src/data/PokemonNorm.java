package data;

import enums.PokemonEnums.Type1;
import enums.PokemonEnums.Type2;

public class PokemonNorm {
    private int id;
    private String name;
    private Type1 type1;
    private Type2 type2;
    private boolean legendary;
    private int generation;
    private int stage;
    private double attack;
    private double defence;
    private double speed;
    private double specialAttack;
    private double specialDefence;
    private double hp;
    private int cluster;

    public PokemonNorm(int id, String name, Type1 type1, Type2 type2, boolean legendary, int generation, int stage) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.legendary = legendary;
        this.stage = stage;
        this.generation = generation;

    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setSpecialAttack(double specialAttack) {
        this.specialAttack = specialAttack;
    }

    public void setSpecialDefence(double specialDefence) {
        this.specialDefence = specialDefence;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Type1 getType1() {
        return type1;
    }

    public Type2 getType2() {
        return type2;
    }

    public boolean isLegendary() {
        return legendary;
    }

    public int getGeneration() {
        return generation;
    }

    public int getStage() {
        return stage;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefence() {
        return defence;
    }

    public double getSpeed() {
        return speed;
    }

    public double getSpecialAttack() {
        return specialAttack;
    }

    public double getSpecialDefence() {
        return specialDefence;
    }

    public double getHp() {
        return hp;
    }

    public boolean getLegendary() {
        return legendary;
    }

    public void setCluster(int counting) {
    }

    public int getCluster() {
        return cluster;
    }
}
