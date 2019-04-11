import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args){
        RandomizedQueue<String> RQ = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty())
            RQ.enqueue(StdIn.readString());
        int i = 0;
        for (String rq : RQ) {
            StdOut.println(rq);
            i++;
            if (i == k)
                break;
        }
    }
}
