package Practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlienDictionary {

    public static void main(String[] args) {

        String[] dict = new String[] {"baa" , "abcd", "cab", "cad"};

        String ans = alienDictionary(dict);
        System.out.println(ans);
    }

    public static String alienDictionary(String[] dict) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i< dict.length; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<dict.length -1; i++){
            String s1 = dict[i];
            String s2 = dict[i+1];

            int minLength = Math.min(s1.length(), s2.length());

            for(int ptr=0; ptr<minLength; ptr++){

                if(s1.charAt(ptr) != s2.charAt(ptr)){
                    adj.get(s1.charAt(ptr) - 'a').add(s2.charAt(ptr) - 'a');
                    break;
                }
            }
        }

        for(List<Integer> list : adj){
            System.out.println(list);
        }

        List<Integer> topo = bfsAlienDict(adj, dict.length);

        StringBuilder sb = new StringBuilder();
        for(Integer it: topo) sb.append((char) (it + (int) 'a'));

        return sb.toString();

    }

    private static List<Integer> bfsAlienDict(ArrayList<ArrayList<Integer>> adj, int n) {

        List<Integer> topo = new ArrayList<>();

        int[] indegree = new int[n];
        for(int i=0; i<n; i++){
            for(int neighbors : adj.get(i)){
                indegree[neighbors]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<n; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){

            Integer node =  queue.poll();
            topo.add(node);

            for(Integer neighbors : adj.get(node)){
                indegree[neighbors]--;
                if(indegree[neighbors] == 0){
                    queue.add(neighbors);
                }
            }
        }
        return topo;
    }
}
