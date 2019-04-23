import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private LineSegment[] LS;
    private int k = 0;

    public BruteCollinearPoints(Point[] points){
        if (points == null)
            throw new IllegalArgumentException("points cannot be null");
        int numberofpoints = points.length;
        for (int i = 0; i < numberofpoints; i++)
            if (points[i] == null)
                throw new IllegalArgumentException("null point");
        for (int i = 0; i < numberofpoints; i++)
            for (int j = i + 1; j < numberofpoints; j++)
                if (points[j].compareTo(points[i]) == 0)
                    throw new IllegalArgumentException("repeated points");
        int numberofallsegments = numberofpoints * (numberofpoints - 1) / 2;
        LS = new LineSegment[numberofallsegments];
        for (int i = 0; i < numberofpoints; i++) {
            for (int j = i + 1; j < numberofpoints; j++) {
                for (int m = j + 1; m < numberofpoints; m++) {
                    for (int n = m + 1; n < numberofpoints; n++){
                        if (points[i].slopeOrder().compare(points[j], points[m]) == 0 && points[i].slopeOrder().compare(points[j], points[n]) == 0){
                            int smallend = 0;
                            int bigend = 0;

                            if (!(points[i].compareTo(points[j]) > 0) && !(points[i].compareTo(points[m]) > 0) && !(points[i].compareTo(points[n]) > 0))
                                smallend = i;
                            if (!(points[j].compareTo(points[i]) > 0) && !(points[j].compareTo(points[m]) > 0) && !(points[j].compareTo(points[n]) > 0))
                                smallend = j;
                            if (!(points[m].compareTo(points[i]) > 0) && !(points[m].compareTo(points[j]) > 0) && !(points[m].compareTo(points[n]) > 0))
                                smallend = m;
                            if (!(points[n].compareTo(points[j]) > 0) && !(points[n].compareTo(points[m]) > 0) && !(points[n].compareTo(points[i]) > 0))
                                smallend = n;

                            if (!(points[i].compareTo(points[j]) < 0) && !(points[i].compareTo(points[m]) < 0) && !(points[i].compareTo(points[n]) < 0))
                                bigend = i;
                            if (!(points[j].compareTo(points[i]) < 0) && !(points[j].compareTo(points[m]) < 0) && !(points[j].compareTo(points[n]) < 0))
                                bigend = j;
                            if (!(points[m].compareTo(points[i]) < 0) && !(points[m].compareTo(points[j]) < 0) && !(points[m].compareTo(points[n]) < 0))
                                bigend = m;
                            if (!(points[n].compareTo(points[j]) < 0) && !(points[n].compareTo(points[m]) < 0) && !(points[n].compareTo(points[i]) < 0))
                                bigend = n;

                            LS[k++] = new LineSegment(points[smallend], points[bigend]);
                        }
                    }
                }
            }
        }
    }    // finds all line segments containing 4 points
    public           int numberOfSegments(){
        return k;
    }        // the number of line segments
    public LineSegment[] segments(){
        LineSegment[] ls = new LineSegment[k];
        for (int i = 0; i < k; i++)
            ls[i] = LS[i];
        return ls;
    }                // the line segments
    public static void main(String[] args){
        Point[] P = new Point[7];
        P[0] = new Point(1,2);
        P[1] = new Point(3,4);
        P[2] = new Point(5,6);
        P[3] = new Point(7,8);
        P[4] = new Point(9,10);
        P[5] = new Point(11,12);
        P[6] = new Point(13,15);

        BruteCollinearPoints BCP = new BruteCollinearPoints(P);
        int a = BCP.numberOfSegments();
        StdOut.println(a);
    }
}
