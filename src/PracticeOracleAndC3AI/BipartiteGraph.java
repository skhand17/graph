package PracticeOracleAndC3AI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class BipartiteGraph {
    public boolean isBipartite(int[][] graph) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        int nodes = graph.length;

        for(int i=0; i<nodes; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<graph.length; i++) {
            for(int j : graph[i]) {
                adj.get(i).add(j);
            }
        }

        int[] visited = new int[nodes];
        Arrays.fill(visited, -1);

        for(int i=0; i<nodes; i++) {
            if(visited[i] == -1){
                if(!bfs(visited, i, adj))
                    return false;
            }
        }

        return true;
    }

    public boolean bfs(int[] visited, int source, ArrayList<ArrayList<Integer>> adjList) {

        visited[source] = 1;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);


        while(!queue.isEmpty()) {
            Integer node = queue.poll();


            for(Integer neighbors : adjList.get(node)) {

                if(visited[neighbors] == -1) {
                    visited[neighbors] = 1 - visited[node];
                    queue.add(neighbors);
                } else if (visited[neighbors] == visited[node]){
                    return false;
                }
            }

        }

        return true;
    }

}

