import java.util.ArrayList;
import java.util.LinkedList;
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
        ArrayList<Integer> bfsResult = bfsOfGraph2(V, adj);

        // Print the BFS traversal result
        System.out.println("BFS Traversal: " + bfsResult);
    }
    public static ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {

        /*
        * This bfs arrayList will store the result
        * */
        ArrayList<Integer> bfs = new ArrayList<>();

        /*
        * Elements will be added to the queue who are not being visited
        * */

        Queue<Integer> queue = new LinkedList<>();

        boolean [] visited = new boolean[V+1];

        /*
        *
        * This is taking care of initial configuration
        * */
        queue.add(1);
        visited[1] = true;

        /*
        * We iterate through the queue till it is not empty
        *
        * */
        while (!queue.isEmpty()){

            /*
            *
            * Take out the node from the Queue
            * */
            Integer node = queue.poll();

            /*
            *
            * Add the element to the bfs ArrayList
            * */
            bfs.add(node);


            /*
            *
            * The Node removed from the queue. We ask AdjList who are its neighbours?
            * If the neighbour of that node has not been visited we mark the visited boolean array to true
            * and add that element to  the queue.
            *
            * */
            for(Integer it : adj.get(node)){
                if(!visited[it]){
                    visited[it] = true;
                    queue.add(it);
                }
            }
        }

        return bfs;
    }

    public static ArrayList<Integer> bfsOfGraph2(int V, ArrayList<ArrayList<Integer>> adj) {


        ArrayList<Integer> bfs = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();

        boolean[] visited = new boolean[V+1];


        queue.add(1);
        visited[1] = true;


        while (!queue.isEmpty()){

            Integer node = queue.poll();
            bfs.add(node);


            for(Integer neighbor : adj.get(node)){

                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return bfs;
    }

}
