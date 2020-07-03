public class Minimum_Size_Subarray_Sum {
/*
Rating: Medium

Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s.
If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.

Follow up:
If you have figured out the O(n) solution,
try coding another solution of which the time complexity is O(n log n). 

*/

/*
    Approach: Two pointer approach from the left. Move right pointer right until the sum
              >= s. Move left pointer right until subarray sum < s. If the subarray sum is
              greater than s, check if the current subarray is smaller than the previous minimum
              size seen.
*/

// asymptotically runtime and space usage are correct

    // O(n) runtime
    // O(1) space
    // Runtime: 2ms beats 34.78% of Java online submissions
    // Memory Usage: 42.1MB beats 10.66% of Java online submissions
    public int minSubArrayLen(int s, int[] nums) {
        // two pointer approach
        int l_index = 0;
        int r_index = 0;
        int min_length = Integer.MAX_VALUE;
        int sum = 0;
        while(r_index < nums.length)
        {
            sum += nums[r_index];
            while(l_index <= r_index && sum >= s)
            {
                if (r_index - l_index + 1 < min_length)
                {
                    min_length = r_index - l_index + 1;
                }
                sum -= nums[l_index];
                ++l_index;
            }
            ++r_index;
        }
        return min_length != Integer.MAX_VALUE ? min_length : 0;
    }
}
