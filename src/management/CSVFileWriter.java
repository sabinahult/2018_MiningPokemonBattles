package management;

import data.*;
import enums.PokemonEnums.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This class is for ud to be able to write a given collection out to a CSV file for use
 * in other programmes to try and so some visualization, for instance of clusters.
 */
public class CSVFileWriter {
    public static void writePokemonsToFile(List<Pokemon> tuples, String filename) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(filename));
        StringBuilder sb = new StringBuilder();

        char comma = ',';

        sb.append("id").append(comma).append("Name").append(comma).append("Type 1").append(comma);
        sb.append("Type 2").append(comma).append("HP").append(comma).append("Attack").append(comma);
        sb.append("Defense").append(comma).append("Special Attack").append(comma).append("Special Defense");
        sb.append(comma).append("Speed").append(comma).append("Evolution Chain ID").append(comma);
        sb.append("Evolution Stage").append(comma).append("Generation").append(comma).append("Legendary");
        sb.append(comma).append("Win Ratio").append(comma);

        // trying to add headers for each entry in a pokemons typemap
        for(Type1 item : tuples.get(1).getTypeMap().keySet()) {
            String add = item.toString();
            String trimmed = add.replace("enums.", "");
            sb.append(trimmed).append(comma);
        }

        sb.append("\n");

        for(Pokemon tuple : tuples) {
            sb.append(tuple.getId()).append(comma).append(tuple.getName()).append(comma).append(tuple.getType1()).append(comma);
            sb.append(tuple.getType2()).append(comma).append(tuple.getHP()).append(comma);
            sb.append(tuple.getAttack()).append(comma).append(tuple.getDefence()).append(comma);
            sb.append(tuple.getSpcAttack()).append(comma).append(tuple.getSpcDefence()).append(comma);
            sb.append(tuple.getSpeed()).append(comma).append(tuple.getEvolutionChain()).append(comma);
            sb.append(tuple.getStage()).append(comma).append(tuple.getGeneration()).append(comma);
            sb.append(tuple.getLegendary()).append(comma).append(String.format("%.2f", tuple.getWinRatio())).append(comma);

            // trying to add the type map value to the csv file
            for(Type1 item : tuple.getTypeMap().keySet()) {
                sb.append(tuple.getTypeMap().get(item)).append(comma);
            }

            sb.append("\n");
        }

        pw.write(sb.toString());
        pw.close();
    }

    public void writePokemonDToFile(List<PokemonD> tuples, String filename) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(filename));
        StringBuilder sb = new StringBuilder();

        char comma = ',';

        sb.append("id").append(comma).append("Name").append(comma).append("Type 1").append(comma);
        sb.append("Type 2").append(comma).append("HP").append(comma).append("Attack").append(comma);
        sb.append("Defense").append(comma).append("Special Attack").append(comma).append("Special Defense");
        sb.append(comma).append("Speed").append(comma).append("Evolution Chain ID").append(comma);
        sb.append("Evolution Stage").append(comma).append("Generation").append(comma).append("Legendary");
        sb.append(comma).append("Win Ratio").append(comma);

        // trying to add headers for each entry in a pokemons typemap
        for(Type1 item : tuples.get(1).getTypeMatching().keySet()) {
            String add = item.toString();
            String trimmed = add.replace("enums.", "");
            sb.append(trimmed).append(comma);
        }

        sb.append("\n");

        for(PokemonD tuple : tuples) {
            sb.append(tuple.getId()).append(comma).append(tuple.getName()).append(comma).append(tuple.getType1()).append(comma);
            sb.append(tuple.getType2()).append(comma).append(tuple.getHp()).append(comma);
            sb.append(tuple.getAttack()).append(comma).append(tuple.getDefence()).append(comma);
            sb.append(tuple.getSpcAttack()).append(comma).append(tuple.getSpcDefence()).append(comma);
            sb.append(tuple.getSpeed()).append(comma).append(tuple.getEvolutionChain()).append(comma);
            sb.append(tuple.getStage()).append(comma).append(tuple.getGeneration()).append(comma);
            sb.append(tuple.getLegendary()).append(comma);

            // trying to add the type map value to the csv file
            for(Type1 item : tuple.getTypeMatching().keySet()) {
                sb.append(tuple.getTypeMatching().get(item)).append(comma);
            }

            sb.append("\n");
        }

        pw.write(sb.toString());
        pw.close();
    }

    public static void writeCombatsToFile(List<Combat> tuples, String filename) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(filename));
        StringBuilder sb = new StringBuilder();

        char comma = ',';

        sb.append("id").append(comma).append("Pokemon 1").append(comma).append("Pokemon 2").append(comma);
        sb.append("Pokemon 1 Type 1").append(comma).append("Pokemon 2 Type 1").append(comma);
        sb.append("Pokemon 1 Type 2").append(comma).append("Pokemon 2 Type 2").append(comma);
        sb.append("Pokemon 1 HP").append(comma).append("Pokemon 2 HP").append(comma);
        sb.append("Pokemon 1 Attack").append(comma).append("Pokemon 2 Attack").append(comma);
        sb.append("Pokemon 1 Defense").append(comma).append("Pokemon 2 Defense").append(comma);
        sb.append("Pokemon 1 Special Attack").append(comma).append("Pokemon 2 Special Attack").append(comma);
        sb.append("Pokemon 1 Special Defense").append(comma).append("Pokemon 2 Special Defense").append(comma);
        sb.append("Pokemon 1 Speed").append(comma).append("Pokemon 2 Speed").append(comma);
        sb.append("Pokemon 1 Generation").append(comma).append("Pokemon 2 Generation").append(comma);
        sb.append("Pokemon 1 Legendary").append(comma).append("Pokemon 2 Legendary").append(comma);
        sb.append("Pokemon 1 Stage").append(comma).append("Pokemon 2 Stage").append(comma);
        sb.append("Winner").append("\n");

        for(Combat c : tuples) {
            Pokemon fir = c.getPokemon1();
            Pokemon sec = c.getPokemon2();

            sb.append(c.getId()).append(comma).append(fir.getName()).append(comma).append(sec.getName()).append(comma);
            sb.append(fir.getType1()).append(comma).append(sec.getType1()).append(comma);
            sb.append(fir.getType2()).append(comma).append(sec.getType2()).append(comma);
            sb.append(fir.getHP()).append(comma).append(sec.getHP()).append(comma);
            sb.append(fir.getAttack()).append(comma).append(sec.getAttack()).append(comma);
            sb.append(fir.getDefence()).append(comma).append(sec.getDefence()).append(comma);
            sb.append(fir.getSpcAttack()).append(comma).append(sec.getSpcAttack()).append(comma);
            sb.append(fir.getSpcDefence()).append(comma).append(sec.getSpcDefence()).append(comma);
            sb.append(fir.getSpeed()).append(comma).append(sec.getSpeed()).append(comma);
            sb.append(fir.getGeneration()).append(comma).append(sec.getGeneration()).append(comma);
            sb.append(fir.getLegendary()).append(comma).append(sec.getLegendary()).append(comma);
            sb.append(fir.getStage()).append(comma).append(sec.getStage()).append(comma);
            sb.append(c.getWinner().getName()).append("\n");
        }

        pw.write(sb.toString());
        pw.close();
    }

    public static void writeCombatDToFile(List<CombatD> tuples, String filename) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(filename));
        StringBuilder sb = new StringBuilder();

        char comma = ',';

        sb.append("id").append(comma).append("Pokemon 1").append(comma).append("Pokemon 2").append(comma);
        sb.append("Attack").append(comma).append("Defense").append(comma);
        sb.append("HP").append(comma).append("Speed").append(comma);
        sb.append("Special Attack").append(comma).append("Special Defense").append(comma);
        sb.append("Legendary").append(comma).append("Stage").append(comma);
        sb.append("Type").append(comma).append("Winner").append(comma);
        sb.append("Class Label").append("\n");

        for(CombatD cd : tuples) {
            sb.append(cd.getId()).append(comma).append(cd.getPokemon1().getName()).append(comma);
            sb.append(cd.getPokemon2().getName()).append(comma);
            sb.append(cd.getAttack()).append(comma).append(cd.getDefence()).append(comma);
            sb.append(cd.getHp()).append(comma).append(cd.getSpeed()).append(comma);
            sb.append(cd.getSpecialAttack()).append(comma).append(cd.getSpecialDefence()).append(comma);
            sb.append(cd.getLegendary()).append(comma).append(cd.getStage()).append(comma);
            sb.append(cd.getType()).append(comma).append(cd.getWinner().getName()).append(comma);
            sb.append(cd.getClassLabel()).append("\n");
        }

        pw.write(sb.toString());
        pw.close();
    }
}
