import com.sun.security.jgss.GSSUtil;

public class FindTheCityWithTheSmallestNumberOfNeighbours {

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0, 1, 3},
                {1, 2, 1},
                {1, 3, 4},
                {2, 3, 1}
        };

        int nodes = 4;
        int threshold = 4;
        int resultantCity = cityWithSmallesNeighbors(edges, nodes, threshold);
        System.out.println(resultantCity);
    }

    public static int cityWithSmallesNeighbors(int[][] edges, int nodes, int threshold) {

        int n = edges.length;
        int[][] matrix = new int[n][n];

        /*
         * Initial Matrix prepared with all values as infinity
         *
         * */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) 1e9;
            }
        }

        for (int i = 0; i < n; i++) {

            int u = edges[i][0];
            int v = edges[i][1];
            int weight = edges[i][2];

            matrix[u][v] = weight;
            matrix[v][u] = matrix[u][v];
        }


        for (int i = 0; i < n; i++) {
            matrix[i][i] = 0;
        }

        /*
         * Floyd warshall algorithm
         * */

        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][via] + matrix[via][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        int cityNo = -1;
        int countMax = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int currentCount = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] <= threshold)
                    currentCount++;
            }
            if (currentCount <= countMax){
                countMax = currentCount;
                cityNo = i;
            }

        }

        return cityNo;
    }
}
