package assignment1.algorithms;

import assignment1.util.Swap;
import java.util.Random;

public class QuickSort {
    private final Random rand = new Random();

    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            int pivotIndex = low + rand.nextInt(high - low + 1);
            int pivot = arr[pivotIndex];
            int i = low, j = high;

            while (i <= j) {
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;
                if (i <= j) {
                    Swap.swap(arr, i, j);
                    i++;
                    j--;
                }
            }

            if (j - low < high - i) {
                if (low < j) quickSort(arr, low, j);
                low = i;
            } else {
                if (i < high) quickSort(arr, i, high);
                high = j;
            }
        }
    }
}
