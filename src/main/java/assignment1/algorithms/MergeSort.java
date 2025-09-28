package assignment1.algorithms;

import assignment1.util.Metrics;

public class MergeSort {

    private final Metrics metrics;

    private static final int CUTOFF = 10;

    public MergeSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void mergeSort(int[] arr){
        int[] aux = new int[arr.length];
        mergeSort(arr, aux, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int[] aux, int lo, int hi) {
        if (hi <= lo) return;

        metrics.enterRec();

        if (hi - lo + 1 <= CUTOFF) {
            InsertionSort.insertionSort(arr, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, aux, lo, mid);
        mergeSort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);

        metrics.exitRec();
    }

    private void merge(int[] arr, int[] aux, int lo, int mid, int hi) {
        for(int k = lo; k <= hi; k++) { // System.arraycopy(arr, lo, aux, lo, hi - lo + 1) можно использовать, так к слову!
            aux[k] = arr[k];
            metrics.incSwap();
        }
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if (i > mid) {arr[k] = aux[j++]; metrics.incSwap();}
            else if (j > hi) {arr[k] = aux[i++]; metrics.incSwap();}
            else {
                metrics.incComp();
                if (aux[j] < aux[i]) {
                    arr[k] = aux[j++];
                } else {
                    arr[k] = aux[i++];
                }
                metrics.incSwap();
            }
        }
    }
}