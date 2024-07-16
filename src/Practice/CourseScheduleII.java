package Practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < prerequisites.length; i++) {
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        for (int i = 0; i < numCourses; i++) {
            for (Integer neighbors : adj.get(i)) {
                indegree[neighbors]++;
            }
        }

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int[] topo = new int[numCourses];
        int count = 0;

        while (!queue.isEmpty()) {

            Integer node = queue.poll();
            topo[count++] = node;

            for (Integer neighbors : adj.get(node)) {
                indegree[neighbors]--;

                if (indegree[neighbors] == 0) {
                    queue.add(neighbors);
                }
            }

        }

        if (count != numCourses) return new int[]{};

        return topo;

    }
}
