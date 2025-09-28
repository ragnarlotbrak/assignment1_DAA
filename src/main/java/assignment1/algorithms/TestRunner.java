package assignment1.algorithms;

import assignment1.metrics.*;
import assignment1.util.*;
import java.util.*;

public class TestRunner {
    public static void main(String[] args) {
        int[] sizes = {10, 50, 100, 500};
        for (int n : sizes) {
            System.out.println("Testing size: " + n);

            int[] arr = new Random().ints(n, 0, 1000).toArray();
            int[] copy = arr.clone();

            Metrics metrics = new Metrics();
            MergeSort ms = new MergeSort(metrics);
            ms.mergeSort(arr);
            Arrays.sort(copy);
            System.out.println("MergeSort correct: " + Arrays.equals(arr, copy) + " | Depth: " + metrics.getMaxDepth());

            arr = new Random().ints(n, 0, 1000).toArray();
            copy = arr.clone();
            metrics.reset();
            QuickSort qs = new QuickSort(metrics);
            qs.quickSort(arr);
            Arrays.sort(copy);
            System.out.println("QuickSort correct: " + Arrays.equals(arr, copy) + " | Depth: " + metrics.getMaxDepth());

            arr = new Random().ints(n, 0, 1000).toArray();
            metrics.reset();
            DeterministicSelect ds = new DeterministicSelect(metrics);
            boolean selectOk = true;
            for (int t = 0; t < 10; t++) {
                int k = new Random().nextInt(arr.length);
                int expected = Arrays.stream(arr.clone()).sorted().toArray()[k];
                int result = ds.deterministicSelect(arr.clone(), k);
                if (result != expected) selectOk = false;
            }
            System.out.println("Select correct: " + selectOk + " | Depth: " + metrics.getMaxDepth());

            if (n <= 2000) {
                Random random = new Random();
                ClosestPairOfPoints.Point[] points = new ClosestPairOfPoints.Point[n];
                for (int i = 0; i < n; i++) points[i] = new ClosestPairOfPoints.Point(random.nextInt(1000), random.nextInt(1000));
                metrics.reset();
                ClosestPairOfPoints cp = new ClosestPairOfPoints(metrics);
                ClosestPairOfPoints.Result fast = cp.closest(points);

                double bruteMin = Double.MAX_VALUE;
                for (int i = 0; i < points.length; i++)
                    for (int j = i + 1; j < points.length; j++)
                        bruteMin = Math.min(bruteMin, Math.hypot(points[i].x - points[j].x, points[i].y - points[j].y));

                System.out.println("ClosestPair correct: " + (Math.abs(fast.dist - bruteMin) < 1e-6) +
                        " | Depth: " + metrics.getMaxDepth());
            }
            System.out.println();
        }
        System.out.println("All tests finished.");
    }
}
