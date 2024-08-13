package PracticeOracleAndC3AI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


class QueuePair {
    int node;
    int parent;

    public QueuePair(int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}
public class CycleDetectUnDirectedGraphBFS {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        int v = 7;
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(2);
        adj.get(1).add(3);
        adj.get(2).add(1);
        adj.get(2).add(5);
        adj.get(3).add(1);
        adj.get(3).add(4);
        adj.get(3).add(6);
        adj.get(4).add(3);
        adj.get(5).add(2);
        adj.get(5).add(7);
        adj.get(6).add(3);
        adj.get(6).add(7);
        adj.get(7).add(5);
        adj.get(7).add(6);

        boolean isCycle = isCyclePresent(adj, v);
        System.out.println(isCycle);
    }

    public static boolean isCyclePresent(ArrayList<ArrayList<Integer>> adjList, int V) {

        boolean[] visited = new boolean[V+1];

        for(int i=0; i<V; i++) {
            if(!visited[i]) {
                if(bfsCycleCheck(adjList, i, visited, V)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean bfsCycleCheck(ArrayList<ArrayList<Integer>> adj, int source,
                                        boolean[] visited, int V) {


        Queue<QueuePair> queue = new LinkedList<>();
        visited[source] = true;
        queue.offer(new QueuePair(source, -1));


        while (!queue.isEmpty()) {
            QueuePair qp = queue.poll();
            int node = qp.node;
            int parentNode = qp.parent;


            for(Integer neighbors : adj.get(node)) {
                if(!visited[neighbors]){
                    visited[neighbors] = true;
                    queue.offer(new QueuePair(neighbors, node));
                } else if (parentNode != neighbors){
                    return true;
                }
            }

        }
        return true;
    }
}
