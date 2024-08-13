package PracticeOracleAndC3AI;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class QueuePairDijktras {

    int distance;
    int node;

    public QueuePairDijktras(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}

class AdjTupleDijktras {
    int destNode;
    int destDistance;

    public AdjTupleDijktras(int destNode, int destDistance) {
        this.destNode = destNode;
        this.destDistance = destDistance;
    }
}


public class DijktrasAlgorithm {

    public static int[] dijktrasAlgo(ArrayList<ArrayList<AdjTupleDijktras>> adj,
                                     int source, int V) {

//        PriorityQueue<QueuePairDijktras> pq = new PriorityQueue<>((x,y) -> x.distance - y.distance);
//
//        int[] distance = new int[V];
//        Arrays.fill(distance, (int)1e9);
//
//        distance[source] = 0;
//
//        pq.add(new QueuePairDijktras(0, source));
//
//        while (!pq.isEmpty()) {
//            QueuePairDijktras qpd = pq.poll();
//            int currentNode = qpd.node;
//            int currentDistance = qpd.distance;
//
//
//            for(AdjTupleDijktras neighbors : adj.get(currentNode)){
//
//                int destinationNode = neighbors.destNode;
//                int destinationDistance = neighbors.destDistance;
//
//                if(currentDistance + destinationDistance < distance[destinationNode]){
//                    distance[destinationNode] = currentDistance + destinationDistance;
//                    pq.offer(new QueuePairDijktras(distance[destinationNode], destinationNode));
//                }
//            }
//
//        }
//        return distance;
//    }

        PriorityQueue<QueuePairDijktras> pq = new PriorityQueue<>((x, y) -> x.distance - y.distance);
        int[] distance = new int[V];
        Arrays.fill(distance, (int) 1e9);

        distance[source] = 0;

        pq.offer(new QueuePairDijktras(0, source));

        while (!pq.isEmpty()) {
            QueuePairDijktras queuePairDijktras = pq.poll();

            int currentDistance = queuePairDijktras.distance;
            int currentNode = queuePairDijktras.node;

            for(AdjTupleDijktras adjTupleDijktras : adj.get(currentNode)) {
                int destionationNode = adjTupleDijktras.destNode;
                int destionationDistance = adjTupleDijktras.destDistance;

                if(currentDistance + destionationDistance < distance[destionationNode]){
                    distance[destionationNode] = currentDistance + destionationDistance;
                    pq.offer(new QueuePairDijktras(distance[destionationNode], destionationNode));
                }
            }
        }
        return distance;
    }

}
