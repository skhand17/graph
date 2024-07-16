package PracticeAgain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CourseScheduleI {

    public static void main(String[] args) {
        int[][] preq = new int[][]{
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
        };
        int numCourses = 4;

        boolean isPossible = canFinish(numCourses, preq);
        System.out.println(isPossible);
    }

    public static boolean canFinish(int numCourses, int[][] preq) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i< preq.length; i++){
            adj.get(preq[i][1]).add(preq[i][0]);
        }

        int[] topo = new int[numCourses];
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<numCourses; i++){
            for(Integer neighbors : adj.get(i)){
                indegree[neighbors]++;
            }
        }
        for(int i=0; i<numCourses; i++){
            if(indegree[i] == 0)
                queue.add(i);
        }
        int count = 0;
        while (!queue.isEmpty()){
            Integer course = queue.poll();
            topo[count++] = course;

            for(Integer neighbors : adj.get(course)){
                indegree[neighbors]--;
                if(indegree[neighbors] == 0)
                    queue.add(neighbors);
            }
        }

        for(int i=0; i<count; i++){
            System.out.print(topo[i] + " ");
        }

        return count == numCourses;
    }
}
