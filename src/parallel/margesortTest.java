package parallel;

import java.util.Random;

public class margesortTest {
    public static void main(String[] args) {
        int[] numbers = createRandomArray(10);
        Sequential_MergeSort sequential = new Sequential_MergeSort(numbers);
        sequential.mergesort(0, numbers.length - 1);
        sequential.showResult();

        numbers = createRandomArray(10);
        int numThreads = Runtime.getRuntime().availableProcessors();
        Paralled_MergeSort parallel = new Paralled_MergeSort(numbers);
        parallel.paralledMergeSort(0, numbers.length - 1, numThreads);
        parallel.showResult();
    }

    public static int[] createRandomArray(int n) {
        int[] a = new int[n];
        Random random = new Random();
        for(int i = 0; i < n; i++) {
            a[i] = random.nextInt(100000);
        }
        return a;
    }

}
