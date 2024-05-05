import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        int nodes = 7;
        int source = 0;

        edges.add(new ArrayList<>(Arrays.asList(3, 2, 6)));
        edges.add(new ArrayList<>(Arrays.asList(5, 3, 1)));
        edges.add(new ArrayList<>(Arrays.asList(0, 1, 5)));
        edges.add(new ArrayList<>(Arrays.asList(1, 5, -3)));
        edges.add(new ArrayList<>(Arrays.asList(1, 2, -2)));
        edges.add(new ArrayList<>(Arrays.asList(3, 4, -2)));
        edges.add(new ArrayList<>(Arrays.asList(2, 4, 3)));

        int[] result = bellmanFord(nodes, edges, source);
        for(int i : result)
            System.out.print(i + " ");
    }

    public static int[] bellmanFord(int V, ArrayList<ArrayList<Integer>> edges, int source) {

        int[] distance = new int[V-1];
        Arrays.fill(distance, (int)1e9);

        distance[source] =  0;

        /*
        * Trying to perform the relaxations Nodes - 1 times
        * */
        for(int i=0; i<V-1; i++){
            for(ArrayList<Integer> edge : edges){
                int u = edge.get(0); // source node
                int v = edge.get(1); // target node
                int weight = edge.get(2); // weight of an edge between u -> v

                if( distance[u] != 1e9 && distance[u] + weight < distance[v])
                    distance[v] = distance[u] + weight;
            }
        }

//        Nth Relaxation to check negative cycle
        for(ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int weight = edge.get(2);

            if(distance[u] != 1e9 && distance[u] + weight < distance[v]){
                int [] temp = new int[1];
                temp[0] = -1;
                return temp;
            }
        }

        return distance;
    }
}
