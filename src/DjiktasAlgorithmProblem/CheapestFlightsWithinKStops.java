package DjiktasAlgorithmProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class QueueTuple {

    int stops;
    int nodes;
    int cost;

    public QueueTuple(int stops, int nodes, int cost) {
        this.stops = stops;
        this.nodes = nodes;
        this.cost = cost;
    }
}

class AdjacencyTuple {

    int vertex;
    int cost;

    public AdjacencyTuple(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }
}

public class CheapestFlightsWithinKStops {

    public static void main(String[] args) {

        int[][] edges = new int[][]{
                {0, 1, 5},
                {0, 3, 2},
                {3, 1, 2},
                {1, 2, 5},
                {1, 4, 1},
                {4, 2, 1}
        };
        int n = 5;
        int source = 0;
        int destination = 2;
        int k = 2;
        int cheap = cheapestFlight(edges, n, source, destination, k);
        System.out.println(cheap);

    }

    public static int cheapestFlight(int[][] edges, int nodes, int source, int destination, int k) {

        ArrayList<ArrayList<AdjacencyTuple>> adj = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(new AdjacencyTuple(edges[i][1], edges[i][2]));
        }

        for (int i = 0; i < nodes; i++) {
            for (AdjacencyTuple tuple : adj.get(i)) {
                System.out.println(i + " -> " + tuple.vertex + " (cost: " + tuple.cost + ")");
            }
        }

        int[] distance = new int[nodes];
        Arrays.fill(distance, (int) 1e9);

        distance[source] = 0;

        Queue<QueueTuple> queue = new LinkedList<>();
        queue.add(new QueueTuple(0, source, 0));

        while (!queue.isEmpty()) {
            QueueTuple queueTuple = queue.peek();

            int s = queueTuple.stops;
            int n = queueTuple.nodes;
            int c = queueTuple.cost;

            queue.poll();
            if (s > k) continue;
            ;

            for (AdjacencyTuple adjacencyTuple : adj.get(n)) {
                int v = adjacencyTuple.vertex;
                int nc = adjacencyTuple.cost;

                if (c + nc < distance[v] && s <= k) {
                    distance[v] = c + nc;
                    queue.add(new QueueTuple(s + 1, v, distance[v]));
                }

            }

        }

        if (distance[destination] == 1e9) return -1;
        return distance[destination];

    }

}
