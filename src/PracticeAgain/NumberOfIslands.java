package PracticeAgain;


import java.util.LinkedList;
import java.util.Queue;

class TupleIsland {

    int row;
    int col;

    public TupleIsland(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class NumberOfIslands {

    public static void main(String[] args) {

        char[][] grid = new char[][]{

                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        int numIslands = numIslands(grid);
        System.out.println(numIslands);
    }

    public static int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];
        int countIslands = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    bfsIslands(i, j, visited, grid);
                    countIslands++;
                }
            }
        }
        return countIslands;
    }

    public static void bfsIslands(int row, int col, boolean[][] visited, char[][] grid) {

        Queue<TupleIsland> queue = new LinkedList<>();
        visited[row][col] = true;

        int[] deltaRow = new int[]{-1, 0, 1, 0};
        int[] deltaCol = new int[]{0, 1, 0, -1};

        queue.add(new TupleIsland(row, col));

        while (!queue.isEmpty()) {
            TupleIsland tupleIsland = queue.poll();
            int currentRow = tupleIsland.row;
            int currentCol = tupleIsland.col;

            for (int i = 0; i < 4; i++) {
                int newRow = currentRow + deltaRow[i];
                int newCol = currentCol + deltaCol[i];

                if (newRow >= 0 && newRow < grid.length
                        && newCol >= 0 && newCol < grid[0].length
                        && !visited[newRow][newCol] && grid[newRow][newCol] == '1') {
                    visited[newRow][newCol] = true;
                    queue.add(new TupleIsland(newRow, newCol));
                }
            }
        }
    }
}
