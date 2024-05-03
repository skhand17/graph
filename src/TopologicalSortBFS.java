import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TopologicalSortBFS {

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

    public static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adjList) {

        int [] indegree = new int[V];
        int [] topo = new int[V];

        for(int i=0; i<V; i++){
            for(int neighbors : adjList.get(i)){
                indegree[neighbors]++;
            }
        }


        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<V; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        int i=0;
        while(!queue.isEmpty()) {

            topo[i++] = queue.peek();
            Integer currentNode = queue.poll();

            for(Integer neighbors : adjList.get(currentNode)){
                indegree[neighbors]--;
                if(indegree[neighbors] == 0)
                    queue.add(neighbors);
            }
        }

        return topo;
    }
}
