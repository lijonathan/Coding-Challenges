public class First_Missing_Positive {
/*
Rating: Hard

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3

Example 2:

Input: [3,4,-1,1]
Output: 2

Example 3:

Input: [7,8,9,11,12]
Output: 1

Note:

Your algorithm should run in O(n) time and uses constant extra space.
*/

/*
    Approach: Iterate through and set all negative values to the maximum integer value.
              Iterate through and find the minimum value. If the minimum value > 1, return 1.
              Let index 0 be the marker for the minimum value. Iterate through the array
              and if the value at the current index != Integer.MAX_VALUE and the value
              - min is less than the length of the array, set nums[i] *= 1 where the index
              i = nums[current_index] - min_value.

            Iterate through the array and the first index that is non-negative, return
            i + min_value. If there are no negative indexes, return the value in the final index + 1.
            (same as num.length + min)

*/

    // O(n) runtime
    // O(1) space
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 37.2 MB, less than 82.25% of Java online submissions
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; ++i)
        {
            if (nums[i] <= 0)
            {
                nums[i] = Integer.MAX_VALUE;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < nums.length; ++k)
        {
            if (nums[k] < min)
            {
                min = nums[k];
            }
        }
        if (min > 1)
        {
            return 1;
        }
        for (int m = 0; m < nums.length; ++m)
        {
            if (Math.abs(nums[m]) != Integer.MAX_VALUE && nums[m] - min < nums.length)
            {
                if (nums[Math.abs(nums[m]) - min] > 0)
                {
                    nums[Math.abs(nums[m]) - min] *= -1;
                }
            }
        }
        for (int i = 0; i < nums.length; ++i)
        {
            if (nums[i] > 0)
            {
                return i + min;
            }
        }
        return nums.length + min;
    }
}
