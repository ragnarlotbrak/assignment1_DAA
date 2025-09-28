package assignment1.algorithms;

import java.util.Arrays;
import assignment1.util.*;

class DeterministicSelect {
    private final Metrics metrics;

    public int deterministicSelect(int[] arr, int k) {
        Guards.check(arr, k);
        return select(arr, 0, arr.length - 1, k);
    }

    public DeterministicSelect(Metrics metrics) {
        this.metrics = metrics;
    }

    private int select(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        metrics.enterRec();

        int pivot = medianOfMedians(arr, left, right);
        int pivotIndex = Partition.partition(arr, left, right, pivot);

        int result;
        if (k == pivotIndex) {
            result = arr[k];
        }
        else if (k < pivotIndex) {
            result = select(arr, left, pivotIndex - 1, k);
        }
        else {
            result = select(arr, pivotIndex + 1, right, k);
        }

        metrics.exitRec();
        return result;
    }

    private int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        int numMedians = (n + 4) / 5;
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            int len = subRight - subLeft + 1;
            medians[i] = arr[subLeft + len / 2];
        }

        return medianOfMedians(medians, 0, numMedians - 1);
    }
}
