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
        for(int i=0; i<k; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<dict.length - 1; i++) {
            String word = dict[i];
            String nextWord = dict[i + 1];

            int minLength = Math.min(word.length(), nextWord.length());
            for (int j = 0; j < minLength; j++) {
                if (word.charAt(j) != nextWord.charAt(j)) {
                    adj.get(word.charAt(j) - 'a').add(nextWord.charAt(j) - 'a');
                    break;
                }
            }
        }

           int[] toporesult = bfsTopo(adj, n, k);

            String ans ="";

            for(Integer it : toporesult){
                ans +=(char)(it +(int)'a');
            }

            return ans;
    }

    private static int[] bfsTopo(List<List<Integer>> adj, int n, int k){

        int[] topo = new int[k];
        int[] indegree = new int[k];

        for(int i=0; i<k; i++){
            for(Integer neighbors : adj.get(i)){
                indegree[neighbors]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<k; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        int count = 0;

        while (!queue.isEmpty()){
            Integer node = queue.poll();

            topo[count++] = node;

            for(Integer neighbors : adj.get(node)){
                indegree[neighbors]--;
                if(indegree[neighbors] == 0){
                    queue.offer(neighbors);
                }
            }
        }
        return topo;
    }
}
