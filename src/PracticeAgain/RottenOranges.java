package PracticeAgain;


import java.util.LinkedList;
import java.util.Queue;

class Tuple{

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
        int[][] grid = new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        int time = orangeRotting(grid);
        System.out.println(time);
    }

    public static int orangeRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        int countFreshOranges = 0;
        int[][]visited = new int[n][m];
        Queue<Tuple> queue = new LinkedList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 2){
                    visited[i][j] = 1;
                    queue.add(new Tuple(i, j, 0));
                } else {
                    visited[i][j] = 0;
                }

                if(grid[i][j] == 1){
                    countFreshOranges++;
                }
            }
        }

        int[] deltaRow = new int[] {-1, 0, 1, 0};
        int[] deltaCol = new int[]{0, 1, 0, -1};

        int tm = 0;
        int count = 0;

        while (!queue.isEmpty()){
            Tuple tuple = queue.poll();
            int row = tuple.row;
            int col = tuple.col;
            int time = tuple.time;
            tm = Math.max(time, tm);

            for(int i=0; i<4; i++){
                int newRow = row + deltaRow[i];
                int newCol = col + deltaCol[i];

                if(newRow >= 0 && newRow < n
                        && newCol >=0 && newCol < m
                        && visited[newRow][newCol] == 0 && grid[newRow][newCol] == 1){
                    visited[newRow][newCol] = 1;
                    count++;
                    queue.add(new Tuple(newRow, newCol, time + 1));
                }
            }
        }

        if(count != countFreshOranges)
            return -1;
        return tm;

    }
}
