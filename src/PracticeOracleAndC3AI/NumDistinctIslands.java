package PracticeOracleAndC3AI;


import java.util.*;

class QueueDistinctIslands {

    int row;
    int col;

    public QueueDistinctIslands(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class NumDistinctIslands {

    public static void main(String[] args) {

        char[][] grid = new char[][]{

                {'1', '1', '0', '1', '1'},
                {'1', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
                {'0', '0', '0', '1', '1'},
                {'1', '1', '0', '1', '0'}
        };

        int distinct = distinctIslandsCount(grid);
        System.out.println(distinct);
    }

    private static int distinctIslandsCount(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];
        Set<List<String>> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    List<String> list = new ArrayList<>();
                    bfsDistinctIslands(visited, i, j, list, grid);
                    set.add(list);
                }
            }
        }
        return set.size();
    }

    private static void bfsDistinctIslands(boolean[][] visited, int row, int col,
                                           List<String> list, char[][] grid) {

        visited[row][col] = true;
        int[] dRow = new int[]{-1, 0, 1, 0};
        int[] dCol = new int[]{0, 1, 0, -1};


        Queue<QueueDistinctIslands> queue = new LinkedList<>();
        queue.offer(new QueueDistinctIslands(row, col));

        while (!queue.isEmpty()) {

            QueueDistinctIslands qt = queue.poll();
            int currentRow = qt.row;
            int currentCol = qt.col;

            for (int i = 0; i < 4; i++) {
                int newRow = currentRow + dRow[i];
                int newCol = currentCol + dCol[i];


                if (newRow >= 0 && newRow < grid.length
                        && newCol >= 0 && newCol < grid[0].length
                        && !visited[newRow][newCol]
                        && grid[newRow][newCol] == '1') {
                    visited[newRow][newCol] = true;
                    list.add(toString(newRow - row, newCol - col));
                }
            }
        }
    }

    public static String toString(int r, int c) {
        return r + " " + c;
    }
}
