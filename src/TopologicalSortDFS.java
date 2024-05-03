

/*
 *
 * Linear ordering of vertices such that if there is an edge between u & v then u should appear before v
 * in that ordering
 * */

import java.util.ArrayList;
import java.util.Stack;

public class TopologicalSortDFS {

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

        for(Integer i : result){
            System.out.print(i + " ");
        }
    }

    /*
    *
    * Intuition to use a stack
    * whoever dfs is getting completed i am putting it in the stack
    * this will give me the linear ordering as asked in topological sorting
    *
    *
    * */


    public static int[] topoSort(int v, ArrayList<ArrayList<Integer>> adj) {

        int[] visited = new int[v];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < v; i++) {
            if (visited[i] == 0) {
                dfs(i, visited, stack, adj);
            }
        }

        int []ans = new int[v];
        int i=0;
        while (!stack.isEmpty()){
            ans[i++] = stack.pop();
        }

        return ans;
    }

    public static void dfs(int node, int[] visited, Stack<Integer> stack, ArrayList<ArrayList<Integer>> adj) {

        visited[node] = 1;
        for(Integer neighbors : adj.get(node)) {
            if(visited[neighbors] == 0)
                dfs(neighbors, visited, stack, adj);
        }
//        one of the most important stuff
        stack.push(node);
    }


}
