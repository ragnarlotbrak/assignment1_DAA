package assignment1.algorithms;

import assignment1.metrics.*;
import assignment1.util.*;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args){

        String algo = "mergesort";
        int size = 30;
        String output = "result.csv";
        int k = 5;

        if (args.length > 0){
            for(int i = 0; i < args.length; i++){
                switch (args[i]){
                    case "--algo": algo = args[++i]; break;
                    case "--size": size = Integer.parseInt(args[++i]); break;
                    case "--output": output = args[++i]; break;
                    case "--k": k = Integer.parseInt(args[++i]); break;
                }
            }
        }
        else{
            Scanner in = new Scanner(System.in);
            System.out.println("Choose algorithm: mergesort, quicksort, select, closest: ");
            algo = in.nextLine().trim().toLowerCase();
            System.out.println("Enter array size: ");
            size = in.nextInt();

        }

        Metrics metrics = new Metrics();

        try (CSVWriter writer = new CSVWriter(output)) {
            switch(algo.toLowerCase()){
                case "mergesort":
                    metrics.reset();
                    int[] arr1 = new Random().ints(size, 0, 100).toArray();
                    Shuffle.shuffle(arr1);
                    MergeSort ms = new MergeSort(metrics);
                    ms.mergeSort(arr1);
                    System.out.println("\nMerge sort result: ");
                    System.out.print(Arrays.toString(arr1));
                    System.out.println("\nMetrics of MergeSort: " + metrics);
                    writer.writeRecord("\nMergeSort", arr1.length,
                            metrics.getComparisons(),
                            metrics.getSwaps(),
                            metrics.getMaxDepth());
                    break;
                case "quicksort":
                    metrics.reset();
                    int[] arr2 = new Random().ints(size, 0, 100).toArray();
                    Shuffle.shuffle(arr2);
                    QuickSort qs = new QuickSort(metrics);
                    qs.quickSort(arr2);
                    System.out.println("\nQuickSort result: ");
                    System.out.print(Arrays.toString(arr2));
                    System.out.println("\nMetrics of QuickSort: " + metrics);
                    writer.writeRecord("QuickSort", arr2.length,
                            metrics.getComparisons(),
                            metrics.getSwaps(),
                            metrics.getMaxDepth());
                    break;
                case "select":
                    metrics.reset();
                    int[] arr3 = new Random().ints(size, 0, 100).toArray();
                    DeterministicSelect ds = new DeterministicSelect(metrics);
                    System.out.println("\nArray: " + Arrays.toString(arr3));
                    int queryK;
                    if (args.length > 0){
                        queryK = k;
                    }
                    else{
                        Scanner in = new Scanner(System.in);
                        System.out.print("Enter the number of elements in the array: ");
                        queryK = in.nextInt() - 1;
                    }
                    int kthElement = ds.deterministicSelect(arr3.clone(), queryK);
                    System.out.println((queryK + 1) + "-th element is: " + kthElement);
                    System.out.println("Metrics of Select: " + metrics);
                    writer.writeRecord("Select", arr3.length,
                            metrics.getComparisons(),
                            metrics.getSwaps(),
                            metrics.getMaxDepth());
                    break;

                case "closest":
                    metrics.reset();
                    Random random = new Random();
                    ClosestPairOfPoints.Point[] points = new ClosestPairOfPoints.Point[size];
                    for (int i = 0; i < size; i++){
                        points[i] = new ClosestPairOfPoints.Point(random.nextInt(1000), random.nextInt(1000));
                    }
                    ClosestPairOfPoints cp = new ClosestPairOfPoints(metrics);
                    ClosestPairOfPoints.Result result = cp.closest(points);
                    System.out.println("\nClosest Pair Of Points result: " + result.p1 + " and " + result.p2);
                    System.out.println("Distance = " + result.dist);
                    System.out.println("Metrics of ClosestPairOfPoints: " + metrics);
                    System.out.println(" ");
                    writer.writeRecord("ClosestPairOfPoints", points.length,
                            metrics.getComparisons(),
                            metrics.getSwaps(),
                            metrics.getMaxDepth());
                    break;
                default:
                    System.out.println("Unknown algorithm: " + algo);
            }
        }
        catch (IOException e){
            System.err.println("Error in working of CSV: " + e.getMessage());
        }
    }
}

