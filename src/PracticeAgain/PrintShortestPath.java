package PracticeAgain;

import java.lang.reflect.Array;
import java.util.*;

class PrintShortestPathTuple {
    int distance;
    int node;

    public PrintShortestPathTuple(int distance, int node) {
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

        List<Integer> result = shortestPathPrint(edges, nodes, edge);
        result.forEach(System.out::println);
    }

    private static List<Integer> shortestPathPrint(int[][] edges, int nodes, int edge) {
        ArrayList<ArrayList<PrintShortestPathTuple>> adj = new ArrayList<>();

        for (int i = 0; i < edge; i++) {
            adj.add(new ArrayList<>());
        }

        int[] distance = new int[nodes + 1];
        Arrays.fill(distance, (int) 1e9);
        int[] parent = new int[nodes + 1];


        PriorityQueue<PrintShortestPathTuple> pq =
                new PriorityQueue<>((x, y) -> x.distance - y.distance);

        for (int i = 0; i < edge; i++) {
            adj.get(edges[i][0]).add(new PrintShortestPathTuple(edges[i][2], edges[i][1]));
            adj.get(edges[i][1]).add(new PrintShortestPathTuple(edges[i][2], edges[i][0]));
        }

        int sourceNode = 1;
        int destinationNode = 5;
        distance[sourceNode] = 0;
        for (int i = 0; i <= nodes; i++) {
            parent[i] = i;
        }
        pq.add(new PrintShortestPathTuple(0, sourceNode));


        while (!pq.isEmpty()) {
            PrintShortestPathTuple tuple = pq.poll();
            int currentNode = tuple.node;
            int currentDist = tuple.distance;

            for (PrintShortestPathTuple pt : adj.get(currentNode)) {
                int destNode = pt.node;
                int destNodeEdgeWt = pt.distance;

                if (currentDist + destNodeEdgeWt < distance[destNode]) {
                    distance[destNode] = currentDist + destNodeEdgeWt;
                    parent[destNode] = currentNode;
                    pq.add(new PrintShortestPathTuple(distance[destNode], destNode));
                }
            }
        }


        List<Integer> result = new ArrayList<>();

        while (parent[destinationNode] != destinationNode) {
            result.add(destinationNode);
            destinationNode = parent[destinationNode];
        }
        result.add(sourceNode);

        Collections.reverse(result);
        return result;
    }
}
