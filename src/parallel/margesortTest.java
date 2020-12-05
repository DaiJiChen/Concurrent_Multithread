package parallel;

import java.util.Random;

public class margesortTest {
    private static final int NUM = 1000000;

    public static void main(String[] args) {
        int[] numbers = createRandomArray(NUM);
        Sequential_MergeSort sequential = new Sequential_MergeSort(numbers);
        long startTime = System.currentTimeMillis();
        sequential.mergesort(0, numbers.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("Time used to sort " + NUM + " numbers using sequential mergesort: " + (endTime - startTime));
        //sequential.showResult();

        numbers = createRandomArray(NUM);
        int numThreads = Runtime.getRuntime().availableProcessors();
        Paralled_MergeSort parallel = new Paralled_MergeSort(numbers);
        startTime = System.currentTimeMillis();
        parallel.paralledMergeSort(0, numbers.length - 1, numThreads);
        endTime = System.currentTimeMillis();
        System.out.println("Time used to sort " + NUM + " numbers using parallel mergesort: " + (endTime - startTime));
        //parallel.showResult();
    }

    public static int[] createRandomArray(int n) {
        int[] a = new int[n];
        Random random = new Random();
        for(int i = 0; i < n; i++) {
            a[i] = random.nextInt(1000);
        }
        return a;
    }

}
