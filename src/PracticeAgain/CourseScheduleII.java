package PracticeAgain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseScheduleII {

    public static void main(String[] args) {
        int[][] preq = new int[][]{
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
        };

        int numCourses = 4;

        int[] order = findOrder(numCourses, preq);
        for(int i=0; i<numCourses; i++){
            System.out.print(order[i] + " ");
        }
    }

    public static int[] findOrder(int numCourses, int[][] preq) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        int[] indegree = new int[numCourses];
        int[] courseOrder = new int[numCourses];

        for(int i=0; i<numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i< preq.length; i++){
            adj.get(preq[i][1]).add(preq[i][0]);
        }

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
            Integer course =  queue.poll();
            courseOrder[count++] = course;

            for(Integer neighbors : adj.get(course)){
                indegree[neighbors]--;
                if(indegree[neighbors] == 0)
                    queue.add(neighbors);
            }
        }

        if(count != numCourses) return new int[]{};
        else return courseOrder;
    }
}
