import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import javax.crypto.SealedObject;
import java.util.ArrayList;
import java.util.Comparator;

public class Solver {
    private int Moves;
    private int Moves_twin;
    private SearchNode Solution;
    private Board Initial_Board;

    private class SearchNode implements Comparable<SearchNode> {
        private SearchNode pred;
        private int moves;
        private Board curr;

        public SearchNode(SearchNode P, Board C, int M){
            this.curr = C;
            this.moves = M;
            this.pred = P;
        }
        public int Manhattan(){
            return curr.manhattan() + moves;
        }
        public int compareTo(SearchNode that){
            if (this.curr.equals(that.curr))
                return 0;
            if (this.Manhattan() < that.Manhattan())
                return -1;
            else
                return 1;
        }
    }
    public Solver(Board initial){
        Initial_Board = initial;
        Moves = 0;
        Moves_twin = 0;
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> pq_twin = new MinPQ<SearchNode>();
        SearchNode Initial = new SearchNode(null, initial, 0);
        SearchNode Initial_twin = new SearchNode(null, initial.twin(), 0);
        pq.insert(Initial);
        pq_twin.insert(Initial_twin);
        while (true) {
            SearchNode Min = pq.delMin();
            SearchNode Min_twin = pq_twin.delMin();
            if (Min.curr.isGoal()){
                Moves_twin += 100;
                break;
            }
            if (Min_twin.curr.isGoal()){
                Moves += 100;
                break;
            }
            Moves += 1;
            Moves_twin += 1;
            for (Board b : Min.curr.neighbors()) {
                if (b.equals(Min.pred))
                    continue;
                SearchNode Curr = new SearchNode(Min, b, Moves);
                pq.insert(Curr);
            }
            for (Board b : Min_twin.curr.neighbors()) {
                if (b.equals(Min_twin.pred))
                    continue;
                SearchNode Curr = new SearchNode(Min_twin, b, Moves_twin);
                pq_twin.insert(Curr);
            }
        }
        if (Moves < Moves_twin)
            Solution = pq.min();
        else
            Solution = pq_twin.min();
    }           // find a solution to the initial board (using the A* algorithm)
    public boolean isSolvable(){
        if (Moves_twin < Moves)
            return false;
        else
            return true;
    }            // is the initial board solvable?
    public int moves(){
        if (Moves < Moves_twin)
            return Moves;
        else
            return -1;
    }                     // min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution(){
        ArrayList<Board> S = new ArrayList<Board>();
        SearchNode s = Solution;
        while (!s.curr.equals(Initial_Board)){
            S.add(s.curr);
            s = s.pred;
        }
        return S;
    }      // sequence of boards in a shortest solution; null if unsolvable
    public static void main(String[] args){
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    } // solve a slider puzzle (given below)
}
