package PracticeAgain;

public class FloodFill {

    public static void main(String[] args) {

        int[][]image = new int[][]{
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        int[][] ans = floodFill(image, 1, 1, 2);

        for(int i=0; i<ans.length; i++){
            for(int j=0; j<ans[0].length; j++){
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static int[][] floodFill(int[][] image, int sourceRow, int sourceCol, int destColor) {

        int currentColor = image[sourceRow][sourceCol];
        int[][] ans = image;
        int[] deltaRow = new int[]{-1, 0, 1, 0};
        int[] deltaCol = new int[]{0, 1, 0, -1};

        dfsFloodFill(image, ans, deltaRow, deltaCol, sourceRow, sourceCol, destColor, currentColor);
        return ans;

    }

    private static void dfsFloodFill(int[][] image, int[][] ans, int[] deltaRow, int[] deltaCol,
                                     int sourceRow,
                                     int sourceCol, int destColor, int currentColor) {

        ans[sourceRow][sourceCol] = destColor;

        for(int i=0; i<4; i++){
            int newRow = sourceRow + deltaRow[i];
            int newCol = sourceCol + deltaCol[i];

            if(newRow >=0 && newRow < image.length
                    && newCol >=0 && newCol < image[0].length
                    && ans[newRow][newCol] != destColor && image[newRow][newCol] == currentColor){
                dfsFloodFill(image, ans, deltaRow, deltaCol, newRow, newCol, destColor, currentColor);

            }
        }
    }
}
