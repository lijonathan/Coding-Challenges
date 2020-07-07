import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Combination_Sum {
/*
Rating: Medium

Given a set of candidate numbers (candidates) (without duplicates)
and a target number (target), find all unique combinations in candidates
where the candidate numbers sums to target.

The same repeated number may be chosen from candidates
unlimited number of times.

Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]

Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/

/*
    Approach: Backtracking - 
              Add the current value to the array and recurse with the current index and then
              after the recursive call returns, remove the value from the array before the next iteration.


                    [1, 2, 3, 4, 5] : 10
Recurisve calls:    1
                    1 1
                    1 1 1
                    1 1 1 1
                    ...
                    ...
                    1 1 1 1 1 1 1 1 1 1 --> add to list
                    1 1 1 1 1 1 1 1 1   --> back up recursive call stack, last 1 removed
                    1 1 1 1 1 1 1 1     --> up recursive call stack, remoes 1
                    1 1 1 1 1 1 1 1 2   --> add to list

                    ..
                    ..
                    ..
*/


    // O(2^n) runtime, worst case is generate all combinations of numbers = 2^n, each item
    // is either in the set or not
    // O(2^n) space
    // Runtime: 3 ms, faster than 77.98% of Java online submissions
    // Memory Usage: 39.5 MB, less than 79.31% of Java online submissions
    public void generate(ArrayList<List<Integer>> combos, int[] candidates,
            ArrayList<Integer> curr, int target, int sum,
            HashSet<ArrayList<Integer>> seen, int indx)
    {
        if (target == sum)
        {
            if (!seen.contains(curr))
            {
                ArrayList<Integer> entry = new ArrayList<Integer>(curr);
                seen.add(entry);
                combos.add(entry);
            }
        }
        else
        {
            for (int i = indx; i < candidates.length; ++i)
            {
                if (candidates[i] > target)
                {
                    break;
                }
                else if (sum + candidates[i] <= target)
                {
                    curr.add(candidates[i]);
                    generate(combos, candidates, curr, target, sum + candidates[i], seen, i);
                    curr.remove(curr.size() - 1);
                }
            }
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        ArrayList<List<Integer>> combos = new ArrayList<List<Integer>>();
        HashSet<ArrayList<Integer>> seen = new HashSet<ArrayList<Integer>>();
        generate(combos, candidates, new ArrayList<Integer>(), target, 0, seen, 0);
        return combos;
    }
}
