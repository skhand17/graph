import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class PairThree {
    int distance;
    int  node;

    public PairThree(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}

public class DijktrasAlgorithmPQ {

    public static int[] dijkstra(int V, ArrayList<ArrayList<PairNew>> adj, int Source) {

        /*
         * Constructing a Min Heap
         * */
        PriorityQueue<PairThree> pq = new PriorityQueue<PairThree>((x, y) -> x.distance - y.distance);


        /*Filling out the distance array
         * */
        int[] distance = new int[V];
        Arrays.fill(distance, (int) 1e9);


        distance[Source] = 0;
        pq.add(new PairThree(0, Source));

        while (!pq.isEmpty()) {
            int dist = pq.peek().distance;
            int node = pq.peek().node;

            pq.poll();

            for (int i = 0; i < adj.get(node).size(); i++) {
                int edgeNode = adj.get(node).get(i).first;
                int edgeWeight = adj.get(node).get(i).second;

                if (dist + edgeWeight < distance[edgeNode]) {
                    distance[edgeNode] = dist + edgeWeight;
                    pq.add(new PairThree(distance[edgeNode], edgeNode));
                }
            }
        }

        return distance;
    }
}
