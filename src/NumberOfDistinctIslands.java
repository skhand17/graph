import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class NumberOfDistinctIslands {

    public static void main(String[] args) {

        char[][] grid = new char[][]
                {       {'1', '1', '0', '1', '1'},
                        {'1', '0', '0', '0', '0'},
                        {'0', '0', '0', '0', '0'},
                        {'0', '0', '0', '0', '1'},
                        {'1', '1', '0', '1', '1'}
                };

        int numberOfDistinctIslands = numberOfConnectedComponents(grid);
        System.out.println(numberOfDistinctIslands);
    }

    public static int numberOfConnectedComponents(char[][] grid) {

        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        HashSet<ArrayList<String>> st = new HashSet<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    ArrayList<String> stringArrayList = new ArrayList<>();
                    bfs(grid, i, j, stringArrayList, visited);
                    st.add(stringArrayList);
                }
            }
        }
        return st.size();
    }

    private static String toString(int r, int c){
        return r + " " + c;
    }

    public static void bfs(char[][] grid, int row, int col, ArrayList<String> stringArrayList, boolean[][] visited) {

        Queue<PairTwo> queue = new LinkedList<>();
        visited[row][col] = true;

        queue.add(new PairTwo(row, col));
//        stringArrayList.add(toString(0 , 0));

        while(!queue.isEmpty()){
            int r = queue.peek().row;
            int c = queue.peek().col;

            queue.poll();
            for(int dRow = -1; dRow <= 1; dRow++){
                for(int dCol = -1; dCol <=1; dCol++){
                    int nRow = r +  dRow;
                    int nCol = c +  dCol;


                    if(nRow >=0 && nRow < grid.length &&
                            nCol >=0 && nCol < grid[0].length &&
                            !visited[nRow][nCol] && grid[nRow][nCol] == '1'){
                        queue.add(new PairTwo(nRow, nCol));
                        visited[nRow][nCol] = true;
                        stringArrayList.add(toString(nRow - row, nCol - col));
                    }
                }
            }
        }
    }
}
