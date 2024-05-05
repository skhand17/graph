/*
*
* You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
* */

/*
*
* This Question has to be understood again
*
* */

import java.util.PriorityQueue;

class Tuple {
    int distance;
    int row;
    int col;

    public Tuple(int distance, int row, int col) {
        this.distance = distance;
        this.row = row;
        this.col = col;
    }
}

public class PathWithMinimumEffort {

    public static void main(String[] args) {
        int[][] heights = new int[][] {
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };

        int minimum = minimumEffort(heights);
        System.out.println(minimum);
    }

    public static int minimumEffort(int heights[][]) {


        PriorityQueue<Tuple> priorityQueue = new PriorityQueue<>((x, y) -> x.distance - y.distance);
        int n = heights.length;
        int m = heights[0].length;

        int[][]dist = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                dist[i][j] = (int)1e9;
            }
        }

        dist[0][0] = 0;
        priorityQueue.add(new Tuple(0 , 0, 0));

        while (!priorityQueue.isEmpty()) {
            int diff = priorityQueue.peek().distance;
            int row = priorityQueue.peek().row;
            int col = priorityQueue.peek().col;

            priorityQueue.poll();

            if(row == n-1 && col == m-1)
                return diff;

            int[]dRow = new int[] {-1, 0, +1, 0};
            int[]dCol = new int[] {0, +1, 0, -1};

            for(int i=0; i<4; i++){
                int nRow = row + dRow[i];
                int nCol = col + dCol[i];

                if(nRow >=0 && nRow < n && nCol >= 0 && nCol < m) {
                    int newEffort = Math.max(Math.abs(heights[row][col] -  heights[nRow][nCol]), diff);

                    if(newEffort < dist[nRow][nCol]){
                        dist[nRow][nCol] = newEffort;
                        priorityQueue.add(new Tuple(newEffort, nRow, nCol));
                    }
                }
            }
        }
        return 0;
    }
}
