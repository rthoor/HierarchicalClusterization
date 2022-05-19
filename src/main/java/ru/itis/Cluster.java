package ru.itis;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private List<Human> humans;
    private Human centroid;

    public Cluster(Human centroid, Human human) {
        this.humans = new ArrayList<>();
        humans.add(centroid);
        humans.add(human);
        this.centroid = centroid;
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "centroid=" + centroid +
                ", humans=" + humans +
                '}';
    }

    public Cluster(List<Human> humans, Human centroid) {
        this.humans = humans;
        this.centroid = centroid;
    }

    public void merge(Cluster cluster){
        this.humans.addAll(cluster.getHumans());
    }

    public void addHuman(Human human){
        humans.add(human);
    }

    public List<Human> getHumans() {
        return humans;
    }

    public void setHumans(List<Human> humans) {
        this.humans = humans;
    }

    public Human getCentroid() {
        return centroid;
    }

    public void setCentroid(Human centroid) {
        this.centroid = centroid;
    }
}
