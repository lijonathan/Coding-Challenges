public class Maximum_Subarray {

/*
Rating: Easy

Given an integer array nums, find the contiguous
subarray (containing at least one number) which has
the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:

If you have figured out the O(n) solution, try
coding another solution using the divide and conquer
approach, which is more subtle.


*/

    public int maxSubArray(int[] nums) {
        // brute force(n^2) check subarray starting at each value
        // [-2, 1,-3,4,-1,2,1,-5,4]
        //         |         |
        // running sum of whats been seen so far
        // if the running sum > max_sum, set max_sum = sum
        // if current value is greater than sum --> whats been seen before
        // is negative, set current sum to curr index val and
    	// continue summing
    	
    	// O(n) time, (1) space
        int sum = 0;
        int max_sum = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; ++i)
        {
            sum += nums[i];
            if (nums[i] > sum)
            {
                sum = nums[i];
            }
            if (sum > max_sum)
            {
                max_sum = sum;
            }
        }
        return max_sum;
    }
}
