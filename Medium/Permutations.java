import java.awt.List;
import java.util.ArrayList;

class Permutations {

/*
Rating: Medium

Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

*/
    private void generate(ArrayList<List<Integer>> combos, int[] nums,
            ArrayList<Integer> curr)
    {
        if (curr.size() == nums.length)
        {
            combos.add(curr);
        }
        else
        {
            for (int i = 0; i < nums.length; ++i)
            {
                int k;
                for (k = 0; k < curr.size(); ++k)
                {
                    if (curr.get(k) == nums[i])
                    {
                        break;
                    }
                }
                if (k == curr.size())
                {
                    ArrayList<Integer> new_list = new ArrayList<Integer>(curr);
                    new_list.add(nums[i]);
                    generate(combos, nums, new_list);
                }
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        // O(n! * n) time a bit faster than that, slower than O(n!) time
        // O(n!) space for the total number of combinations
        // beat 90.89% of Java submissions for speed, beat 73.35% of
        // Java submissions for memory
        ArrayList<List<Integer>> combos = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length; ++i)
        {
            ArrayList<Integer> curr = new ArrayList<Integer>();
            curr.add(nums[i]);
            generate(combos, nums, curr);
        }
        return combos;
    }
}
