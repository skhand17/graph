package Practice;


import java.util.LinkedList;
import java.util.Queue;

class Pair {

    int row;
    int col;
    int time;

    public Pair(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

public class RottenOranges {

    //    2 - represents rotten oranges
//    1- represent a fresh orange
//    0 - represent an empty cell
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 1, 2},
                {0, 1, 1},
                {2, 1, 1}};

        int minimumTime = rottenOranges(grid);
        System.out.println(minimumTime);
    }

    public static int rottenOranges(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;

        Queue<Pair> queue = new LinkedList<>();
        int[][] visited = new int[row][col];

        int totalFreshOranges = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Pair(i, j, 0));
                    visited[i][j] = 2;
                } else {
                    visited[i][j] = 0;
                }
                if (grid[i][j] == 1)
                    totalFreshOranges++;
            }
        }

        int totalTime = 0;
        int count = 0;
        int[] deltaRow = new int[]{-1, 0, 1, 0};
        int[] deltaCol = new int[]{0, +1, 0, -1};
        while (!queue.isEmpty()) {

            int r = queue.peek().row;
            int c = queue.peek().col;
            int t = queue.peek().time;
            totalTime = Math.max(totalTime, t);
            queue.poll();

            /*Check on all directions */

            for (int i = 0; i < 4; i++) {
                int newRow = r + deltaRow[i];
                int newCol = c + deltaCol[i];

                if (newRow >= 0 && newRow < row &&
                        newCol >= 0 && newCol < col &&
                        grid[newRow][newCol] == 1 && visited[newRow][newCol] == 0) {

                    visited[newRow][newCol] = 2;
                    queue.add(new Pair(newRow, newCol, t + 1));
                    count++;
                }
            }
        }

        if (count != totalFreshOranges) return -1;
        return totalTime;

    }
}
