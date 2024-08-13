package Practice;


import java.util.LinkedList;
import java.util.Queue;

class PairFour {

    int row;
    int col;

    public PairFour(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
public class NumberOfIslands {

    public static void main(String[] args) {

        int[][] grid = new int[][]{

                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };

        int totalNoOfIslands = numberOfIslands(grid);
        System.out.println(totalNoOfIslands);
    }

    public static int numberOfIslands(int[][] grid) {
        int noOfIslands = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int n = grid.length;
        int m = grid[0].length;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    bfs(visited, grid, i, j);
                    noOfIslands++;
                }
            }
        }
        return noOfIslands;
    }

    public static void bfs(boolean[][] visited, int[][] grid, int row, int col) {

        Queue<PairFour> queue = new LinkedList<>();
        queue.offer(new PairFour(row, col));
        visited[row][col] = true;

        int[] deltaRow = new int[] {-1, 0, 1, 0};
        int[] deltaCol = new int[] {0, 1, 0, -1};

        while (!queue.isEmpty()) {

            PairFour pairFour = queue.poll();
            int rw = pairFour.row;
            int cl = pairFour.col;

            for(int i=0; i<4; i++) {

                int newRow = rw + deltaRow[i];
                int newCol = cl + deltaCol[i];

                if(newRow >=0 && newRow < grid.length
                        && newCol >=0 && newCol < grid[0].length
                        && grid[newRow][newCol] == 1 && !visited[newRow][newCol]){
                    visited[newRow][newCol] = true;
                    queue.offer(new PairFour(newRow, newCol));
                }
            }
        }

    }
}