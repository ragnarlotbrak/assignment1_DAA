package assignment1.algorithms;

public class MergeSort {

    private static final int CUTOFF = 10;

    public void mergeSort(int[] arr){
        int[] aux = new int[arr.length];
        mergeSort(arr, aux, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        if (hi - lo + 1 <= CUTOFF) {
            InsertionSort.insertionSort(arr, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, aux, lo, mid);
        mergeSort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    private void merge(int[] arr, int[] aux, int lo, int mid, int hi) {
        for(int k = lo; k <= hi; k++) { // System.arraycopy(arr, lo, aux, lo, hi - lo + 1) можно использовать, так к слову!
            aux[k] = arr[k];
        }
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (aux[j] < aux[i]) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }
    }
}