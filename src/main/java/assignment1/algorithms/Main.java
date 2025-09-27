package assignment1.algorithms;

import assignment1.util.Shuffle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] base = {73, 15, 92, 48, 6, 57, 34, 88, 21, 100,
                62, 3, 79, 46, 27, 9, 53, 64, 81, 18,
                95, 40, 7, 30, 67, 12, 55, 85, 99, 24,
                14, 37, 71, 2, 90, 44, 59, 26, 83, 11,
                97, 32, 4, 68, 19, 61, 29, 75, 8, 50};
        int n  = base.length;


        int[] arr1 = base.clone();
        Shuffle.shuffle(arr1);
        MergeSort ms = new MergeSort();
        ms.mergeSort(arr1);

        System.out.println("Merge sort result: ");
        for (int i = 0; i < n; i++) {        //можно использовать просто System.out.print(Arrays.toString(arr*));!
            System.out.print(arr1[i] + " "); //но хочу чтоб был цикл)
        }

        System.out.println(" ");

        int[] arr2 = base.clone();
        Shuffle.shuffle(arr2);

        QuickSort qs = new QuickSort();
        qs.quickSort(arr2);

        System.out.println("\nQuick sort result: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr2[i] + " ");
        }

        System.out.println(" ");

        int[] arr = {12, 67, 5, 43, 0, 28, 39, 70, 18, 54,
                7, 61, 25, 33, 46, 2, 68, 14, 37, 49};

        DeterministicSelect ds = new DeterministicSelect();
        System.out.println("\nDeterministic Select result:");
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.print("      Enter the number of elements in the array: ");

        Scanner in = new Scanner(System.in);
        int k = in.nextInt() - 1;

        int kthElement = ds.deterministicSelect(arr.clone(), k);
        System.out.println("\n" + (k + 1) + "-th element is: " + kthElement);

        ClosestPairOfPoints.Point[] points = {
                new ClosestPairOfPoints.Point(2, 3),
                new ClosestPairOfPoints.Point(12, 30),
                new ClosestPairOfPoints.Point(40, 50),
                new ClosestPairOfPoints.Point(5, 1),
                new ClosestPairOfPoints.Point(12, 10),
                new ClosestPairOfPoints.Point(3, 4)
        };

        ClosestPairOfPoints.Result result = ClosestPairOfPoints.closest(points);

        System.out.println("\nClosest pair result: " + result.p1 + " and " + result.p2);
        System.out.println("Distance = " + result.dist);
    }
}

