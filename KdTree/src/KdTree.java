import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;


public class KdTree {
    private int N;
    private Node root;
    private final RectHV Big_Rect = new RectHV(0.0,0.0,1.0,1.0);

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D p, RectHV rect, Node lb, Node rt){
            this.p = p;
            this.lb = lb;
            this.rt = rt;
            this.rect = rect;
        }
    }

    public         KdTree(){
        N = 0;
        root = null;
    }                               // construct an empty set of points
    public           boolean isEmpty(){
        return N == 0;
    }                      // is the set empty?
    public               int size(){
        return N;
    }                         // number of points in the set
    public              void insert(Point2D p){
        if (p == null)
            throw new IllegalArgumentException("argument cannot be null");
        if (N == 0) {
            root = new Node(p, Big_Rect, null, null);
            N++;
        }
        else if (!contains(p))
        {
            int level = 0;
            root = insert(root, p, level);
        }
    }              // add the point to the set (if it is not already in the set)
    private Node insert(Node x, Point2D p, int level){
        if (x == null){
            this.N++;
            return new Node(p, null,null,null); // compute the rectangular
        }
        level++;
        if (level % 2 != 0) // odd level, compare x coordinates
            if (p.x() < x.p.x())
                x.lb = insert(x.lb, p, level);
            else
                x.rt = insert(x.rt, p, level);
        else
            if (p.y() < x.p.y())
                x.lb = insert(x.lb, p, level);
            else
                x.rt = insert(x.rt, p, level);
        return  x;
    }
    public           boolean contains(Point2D p){
        int level = 0;
        return contains(root, p, level);
    }            // does the set contain point p?
    private boolean contains(Node x, Point2D p, int level){
        if (x == null)
            return false;
        level++;
        if (level % 2 != 0)
            if (p.x() < x.p.x())
                return contains(x.lb, p, level);
            else if (p.x() > x.p.x())
                return  contains(x.rt, p, level);
            else
                return p.y() == x.p.y();
        else
            if (p.y() < x.p.y())
                return contains(x.lb, p, level);
            else if (p.y() > x.p.y())
                return  contains(x.rt, p, level);
            else
                return p.x() == x.p.x();
    }
    public              void draw(){

    }                         // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect){
        return range(root, rect);
    }             // all points that are inside the rectangle (or on the boundary)
    private Iterable<Point2D> range(Node x, RectHV rect){
        ArrayList<Point2D> all_points = new ArrayList<Point2D>();
        if (x == null || !rect.intersects(x.rect))
            return all_points;
        if (rect.contains(x.p))
            all_points.add(x.p);
        Iterable<Point2D> left_points = range(x.lb, rect);
        Iterable<Point2D> right_points = range(x.rt, rect);
        for (Point2D p : left_points)
            all_points.add(p);
        for (Point2D p : right_points)
            all_points.add(p);
        return all_points;
    }
    public           Point2D nearest(Point2D p){
        return nearest(root, p);
    }             // a nearest neighbor in the set to point p; null if the set is empty
    private Point2D nearest(Node x, Point2D p){
        //if (x.lb == null && )
          //  return x.p;
        double nearest_distance = x.p.distanceTo(p);
        if (x.rt.rect.contains(p) || x.lb.rect.distanceTo(p) > nearest_distance)
            return nearest(x.rt, p);
        if (x.lb.rect.contains(p) || x.rt.rect.distanceTo(p) > nearest_distance)
            return nearest(x.lb, p);
    }
    public static void main(String[] args){

    }                  // unit testing of the methods (optional)
}
