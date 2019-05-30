import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Objects;

public class Board {
    private int[][] Block;

    public Board(int[][] blocks){
        for (int i = 0; i < blocks.length; i++)
            for (int j = 0; j < blocks[i].length; j++)
                Block[i][j] = blocks[i][j];
    }           // construct a board from an n-by-n array of blocks
                // (where blocks[i][j] = block in row i, column j)
    public int dimension(){
        return Block.length;
    }                 // board dimension n
    public int hamming(){
        int H = 0;
        for (int i = 0; i < dimension(); i++)
            for (int j = 0; j < dimension(); j++)
                if(Block[i][j] == 0)
                    continue;
                else if(Block[i][j] != (i * dimension() + j + 1))
                    H += 1;
        return H;
    }                   // number of blocks out of place
    public int manhattan(){
        int M = 0;
        for (int i = 0; i < dimension(); i++)
            for (int j = 0; j < dimension(); j++)
                if(Block[i][j] == 0)
                    continue;
                else if(Block[i][j] != (i * dimension() + j + 1)){
                    int i_should = (int)Math.ceil(Block[i][j] / dimension());
                    int j_should = Block[i][j] % dimension();
                    M += Math.abs(j_should - j )+ Math.abs(i_should - i) - 2;
                }
        return M;
    }                 // sum of Manhattan distances between blocks and goal
    public boolean isGoal(){
        for (int i = 0; i < dimension(); i++)
            for (int j = 0; j < dimension(); j++)
                if(Block[i][j] == 0)
                    continue;
                else if(Block[i][j] != (i * dimension() + j + 1))
                    return false;
        return true;
    }                // is this board the goal board?
    private boolean isInboard(int i, int j){
        return (i >= 0 && i <dimension()) && (j >= 0 && j < dimension());
    }
    private void exchange(int i, int j, int m, int n){
        int temp = Block[i][j];
        Block[i][j] = Block[m][n];
        Block[m][n] = temp;
    }
    public Board twin(){  // hard code the blocks change
        Board Twin = new Board(this.Block);
        int i = 1;
        int j = 0;
        int i_twin = 0;
        int j_twin = 2;
        int temp;
        if(Twin.Block[i][j] == 0){
            j++;
            Twin.exchange(i,j,i_twin,j_twin);
            return Twin;
        }
        if (Twin.Block[i_twin][j_twin] == 0)
            j--;
            Twin.exchange(i,j,i_twin,j_twin);
            return Twin;
    }                    // a board that is obtained by exchanging any pair of blocks
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
        for (int i = 0; i < dimension(); i++)
            for(int j = 0; j < dimension(); j++)
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
    public String toString(){

    }               // string representation of this board (in the output format specified below)

    public static void main(String[] args){

    } // unit tests (not graded)
}
