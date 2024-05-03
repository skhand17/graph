import com.sun.jdi.PathSearchingVirtualMachine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DetectACycleInDirectedGraphBFS {
    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int V = 5;

        for(int i=0; i<=V; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(2);

        adj.get(2).add(3);

        adj.get(3).add(4);
        adj.get(3).add(5);

        adj.get(4).add(2);

        boolean isCyclic = hasCycle(V, adj);
        System.out.println(isCyclic);

    }

    public static boolean hasCycle(int V, ArrayList<ArrayList<Integer>> adjList) {

        int [] indegree = new int[V+1];
        for(int i=1; i<=V; i++){
            for(Integer neighbors : adjList.get(i)){
                indegree[neighbors]++;
            }
        }

//        int [] toposort = new int[V+1];
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=V; i++){
            if(indegree[i] == 0)
                queue.add(i);
        }

        int count = 0;
        while(!queue.isEmpty()) {
            Integer node = queue.peek();
            queue.poll();

//            toposort[count++] = node;

            for(Integer neighbors : adjList.get(node)) {
                indegree[neighbors]--;
                if(indegree[neighbors] == 0)
                    queue.add(neighbors);
            }
        }


        return count == V ? false : true;
    }
}
