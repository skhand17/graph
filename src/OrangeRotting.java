import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int row;
    int col;
    int time;

    public Pair(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}


public class OrangeRotting {


    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 1, 2},
                {0, 1, 1},
                {2, 1, 1}};

        int minimumTime = orangeRotting(grid);
        System.out.println(minimumTime);

    }

    public static int orangeRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        System.out.println("Row length :" + n);
        System.out.println("Column Length: " + m);


        /*
         * Queue will be storing a pair of row, col, and initial time
         *
         * */
        Queue<Pair> queue = new LinkedList<>();

        /*
         *
         * Let's start with the initial configuration
         *
         * */
        int currentFreshOranges = 0;
        int[][] visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == 2) {
                    visited[i][j] = 2;
                    queue.add(new Pair(i, j, 0));
                } else {
                    visited[i][j] = 0;
                }

                if (grid[i][j] == 1) {
                    currentFreshOranges++;
                }
            }
        }

        System.out.println("Current Fresh Oranges : " + currentFreshOranges);

        int time = 0;
        int[] dRow = new int[]{-1, 0, +1, 0};
        int[] dCol = new int[]{0, +1, 0, -1};

        int count = 0;


        while (!queue.isEmpty()) {

            int row = queue.peek().row;
            int col = queue.peek().col;
            int tm = queue.peek().time;
            time = Math.max(time, tm);
            queue.poll();

            for (int i = 0; i < 4; i++) {
                int newRow = row + dRow[i];
                int newCol = col + dCol[i];
                /*
                * Here we check if new rows and new columns are within the range
                * We also check if they are not already visited
                * We also check if the grid has fresh oranges
                * */
                if (newRow >= 0 && newRow < n && newCol >= 0
                        && newCol < m && visited[newRow][newCol] == 0 &&
                            grid[newCol][newCol] == 1) {

                    queue.add(new Pair(newRow, newCol,tm+1));
                    visited[newRow][newCol] = 2;
                    count++;
                }
            }
        }

        if(count != currentFreshOranges) return -1;
        return time;
    }
}
