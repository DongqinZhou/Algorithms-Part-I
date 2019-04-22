import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y){
        assert (x >= 0 && x <= 32767);
        assert (y >= 0 && y <= 32767);
        this.x = x;
        this.y = y;
    }                         // constructs the point (x, y)
    public   void draw(){
        StdDraw.point(x, y);
    }                               // draws this point
    public   void drawTo(Point that){
        StdDraw.line(this.x, this.y, that.x, that.y);
    }                   // draws the line segment from this point to that point
    public String toString(){
        return "(" + x + ", " + y + ")";
    }                           // string representation
    public               int compareTo(Point that){
        if (this.y < that.y)
            return -1;
        if (this.y == that.y && this.x < that.x)
            return -1;
        if (this.y == that.y && this.x == that.x)
            return 0;
        return +1;
    }     // compare two points by y-coordinates, breaking ties by x-coordinates
    public            double slopeTo(Point that){
        if (this.compareTo(that) == 0)
            return Double.NEGATIVE_INFINITY;
        if (this.y == that.y)
            return +0;
        if (this.x == that.x)
            return Double.POSITIVE_INFINITY;
        return (that.y - this.y) / (that.x - this.x);
    }       // the slope between this point and that point
    public Comparator<Point> slopeOrder(){
        return new SlopeOrder();
    }// compare two points by slopes they make with this point
    private class SlopeOrder implements Comparator<Point>{
        @Override
        public int compare(Point o1, Point o2) {
            if (slopeTo(o1) < slopeTo(o2))
                return -1;
            if (slopeTo(o1) > slopeTo(o2))
                return +1;
            return 0;
        }
    }
    public static void main(String[] args){
        Point P1 = new Point(5,4);
        Point P2 = new Point(8,7);
        Point P3 = new Point(5,7);
        //P1.draw();
        //P1.drawTo(P2);
        double P1P2 = P1.slopeTo(P2);
        double P1P3 = P1.slopeTo(P3);
        StdOut.println(P1P2);
        StdOut.println(P1P3);
        int P2P3 = P1.slopeOrder().compare(P3,P2);
        StdOut.println(P2P3);
    }
}
