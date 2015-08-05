public class PointWrapper implements Comparable<PointWrapper> {

    public static PointWrapper origin;
    public Point point;

    public PointWrapper(Point point) {
        this.point = point;
    }

    @Override
    public int compareTo(PointWrapper o) {
        return origin.point.SLOPE_ORDER.compare(point, o.point);
    }
}
