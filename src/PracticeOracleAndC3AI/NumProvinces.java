package PracticeOracleAndC3AI;

import java.util.ArrayList;

public class NumProvinces {
    public static void main(String[] args) {
        int[][]isConnected = new int[][] {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };

        int numProvinces = countProvinces(isConnected);
        System.out.println(numProvinces);
    }

    private static int countProvinces(int[][] isConnected) {

        int N = isConnected.length;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for(int i=0; i<=N; i++){
            adjList.add(new ArrayList<>());
        }

        for(int i=0; i<isConnected.length; i++) {
            for(int j=0; j<isConnected[0].length; j++) {
                if(isConnected[i][j] == 1 && i!=j) {
                    adjList.get(i).add(j);
                }
            }
        }

        for(ArrayList<Integer> list : adjList){
            System.out.println(list);
        }

        boolean[] visited = new boolean[N+1];
        int count = 0;

        for(int i=0; i<N; i++) {
            if(!visited[i]){
                count++;
                dfsProvinces(visited, adjList, i);
            }
        }

        return count;
    }

    private static void dfsProvinces(boolean[] visited,
                                     ArrayList<ArrayList<Integer>> adjList, int i) {

        visited[i] = true;
        for(Integer neighbors : adjList.get(i)) {
            if(!visited[neighbors]){
                dfsProvinces(visited, adjList, neighbors);
            }
        }
    }
}
