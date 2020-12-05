package parallel;

public class Paralled_MergeSort {

    private int[] nums;
    private int[] tempArray;

    public Paralled_MergeSort(int[] nums) {
        this.nums = nums;
        tempArray = new int[nums.length];
    }

    public void paralledMergeSort(int low, int high, int numThreads) {
        if(numThreads <= 1) {
            mergesort(low, high);
            return;
        }

        int mid = (low + high) / 2;

        Thread leftSorter = mergesortParalled(low, mid, numThreads);
        Thread rightSorter = mergesortParalled(mid + 1, high, numThreads);

        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(low, mid, high);
    }

    public Thread mergesortParalled(int low, int high, int numThreads) {
        return new Thread() {
            @Override
            public void run() {
                paralledMergeSort(low, high, numThreads/2);
            }
        };
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


