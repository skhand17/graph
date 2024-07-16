package PracticeOracleAndC3AI;


import java.util.LinkedList;
import java.util.Queue;

class QueueTupleIsland {

    int row;
    int col;

    QueueTupleIsland(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class NumIslands {
    public static void main(String[] args) {

        int[][] grid = new int[][]{

                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}
        };

        int numIslands = numEnclaves(grid);
        System.out.println(numIslands);
    }

    public static int numEnclaves(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] visited = new int[n][m];

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0 && grid[i][j] == 1) {
                    count++;
                    bfsIslands(visited, i, j, grid);
                }
            }
        }
        return count;
    }

    public static void bfsIslands(int[][] visited, int row, int col, int[][] grid) {

        visited[row][col] = 1;
        Queue<QueueTupleIsland> queue = new LinkedList<>();
        queue.offer(new QueueTupleIsland(row, col));
        int[] deltaRow = new int[]{-1, 0, 1, 0};
        int[] deltaCol = new int[]{0, 1, 0, -1};

        while (!queue.isEmpty()) {

            QueueTupleIsland tuple = queue.poll();
            int currentRow = tuple.row;
            int currentCol = tuple.col;

            for (int i = 0; i < 4; i++) {
                int newRow = currentRow + deltaRow[i];
                int newCol = currentCol + deltaCol[i];

                if (newRow >= 0 && newRow < visited.length
                        && newCol >= 0 && newCol < visited[0].length
                        && visited[newRow][newCol] == 0 && grid[newRow][newCol] == 1) {
                    visited[newRow][newCol] = 1;
                    queue.offer(new QueueTupleIsland(newRow, newCol));

                }
            }

        }
    }
}