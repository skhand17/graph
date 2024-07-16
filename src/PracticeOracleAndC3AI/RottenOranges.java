package PracticeOracleAndC3AI;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Tuple {
    int row;
    int col;
    int time;

    public Tuple(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}
public class RottenOranges {

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        int minutes = rottenOrangesBFS(grid);
        System.out.println(minutes);
    }

    public static int rottenOrangesBFS(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] visited = new int[n][m];
//        Arrays.fill(visited, 0);

        int totalFreshOrangesCount = 0;
        Queue<Tuple> queue = new LinkedList<>();


        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(grid[i][j] == 2){
                    queue.add(new Tuple(i, j, 0));
                    visited[i][j] = 1;
                } else{
                    visited[i][j] = 0;
                }
                if(grid[i][j] == 1){
                    totalFreshOrangesCount++;
                }
            }
        }

        int[] dRow = new int[] {-1, 0, 1, 0};
        int[] dCol = new int[]{0, 1, 0, -1};
        int minTime = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            Tuple tuple = queue.poll();
            int row = tuple.row;
            int col = tuple.col;
            int time = tuple.time;
            minTime = Math.max(minTime, time);
            for(int i=0; i<4; i++){
                int newRow = row + dRow[i];
                int newCol = col + dCol[i];

                if(newRow >= 0 && newRow < n
                        && newCol >=0 && newCol < m
                            && grid[newRow][newCol] == 1
                                && visited[newRow][newCol] == 0) {
                    count++;
                    visited[newRow][newCol] = 1;
                    queue.add(new Tuple(newRow, newCol, time + 1));
                }
            }
        }

        if(count != totalFreshOrangesCount)
            return -1;
        return minTime;
    }
}
