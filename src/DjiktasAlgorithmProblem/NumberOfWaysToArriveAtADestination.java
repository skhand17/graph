package DjiktasAlgorithmProblem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class PairWays {

    int vertex;
    int distance;

    public PairWays(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }
}

public class NumberOfWaysToArriveAtADestination {

    public static void main(String[] args) {

        int[][] edges = new int[][]{
                {0, 4, 5},
                {0, 6, 7},
                {0, 1, 2},
                {4, 6, 2},
                {6, 3, 3},
                {6, 5, 1},
                {3, 1, 3},
                {3, 5, 1},
                {1, 2, 3},
                {2, 5, 1}
        };

        int nodes = 7;
        int source = 0;
        int destination = 6;

        int numberOfWays = numberOfWaysToArrive(edges, source, destination, nodes);
        System.out.println(numberOfWays);
    }

    public static int numberOfWaysToArrive(int[][] edges, int source, int destination, int nodes) {

        ArrayList<ArrayList<PairWays>> adj = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            adj.add(new ArrayList<>());
        }

        /*
         * Creating an Adjanceny list out of the edges that we have
         *
         * */
        for (int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(new PairWays(edges[i][1], edges[i][2]));
            adj.get(edges[i][1]).add(new PairWays(edges[i][0], edges[i][2]));
        }

        for (int i = 0; i < edges.length; i++) {
            for (PairWays pairWays : adj.get(i)) {
                System.out.println("source :" + i + " - > destionation :" + pairWays.vertex + " distance :" + pairWays.distance);
            }
        }

        /*Initial configuration*/

        int[] distanceArray = new int[nodes];
        int[] numberOfWaysArray = new int[nodes];

        for(int i=0; i<nodes; i++){
            distanceArray[i] = (int)1e9;
            numberOfWaysArray[i] = 0;
        }

        distanceArray[source] = 0;
        numberOfWaysArray[source] = 1;

        PriorityQueue<PairWays> priorityQueue = new PriorityQueue<>((x,  y) -> x.distance - y.distance);
        priorityQueue.add(new PairWays(0, 0));

        int mod = (int) 1e9 + 7;


        while (!priorityQueue.isEmpty()){

            PairWays pair = priorityQueue.peek();
            int vertex = pair.vertex;;
            int dist = pair.distance;

            priorityQueue.poll();


            for(PairWays p : adj.get(vertex)){
                int node = p.vertex;
                int edgeWt = p.distance;

                if(dist + edgeWt < distanceArray[node]){
                    distanceArray[node] = dist + edgeWt;
                    priorityQueue.add(new PairWays(node, distanceArray[node]));
                    numberOfWaysArray[node] = numberOfWaysArray[vertex];
                } else  if (dist + edgeWt == distanceArray[node]){
                    numberOfWaysArray[node] = (numberOfWaysArray[node] + numberOfWaysArray[vertex])%mod;
                }
            }
        }

        return numberOfWaysArray[nodes - 1] % mod;

    }
}
