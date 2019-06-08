import edu.princeton.cs.algs4.*;

import java.util.ArrayList;

public class PointSET {
    private SET<Point2D> set;

    public         PointSET(){        set = new SET<Point2D>();    }                               // construct an empty set of points
    public           boolean isEmpty(){        return set.isEmpty();    }                      // is the set empty?
    public               int size(){        return set.size();    }                         // number of points in the set
    public              void insert(Point2D p){
        if (p == null)
            throw new IllegalArgumentException("argument cannot be null");
        if (!set.contains(p))
            set.add(p);
    }              // add the point to the set (if it is not already in the set)
    public           boolean contains(Point2D p){
        if (p == null)
            throw new IllegalArgumentException("argument cannot be null");
        return set.contains(p);
    }            // does the set contain point p?
    public              void draw(){
        for (Point2D p : set)
            StdDraw.point(p.x(),p.y());
    }                         // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect){
        if (rect == null)
            throw new IllegalArgumentException("argument cannot be null");
        ArrayList<Point2D> all_points = new ArrayList<Point2D>();
        for (Point2D p : set)
            if (rect.contains(p))
                all_points.add(p);
         return all_points;
    }             // all points that are inside the rectangle (or on the boundary)
    public           Point2D nearest(Point2D p){
        if (p == null)
            throw new IllegalArgumentException("argument cannot be null");
        if (isEmpty())
            return null;
        Point2D min_point = new Point2D(5.0,5.0);
        for (Point2D point : set)
            if (point.distanceTo(p) < min_point.distanceTo(p))
                min_point = point;
        return min_point;
    }             // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args){
        PointSET pset = new PointSET();
        In in = new In(args[0]);
        for (int i = 0; i < 10; i++){
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x,y);
            pset.insert(p);
        }
        //pset.draw();
        double x = 0.8;
        double y = 0.2;
        Point2D Point = new Point2D(x,y);
        Point2D nearest_point = pset.nearest(Point);
    }                  // unit testing of the methods (optional)
}