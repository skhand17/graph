package Practice;

import java.util.*;


class PairFive {

    int row;
    int col;

    public PairFive(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class NumberOfDistinctIslands {

    public static void main(String[] args) {

        char[][] grid = new char[][]
                {{'1', '1', '0', '1', '1'},
                        {'1', '0', '0', '0', '0'},
                        {'0', '0', '0', '0', '0'},
                        {'0', '0', '0', '0', '1'},
                        {'1', '1', '0', '1', '1'}
                };

        int numberOfDistinct = numberOfDistinctIslands(grid);
        System.out.println(numberOfDistinct);


    }

    public static int numberOfDistinctIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        Set<List<String>> set = new HashSet<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    List<String> result = new ArrayList<>();
                    bfs(grid, visited, result, i, j);
                    set.add(result);
                }
            }
        }
        return set.size();
    }

    private static void bfs(char[][] grid, boolean[][] visited, List<String> result, int row, int col) {

        Queue<PairFive> queue = new LinkedList<>();
        visited[row][col] = true;

        queue.add(new PairFive(row, col));

        int[] deltaRow = new int[]{-1, 0, 1, 0};
        int[] deltaCol = new int[]{0, 1, 0, -1};

        while (!queue.isEmpty()) {

            int r = queue.peek().row;
            int c = queue.peek().col;
            queue.poll();

            for (int i = 0; i < 4; i++) {
                int newRow = r + deltaRow[i];
                int newCol = c + deltaCol[i];

                if (newRow >= 0 && newRow < grid.length
                        && newCol >= 0 && newCol < grid[0].length
                        && !visited[newRow][newCol] &&
                        grid[newRow][newCol] == '1') {
                    visited[newRow][newCol] = true;
                    queue.add(new PairFive(newRow, newCol));
                    result.add(toString(newRow - row, newCol - col));

                }
            }
        }

    }

    private static String toString(int r, int c) {
        return r + " " + c;
    }
}
