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

        int[][]grid = new int[][] {

                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };

        int totalNoOfIslands = numberOfIslands(grid);
        System.out.println(totalNoOfIslands);
    }

    public static int numberOfIslands(int[][]grid){

        int n = grid.length;
        int m = grid[0].length;
        boolean[][]visited = new boolean[n][m];
        int count = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    bfs(i, j, visited, grid);
                    count++;
                }
            }
        }
        return count;
    }

    private static void bfs(int row, int col, boolean[][] visited, int[][] grid) {

        Queue<PairFour> queue = new LinkedList<>();
        visited[row][col] = true;

        queue.add(new PairFour(row, col));
        int[] deltaRow = new int[] {-1, 0, 1, 0};
        int[] deltaCol = new int[]{0, 1, 0, -1};

        while (!queue.isEmpty()) {

            int r = queue.peek().row;
            int c =  queue.peek().col;

            queue.poll();

            for(int i=0; i<4; i++){
                int newRow = r + deltaRow[i];
                int newCol = c + deltaCol[i];


                if(newRow >=0 && newRow < grid.length &&
                        newCol >=0 && newCol < grid[0].length &&
                        !visited[newRow][newCol] && grid[newRow][newCol] == 1) {
                    visited[newRow][newCol] = true;
                    queue.add(new PairFour(newRow, newCol));
                }

            }
        }

    }

}
