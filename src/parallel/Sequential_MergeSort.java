package parallel;

public class Sequential_MergeSort {

    private int[] nums;
    private int[] tempArray;

    public Sequential_MergeSort(int[] nums) {
        this.nums = nums;
        tempArray = new int[nums.length];
    }

    public void mergesort(int low, int high) {
        if(low >= high) return;

        int mid = (low + high) / 2;

        mergesort(low, mid);
        mergesort(mid+1, high);
        merge(low, mid, high);
    }

    public void merge(int low, int mid, int high) {
        for(int i = low; i <= high; i++)
            tempArray[i] = nums[i];

        int i = low;
        int j = mid + 1;
        int index = low;

        while(i <= mid && j <= high) {
            if(tempArray[i] < tempArray[j])
                nums[index++] = tempArray[i++];
            else
                nums[index++] = tempArray[j++];
        }

        while(i <= mid) {
            nums[index++] = tempArray[i++];
        }

        while(j <= high) {
            nums[index++] = tempArray[j++];
        }
    }

    public void showResult() {
        for(int num : nums)
            System.out.print(num+" ");
        System.out.println();
    }

    public boolean isSorted() {
        for(int i = 1; i < nums.length; i++)
            if(nums[i] < nums[i - 1])
                return false;
        return true;
    }
}


