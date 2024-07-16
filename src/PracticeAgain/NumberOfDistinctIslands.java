package PracticeAgain;

import java.util.*;


class TupleDistinct {
    int row;
    int col;

    public TupleDistinct(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
public class NumberOfDistinctIslands {

    public static void main(String[] args) {

        char[][] grid = new char[][]{

                {'1', '1', '0', '1', '1'},
                {'1', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
                {'0', '0', '0', '1', '1'},
                {'1', '1', '0', '1', '0'}
        };

        int distinct = distinctIslands(grid);
        System.out.println(distinct);
    }

    public static int distinctIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];

        Set<List<String>> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    List<String> list = new ArrayList<>();
                    bfsDistinct(i, j, visited, grid, list);
                    set.add(list);
                }
            }
        }

        return set.size();
    }

    private static void bfsDistinct(int row, int col, boolean[][] visited,
                                    char[][] grid, List<String> list) {

        Queue<TupleDistinct> queue = new LinkedList<>();
        visited[row][col] = true;

        queue.add(new TupleDistinct(row, col));

        int[] deltaRow = new int[]{-1, 0, 1, 0};
        int[] deltaCol = new int[]{0, 1, 0, -1};
        while (!queue.isEmpty()){
            TupleDistinct tupleDistinct = queue.poll();

            int currentRow = tupleDistinct.row;
            int currentCol = tupleDistinct.col;

            for(int i=0; i<4; i++){
                int newRow = currentRow + deltaRow[i];
                int newCol = currentCol + deltaCol[i];


                if(newRow >= 0 && newRow < grid.length
                        && newCol >=0 && newCol < grid[0].length
                        && grid[newRow][newCol] == '1' && !visited[newRow][newCol]) {
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
