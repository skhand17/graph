package Practice;

public class FloodFill {

    public static void main(String[] args) {

        int[][]image = new int[][] {

                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        int sourceRow = 1;
        int sourceCol = 1;
        int newColor = 2;

        int[][] ans = floodfill(image, sourceRow, sourceCol, newColor);
        for(int i=0; i<ans.length; i++){
            for(int j=0; j<ans[0].length; j++){
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] floodfill(int[][]image, int sourceRow, int sourceCol, int newColor) {

        int initialColor = image[sourceRow][sourceCol];
        int[][] ans = image;

        int[] deltaRow = new int[]{-1, 0, 1, 0};
        int[] deltaCol = new int[]{0, 1, 0, -1};

        dfs(sourceRow, sourceCol, image, newColor, deltaRow, deltaCol, ans, initialColor);
        return ans;


    }

    private static void dfs(int sourceRow, int sourceCol, int[][] image,
                            int newColor, int[] deltaRow, int[] deltaCol, int[][] ans, int initialColor) {

        ans[sourceRow][sourceCol] = newColor;
        int n = image.length;
        int m = image[0].length;

        for(int i=0; i<4; i++){
            int newRow = sourceRow + deltaRow[i];
            int newCol = sourceCol + deltaCol[i];

            if(newRow >=0 && newRow < n && newCol >=0 && newCol < m &&
                    image[newRow][newCol] == initialColor && ans[newRow][newCol] != newColor){
                dfs(newRow, newCol, image, newColor, deltaRow, deltaCol, ans, initialColor);
            }
        }
    }
}
