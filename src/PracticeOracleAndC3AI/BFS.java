package PracticeOracleAndC3AI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        int V = 5; // Number of vertices
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Initialize the adjacency list with empty lists for each vertex
        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges to the adjacency list (dummy graph)
        adj.get(1).add(2);
        adj.get(1).add(3);
        adj.get(2).add(4);
        adj.get(3).add(4);
        adj.get(3).add(5);

        // Call the bfsOfGraph function
        ArrayList<Integer> bfsResult = bfsOfGraph(V, adj);

        // Print the BFS traversal result
        System.out.println("BFS Traversal: " + bfsResult);
    }

    public static ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adjList) {

        int[] visited = new int[V+1];
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> resultBfs = new ArrayList<>();

        visited[1] = 1;
        queue.add(1);

        while (!queue.isEmpty()) {
            Integer node =  queue.peek();
            resultBfs.add(node);
            queue.poll();

            for(Integer neighbors: adjList.get(node)) {
                if(visited[neighbors] == 0){
                    visited[neighbors] = 1;
                    queue.add(neighbors);
                }
            }
        }
        return resultBfs;
    }
}
