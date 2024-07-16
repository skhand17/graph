package PracticeOracleAndC3AI;

public class FloodFill {

    public static void main(String[] args) {
        int[][] image = new int[][] {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        int[][] ans = floodFill(image, 1, 1, 2);

        for(int i=0; i<ans.length; i++) {
            for(int j=0; j<ans[0].length; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int destColor) {


        int[][] ans = image;
        int sourceColor = image[sr][sc];
        int[] dRow = new int[] {-1, 0, 1, 0};
        int[] dCol = new int[] {0, 1, 0, -1};
        dfsFloodFill(image, ans, sr, sc, destColor, dRow, dCol, sourceColor);
        return ans;

    }

    public static void dfsFloodFill(int[][] image, int[][] ans, int sr,
                                    int sc, int destColor, int[] dRow,
                                    int[] dCol, int sourceColor) {

        ans[sr][sc] = destColor;
        for(int i=0; i<4; i++) {
            int newRow = sr + dRow[i];
            int newCol = sc + dCol[i];

            if(newRow >= 0 && newRow < image.length
                    && newCol >=0 && newCol < image[0].length
                    && image[newRow][newCol] == sourceColor && ans[newRow][newCol] != destColor){
                dfsFloodFill(image, ans, newRow, newCol, destColor, dRow, dCol, sourceColor);
            }
        }

    }
}
