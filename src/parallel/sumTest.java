package parallel;

import static parallel.margesortTest.createRandomArray;

public class sumTest {
    private static final int NUM = 10;
    public static void main(String[] args) {
        int[] nums = createRandomArray(NUM);
        long start = System.currentTimeMillis();
        System.out.println(Sequential_sum.sum(nums));
        long end = System.currentTimeMillis();
        System.out.println("Sequential sum takes: " + (end - start) + "ms");

//        nums = createRandomArray(NUM);
//        start = System.currentTimeMillis();
//        System.out.println(Parallel_sum.sum(nums));
//        end = System.currentTimeMillis();
//        System.out.println("Paralled sum takes: " + (end - start) + "ms");
    }
}
