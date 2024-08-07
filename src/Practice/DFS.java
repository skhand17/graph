package Practice;

import java.util.ArrayList;

public class DFS {

    public static void main(String[] args) {

        int V = 8;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(2);
        adj.get(1).add(3);
        adj.get(2).add(1);
        adj.get(2).add(5);
        adj.get(2).add(6);
        adj.get(5).add(2);
        adj.get(6).add(2);

        adj.get(3).add(1);
        adj.get(3).add(4);
        adj.get(3).add(7);


        adj.get(4).add(3);
        adj.get(4).add(8);


        adj.get(7).add(3);
        adj.get(7).add(8);

        adj.get(8).add(4);
        adj.get(8).add(7);

        ArrayList<Integer> dfs = dfsOfGraph(adj, V);
        System.out.println(dfs);
    }

    public static ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj, int V) {

        ArrayList<Integer> dfs = new ArrayList<>();
        boolean[] visited  = new boolean[V+1];
        Depth(adj, dfs, 1, visited);


        return dfs;
    }

    public static void Depth(ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> dfs,
                             int source, boolean[] visited) {

        visited[source] = true;
        dfs.add(source);

        for(Integer neighbors : adj.get(source)) {
            if(!visited[neighbors]){
                Depth(adj, dfs, neighbors, visited);
            }
        }





    }
}