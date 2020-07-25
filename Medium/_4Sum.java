import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class _4Sum {
/*
Rating: Medium

Given an array nums of n integers and an integer target, are there
elements a, b, c, and d in nums such that a + b + c + d = target?
Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/

/*
    Approach: Utilize two pointer approach for sum in sorted array. Sort the array.
              Create a HashSet of quadruplets already seen before.

              Outer loop iterates from 0 to n - 4, inner loop from outer_loop + 1 to
              n - 3, and two pointer approach from inner_loop + 1 and end both towards the middle
              (two pointer approach for sorted array).

              This approach can be extended to k_sum for a O(n^(k - 1)) by adding more looping for each
              additional k.
*/


    // O(n^3) time
    // O(n) space for sorting, HashSet of seen quadruplets
    // Runtime: 14 ms, faster than 73.20% of Java online submissions
    // Memory Usage: 40.1 MB, less than 31.90% of Java online submissions
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        HashSet<ArrayList<Integer>> seen = new HashSet<ArrayList<Integer>>();
        ArrayList<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length - 3; ++i)
        {
            if (i > 0 && nums[i] == nums[i - 1])
            {
                continue;
            }
            for (int k = i + 1; k < nums.length - 2; ++k)
            {
                if (k > i + 1 && nums[k] == nums[k - 1])
                {
                    continue;
                }
                int l_ptr = k + 1;
                int r_ptr = nums.length - 1;
                int sum = nums[i] + nums[k];
                while(l_ptr < r_ptr)
                {
                    int four_total = sum + nums[l_ptr] + nums[r_ptr];
                    if (four_total == target)
                    {
                        ArrayList<Integer> entry = new ArrayList<Integer>();
                        entry.add(nums[i]);
                        entry.add(nums[k]);
                        entry.add(nums[l_ptr]);
                        entry.add(nums[r_ptr]);
                        if (!seen.contains(entry))
                        {
                            quadruplets.add(entry);
                            seen.add(entry);
                        }
                        ++l_ptr;
                    }
                    else if (four_total < target)
                    {
                        ++l_ptr;
                    }
                    else
                    {
                        --r_ptr;
                    }
                }
            }
        }
        return quadruplets;
    }
}
