package data;

import enums.PokemonEnums.*;

import java.util.Map;

public class PokemonD {
    String name;
    int ID;
    Type1 type1;
    Type2 type2;
    HP hp;
    Attack attack;
    Defence defence;
    SpecialAttack spcAttack;
    SpecialDefence spcDefence;
    Speed speed;
    Generation generation;
    Stage stage;
    Legendary legendary;
    int evolutionChain;
    int cluster;
    Map<Type1, Double> typeMatching;


    public PokemonD(String name, int number, Type1 type1, Type2 type2, Generation generation, Stage stage, Legendary legendary, int evolutionChain, HP hp, Attack attack, Defence def, SpecialAttack specAtta, SpecialDefence specDefe, Speed speedL, Map<Type1, Double> typeMap) {
    this.name = name;
    this.ID = number;
    this.type1 = type1;
    this.type2 = type2;
    this.hp = hp;
    this.attack = attack;
    this.defence = def;
    this.spcAttack = specAtta;
    this.spcDefence = specDefe;
    this.speed = speedL;
    this.generation = generation;
    this.stage = stage;
    this.legendary = legendary;
    this.evolutionChain = evolutionChain;
    typeMatching = typeMap;

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return ID;
    }

    public Type1 getType1() {
        return type1;
    }

    public Type2 getType2() {
        return type2;
    }

    public HP getHp() {
        return hp;
    }

    public Attack getAttack() {
        return attack;
    }

    public Defence getDefence() {
        return defence;
    }

    public SpecialAttack getSpcAttack() {
        return spcAttack;
    }

    public SpecialDefence getSpcDefence() {
        return spcDefence;
    }

    public Speed getSpeed() {
        return speed;
    }

    public Generation getGeneration() {
        return generation;
    }

    public Stage getStage() {
        return stage;
    }

    public Legendary getLegendary() {
        return legendary;
    }

    public int getEvolutionChain() {
        return evolutionChain;
    }

    public Map<Type1, Double> getTypeMatching() {
        return typeMatching;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public int getCluster() {
        return cluster;
    }
}
