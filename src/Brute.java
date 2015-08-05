public class Brute {
    private Point[] array;

    private Brute(String fileName) {
        In in = new In(fileName);
        if (!in.hasNextLine()) throw new IllegalArgumentException();
        int pointsCount = in.readInt();
        array = new Point[pointsCount];
        for (int i = 0; i < pointsCount; i++) {
            if (!in.hasNextLine()) throw new IllegalArgumentException();
            array[i] = new Point(in.readInt(), in.readInt());
        }
        StdOut.println();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        findCollinear4();
    }

    public static void main(String[] args) {
        if (args.length == 0) throw new IllegalArgumentException();
        new Brute(args[0]);
    }

    private void findCollinear4() {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            Point p = array[i];
            p.draw();
            for (int j = i + 1; j < n; j++) {
                Point q = array[j];
                double slopeQ = p.slopeTo(q);
                for (int k = j + 1; k < n; k++) {
                    Point r = array[k];
                    double slopeR = p.slopeTo(r);
                    if (slopeQ != slopeR) continue;
                    for (int l = k + 1; l < n; l++) {
                        Point s = array[l];
                        double slopeS = p.slopeTo(s);
                        if (slopeQ != slopeS) continue;
                        Point[] segment = {p, q, r, s};
                        Insertion.sort(segment);
                        StdOut.println(segment[0] + " -> " + segment[1] + " -> " + segment[2] + " -> " + segment[3]);
                        segment[0].drawTo(segment[3]);
                    }
                }
            }
        }
    }
}
