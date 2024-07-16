package Practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int V = 5;

        for(int i=0; i<=V; i++){
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(2);
        adj.get(1).add(5);

        adj.get(2).add(1);
        adj.get(2).add(5);
        adj.get(2).add(3);

        adj.get(3).add(2);
        adj.get(3).add(4);
        adj.get(3).add(5);

        adj.get(4).add(3);
        adj.get(4).add(5);

        adj.get(5).add(1);
        adj.get(5).add(2);
        adj.get(5).add(3);
        adj.get(5).add(4);

        ArrayList<Integer> bfsList = bfs(adj, V, 1);
        for(Integer node : bfsList){
            System.out.print(node + " ");
        }

    }

    public static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj, int V, int source) {

        ArrayList<Integer> bfs = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visisted = new boolean[V+1];


//        Initial Configuration

        queue.add(1);
        visisted[1] = true;



        while(!queue.isEmpty()) {

            Integer node = queue.peek();
            queue.poll();


            bfs.add(node);



            for(Integer neighbors : adj.get(node)) {
                if(!visisted[neighbors]){
                    visisted[neighbors] = true;
                    queue.add(neighbors);
                }
            }
        }
        return bfs;

    }
}
