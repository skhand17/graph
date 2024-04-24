import java.util.ArrayList;

public class BipartiteGraphDFS {

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int v = 4;

        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }


        adj.get(1).add(2);
        adj.get(1).add(4);

        adj.get(2).add(1);
        adj.get(2).add(3);

        adj.get(3).add(2);
        adj.get(3).add(4);

        adj.get(4).add(3);
        adj.get(4).add(1);

//        adj.get(5).add(1);
//        adj.get(5).add(4);

        boolean isBipartite = componentCheck(adj, v);
        System.out.println(isBipartite);
    }

    public static boolean componentCheck(ArrayList<ArrayList<Integer>> adj, int v) {

        int[] color = new int[v + 1];
        for (int i = 0; i <= v; i++) {
            color[i] = -1;
        }

        for (int i = 1; i <= v; i++) {
            if (color[i] == -1) {
                color[i] = 0;
                if (dfs(adj, color, i) == false) return false;
            }

        }
        return true;
    }

    public static boolean dfs(ArrayList<ArrayList<Integer>> adj, int[] color, int source) {

        for (Integer neighbors : adj.get(source)) {

            if (color[neighbors] == -1) {
                color[neighbors] = 1 - color[source];
                if (dfs(adj, color, neighbors) == false) return false;
            } else {
                if (color[neighbors] == color[source]) {
                    return false;
                }
            }
        }
        return true;
    }
}
