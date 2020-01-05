package PokemonCluster;

import data.PokemonNorm;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents each cluster with a medoid center and a list of cluster members.
 */
public class KMedoidCluster {
    private PokemonNorm medoid;
    private List<PokemonNorm> members;
    private double error;

    public KMedoidCluster(PokemonNorm medoid) {
        this.medoid = medoid;
        members = new ArrayList<>();
        error = 0.0;
    }

    public PokemonNorm getMedoid() {
        return medoid;
    }

    public List<PokemonNorm> getMembers() {
        return members;
    }

    public void setMedoid(PokemonNorm medoid) {
        this.medoid = medoid;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getError() {
        return error;
    }

    public void addClusterMember(PokemonNorm newMember, double distance) {
        members.add(newMember);
        error += Math.pow(distance, 2);
    }

    public void clearCluster(){
        members.clear();
    }
}
