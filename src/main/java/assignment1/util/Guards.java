package assignment1.util;

public class Guards {
    public static void check(int[] arr, int k) {
        if(arr == null){
            throw new NullPointerException("arr is null");
        }
        else if(arr.length <= k || k < 0) {
            throw new IndexOutOfBoundsException("arr.length < k");
        }
    }
}
