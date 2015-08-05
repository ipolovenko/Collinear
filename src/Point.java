/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            double slope1 = slopeTo(o1);
            double slope2 = slopeTo(o2);
            if (slope1 == slope2) return 0;
            if (slope1 == Double.MAX_VALUE || slope2 == -Double.MAX_VALUE) return 1;
            if (slope1 == -Double.MAX_VALUE || slope2 == Double.MAX_VALUE) return -1;
            double diff = slope1 - slope2;
            return (diff > 0) ? 1 : -1;
        }
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (compareTo(that) == 0) return -Double.MAX_VALUE;
        if (x == that.x) return Double.MAX_VALUE;
        if (y == that.y) return 0;
        double xd0 = (double) x;
        double yd0 = (double) y;
        double xd1 = (double) that.x;
        double yd1 = (double) that.y;
        return (yd1 - yd0) / (xd1 - xd0);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (y < that.y || (y == that.y && x < that.x)) return -1;
        else if (y > that.y || (y == that.y && x > that.x)) return 1;
        else return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
