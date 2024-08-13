package PracticeOracleAndC3AI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DetectACycleInDAGTopo {

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int nodes = 5;

        for(int i=0; i<=nodes; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
//        adj.get(3).add(5);
        adj.get(4).add(5);

        boolean isCycle = detectCycleBFSTopo(adj, nodes);
        System.out.println(isCycle);
    }

    private static boolean detectCycleBFSTopo(ArrayList<ArrayList<Integer>> adj, int nodes) {

        int[] topoResult = new int[nodes + 1];
        int[] inDegree = new int[nodes + 1];

//        Computing indegree
        for(int i=1; i<=nodes; i++){
            for(Integer neighbors : adj.get(i)) {
                inDegree[neighbors]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=nodes; i++) {
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            topoResult[count++] = node;

            for(Integer neighbors : adj.get(node)){
                inDegree[neighbors]--;
                if(inDegree[neighbors] == 0){
                    queue.offer(neighbors);
                }
            }
        }
        return count != nodes;
    }
}
