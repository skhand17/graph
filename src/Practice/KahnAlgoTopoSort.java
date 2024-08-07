package Practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class KahnAlgoTopoSort {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        int v = 6;

        for(int i=0; i<v; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(2).add(3);
        adj.get(3).add(1);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(5).add(0);
        adj.get(5).add(2);

        int[] result = topoSort(v, adj);
        for(int r : result){
            System.out.print(r + " ");
        }
    }

    private static int[] topoSort(int v, ArrayList<ArrayList<Integer>> adj) {


        int[] topo = new int[v];
        int[] indegree = new int[v];
        /*
        * Setting up the indegree for the topo sort to work
        * */

        for(int i=0; i<v; i++){
            for(int neighbors : adj.get(i)){
                indegree[neighbors]++;
            }
        }

        /* Initial Configuration */
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<v; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }
        int i=0;
        while (!queue.isEmpty()) {

            Integer node = queue.peek();
            queue.poll();
            topo[i++] = node;
            for(Integer neighbors : adj.get(node)) {
                indegree[neighbors]--;
                if(indegree[neighbors] == 0){
                    queue.add(neighbors);
                }
            }

        }

        return topo;

    }
}
