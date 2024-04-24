import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BipartiteGraphBFS {
    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int v = 4;

        for(int i=0; i<=v; i++){
            adj.add(new ArrayList<>());
        }


        adj.get(1).add(2);
        adj.get(1).add(4);
//        adj.get(1).add(5);

        adj.get(2).add(1);
        adj.get(2).add(3);
//        adj.get(2).add(6);

        adj.get(3).add(2);
        adj.get(3).add(4);

        adj.get(4).add(3);
        adj.get(4).add(1);
//        adj.get(4).add(5);

//        adj.get(5).add(1);
//        adj.get(5).add(4);
//        adj.get(4).add(7);
//
//        adj.get(5).add(6);
//        adj.get(5).add(4);
//
//        adj.get(6).add(2);
//        adj.get(6).add(5);
//
//        adj.get(7).add(4);
//        adj.get(7).add(8);
//
//        adj.get(8).add(7);

        boolean isBipartite = isCheckForComponents(adj, v);
        System.out.println(isBipartite);
    }

    public static boolean isCheckForComponents(ArrayList<ArrayList<Integer>> adj, int v) {

        int[] visited = new int[v+1];

        for(int i=0; i<=v; i++){
            visited[i] = -1;
        }

        for(int i=1; i<=v; i++){
            if(visited[i] == -1) {
                if(bfs(adj, visited, i) == false) return false;
            }

        }
        return true;
    }

    private static boolean bfs(ArrayList<ArrayList<Integer>> adj, int[] visited, int source) {

        Queue<Integer> queue = new LinkedList<>();
        visited[source] = 0;

        queue.add(source);

        while (!queue.isEmpty()) {

            Integer node = queue.peek();
            queue.poll();

            for(Integer neighbours : adj.get(node)) {

                if(visited[neighbours] == -1) {
                    visited[neighbours] = 1 - visited[node];
                    queue.add(neighbours);
                } else {
                    if(visited[neighbours] == visited[node]){
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
