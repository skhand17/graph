package PracticeOracleAndC3AI;

import java.util.*;

class QT{

    int stops;
    int node;
    int cost;

    QT(int s, int n, int c){
        this.stops = s;
        this.node = n;
        this.cost = c;
    }
}

class AT {

    int node;
    int cost;

    AT(int node, int cost){
        this.node = node;
        this.cost = cost;
    }
}

class CheapestFlights {
    public int findCheapestPrice(int nodes, int[][] edges, int source, int destination, int k) {

        ArrayList<ArrayList<AT>> adj = new ArrayList<>();
        for(int i=0; i<nodes; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++) {
            adj.get(edges[i][0]).add(new AT(edges[i][1], edges[i][2]));
        }

        int[] distance = new int[nodes];
        int[] parent = new int[nodes];

        Arrays.fill(distance, (int)1e9);
        distance[source] = 0;

        for(int i=0; i<nodes; i++) {
            parent[i] = i;
        }


        Queue<QT> queue = new LinkedList<>();
        queue.offer(new QT(0, source, 0));


        while(!queue.isEmpty()) {
            QT tuple = queue.poll();

            int stops = tuple.stops;
            int currentNode = tuple.node;
            int currentCost = tuple.cost;

            if(stops > k)
                continue;

            for(AT adjTuple: adj.get(currentNode)) {
                int destNode = adjTuple.node;
                int destDistance = adjTuple.cost;

                if(currentCost + destDistance < distance[destNode]) {
                    distance[destNode] = currentCost + destDistance;
                    parent[destNode] = currentNode;

                    queue.offer(new QT(stops + 1, destNode, distance[destNode]));
                }

            }
        }

        List<Integer> list = new ArrayList<>();
        while(parent[destination] != destination){
            list.add(destination);
            destination = parent[destination];
        }

        Collections.reverse(list);
        System.out.println(list);


        if(distance[destination] == (int)1e9)
            return -1;
        return distance[destination];

    }
}
