import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlienDictionary {
    public static void main(String[] args) {
        int N = 5, K = 4;
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        String ans = findOrder(dict, N, K);

        for (int i = 0; i < ans.length(); i++) {
            System.out.print(ans.charAt(i) + " ");
        }
        System.out.println("");
    }

    private static String findOrder(String[] dict, int n, int k) {

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0; i< k; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<n-1; i++){
            String s1 = dict[i];
            String s2 = dict[i+1];
            int minLength = Math.min(s1.length(), s2.length());

            for(int ptr =0; ptr < minLength; ptr++){
                if(s1.charAt(ptr) != s2.charAt(ptr)){
                    adj.get(s1.charAt(ptr) - 'a').add(s2.charAt(ptr) - 'a');
                    break;
                }
            }
        }

        List<Integer> topoSort = topoSortAlien(k, adj);
        String ans="";

        for(int it: topoSort){
            ans += (char)(it + (int)('a'));
        }

        return ans;
    }

    private static List<Integer> topoSortAlien(int k, List<List<Integer>> adj) {

        int[] indegree = new int[k];

        for(int i=0; i<k; i++){
            for(Integer neighbors : adj.get(i)){
                indegree[neighbors]++;
            }
        }

        List<Integer> topo = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<k; i++){
            if(indegree[i] == 0)
                queue.add(i);
        }
        while(!queue.isEmpty()) {
            Integer node = queue.peek();
            topo.add(queue.poll());

            for(Integer neighbors : adj.get(node)){
                indegree[neighbors]--;
                if(indegree[neighbors] == 0)
                    queue.add(neighbors);
            }
        }
        return topo;
    }
}
