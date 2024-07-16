package Practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


class PairTwo {

    int currentNode;
    int currentNodeParent;

    public PairTwo(int currentNode, int currentNodeParent) {
        this.currentNode = currentNode;
        this.currentNodeParent = currentNodeParent;
    }
}
public class CycleDetectioninUnDirectedGraph {
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


        boolean isCycle = isCycleInComponents(v, adj);
        System.out.println(isCycle);
    }

    private static boolean isCycleInComponents(int v, ArrayList<ArrayList<Integer>> adj) {

        boolean[]visited = new boolean[v+1];
        for(int i=1; i<v; i++) {
            if(!visited[i]){
                if(checkForCycle(adj, visited, i)) return true;
            }
        }
        return false;
    }

    private static boolean checkForCycle(ArrayList<ArrayList<Integer>> adj, boolean[]visited, int source) {

        Queue<PairTwo> queue = new LinkedList<>();
        queue.add(new PairTwo(source, -1));

        visited[source] = true;


        while (!queue.isEmpty()) {

            int currentNode = queue.peek().currentNode;
            int currentNodeParent = queue.peek().currentNodeParent;

            queue.poll();

            for (Integer neighbors : adj.get(currentNode)) {

                if (!visited[neighbors]) {
                    visited[neighbors] = true;
                    queue.add(new PairTwo(neighbors, currentNode));
                } else if (currentNodeParent != neighbors) {
                    return true;
                }
            }
        }
        return false;
    }
}
