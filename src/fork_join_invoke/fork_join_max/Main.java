package fork_join_invoke.fork_join_max;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    static final int THREASHOLD = 1000;

    public static void main(String[] args) {
        int[] nums = initializeNms();

        SequentialMaxFinding sequentialMaxFinding = new SequentialMaxFinding();
        long start = System.currentTimeMillis();
        System.out.println("Max: " + sequentialMaxFinding.sequentialMaxFind(nums, nums.length));
        long end = System.currentTimeMillis();
        System.out.println("Sequential Time cost: " + (end - start));


        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ParallelMaxFinding parallelMaxFinding = new ParallelMaxFinding(nums, 0, nums.length);

        start = System.currentTimeMillis();
        System.out.println("Max: " + pool.invoke(parallelMaxFinding));
        end = System.currentTimeMillis();
        System.out.println("Prrallel Time cost: " + (end - start));
    }

    public static int[] initializeNms() {
        Random random = new Random();

        int[] nums = new int[10000];

        for (int i = 0; i < 10000; i++) {
            nums[i] = random.nextInt(1000);
        }
        return nums;
    }
}
