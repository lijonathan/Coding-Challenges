public class House_Robber {
/*
Rating: Easy

You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed, the only constraint
stopping you from robbing each of them is that adjacent houses have
security system connected and it will automatically contact the police
if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money
of each house, determine the maximum amount of money you can rob tonight
without alerting the police.

 

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.

Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12. 

Constraints:

    0 <= nums.length <= 100
    0 <= nums[i] <= 400
*/

/*
    Approach: Dynamic Programming -- bottom up = tabulation

              Create a n sized tabulate array. Set the first index value
              equal to nums[0] and the second array value equal to max(nums[0], nums[1]). -->
              At the second house base case, only rob the house that would give more money.

              Iterate from index 3 to the end of the nums array. At each step, put into the tabulate
              table at that index the max(tabulate[index - 2] + nums[index], tabulate[index - 1]), represents
              the two options of either robbing the current index house or not.

              Return the last index value of tabulate table.
*/


    // O(n) runtime
    // O(n) space for recursive call stack
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 38.6 MB, less than 5.00% of Java online submissions
    public void value(int[] nums, int index, int[] memoize)
    {
        if (index == nums.length - 1)
        {
            memoize[index] = Math.max(nums[index] + memoize[index - 2],
                                               memoize[index - 1]);
            return;
        }
        else if (index > nums.length - 1)
        {
            return;
        }
        else
        {
            memoize[index] = Math.max(nums[index] + memoize[index - 2],
                                               memoize[index - 1]);
            value(nums, index + 1, memoize);
        }
    }
    public int rob(int[] nums) {
        // base case n = 1 --> rob
        // case n = 2 --> rob larger
        // case n = 3, rob 1 & 3 or larger of (1 & 2)
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        else if (nums.length == 1)
        {
            return nums[0];
        }
        else if (nums.length == 2)
        {
            return Math.max(nums[0], nums[1]);
        }
        int[] memoize = new int[nums.length];
        memoize[0] = nums[0];
        memoize[1] = Math.max(nums[0], nums[1]);
        value(nums, 2, memoize);
        return memoize[memoize.length - 1];
    }
}
