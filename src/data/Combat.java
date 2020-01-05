package data;

public class Combat {
    int id;
    Pokemon pokemon1;
    Pokemon pokemon2;
    Pokemon winner;

    public Combat(int id, Pokemon pokemon1, Pokemon pokemon2, Pokemon winner) {
        this.id = id;
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.winner = winner;
    }

    public int getId() { return id; }

    public Pokemon getPokemon1() {
        return pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }

    public Pokemon getWinner() {
        return winner;
    }


}
