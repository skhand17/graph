package PracticeAgain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

class AdjTuple {

    int destNode;
    int edgeWeight;

    public AdjTuple(int destNode, int edgeWeight) {
        this.destNode = destNode;
        this.edgeWeight = edgeWeight;
    }
}

class QueueTuple {

    int distance;
    int node;

    public QueueTuple(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}

public class DiktrasAlgorithm {

    public static int[] dijkstra(int V, ArrayList<ArrayList<AdjTuple>> adj, int source) {

        PriorityQueue<QueueTuple> queue = new PriorityQueue<>((x, y) -> x.distance - y.distance);
        int[] distance = new int[V];
        Arrays.fill(distance, (int)1e9);

        distance[source] = 0;
        queue.offer(new QueueTuple(0, source));

        while (!queue.isEmpty()) {
            QueueTuple tuple = queue.poll();
            int dist = tuple.distance;
            int node = tuple.node;

            for(int i=0; i<adj.get(node).size(); i++){
                int destNode = adj.get(node).get(i).destNode;
                int dis = adj.get(node).get(i).edgeWeight;

                if(dist + dis < distance[destNode]){
                    distance[destNode] = dis + dist;
                    queue.offer(new QueueTuple(distance[destNode], destNode));
                }
            }
        }
        return distance;
    }
}
