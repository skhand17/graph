import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class PairOne {
    int currentNode;
    int currentNodeParent;

    public PairOne(int currentNode, int currentNodeParent) {
        this.currentNode = currentNode;
        this.currentNodeParent = currentNodeParent;
    }
}


public class DetectACycleInAnUndirectedGraph {

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        int v = 7;

        for(int i=0; i<=v; i++){
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


    public static boolean isCycleInComponents(int v, ArrayList<ArrayList<Integer>> adj) {

        boolean[] visited = new boolean[v+1];
        for(int i=1; i<=v; i++){
            if(!visited[i]){
                if(checkForCycle(adj, i, visited, v)) return true;
            }
        }
        return false;
    }

    public static boolean checkForCycle(ArrayList<ArrayList<Integer>> adj, int src, boolean[] visited, int v){

        Queue<PairOne> queue = new LinkedList<>();

        queue.add(new PairOne(src, -1));
        visited[1] = true;


        while (!queue.isEmpty()) {

            int currentNode = queue.peek().currentNode;
            int currentNodeParent = queue.peek().currentNodeParent;

            queue.poll();


            for(Integer neighbour : adj.get(currentNode)) {

                if(!visited[neighbour]){
                    visited[neighbour]= true;
                    queue.add(new PairOne(neighbour, currentNode));

                } else if(currentNodeParent != neighbour)
                    return  true;
            }

        }
        return false;
    }




}
