package Practice;

import java.util.*;

public class BipartitieGraph {

    public static void main(String[] args) {


        int[][] graph = new int[][] {
                {1, 3}
                ,{0, 2 },
                {1, 3},
                {0, 2}
        };

        List<List<Integer>> adj = new ArrayList<>();
        int n = graph.length;
        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0; i<n; i++){
            for(int j : graph[i]){
                adj.get(i).add(j);
            }
        }

//        for(List<Integer> li : adj){
//            System.out.println(li);
//        }

        boolean isGraphBipartite = componentCheck(adj, n);
        System.out.println(isGraphBipartite);


    }

    public static boolean componentCheck(List<List<Integer>> adj, int n) {

        int[] visited = new int[n];
//        for(int i=0; i<n; i++){
//            visited[i] = -1;
//        }
        Arrays.fill(visited, -1);

        for(int i=0; i<n; i++){
            if(visited[i] == -1){
                if(!bfs(visited, i, adj)) return false;
            }
        }
        return true;
    }

    private static boolean bfs(int[] visited, int source, List<List<Integer>> adj) {

        visited[source] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            Integer node = queue.peek();
            queue.poll();

            for(Integer neighbors : adj.get(node)) {
                if(visited[neighbors] == -1){
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
