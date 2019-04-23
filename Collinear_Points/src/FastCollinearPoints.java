import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    //private static final Comparator<Point> By_Slope =
    private LineSegment[] LS;
    private int k = 0;
    private static Point[] points1;

    public FastCollinearPoints(Point[] points){
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
        LS = new LineSegment[numberofpoints * (numberofpoints - 1) / 2];
        points1 = new Point[points.length];
        Point[] smallends = new Point[points.length];
        for (int i = 0; i < numberofpoints; i++)
            points1[i] = points[i];
        for(int i = 0; i < numberofpoints; i++){
            /*
            double[] slopetothis = new double[numberofpoints - 1];
            for (int j = 0; j < numberofpoints; j++)
                slopetothis[j] = points[i].slopeTo(points[j]);
                */
            Arrays.sort(points, 0, numberofpoints-1, points1[i].slopeOrder());
            for (int j = 1; j < numberofpoints; j++)
                for (int m = j+1; m < numberofpoints; m++)
                {
                    if(points1[i].slopeOrder().compare(points[j], points[m]) == 0)
                        continue;
                    else if((m - j) >= 3){
                        int smallend = 0;
                        int bigend = 0;
                        for (int n = j; n < m; n++){
                            if (points[n].compareTo(points[smallend]) < 0)
                                smallend = n;
                            if (points[n].compareTo(points[bigend]) > 0)
                                bigend = n;
                        }
                        if (k == 0)
                        {
                            smallends[k] = points[smallend];
                            LS[k++] = new LineSegment(points[smallend], points[bigend]);
                        }
                        else if(checkinpoints(points[smallend], smallends) == 0) {
                            smallends[k] = points[smallend];
                            LS[k++] = new LineSegment(points[smallend], points[bigend]);
                        }
                }
                    else
                        break;
                }
        }
    }     // finds all line segments containing 4 or more points
    private int checkinpoints(Point point, Point[] points) {
        if (points == null)
            return 0;
        for (int i = 0; i < k; i++)
            if (point.compareTo(points[i]) == 0)
                return 1;
        return 0;
    }
    public           int numberOfSegments(){
        return k;
    }        // the number of line segments
    public LineSegment[] segments(){
        LineSegment[] ls = new LineSegment[k];
        int [] startpoints = new int[k];
        for (int i = 0; i < k; i++) {

            ls[i] = LS[i];
        }
        return ls;
    }                // the line segments
    /*public static void main(String[] args) {
        Point[] P = new Point[7];
        P[0] = new Point(1,1);
        P[1] = new Point(3,3);
        P[5] = new Point(2,1);
        P[3] = new Point(3,2);
        P[4] = new Point(4,3);
        P[2] = new Point(5,4);
        P[6] = new Point(2,4);

        FastCollinearPoints FCP = new FastCollinearPoints(P);
        int a = FCP.numberOfSegments();
        StdOut.println(a);
        LineSegment[] ls = FCP.segments();
        for (int i = 0; i < ls.length; i++)
            StdOut.println(ls[0].toString());
    }*/


    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
