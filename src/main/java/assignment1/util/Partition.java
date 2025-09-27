package assignment1.util;

public class Partition {
    public static int partition(int[] arr, int left, int right, int pivot) {
        int pivotPos = -1;
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivot) {
                pivotPos = i;
                break;
            }
        }
        if (pivotPos == -1) pivotPos = right;
        Swap.swap(arr, pivotPos, right);

        int store = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                Swap.swap(arr, store, i);
                store++;
            }
        }
        Swap.swap(arr, store, right);
        return store;
    }
}
