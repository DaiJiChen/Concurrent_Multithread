package fork_join_invoke.fork_join_max;

import java.util.concurrent.RecursiveTask;

public class ParallelMaxFinding extends RecursiveTask<Integer> {
    private int[] nums;
    private int highIndex;
    private int lowIndex;

    public ParallelMaxFinding(int[] nums, int lowIndex, int highIndex) {
        this.nums = nums;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }


    @Override
    protected Integer compute() {
        if(highIndex - lowIndex < Main.THREASHOLD) {
            return sequentialMaxFind();
        }
        else {
            int mid = (highIndex + lowIndex) / 2;

            ParallelMaxFinding task1 = new ParallelMaxFinding(nums, lowIndex, mid);
            ParallelMaxFinding task2 = new ParallelMaxFinding(nums, mid+1, highIndex);

            invokeAll(task1, task2);
            return Math.max(task1.join(), task2.join());
        }
    }


    private int sequentialMaxFind() {
        int max = nums[0];

        for(int num: nums) {
            if(num > max) max = num;
        }

        return max;
    }
}
