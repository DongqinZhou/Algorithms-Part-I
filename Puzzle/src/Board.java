import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Objects;

public class Board {
    private int[][] Block;
    private final int N;

    public Board(int[][] blocks){
        N = blocks.length;
        Block = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                Block[i][j] = blocks[i][j];
    }           // construct a board from an n-by-n array of blocks
                // (where blocks[i][j] = block in row i, column j)
    public int dimension(){
        return N;
    }                 // board dimension n
    public int hamming(){
        int H = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if(Block[i][j] == 0)
                    continue;
                else if(Block[i][j] != (i * N + j + 1))
                    H += 1;
        return H;
    }                   // number of blocks out of place
    public int manhattan(){
        int M = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if(Block[i][j] == 0)
                    continue;
                else if(Block[i][j] != (i * N + j + 1)){
                    int i_should = (int)Math.ceil(Block[i][j] * 1.0 / N);
                    int j_should = (Block[i][j] - 1) % N + 1 ;
                    M += Math.abs(j_should - j - 1)+ Math.abs(i_should - i - 1) ;
                }
        return M;
    }                 // sum of Manhattan distances between blocks and goal
    public boolean isGoal(){
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if(Block[i][j] == 0)
                    continue;
                else if(Block[i][j] != (i * N + j + 1))
                    return false;
        return true;
    }                // is this board the goal board?
    private boolean isInboard(int i, int j){
        return (i >= 0 && i <N) && (j >= 0 && j < N);
    }
    private void exchange(int i, int j, int m, int n){
        int temp = Block[i][j];
        Block[i][j] = Block[m][n];
        Block[m][n] = temp;
    }
    public Board twin(){
        Board Twin = new Board(this.Block);
        if (Twin.Block[0][0] != 0 && Twin.Block[0][1] != 0)
            Twin.exchange(0,0,0,1);
        else
            Twin.exchange(1,0,1,1);
        return Twin;
    }                // a board that is obtained by exchanging any pair of blocks
    public boolean equals(Object y){  // https://www.sitepoint.com/implement-javas-equals-method-correctly/
        if (this == y)
            return  true;
        if (y == null)
            return  false;
        if(getClass() != y.getClass())
            return false;
        Board y_board = (Board) y;
        return Objects.equals(Block, y_board.Block);
    }        // does this board equal y?
    public Iterable<Board> neighbors(){
        ArrayList<Board> Ns = new ArrayList<Board>();
        for (int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                if (Block[i][j] != 0)
                    continue;
                else{
                    if (isInboard(i+1,j))
                    {
                        Board neighbor = new Board(this.Block);
                        neighbor.exchange(i,j,i+1,j);
                        Ns.add(neighbor);
                    }
                    if (isInboard(i,j+1))
                    {
                        Board neighbor = new Board(this.Block);
                        neighbor.exchange(i,j,i,j+1);
                        Ns.add(neighbor);
                    }
                    if (isInboard(i-1,j))
                    {
                        Board neighbor = new Board(this.Block);
                        neighbor.exchange(i,j,i-1,j);
                        Ns.add(neighbor);
                    }
                    if (isInboard(i, j-1))
                    {
                        Board neighbor = new Board(this.Block);
                        neighbor.exchange(i,j,i,j-1);
                        Ns.add(neighbor);
                    }
                }
        return  Ns;
    }     // all neighboring boards
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", Block[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }               // string representation of this board (in the output format specified below)

    public static void main(String[] args){
        int[][] arr = {{2,3,1},{4,7,8},{0,5,6}};
        Board board = new Board(arr);
        StdOut.print(board.toString());
        StdOut.println(board.isGoal());
        StdOut.println(board.hamming());
        StdOut.println(board.manhattan());
        StdOut.println(board.twin());
        StdOut.println(board.neighbors());
    } // unit tests (not graded)
}
