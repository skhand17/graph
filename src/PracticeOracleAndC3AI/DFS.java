package PracticeOracleAndC3AI;

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

        ArrayList<Integer> dfs = dfsGraph(adj, V);
        System.out.println(dfs);

    }

    public static ArrayList<Integer> dfsGraph(ArrayList<ArrayList<Integer>> adj, int V) {

        ArrayList<Integer> dfs = new ArrayList<>();
        int[] visited = new int[V+1];
        dfs(1, visited, dfs, adj);
        return dfs;
    }
    public static void dfs(int source, int[] visited,
                           ArrayList<Integer> dfs, ArrayList<ArrayList<Integer>> adj) {

        visited[source] = 1;
        dfs.add(source);
        for(Integer neighbors : adj.get(source)) {
            if(visited[neighbors] == 0){
                dfs(neighbors, visited, dfs, adj);
            }
        }
    }

}
