package PracticeAgain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class DetectACycleInDirectedGraph {

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int v = 10;
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }

//        1
        adj.get(1).add(2);

//        2
        adj.get(2).add(3);

//        3

        adj.get(3).add(4);
        adj.get(3).add(7);


//        4
        adj.get(4).add(5);


//        5

        adj.get(5).add(6);


//        6


//        7
        adj.get(7).add(5);

//        8
        adj.get(8).add(9);
        adj.get(8).add(2);

//        9
        adj.get(9).add(10);
//        10
        adj.get(10).add(8);

        boolean isCyclic =checkForCycle(adj, v);
        System.out.println(isCyclic);
    }

    public static boolean checkForCycle(ArrayList<ArrayList<Integer>> adj, int v) {

        int[] visited = new int[v+1];
        Arrays.fill(visited, 0);
        int[] pathVisited = new int[v+1];
        Arrays.fill(pathVisited, 0);
        for(int i=1; i<=v; i++) {
            if(visited[i] == 0){
                if(dfsCycleCheck(adj, visited, pathVisited, i))
                    return true;
            }
        }
        return false;
    }

    public static boolean dfsCycleCheck(ArrayList<ArrayList<Integer>> adj,
                                        int[] visited, int[] pathVisited, int source) {

        pathVisited[source] = 1;
        visited[source] = 1;

        for(Integer neighbors : adj.get(source)){
            if(visited[neighbors] == 0){
                if(dfsCycleCheck(adj, visited, pathVisited, neighbors)) return true;
            } else {
                if(pathVisited[neighbors] == 1)
                    return true;
            }
        }
        pathVisited[source] = 0;
        return false;
    }
}
