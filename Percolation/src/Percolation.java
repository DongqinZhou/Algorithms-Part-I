import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF Grid;
    private int N;
    private int [][] Grid_Open; // 0 = closed site

    public Percolation(int n){ // create n-by-n grid, with all sites blocked
        if (n <= 0)
            throw new IllegalArgumentException("n must be positive");
        N = n;
        Grid_Open = new int [N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                Grid_Open[i][j] = 0;
        Grid = new WeightedQuickUnionUF(N*N);
    }
    private int xyTo1D(int row, int col){  // convert 2D coordinates to 1D index
        return (row - 1) * N + col - 1;
    }
    private void validate(int p) {
        if (p < 0 || p >= N)
            throw new IllegalArgumentException("index out of range");
    }
    private boolean isInGrid(int row, int col){
        return row >= 1 && row <= N && col >= 1 && col <= N;
    }
    public void open(int row, int col){ // open site (row, col) if it is not open already
        validate(row);
        validate(col);
        Grid_Open[row-1][col-1] = 1;   // input index to be the position of each site, while the array index is one less than the position
        if(isInGrid(row, col+1) && isOpen(row, col+1))
            Grid.union(xyTo1D(row, col), xyTo1D(row, col+1));
        if(isInGrid(row, col-1) && isOpen(row, col-1))
            Grid.union(xyTo1D(row, col), xyTo1D(row, col-1));
        if(isInGrid(row-1, col) && isOpen(row-1, col))
            Grid.union(xyTo1D(row, col), xyTo1D(row-1, col));
        if(isInGrid(row+1, col) && isOpen(row+1, col))
            Grid.union(xyTo1D(row, col), xyTo1D(row+1, col));
        // connect this site with other sites around it
    }
    private boolean isOpen(int row, int col){ // is site (row, col) open?
        validate(row);
        validate(col);
        return Grid_Open[row-1][col-1] == 1;
    }
    public boolean isFull(int row, int col){ // is site (row, col) full?
        validate(row);
        validate(col);
        return isOpen(row, col) && row == 1;
    }
    public int numberOfOpenSites() { // number of open sites
        int sum = 0;
        for (int[] row : Grid_Open)
            for (int element : row)
                sum += element;

        return sum;
    }
    public boolean percolates() { // does the system percolate?

        return false;
    }

    public static void main(String[] args){  // test client (optional)

    }
}
