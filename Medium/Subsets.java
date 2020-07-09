import java.util.ArrayList;
import java.util.List;

public class Subsets {
/*
Rating: Medium

Given a set of distinct integers, nums, return
all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

    /*
    Approach: Backtracking -- recurse through the indices of the numbers list. Add the current index
              value to the list and recurse. Then remove the current value and recurse again.

    Follow the recursive calls:
              [1, 2, 3]

              1, 2, 3 --> add
              1, 2    --> add
              1, 3    --> add
              1       --> add
              2, 3    --> add
              2       --> add
              3       --> add
              null    --> add
    
    */

    // O(n * 2^n) time -- generate 2^b subsets, and for each subset, copy it into the answer list
    // O(n * 2^n) space -- generate 2^n subsets and each subset has n space potentially
    // Runtime: 1 ms, faster than 64.05% of Java online submissions
    // Memory Usage: 39.6 MB, less than 72.68% of Java online submissions
    public void generate(ArrayList<List<Integer>> sets, ArrayList<Integer> curr,
            int[] nums, int idx)
    {
        if (idx == nums.length)
        {
            ArrayList<Integer> entry = new ArrayList<Integer>(curr);
            sets.add(entry);
        }
        else
        {
            curr.add(nums[idx]);
            generate(sets, curr, nums, idx + 1);
            curr.remove(curr.size() - 1);
            generate(sets, curr, nums, idx + 1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<List<Integer>> sets = new ArrayList<List<Integer>>();
        generate(sets, new ArrayList<Integer>(), nums, 0);
        return sets;
    }
}
