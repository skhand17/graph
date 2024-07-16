package Practice;

public class SurroundedRegions {

    public static void main(String[] args) {

        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X', 'O'},
                {'X', 'X', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'X'},
                {'O', 'O', 'X', 'X', 'X'}
        };

        char[][]board1 = surroundedRegions(board);

        for(int i=0; i<board1.length; i++){
            for(int j=0; j<board1[0].length; j++){
                System.out.print(board1[i][j] +  " ");
            }
            System.out.println();
        }



    }

    public static char[][] surroundedRegions(char[][] board) {

        int n = board.length;
        int m = board[0].length;

        int[][] visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = 0;
            }
        }

        int[] deltaRow = new int[]{-1, 0, 1, 0};
        int[] deltaCol = new int[]{0, 1, 0, -1};

        /*Traverse the First row and last row*/
        for(int j=0; j<m; j++){
            if(visited[0][j] == 0 && board[0][j] =='O'){
                dfs(visited, board, 0, j, deltaRow, deltaCol);
            }
            if(visited[n-1][j] == 0 && board[n-1][j] == 'O'){
                dfs(visited, board, n-1, j, deltaRow, deltaCol);
            }
        }

        /*Traverse the First and the Last column*/
        for(int i=0; i<n; i++){
            if(visited[i][0] == 0 && board[i][0] == 'O'){
                dfs(visited, board, i, 0, deltaRow, deltaCol);
            }
            if(visited[i][m-1] == 0 && board[i][m-1] == 'O'){
                dfs(visited, board, i, m-1, deltaRow, deltaCol);
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(visited[i][j]== 0 && board[i][j] =='O'){
                    board[i][j] = 'X';
                }
            }
        }

        return board;
    }


    public static void dfs(int[][] visited, char[][] board, int row,
                    int col, int[] deltaRow, int[] deltaCol) {
        visited[row][col] = 1;
        for (int i = 0; i < 4; i++) {
            int newRow = row + deltaRow[i];
            int newCol = col + deltaCol[i];
            if (newRow >= 0 && newRow < board.length &&
                    newCol >= 0 && newCol < board[0].length &&
                    visited[newRow][newCol] == 0 &&
                    board[newRow][newCol] == 'O') {
                dfs(visited, board, newRow, newCol, deltaRow, deltaCol);
            }
        }
    }

}
