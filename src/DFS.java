import java.lang.reflect.Array;
import java.util.ArrayList;

/*
 * This is a depth first traversal
 *
 * */
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


        for (ArrayList<Integer> innerList : adj) {
            System.out.println(innerList);
        }

        ArrayList<Integer> dfs = dfsOfGraph(adj, V);
        System.out.println(dfs);

    }


    public static ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj, int V) {

        boolean[] visited = new boolean[V + 1];
        ArrayList<Integer> dfs = new ArrayList<>();

        /*
         *
         * This is done to start the dfs algorithm for the initial node
         *
         * */
        dfs(1, adj, visited, dfs);

        return dfs;
    }

    public static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> dfs) {

        /*
        * We mark the node as visited first
        * Add it to the dfs list
        * Check out its neighbours from adjancency list
        * if that neighbour is not visited then call the recursive function again
        *
        * */
        visited[node] = true;
        dfs.add(node);

        for(Integer neighbour : adj.get(node)){

            if(!visited[neighbour]){
                dfs(neighbour, adj, visited, dfs);
            }
        }
    }
}
