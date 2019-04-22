import edu.princeton.cs.algs4.StdOut;

public class LineSegment {
    private final Point p;   // one endpoint of this line segment
    private final Point q;   // the other endpoint of this line segment

    public LineSegment(Point p, Point q) {
        if (p == null || q == null) {
            throw new NullPointerException("argument is null");
        }
        this.p = p;
        this.q = q;
    }
    public void draw() {
        p.drawTo(q);
    }
    public String toString() {
        return p + " -> " + q;
    }
    public int hashCode() {
        throw new UnsupportedOperationException();
    }
    public static void main(String[] args){
        Point P1 = new Point(4,5);
        Point P2 = new Point(6,5);
        LineSegment LS = new LineSegment(P1, P2);
        StdOut.println(LS.toString());
    }
}