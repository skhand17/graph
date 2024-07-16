package PracticeOracleAndC3AI;

public class SurroundedRegionsDFS {

    public static void main(String[] args) {

        char[][] grid = new char[][] {
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        };

        surroundedRegion(grid);

        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void surroundedRegion(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] visited = new int[n][m];

        int[] deltaRow = new int[]{-1, 0, 1, 0};
        int[] deltaCol = new int[]{0, 1, 0, -1};

//        Traverse the first and the last row and run dfs

        for(int j=0; j<m; j++) {
            if(grid[0][j] == 'O' && visited[0][j] == 0){
                dfsSurroundedRegion(visited, grid, 0, j, deltaRow, deltaCol);
            }
            if(grid[n-1][j] =='O' && visited[n-1][j] == 0){
                dfsSurroundedRegion(visited, grid, n-1, j, deltaRow, deltaCol);
            }
        }

//        Traverse the first and the last column and run dfs

        for(int i=0; i<n; i++) {
            if(grid[i][0] == 'O' && visited[i][0] == 0){
                dfsSurroundedRegion(visited, grid, i, 0, deltaRow, deltaCol);
            }

            if(grid[i][m-1] == 'O' && visited[i][m-1] == 0){
                dfsSurroundedRegion(visited, grid, i, m-1, deltaRow, deltaCol);
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(visited[i][j] == 0 && grid[i][j] == 'O'){
                    grid[i][j] = 'X';
                }
            }
        }
    }

    private static void dfsSurroundedRegion(int[][] visited,
                                            char[][] grid,
                                            int row, int col, int[] deltaRow, int[] deltaCol) {
        visited[row][col] = 1;
        for(int i=0; i<4; i++) {
            int newRow = row + deltaRow[i];
            int newCol = col + deltaCol[i];

            if(newRow >=0 && newRow <grid.length
                    && newCol >=0 && newCol < grid[0].length &&
                    grid[newRow][newCol] == 'O' && visited[newRow][newCol] == 0){
                dfsSurroundedRegion(visited, grid, newRow, newCol, deltaRow, deltaCol);
            }

        }
    }
}
