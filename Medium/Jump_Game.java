public class Jump_Game {

/*
Rating: Medium

Given an array of non-negative integers, you are initially
positioned at the first index of the array.

Each element in the array represents your maximum jump
length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what.
Its maximum jump length is 0, which makes it impossible to reach the last index.



Constraints:

    1 <= nums.length <= 3 * 10^4
    0 <= nums[i][j] <= 10^5
*/

/*
    Approach: Keep track of the furthest index that can be while iterating through the array.
              Start at index 0, the furthest that can be reached is 0 + nums[0]. If the current
              index is <= the furthest, update the furthest with the max of the furthest and the
              current index + nums[current index]. If i > furthest index, return false. Otherwise if
              the furthest >= last index, return true.
*/


    // O(n) runtime
    // O(1) space
    // Runtime: 1 ms, faster than 99.32% of Java online submissions
    // Memory Usage: 41.7 MB, less than 57.28% of Java online submissions
    public boolean canJump(int[] nums) {
        if (nums.length == 1)
        {
            return true;
        }
        int furthest = 0;
        for (int i = 0; i < nums.length; ++i)
        {
            if (i <= furthest)
            {
                int jump_length = nums[i];
                if (i + jump_length >= nums.length - 1)
                {
                    return true;
                }
                else if (i + jump_length > furthest)
                {
                    furthest = i + jump_length;
                }
            }
            else
            {
                return false;
            }
        }
        return furthest >= nums.length - 1;
    }
}
