package assignment1.util;

import java.util.Random;

public class Shuffle {
    public static final Random RANDOM = new Random();

    public static int[] shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            Swap.swap(arr, i, j);
        }
        return arr;
    }
}
