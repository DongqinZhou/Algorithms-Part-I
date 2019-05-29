public class Board {
    public Board(int[][] blocks){

    }           // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public int dimension(){
        return 0;
    }                 // board dimension n
    public int hamming(){
        return 0;
    }                   // number of blocks out of place
    public int manhattan(){
        return 0;
    }                 // sum of Manhattan distances between blocks and goal
    public boolean isGoal(){
        return false;
    }                // is this board the goal board?
    //public Board twin(){

    //}                    // a board that is obtained by exchanging any pair of blocks
    public boolean equals(Object y){
        return true;
    }        // does this board equal y?
    //public Iterable<Board> neighbors(){

    //}     // all neighboring boards
    //public String toString()               // string representation of this board (in the output format specified below)

    public static void main(String[] args){
        
    } // unit tests (not graded)
}
