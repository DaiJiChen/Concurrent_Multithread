package fork_join_invoke.fork_join_max;

public class SequentialMaxFinding {
    public int sequentialMaxFind(int[] nums, int highIndex) {
        int max = nums[0];

        for(int num: nums) {
            if(num > max) max = num;
        }

        return max;
    }
}
