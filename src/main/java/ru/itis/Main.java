package ru.itis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static List<Cluster> clusters = new ArrayList<>();
    private static List<Human> humanList = new ArrayList<>();

    public static void main(String[] args) {
        Main main = new Main();
        main.fillData();
        while (humanList.size() > 0 || clusters.size() != 2){
            main.pickCluster();
            if(clusters.size() == 1 && humanList.size() == 1){
                break;
            }
        }
    }


    private void pickCluster(){
        List<Human> centroids = new ArrayList<>(humanList);
        for(Cluster cluster : clusters){
            centroids.add(cluster.getCentroid());
        }

        int minDistance = Integer.MAX_VALUE;
        Human human1 = null;
        Human human2 = null;
        for(int i = 0; i < centroids.size()-1; i++){
            for(int j = i+1; j < centroids.size(); j++){
                int heightDif = Math.abs(centroids.get(i).getHeight()-centroids.get(j).getHeight());
                int weightDif = Math.abs(centroids.get(i).getWeight()-centroids.get(j).getWeight());

                int distance = (int) Math.sqrt(heightDif*heightDif + weightDif*weightDif);
                if (distance <= minDistance){
                    minDistance = distance;
                    human1 = centroids.get(i);
                    human2 = centroids.get(j);
                }
            }
        }
        Cluster cluster1 = findClusterByCentroid(human1);
        Cluster cluster2 = findClusterByCentroid(human2);
        humanList.remove(human1);
        humanList.remove(human2);
        if(cluster1 == null && cluster2 == null){
            clusters.add(new Cluster(human1, human2));
        } else if(cluster1 != null && cluster2 == null){
            clusters.get(clusters.indexOf(cluster1)).addHuman(human2);
        } else if(cluster1 == null && cluster2 != null){
            clusters.get(clusters.indexOf(cluster2)).addHuman(human1);
        } else{
            mergeClusters(cluster1, cluster2);
        }
        System.out.println("-----");
        System.out.println("clusters: " + clusters.size());
        clusters.forEach(System.out::println);
        System.out.println("humans left: " + humanList.size());
        humanList.forEach(System.out::println);
    }

    private void mergeClusters(Cluster cluster1, Cluster cluster2){
        clusters.remove(cluster1);
        clusters.remove(cluster2);
        cluster1.merge(cluster2);
        clusters.add(cluster1);
    }

    private Cluster findClusterByCentroid(Human human){
        for (Cluster cluster : clusters){
            if(cluster.getCentroid() == human){
                return cluster;
            }
        }
        return null;
    }

    private void fillData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/humans.txt"));
            String s;
            while((s = br.readLine())!=null){
                humanList.add(parseHuman(s));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Human parseHuman(String row){
        String[] rows = row.split(",");
        return new Human(rows[0], rows[1], Integer.parseInt(rows[2]), Integer.parseInt(rows[3]), Integer.parseInt(rows[4]));
    }

}
