package PracticeOracleAndC3AI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AlienDictionary {

    public static void main(String[] args) {

        int N = 5, K = 4;
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        String ans = findOrderAlienDictionary(dict, N, K);
        System.out.println(ans);
    }

    private static String findOrderAlienDictionary(String[] dict, int n, int k) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<k; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<dict.length - 1; i++) {
            String word = dict[i];
            String nextWord = dict[i+1];

            int minLength = Math.min(word.length(), nextWord.length());

            for(int j=0; j<minLength; j++) {

                if(word.charAt(j) != nextWord.charAt(j)) {
                    adj.get(word.charAt(j) - 'a').add(nextWord.charAt(j) - 'a');
                    break;
                }
            }
        }

        for(ArrayList<Integer> list : adj){
            System.out.println(list);
        }

        int[] wordOrder = topoSort(adj, n, k);

        for(Integer num :  wordOrder){
            char ch = (char) ( num + (int)'a');
            sb.append(ch);
        }
        return sb.toString();
    }

    public static int[] topoSort(ArrayList<ArrayList<Integer>> adj, int n, int k) {

        int[] wordOrder = new int[k];
        int[] inDegree = new int[k];

        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<k; i++){
            for(Integer neighbors : adj.get(i)) {
                inDegree[neighbors]++;
            }
        }

        for(int i=0; i<k; i++) {
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }

        int count = 0;
        while(!queue.isEmpty()) {
            Integer node = queue.poll();
            wordOrder[count++] = node;

            for(Integer neighbors : adj.get(node)) {
                inDegree[neighbors]--;
                if(inDegree[neighbors] == 0){
                    queue.offer(neighbors);
                }
            }
        }
        if(k!=count) return new int[]{};
        return wordOrder;
    }

}