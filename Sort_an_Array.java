public class Sort_an_Array {

/*
Rating: Medium
 
Given an array of integers nums, sort the array in ascending order.

Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]

Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]

Constraints:

    1 <= nums.length <= 50000
    -50000 <= nums[i] <= 50000
*/

    /* MergeSort */
    
    // Runtime: 7ms beats 33.95% of Java online submissions
    // Memory Usage: 46.8MB beats 67.09% of Java online submissions
    // O(n log n) time
    // O(n) space --> n space for temporary array, log(n) space for recursive stack frames
    
    /* Faster submissions implemented Quicksort */
    public void merge(int[] nums, int start, int mid, int end)
    {
        int[] temp = new int[end - start + 1];
        int l_index = start;
        int r_index = mid + 1;
        int index = 0;
        while (l_index <= mid || r_index <= end)
        {
            if (l_index > mid)
            {
                temp[index] = nums[r_index];
                ++r_index;
            }
            else if (r_index > end)
            {
                temp[index] = nums[l_index];
                ++l_index;
            }
            else if (nums[l_index] < nums[r_index])
            {
                temp[index] = nums[l_index];
                ++l_index;
            }
            else
            {
                temp[index] = nums[r_index];
                ++r_index;
            }
            ++index;
        }
        index = 0;
        for (int i = start; i <= end; ++i)
        {
            nums[i] = temp[index];
            ++index;
        }
    }
    public void mergeSort(int[] nums, int start, int end)
    {
        if (start < end)
        {
            int mid = (end + start) / 2;
            mergeSort(nums, start, mid);
            mergeSort(nums, mid + 1, end);
            merge(nums, start, mid, end);
        }
    }
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length -  1);
        return nums;
    }
}
