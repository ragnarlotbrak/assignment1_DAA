package assignment1;

import java.util.Arrays;

class DeterministicSelect {
    public int deterministicSelect(int[] arr, int k) {
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k out of bounds");
        }
        return select(arr, 0, arr.length - 1, k);
    }

    private int select(int[] arr, int left, int right, int k) {
        while (true) {
            if (left == right) return arr[left];

            int pivot = medianOfMedians(arr, left, right);
            int pivotIndex = partition(arr, left, right, pivot);

            if (k == pivotIndex) return arr[k];
            else if (k < pivotIndex) right = pivotIndex - 1;
            else left = pivotIndex + 1;
        }
    }

    private int partition(int[] arr, int left, int right, int pivot) {
        int pivotPos = -1;
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivot) {
                pivotPos = i;
                break;
            }
        }
        if (pivotPos == -1) pivotPos = right;
        swap(arr, pivotPos, right);

        int store = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, store, i);
                store++;
            }
        }
        swap(arr, store, right);
        return store;
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

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
    }
}
