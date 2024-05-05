import com.sun.jdi.PathSearchingVirtualMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


class PairNew {
    int first;
    int second;

    public PairNew(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
public class ShortestPathInDAG {
    public static void main(String[] args) {

        int n =7;
        int m = 8;

        int[][] edges = {
                {0, 4, 2},
                {0, 5, 3},
                {5, 4, 1},
                {4, 6, 3},
                {4, 2, 1},
                {6, 1, 2},
                {2, 3, 3},
                {1, 3, 1}
        };

        int[] result =  shortestPath(n, m, edges);
        for(int i=0; i<n; i++){
            System.out.print(result[i] + " ");
        }

    }

    private static int[] shortestPath(int n, int m, int[][] edges) {

        ArrayList<ArrayList<PairNew>> adj = new ArrayList<>();
        for(int i=0; i<n; i++){
            ArrayList<PairNew> temp = new ArrayList<>();
            adj.add(temp);
        }
        /*
        * This process will create an adjancency list
        *
        * */

        for(int i=0; i<m; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adj.get(u).add(new PairNew(v, wt));
        }

        /*
        * Print out the adjancency list
        *
        * */
        for (int i = 0; i < n; i++) {
            for (PairNew pair : adj.get(i)) {
                System.out.println(i + " -> " + pair.first + " (weight: " + pair.second + ")");
            }
        }
        int[] visited = new int[n];
//
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++){
            if(visited[i] == 0)
                dfsTopo(stack, adj, visited, i);
        }

        /*
        * Printing out the content of a stack
        * */
//        while (!stack.isEmpty()){
//            System.out.print(stack.pop());
//        }

        /*
        * Constructed distance array with all values as infinity
        * */
        int[] distance = new int[n];
        Arrays.fill(distance, (int) (1e9));

        distance[0] = 0;

        while(!stack.isEmpty()){
            Integer node = stack.peek();
            stack.pop();

            for(int i=0; i< adj.get(node).size(); i++){
                int v =  adj.get(node).get(i).first;
                int wt =  adj.get(node).get(i).second;

                if(distance[node] + wt < distance[v])
                    distance[v] = distance[node] + wt;
            }
        }

        for(int i=0; i<n; i++){
            if(distance[i] == 1e9)
                distance[i] = -1;
        }


        return distance;
    }

    private static void dfsTopo(Stack<Integer> stack, ArrayList<ArrayList<PairNew>> adj, int[] visited, int node) {
        visited[node] = 1;

        for (PairNew neighbors : adj.get(node)) {
            int v = neighbors.first;
            if (visited[v] == 0)
                dfsTopo(stack, adj, visited, v);
        }
        stack.push(node);
    }

}
