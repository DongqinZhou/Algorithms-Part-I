import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

import edu.princeton.cs.algs4.StdDraw;
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
    public           boolean isEmpty(){ return N == 0;  }                      // is the set empty?
    public               int size(){ return N;    }                         // number of points in the set
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
            if (p.x() < x.p.x()) {
                x.lb = insert(x.lb, p, level);
                x.lb.rect = buildoddrect(x.rect, x.p, p);
            }
            else  {
                x.rt = insert(x.rt, p, level);
                x.rt.rect = buildoddrect(x.rect, x.p, p);
            }
        else
            if (p.y() < x.p.y()){
                x.lb = insert(x.lb, p, level);
                x.lb.rect = buildevenrect(x.rect, x.p, p);
            }
            else{
                x.rt = insert(x.rt, p, level);
                x.rt.rect = buildevenrect(x.rect, x.p, p);
            }
        return  x;
    }
    private RectHV buildoddrect(RectHV Rect, Point2D curr, Point2D p){
        if (p.x() < curr.x())
            return new RectHV(Rect.xmin(), Rect.ymin(), curr.x(), Rect.ymax());
        else
            return new RectHV(curr.x(), Rect.ymin(), Rect.xmax(), Rect.ymax());
    }
    private RectHV buildevenrect(RectHV Rect, Point2D curr, Point2D p){
        if (p.y() < curr.y())
            return new RectHV(Rect.xmin(), Rect.ymin(), Rect.xmax(), curr.y());
        else
            return new RectHV(Rect.xmin(), curr.y(), Rect.xmax(), Rect.ymax());
    }
    public           boolean contains(Point2D p){
        if (p == null)
            throw new IllegalArgumentException("argument cannot be null");
        int level = 0;
        return contains(root, p, level);
    }            // does the set contain point p?
    private boolean contains(Node x, Point2D p, int level){
        if (x == null)
            return false;
        level++;
        if (x.p.equals(p))
            return true;
        if (level % 2 != 0)
            if (p.x() < x.p.x())
                return contains(x.lb, p, level);
            else
                return  contains(x.rt, p, level);
        else
            if (p.y() < x.p.y())
                return contains(x.lb, p, level);
            else
                return  contains(x.rt, p, level);
    }
    public              void draw(){
        //StdDraw.setScale(0, 1);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius();
        Big_Rect.draw();
        int level = 0;
        draw(root, Big_Rect, level);
    }                         // draw all points to standard draw
    private void draw(Node x, RectHV rect, int level) {
        if (x == null)
            return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        x.p.draw();

        level++;
        Point2D min, max;
        if (level % 2 != 0){
            StdDraw.setPenColor(StdDraw.RED);
            min = new Point2D(x.p.x(), rect.ymin());
            max = new Point2D(x.p.x(), rect.ymax());
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            min = new Point2D(rect.xmin(), x.p.y());
            max = new Point2D(rect.xmax(), x.p.y());
        }
        StdDraw.setPenRadius();
        min.drawTo(max);
        //StdOut.println(level);
        if (x.lb != null)
            draw(x.lb, x.lb.rect, level);
        //StdOut.println(level);
        if (x.rt != null)
            draw(x.rt, x.rt.rect, level);
    }
    public Iterable<Point2D> range(RectHV rect){
        if (rect == null)
            throw new IllegalArgumentException("argument cannot be null");
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
        if (p == null)
            throw new IllegalArgumentException("argument cannot be null");
        Node nearest_node = root;
        int level = 0;
        return nearest(root, p, root, level).p;
    }             // a nearest neighbor in the set to point p; null if the set is empty
    private Node nearest(Node x, Point2D p, Node Nearest, int level){
        if (x == null)
            return Nearest;
        if (x.p.distanceTo(p) < Nearest.p.distanceTo(p))
            Nearest = x;
        level++;
        if (level % 2 != 0)
            if (x.p.x() <= p.x()){
                Node Near = nearest(x.rt, p, Nearest, level);
                if (x.lb == null || Near.p.distanceTo(p) < x.lb.rect.distanceTo(p))
                    return Near;
                else
                    return nearest(x.lb, p, Near, level);
            }
            else {
                Node Near = nearest(x.lb, p, Nearest, level);
                if (x.rt == null || Near.p.distanceTo(p) < x.rt.rect.distanceTo(p))
                    return Near;
                else
                    return nearest(x.rt, p, Near, level);
            }
        else
            if (x.p.y() > p.y()){
                Node Near = nearest(x.lb, p, Nearest, level);
                if (x.rt == null || Near.p.distanceTo(p) < x.rt.rect.distanceTo(p))
                    return Near;
                else
                    return nearest(x.rt, p, Near, level);
            }
            else {
                Node Near = nearest(x.rt, p, Nearest, level);
                if (x.lb == null || Near.p.distanceTo(p) < x.lb.rect.distanceTo(p))
                    return Near;
                else
                    return nearest(x.lb, p, Near, level);
            }
    }
    public static void main(String[] args){
        KdTree kdtree = new KdTree();
        In in = new In(args[0]);
        for (int i = 0; i < 10; i++){
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x,y);
            kdtree.insert(p);
        }
        kdtree.draw();
        double x = 0.75;
        double y = 0.5625;
        Point2D Point = new Point2D(x,y);
        Point2D nearest_point = kdtree.nearest(Point);
        StdOut.println(nearest_point.x());
        StdOut.println(nearest_point.y());
        StdOut.println(nearest_point.distanceTo(Point));


        /*
        RectHV Rect = new RectHV(0.3,0.4,0.425,0.8);

        for (Point2D p : kdtree.range(Rect)){
            StdOut.println(p.x());
            StdOut.println(p.y());
        }

         */
    }                  // unit testing of the methods (optional)
}
