public class Minimum_Value_to_Get_Positive_Step_by_Step_Sum {
/*
Rating: Easy

Given an array of integers nums, you start with an initial
positive value startValue.

In each iteration, you calculate the step by step sum of
startValue plus elements in nums (from left to right).

Return the minimum positive value of startValue such that the step
by step sum is never less than 1.

Example 1:

Input: nums = [-3,2,-3,4,2]
Output: 5
Explanation: If you choose startValue = 4, in the third iteration your step by step sum is less than 1.
                step by step sum
                startValue = 4 | startValue = 5 | nums
                  (4 -3 ) = 1  | (5 -3 ) = 2    |  -3
                  (1 +2 ) = 3  | (2 +2 ) = 4    |   2
                  (3 -3 ) = 0  | (4 -3 ) = 1    |  -3
                  (0 +4 ) = 4  | (1 +4 ) = 5    |   4
                  (4 +2 ) = 6  | (5 +2 ) = 7    |   2

Example 2:

Input: nums = [1,2]
Output: 1
Explanation: Minimum start value should be positive. 

Example 3:

Input: nums = [1,-2,-3]
Output: 5

Constraints:

    1 <= nums.length <= 100
    -100 <= nums[i] <= 100
*/

/*
    Approach: Sum all the values in the array and track the minimum point of the sum. If it
              is greater than 0, return 1, otherwise return min * -1 + 1.
*/

    // O(n) runtime
    // O(1) space
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 36.9 MB, less than 69.25% of Java online submissions
    public int minStartValue(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; ++i)
        {
            sum += nums[i];
            nums[i] = sum;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; ++i)
        {
            if (nums[i] < min)
            {
                min = nums[i];
            }
        }
        return min > 0 ? 1 : min * -1 + 1;
    }
}
