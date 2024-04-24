import java.util.LinkedList;
import java.util.Queue;

class PairTwo {
    int row;
    int col;

    public PairTwo(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class NumberOfIslands {


    public static void main(String[] args) {

        char[][] grid = new char[][]
                {{'0', '1', '1', '0'},
                        {'0', '1', '1', '0'},
                        {'0', '0', '1', '0'},
                        {'0', '0', '0', '0'},
                        {'1', '1', '0', '1'}
                };

        int numberIslands = numberOfIslands(grid);
        System.out.println(numberIslands);
    }

    public static int numberOfIslands(char[][] grid) {

        int row = grid.length;
        int col = grid[0].length;
        int count = 0;

        /*
         * To keep track of visited boolean
         *
         * */
        boolean[][] visited = new boolean[row][col];
        /*
         * This is done to find the starting points if we have unconnected components.
         * The moment we find one then we run BFS using the starting point
         *
         * */

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    bfs(i, j, visited, grid);
                    count++;
                }
            }
        }
        return count;
    }

    private static void bfs(int r, int c, boolean[][] visited, char[][] grid) {

        Queue<PairTwo> queue = new LinkedList<>();
        visited[r][c] = true;

        queue.add(new PairTwo(r, c));

        while (!queue.isEmpty()) {

            int currentRow = queue.peek().row;
            int currentCol = queue.peek().col;
            queue.poll();
            /*
             *
             * For each element popped out from the queue we try to find its all neighbours in all four directions
             * After getting a newRow and a newCol we see whether that position is valid or not by checking whether
             * it is greater than 0 and less than the range of either row or column and check if it has not been visited
             * && we will only add those positions in the queue if those elements have not been visited
             *
             * */
            for (int dRow = -1; dRow <= 1; dRow++) {
                for (int dCol = -1; dCol <= 1; dCol++) {

                    int nRow = currentRow + dRow;
                    int nCol = currentCol + dCol;

                    if (nRow >= 0 && nRow < grid.length &&
                            nCol >= 0 && nCol < grid[0].length &&
                            !visited[nRow][nCol] &&
                            grid[nRow][nCol] == '1') {

                        queue.add(new PairTwo(nRow, nCol));
                        visited[nRow][nCol] = true;
                    }
                }
            }
        }

    }
}
