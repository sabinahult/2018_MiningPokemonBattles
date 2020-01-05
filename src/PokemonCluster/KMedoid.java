package PokemonCluster;

import data.PokemonNorm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMedoid {
    private static List<PokemonNorm> data;

    public static List<KMedoidCluster> cluster(List<PokemonNorm> pok, int k) {
        data = pok;
        // find minimum and maximum values for attributes for use in distance calculation
        Distance.setExtremes(data);
        List<PokemonNorm> medoids = new ArrayList<>();
        List<KMedoidCluster> clusters = new ArrayList<>();

        // select k random pokemons as initial medoids
        Random randomGenerator = new Random();
        while(k > clusters.size()) {
            int randomIndex = randomGenerator.nextInt(data.size() - 1);
            PokemonNorm randomPok = data.get(randomIndex);
            if (!medoids.contains(randomPok)) {
                medoids.add(randomPok);
                clusters.add(new KMedoidCluster(randomPok));
            }
        }

        clusters = assignCluster(medoids, clusters);

        // swap the medoids with random pokemons and check whether this improves the clustering
        // until no change has occurred for 100 swaps

        int count = 0;
        while(count < 100) {
            for (KMedoidCluster cluster : clusters) {
                int randomIndex = randomGenerator.nextInt(data.size() - 1);
                PokemonNorm randomPok = data.get(randomIndex);
                if(!medoids.contains(randomPok)) {
                    double totalCost = calculateCost(clusters, cluster, medoids, randomPok);
                    if(totalCost < 0) {
                        medoids.remove(cluster.getMedoid());
                        medoids.add(randomPok);
                        cluster.setMedoid(randomPok);
                        assignCluster(medoids, clusters);
                        count = 0;
                    } else {
                        count++;
                    }
                }
            }
        }
        return clusters;
    }

    /**
     * Calculates the cost of swapping a medoid with a given pokemon based on the absolute error
     * (Han, Kamper, Pei: 2012: p. 455)
     * @param clusters list of current clusters
     * @param clusterToSwap the relevant cluster to calculate on
     * @param medoids the list of current medoids
     * @return - the absolute error
     */
    private static double calculateCost(List<KMedoidCluster> clusters, KMedoidCluster clusterToSwap, List<PokemonNorm> medoids, PokemonNorm randomPok) {
        //the sum of the error (which is the sum of the distances to the medoid squared) of the clusters
        double currentCost = 0;
        for (KMedoidCluster cluster : clusters) {
            currentCost += cluster.getError();
        }
        medoids.remove(clusterToSwap.getMedoid());
        medoids.add(randomPok);

        //measuring the error of all assignments to closest cluster
        double swapCost = 0;
        for (PokemonNorm p : data) {
            double shortestDistance = Double.MAX_VALUE;
            for (PokemonNorm medoid : medoids) {
                double distance = Distance.calculateDistance(p, medoid);
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                }
            }
            swapCost += Math.pow(shortestDistance, 2);
        }
        medoids.remove(randomPok);
        medoids.add(clusterToSwap.getMedoid());
        // if returned value is positive, the swap has smaller cost than the current
        return swapCost - currentCost;
    }

    /**
     * For each pokemon finds the closest medoid and assign the pokemon to that cluster
     * @param medoids list of current medoids
     * @param clusters list of current clusters
     * @return - a list of clusters, now with members
     */
    private static List<KMedoidCluster> assignCluster(List<PokemonNorm> medoids, List<KMedoidCluster> clusters) {
        // clearing all clusters before assigning
        for(KMedoidCluster cluster : clusters) {
            cluster.setError(0.0);
            cluster.clearCluster();
        }
        // assign all pokemons to the closest medoids cluster
        for (PokemonNorm p : data) {
            if (!medoids.contains(p)) {
                double shortestDistance = Double.MAX_VALUE;
                KMedoidCluster bestCluster = null;
                for (KMedoidCluster cluster : clusters) {
                    PokemonNorm medoid = cluster.getMedoid();
                    double distance = Distance.calculateDistance(p, medoid);
                    if (distance < shortestDistance) {
                        shortestDistance = distance;
                        bestCluster = cluster;
                    }
                }

                // this can throw a nullpointer because the assignment of the bestCluster is done in
                // and if statement, meaning that logically there might be a situation where this doesn't
                // happen and the bestCluster will be null at this point...
                bestCluster.addClusterMember(p, shortestDistance);
            }
        }
        return clusters;
    }
}