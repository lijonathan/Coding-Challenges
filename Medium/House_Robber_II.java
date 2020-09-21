public class House_Robber_II {
/*
Rating: Medium

You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed. All houses at this
place are arranged in a circle. That means the first house is the neighbor
of the last one. Meanwhile, adjacent houses have security system connected
and it will automatically contact the police if two adjacent houses were broken
into on the same night.

Given a list of non-negative integers representing the amount of money
of each house, determine the maximum amount of money you can rob tonight
without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.

Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
*/

/*
    Approach: The same as House Robber I except that the last and first houses are connected.
              Create two tabulation arrays of the size of the number of houses.

              The recurrence is still the same, max(dp[i - 2] + nums[i], dp[i - 1]) except one starts
              at i = 1 to nums.length - 1 and the other dp array goes from 0 to nums.length - 2.

              Initialize dp[0] = nums[0] and dp[1] = Math.max(nums[1], dp[0]).
              Initialize dp_2[1] = nums[1].

              Afterwards, take the max of dp[nums.length - 2] and dp_2[nums.length - 1].
*/

    // O(n) runtime
    // O(n) space
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 37.1 MB, less than 50.53% of Java online submissions
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
        {
            return 0;
        }
        else if (nums.length == 1)
        {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        int[] dp_2 = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]);

        dp_2[1] = nums[1];
        for (int i = 2; i < nums.length; ++i)
        {
            if (i < nums.length - 1)
            {
                dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
            }
            dp_2[i] = Math.max(nums[i] + dp_2[i - 2], dp_2[i - 1]);
        }

        return Math.max(dp[nums.length - 2], dp_2[nums.length - 1]);
    }
}
