package Practice;

import java.util.ArrayList;

public class NumberOfProvinces {

    public static void main(String[] args) {
        int[][]isConnected = new int[][] {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };

        int numberOfProvinces = findCircleNum(isConnected);
        System.out.println(numberOfProvinces);
    }

    public static int findCircleNum(int[][] isConnected) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int V = isConnected.length;

        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<isConnected.length; i++) {
            for(int j=0; j<isConnected[0].length; j++) {
                if(isConnected[i][j] == 1 && i!=j){
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        boolean[] visited = new boolean[V];
        int count = 0;
        for(int i=0; i<V; i++) {
            if(!visited[i]){
                count++;
                dfs(adj, visited, i);
            }
        }

        return count;
    }


    public static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int source) {
        visited[source] = true;
        for(Integer neighbors : adj.get(source)){
            if(!visited[neighbors]){
                dfs(adj, visited, neighbors);
            }
        }
    }
}
