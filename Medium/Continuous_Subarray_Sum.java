import java.util.HashMap;

public class Continuous_Subarray_Sum {
/*
Rating: Medium

Given a list of non-negative numbers and a target integer k, write a
function to check if the array has a continuous subarray of size at
least 2 that sums up to a multiple of k, that is, sums up to n*k where
n is also an integer.

Example 1:

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

Example 2:

Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 

Constraints:

    The length of the array won't exceed 10,000.
    You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
*/

    // Runtime: 2 ms, faster than 99.44% of Java online submissions
    // Memory Usage: 40 MB, less than 66.63% of Java online submissions
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2)
        {
            return false;
        }
        else if (k == 1 || k == -1)
        {
            return true;
        }
        if (k < 0)
        {
            k = k * -1;
        }
        int[] prefix_sums = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; ++i)
        {
            sum += nums[i];
            if (i > 0 && nums[i] == 0 && nums[i - 1] == 0) // anything is mult of 0
            {                                              // 
                return true;
            }
            prefix_sums[i] = sum;
        }
        if (k == 0) // any mu
        {
            return false;
        }
        HashMap<Integer, Integer> mods_k = new HashMap<Integer, Integer>();
        mods_k.put(0, -1);
        for (int i = 0; i < prefix_sums.length; ++i)
        {
            int diff = prefix_sums[i] % k;
            if (mods_k.containsKey(diff) && prefix_sums[i] >= k &&
                    mods_k.get(diff) < i - 1)
            {
                return true;
            }
            if (!mods_k.containsKey(diff))
            {
                mods_k.put(diff, i);
            }
        }
        return false;   
    }
}
