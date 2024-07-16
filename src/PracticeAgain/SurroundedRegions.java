package PracticeAgain;

public class SurroundedRegions {

    public static void main(String[] args) {

        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'X', 'O', 'O'}
        };

        solveSurroundedRegions(board);

        for(int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static char[][] solveSurroundedRegions(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        int[][]visited = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                visited[i][j] = 0;
            }
        }

        int[] deltaRow = new int[]{-1, 0, 1, 0};
        int[] deltaCol = new int[]{0, 1, 0, -1};

//        Traverse through the boundaries

//        traversing first row and last row

        for(int j=0; j<m; j++){
            if(visited[0][j] == 0 && board[0][j] == 'O'){
                dfs(visited, board, deltaRow, deltaCol, 0, j);
            }

            if(visited[n-1][j] == 0 && board[n-1][j] == 'O'){
                dfs(visited, board, deltaRow, deltaCol, n-1, j);
            }
        }

//        traversing first column and last column
        for(int i=0; i<n; i++){
            if(visited[i][0] == 0 && board[i][0] == 'O'){
                dfs(visited, board, deltaRow, deltaCol, i, 0);
            }

            if(visited[i][m-1] == 0 && board[i][m-1] == 'O'){
                dfs(visited, board, deltaRow, deltaCol, i, m-1);
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(visited[i][j] == 0 && board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }

        return board;
    }

    private static void dfs(int[][] visited, char[][] board,
                            int[] deltaRow, int[] deltaCol, int i, int j) {

        visited[i][j] = 1;
        for(int k=0; k<4; k++){
            int newRow = i + deltaRow[k];
            int newCol = j + deltaCol[k];

            if(newRow >=0 && newRow < board.length &&
                newCol >=0 && newCol < board[0].length &&
                visited[newRow][newCol] == 0 && board[newRow][newCol] == 'O'){
                dfs(visited, board, deltaRow, deltaCol, newRow, newCol);
            }
        }
    }
}
