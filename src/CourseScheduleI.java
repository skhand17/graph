import com.sun.jdi.PathSearchingVirtualMachine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseScheduleI {
    public static void main(String[] args) {
        int[][] preq = new int[][]{
                {1, 2},
                {4, 3},
                {2, 4},
                {4, 1}
        };

        int[][] preq1 = new int[][]{
                {1, 0},
                {2, 1},
                {3, 2}
        };

        boolean isPoss = isPossible(4, preq1);
        System.out.println(isPoss);
    }

    public static boolean isPossible(int V, int[][] preq) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < preq.length; i++) {
            adj.get(preq[i][0]).add(preq[i][1]);
        }

        int[] indegree = new int[V + 1];
        for (int i = 0; i < V; i++) {
            for (Integer neighbors : adj.get(i))
                indegree[neighbors]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                queue.add(i);
        }

        int count = 0;
        while (!queue.isEmpty()) {

            Integer node = queue.peek();
            count++;
            queue.poll();

            for (Integer neighbors : adj.get(node)) {
                indegree[neighbors]--;
                if (indegree[neighbors] == 0)
                    queue.add(neighbors);
            }
        }

        return count == V ? true : false;
    }
}
