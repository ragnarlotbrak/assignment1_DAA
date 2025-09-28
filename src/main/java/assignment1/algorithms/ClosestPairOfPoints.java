package assignment1.algorithms;

import java.util.*;
import assignment1.util.*;

public class ClosestPairOfPoints {

    private final Metrics metrics;

    public ClosestPairOfPoints(Metrics metrics) {
        this.metrics = metrics;
    }

    public static class Point {
        double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static class Result {
        Point p1, p2;
        double dist;
        Result(Point p1, Point p2, double dist) {
            this.p1 = p1;
            this.p2 = p2;
            this.dist = dist;
        }
    }

    private double dist(Point a, Point b) {
        metrics.incComp();
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private Result bruteForce(Point[] points, int left, int right) {
        double min = Double.POSITIVE_INFINITY;
        Point p1 = null, p2 = null;
        for (int i = left; i < right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double d = dist(points[i], points[j]);
                if (d < min) {
                    min = d;
                    p1 = points[i];
                    p2 = points[j];
                }
            }
        }
        return new Result(p1, p2, min);
    }

    private Result stripClosest(List<Point> strip, double d, Result best) {
        strip.sort(Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < best.dist; j++) {
                if (j - i > 7) break;
                double dist = dist(strip.get(i), strip.get(j));
                if (dist < best.dist) {
                    best = new Result(strip.get(i), strip.get(j), dist);
                }
            }
        }
        return best;
    }

    private Result closestUtil(Point[] points, int left, int right) {
        if (right - left <= 3) {
            return bruteForce(points, left, right);
        }

        metrics.enterRec();

        int mid = (left + right) / 2;
        Point midPoint = points[mid];

        Result leftRes = closestUtil(points, left, mid);
        Result rightRes = closestUtil(points, mid + 1, right);

        Result best = (leftRes.dist < rightRes.dist) ? leftRes : rightRes;

        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midPoint.x) < best.dist) {
                strip.add(points[i]);
            }
        }

        Result result = stripClosest(strip, best.dist, best);

        metrics.exitRec();
        return result;
    }

    public Result closest(Point[] points) {
        Arrays.sort(points, Comparator.comparingDouble(p -> p.x));
        return closestUtil(points, 0, points.length - 1);
    }
}
