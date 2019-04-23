import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    //private static final Comparator<Point> By_Slope =
    private LineSegment[] LS;
    private int k = 0;

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
        for(int i = 0; i < numberofpoints; i++){
            /*
            double[] slopetothis = new double[numberofpoints - 1];
            for (int j = 0; j < numberofpoints; j++)
                slopetothis[j] = points[i].slopeTo(points[j]);
                */
            Arrays.sort(points, 0, numberofpoints-1, points[i].slopeOrder());
            for (int j = i+1; j < numberofpoints; j++)
                for (int m = j+1; m < numberofpoints; m++)
                {
                    if(points[i].slopeOrder().compare(points[j], points[m]) == 0)
                        continue;
                    else if((m - j) >= 3){
                        int smallend = i;
                        int bigend = i;
                        for (int n = j; n < m; n++){
                            if (points[n].compareTo(points[smallend]) < 0)
                                smallend = n;
                            if (points[n].compareTo(points[bigend]) > 0)
                                bigend = n;
                        }
                        LS[k++] = new LineSegment(points[smallend], points[bigend]);
                }
                    else
                        break;
                }
        }
    }     // finds all line segments containing 4 or more points
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
        P[1] = new Point(4,4);
        P[5] = new Point(5,6);
        P[3] = new Point(7,8);
        P[4] = new Point(8,10);
        P[2] = new Point(11,12);
        P[6] = new Point(13,15);

        FastCollinearPoints FCP = new FastCollinearPoints(P);
        int a = FCP.numberOfSegments();
        StdOut.println(a);
    }
}
