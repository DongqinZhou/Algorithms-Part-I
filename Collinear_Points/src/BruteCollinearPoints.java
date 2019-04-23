import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private LineSegment[] LS;
    private int k = 0;
    private int numberofpoints;
    private Point[] allpoints;

    public BruteCollinearPoints(Point[] points){
        if (points == null)
            throw new IllegalArgumentException("points cannot be null");
        allpoints = points;
        numberofpoints = points.length;
        int numberofallsegments = numberofpoints * (numberofpoints - 1) / 2;
        LS = new LineSegment[numberofallsegments];
        for (int i = 0; i < numberofpoints; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("null point");
            for (int j = i + 1; j < numberofpoints; j++) {
                if(points[j] == null)
                    throw new IllegalArgumentException("null point");
                if (points[j].compareTo(points[i]) == 0)
                    throw new IllegalArgumentException("repeated points");
                for (int m = j + 1; m < numberofpoints; m++) {
                    if (points[m] == null)
                        throw new IllegalArgumentException("null point");
                    if (points[m].compareTo(points[i]) == 0 || points[m].compareTo(points[j]) == 0)
                        throw new IllegalArgumentException("repeated points");
                    for (int n = m + 1; n < numberofpoints; n++){
                        if (points[n] == null)
                            throw new IllegalArgumentException("null point");
                        if (points[n].compareTo(points[i]) == 0 || points[n].compareTo(points[j]) == 0 || points[n].compareTo(points[m]) == 0)
                            throw new IllegalArgumentException("repeated points");
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[m]) && points[i].slopeTo(points[j]) == points[i].slopeTo(points[n]))
                            LS[k++] = new LineSegment(points[i], points[j]);
                    }
                }
            }
        }
    }    // finds all line segments containing 4 points
    public           int numberOfSegments(){
        return LS.length;
    }        // the number of line segments
    public LineSegment[] segments(){
        return null;
    }                // the line segments
    public static void main(String[] args){
        Point[] P = new Point[4];
        P[0] = new Point(1,2);
        P[1] = new Point(3,4);
        P[2] = new Point(5,6);
        P[3] = new Point(7,8);
        BruteCollinearPoints BCP = new BruteCollinearPoints(P);
        int a = BCP.numberOfSegments();
        StdOut.println(a);
    }
}
