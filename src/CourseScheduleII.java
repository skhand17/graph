import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseScheduleII {
    public static void main(String[] args) {

        int[][] preq = new int[][] {
                {1, 2},
                {4, 3},
                {4, 1}
        };

        int [] course = courseSchedule(4, preq);
        for(int i: course){
            System.out.println(i);
        }
    }

    public static int[] courseSchedule(int V, int[][] preq) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<=V; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i< preq.length; i++){
            adj.get(preq[i][1]).add(preq[i][0]);
        }

        int[] indegree = new int[V+1];
        for(int i=0; i<=V; i++){
            for(Integer neighbors: adj.get(i))
                indegree[neighbors]++;
        }

        int[] topo = new int[V+1];

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=V; i++){
            if(indegree[i] == 0)
                queue.add(i);
        }

        int count = 0;

        while(!queue.isEmpty()) {

            Integer node = queue.peek();
            topo[count++] = queue.poll();

            for(Integer neighbors : adj.get(node)) {
                indegree[neighbors]--;
                if(indegree[neighbors] == 0)
                    queue.add(neighbors);
            }
        }
        if(count == V)
            return topo;
        else
            return new int[]{};

    }
}
