import java.util.ArrayList;

public class DetectACycleInDirectedGraph {
    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int v = 10;
        for(int i=0; i<=v; i++){
            adj.add(new ArrayList<>());
        }


//        1
        adj.get(1).add(2);

//        2
        adj.get(2).add(3);

//        3

        adj.get(3).add(4);
        adj.get(3).add(7);


//        4
        adj.get(4).add(5);


//        5

        adj.get(5).add(6);


//        6


//        7
        adj.get(7).add(5);

//        8
        adj.get(8).add(9);

//        9
        adj.get(9).add(10);
//        10
//        adj.get(10).add(8);


        boolean cylic = isCyclic(v, adj);
        System.out.println(cylic);
    }

    private static boolean isCyclic(int v, ArrayList<ArrayList<Integer>> adj) {

        int [] visited = new int[v+1];
        int [] pathVisted = new int[v+1];

        for(int i=1; i<=v; i++){

            if(dfsCheck(adj, i, visited, pathVisted)){
                return true;
            }
        }
        return false;
    }

    private static boolean dfsCheck(ArrayList<ArrayList<Integer>> adj, int source, int[] visited, int[] pathVisted) {

        visited[source] = 1;
        pathVisted[source] = 1;

        for(Integer neighbors : adj.get(source)) {

            if(visited[neighbors] == 0){
                if(dfsCheck(adj, neighbors, visited, pathVisted)) return true;
            } else {
                if(pathVisted[neighbors] == 1)
                    return true;
            }
        }
        pathVisted[source] = 0;
        return false;
    }
}
