import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int T;
    private double [] X;

    public PercolationStats(int n, int trials){ // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0)
            throw  new IllegalArgumentException("n or trials must be positive");
        T = trials;
        X = new double[T];
        for (int i = 0; i < T; i++){
            Percolation instance = new Percolation(n);
            while(!instance.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                instance.open(row, col);
            }
            double x = 1.0 * instance.numberOfOpenSites() / (n * n);
            X[i] = x;
        }
    }
    public double mean(){
        return StdStats.mean(X); // sample mean of percolation threshold
    }
    public double stddev(){
        return StdStats.stddev(X);
    }                        // sample standard deviation of percolation threshold
    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }                  // low  endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }                  // high endpoint of 95% confidence interval
    public static void main(String[] args){ // test client (described below)
        StdOut.println("Please input integer n for a n * n grid:");
        int n = StdIn.readInt();
        StdOut.println("Please input integer T for the number of trials:");
        int T = StdIn.readInt();
        PercolationStats test = new PercolationStats(n, T);
        double X_mean = test.mean();
        double X_stddev = test.stddev();
        double X_confiLo = test.confidenceLo();
        double X_confiHi = test.confidenceHi();
        StdOut.println("mean" + "=" + X_mean);
        StdOut.println("stddev" + "=" + X_stddev);
        StdOut.println("95% confidence interval" + "=" + "[" + X_confiLo + "," + X_confiHi + "]");
    }
}
