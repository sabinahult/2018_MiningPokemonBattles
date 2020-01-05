import CombatClassification.ID3;
import CombatClassification.Node;
import CombatClassification.TestID3;
import PokemonCluster.KMedoid;
import PokemonCluster.KMedoidCluster;
import data.*;
import management.DataManager;
import management.Discretizer;
import management.Normalizer;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        try {
            System.out.println("**************************** Reading in data ************************************");
            // creating initial pokemons
            double start = System.currentTimeMillis();
            List<Pokemon> pokemons = DataManager.readPokemons("data/pokemonLim.csv");
            System.out.println("PokemonLim read ind. " + pokemons.size() + " Pokemons created.");
            System.out.println("Time: " + (System.currentTimeMillis() - start) / 1000);

            // adding data from additional data set
            start = System.currentTimeMillis();
            pokemons = DataManager.addData("data/pokemon.csv", pokemons);
            System.out.println("Type Matching Map added.");
            System.out.println("Time: " + (System.currentTimeMillis() - start) / 1000);

            // adding evolution from additional data set
            start = System.currentTimeMillis();
            pokemons = DataManager.addEvolution("data/pokedex.csv", pokemons);
            System.out.println("Evolutions added.");
            System.out.println("Time: " + (System.currentTimeMillis() - start) / 1000);


            // creating initial combats
            start = System.currentTimeMillis();
            List<Combat> combats = DataManager.readCombats("data/combats.csv", pokemons);
            System.out.println("Combats read in. " + combats.size() + " Combats created.");
            System.out.println("Time: " + (System.currentTimeMillis() - start) / 1000);

            System.out.println("********************************* k-Medoids *************************************");
            List<PokemonNorm> normalizedData = Normalizer.normalize(pokemons);
            List<KMedoidCluster> clusters = KMedoid.cluster(normalizedData, 5);
            int counting = 1;
            for(KMedoidCluster k : clusters) {
                for(PokemonNorm pok : k.getMembers()) {
                    pok.setCluster(counting);
                }
                counting++;
            }


            System.out.println("************************************* ID3 **************************************");

            List<PokemonD> discretePokemons = Discretizer.dicretizePokemons(pokemons);
            for(PokemonD p : discretePokemons) {
                for(PokemonNorm q : normalizedData) {
                    if(p.getId() == q.getId()) p.setCluster(q.getCluster());
                }
            }
            List<CombatD> discreteCombats = Discretizer.discretizeCombats(combats, discretePokemons);
            List<CombatD> training = new ArrayList<>();

            Random randomGenerator = new Random();
            for(int i = 0; i < 5000; i++) {
                int index = randomGenerator.nextInt(50000);
                training.add(discreteCombats.get(index));
            }

            List<CombatD> remains = new ArrayList<>();
            remains.addAll(discreteCombats);
            remains.removeAll(training);

            Node decisionTree = new ID3(training).getDecisionTree();
            //ID3.printTree(decisionTree, null, -1);

            String nodesPerLevel = ID3.nodesByLevel(decisionTree);
            //System.out.println(nodesPerLevel);

            String result = TestID3.testDecisionRules(decisionTree, remains);
            System.out.println(result);

            // experiment
            System.out.println("\n ***** Experiment *****");

            int k = 100;
            double[] corrects = new double[k];
            for(int x = 0; x < k; x++) {
                List<CombatD> c = new ArrayList<>();

                Random rg = new Random();
                for(int i = 0; i < 2500; i++) {
                    int index = rg.nextInt(50000);
                    training.add(discreteCombats.get(index));
                }

                List<CombatD> r = new ArrayList<>();
                r.addAll(c);
                r.removeAll(training);

                Node t = new ID3(r).getDecisionTree();
                corrects[x] = TestID3.getPercentCorrect();
            }

            double sum = Arrays.stream(corrects).sum();
            double avg = sum / corrects.length;
            System.out.println("Average: " + avg);

            Map<Integer, List<CombatD>> fc = TestID3.getFalselyClassifiedObjects();
            int numOfWrong = fc.get(1).size() + fc.get(2).size();
            System.out.println("Number of wrongly classified when testing on all combat: " + numOfWrong);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
