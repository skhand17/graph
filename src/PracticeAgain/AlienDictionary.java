package PracticeAgain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlienDictionary {

    public static void main(String[] args) {

        int N = 5, K = 4;
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        String ans = findOrderAlienDict(dict, N, K);
        System.out.println(ans);
    }

    private static String findOrderAlienDict(String[] dict, int n, int k) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<k; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<dict.length - 1; i++){
            String s1 = dict[i];
            String s2 = dict[i+1];

            int minLength = Math.min(s1.length(), s2.length());

            for(int j=0; j<minLength; j++){
                if(s1.charAt(j) != s2.charAt(j)){
                    adj.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                    break;
                }
            }
        }
        for(ArrayList<Integer> list : adj){
            System.out.println(list);
        }

        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> topo = topoSort(adj, k);

        for(int i: topo){
            char ch =(char)(i + (int)('a'));
            stringBuilder.append(ch);
        }

        return stringBuilder.toString();

    }

    private static List<Integer> topoSort(ArrayList<ArrayList<Integer>> adj, int k) {

        int[] indegree = new int[k];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        for(int i=0; i<k; i++){
            for(Integer neighbors : adj.get(i)){
                indegree[neighbors]++;
            }
        }

        for(int i=0; i<k; i++){
            if(indegree[i] == 0)
                queue.add(i);
        }

        while (!queue.isEmpty()){
            Integer node = queue.poll();
            list.add(node);

            for(Integer neighbors : adj.get(node)){
                indegree[neighbors]--;
                if(indegree[neighbors] == 0)
                    queue.add(neighbors);
            }
        }
        return list;
    }
}
