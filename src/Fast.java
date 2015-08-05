public class Fast {
    private PointWrapper[] array;
    private PointWrapper[] sortedArray;
    private static PointWrapper origin;

    private Fast(String fileName) {
        In in = new In(fileName);
        if (!in.hasNextLine()) throw new IllegalArgumentException();
        int pointsCount = in.readInt();
        array = new PointWrapper[pointsCount];
        sortedArray = new PointWrapper[pointsCount];
        for (int i = 0; i < pointsCount; i++) {
            if (!in.hasNextLine()) throw new IllegalArgumentException();
            array[i] = new PointWrapper(new Point(in.readInt(), in.readInt()));
            sortedArray[i] = array[i];
        }
        StdOut.println();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        findCollinear();
    }

    public static void main(String[] args) {
        if (args.length == 0) throw new IllegalArgumentException();
        new Fast(args[0]);
    }

    private void findCollinear() {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            origin = array[i];
            origin.point.draw();
            QuickX.sort(sortedArray);
            int j = 1;
            while (j < n) {
                int count = 1;
                double slopeJ = origin.point.slopeTo(sortedArray[j].point);
                while (j + count < n && slopeJ == origin.point.slopeTo(sortedArray[j + count].point)) {
                    count++;
                }
                if (count >= 3) {
                    Point[] segment = new Point[count + 1];
                    int sIndex = 0;
                    segment[sIndex++] = origin.point;
                    for (int s = j, sLength = j + count; s < sLength; s++) {
                        segment[sIndex++] = sortedArray[s].point;
                    }
                    Insertion.sort(segment);
                    for (int s = 0, sLength = segment.length; s < sLength; s++) {
                        StdOut.print(segment[s]);
                        if (s < sLength - 1) StdOut.print(" -> ");
                        else StdOut.print("\n");
                    }
                    segment[0].drawTo(segment[segment.length - 1]);
                    j += count;
                } else j++;
            }
        }
    }

    private static class PointWrapper implements Comparable<PointWrapper> {

        private Point point;

        private PointWrapper(Point point) {
            this.point = point;
        }

        @Override
        public int compareTo(PointWrapper o) {
            return origin.point.SLOPE_ORDER.compare(point, o.point);
        }
    }
}
