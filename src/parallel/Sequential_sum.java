package parallel;

public class Sequential_sum {
    public static long sum(int[] nums) {
        long total = 0;
        for(int num: nums)
            total += num;
        return total;
    }
}
