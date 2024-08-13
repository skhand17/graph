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
                {       {'1', '1', '0', '1', '1'},
                        {'1', '0', '0', '0', '0'},
                        {'0', '0', '0', '0', '0'},
                        {'0', '0', '0', '1', '1'},
                        {'1', '1', '0', '0', '1'}
                };

        int numberOfDistinct = numberOfDistinctIslands(grid);
        System.out.println(numberOfDistinct);


    }

    public static int numberOfDistinctIslands(char[][] grid) {

        Set<List<String>> set = new HashSet<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int n = grid.length;
        int m = grid[0].length;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {

                if(grid[i][j] =='1' && !visited[i][j]){
                    List<String> list = new ArrayList<>();
                    bfs(visited, list, grid, i, j);
                    set.add(list);
                }
            }
        }
        return set.size();
    }

    public static void bfs(boolean[][] visited, List<String> list, char[][] grid, int rw, int cl) {

        visited[rw][cl] = true;
        Queue<PairFive> queue = new LinkedList<>();
        queue.offer(new PairFive(rw, cl));

        int[] deltaRow = new int[]{-1, 0, 1, 0};
        int[] deltaCol = new int[]{0, 1, 0, -1};

        while (!queue.isEmpty()) {
            PairFive pairFive = queue.poll();
            int row = pairFive.row;
            int col = pairFive.col;


            for(int i=0; i<4; i++) {

                int newRow = row + deltaRow[i];
                int newCol = col + deltaCol[i];

                if(newRow >=0 && newRow < grid.length &&
                        newCol >=0 && newCol < grid[0].length &&
                        grid[newRow][newCol] == '1' && !visited[newRow][newCol]){

                    visited[newRow][newCol] = true;
                    queue.offer(new PairFive(newRow, newCol));
                    list.add(toString(newRow - row, newCol - col));
                }
            }
        }
    }

    public static String toString(int row, int col) {
        return row + " " + col;
    }

}