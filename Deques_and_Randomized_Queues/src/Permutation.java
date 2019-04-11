import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args){
        RandomizedQueue<String> RQ = new RandomizedQueue<>();
        StdOut.println("Please input the number of strings for input:");
        int n = StdIn.readInt();
        StdOut.println("Please input the strings:");
        while (!StdIn.isEmpty()){
            String input = StdIn.readString();
            RQ.enqueue(input);
        }
        StdOut.println("Please input the number of strings for output (k <= n):");
        int k = StdIn.readInt();
        while (k > n){
            StdOut.println("Please input the number of strings for output (k <= n):");
            k = StdIn.readInt();
        }
        for (int j = 0; j < k; j++) {
            StdOut.println(RQ.sample());
        }
    }
}
