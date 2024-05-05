package DjiktasAlgorithmProblem;

/*
 *
 * This question ask me to the print the shortest path
 *
 * */

import java.util.*;

/*
* TC : O(E LOG V) + O(N)
* Since it is a Dijktras algorithm whose complexity is O(E LOG V)
*
*
* */
class PairShortestPath {
    int distance;
    int node;

    public PairShortestPath(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}

public class PrintShortestPath {
    public static void main(String[] args) {

        int[][] edges = new int[][]{
                {1, 2, 2},
                {1, 4, 1},
                {2, 3, 4},
                {2, 5, 5},
                {3, 4, 3},
                {3, 5, 1},
        };

        int nodes = 5;
        int edge = 6;

        List<Integer> result = shortestPath(edges, nodes, edge);
        result.forEach(System.out::println);
    }

    public static List<Integer> shortestPath(int[][] edges, int n, int m) {

        ArrayList<ArrayList<PairShortestPath>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            adj.get(edges[i][0]).add(new PairShortestPath(edges[i][2], edges[i][1]));
            adj.get(edges[i][1]).add(new PairShortestPath(edges[i][2], edges[i][0]));
        }

        for (int i = 0; i < n; i++) {
            for (PairShortestPath pair : adj.get(i)) {
                System.out.println(i + " -> " + pair.node + " (weight: " + pair.distance + ")");
            }
        }

        int sourceNode = 1;
        int destinationNode = 5;

        PriorityQueue<PairShortestPath> queue = new PriorityQueue<>((x, y) -> x.distance - y.distance);

        /*
        * Computes the distance*/
        int[] distance = new int[n + 1];
        /*
        * Computes the parent using memoization
        * */
        int[] parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            distance[i] = (int) 1e9;
            parent[i] = i;
        }

        distance[sourceNode] = 0;
        parent[sourceNode] = sourceNode;

        queue.add(new PairShortestPath(0, sourceNode));

        while (!queue.isEmpty()) {
            int dist = queue.peek().distance;
            int vertex = queue.peek().node;

            queue.poll();

            for (PairShortestPath pair : adj.get(vertex)) {
                int distAdjNode = pair.distance;
                int adjNode = pair.node;

                if (dist + distAdjNode < distance[adjNode]) {
                    distance[adjNode] = dist + distAdjNode;
                    parent[adjNode] = vertex;
                    queue.add(new PairShortestPath(distance[adjNode], adjNode));
                }
            }
        }

//        /*
//        * Printing distance array
//        * */
//        for(int i=1; i<=n; i++){
//            System.out.print(distance[i] + " ");
//        }
//        System.out.println();
//        /*
//        * Printing parent array
//        * */
//        for(int i=1; i<=n; i++){
//            System.out.print(parent[i] + " ");
//        }

        List<Integer> result = new ArrayList<>();
        /*
         * This is nothing but storing the results of the parent array to figure out the shortest path between source and destination
         *
         * */
        if (distance[destinationNode] == 1e9) {
            result.add(-1);
            return result;
        }


        while (parent[destinationNode] != destinationNode) {
            result.add(destinationNode);
            destinationNode = parent[destinationNode];
        }

        result.add(sourceNode);


        Collections.reverse(result);
        return result;
    }

}
