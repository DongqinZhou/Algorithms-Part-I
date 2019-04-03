public class Percolation {
    private int N;  // number of sites in an edge
    private int[][] Grid = new int[N][N]; // block site = 0, what about using 1-d array

    public Percolation(int n){ // create n-by-n grid, with all sites blocked
        N = n;
        if (n <= 0)
            throw new IllegalArgumentException("n must be positive");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                Grid[i][j] = 0;
    }
    public void open(int row, int col){ // open site (row, col) if it is not open already
        if (row > N | row < 1 | col > N | col < 1)
            throw new IllegalArgumentException("index out of range");
        Grid[row-1][col-1] = 1;   // input index to be the position of each site, while the array index is one less than the position
        // connect this site with other sites around it
    }
    public boolean isOpen(int row, int col){ // is site (row, col) open?
        if (row > N | row < 1 | col > N | col < 1)
            throw new IllegalArgumentException("index out of range");
        return Grid[row-1][col-1] == 1;
    }
    public boolean isFull(int row, int col){ // is site (row, col) full?
        if (row > N | row < 1 | col > N | col < 1)
            throw new IllegalArgumentException("index out of range");

        return isOpen(row, col) & row == 1;
    }
    public int numberOfOpenSites() { // number of open sites
        int sum = 0;
        for (int[] row : Grid)
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
