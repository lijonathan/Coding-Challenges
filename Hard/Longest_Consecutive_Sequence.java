import java.util.HashSet;

public class Longest_Consecutive_Sequence {
/*
Rating: Hard

Given an unsorted array of integers,
find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements
sequence is [1, 2, 3, 4]. Therefore its length is 4.
*/

/*
    Approach: Add all numbers to a HashSet. Iterate through the hashset but do not
              visit numbers already seen. Expand left and right from the current number checking
              if they exist in the nums HashSet and tracking the longest sequence.
              Add all seen numbers into a seen Hashset.
*/

    // O(n) runtime
    // O(n) space
    // Runtime: 6 ms, faster than 41.83% of Java online submissions
    // Memory Usage: 40.9 MB, less than 26.81% of Java online submissions
    public int longestConsecutive(int[] nums) {

        HashSet<Integer> set = new HashSet<Integer>();
        HashSet<Integer> seen = new HashSet<Integer>();
        for (int i = 0; i < nums.length; ++i)
        {
            set.add(nums[i]);
        }

        int left;
        int right;
        int max_seq = 0;
        for (Integer x: set)
        {
            if (!seen.contains(x))
            {
                seen.add(x);
                int count = 1;
                left = x - 1;
                right = x + 1;
                while (set.contains(left) || set.contains(right))
                {
                    if (set.contains(left))
                    {
                        ++count;
                        seen.add(left);
                        --left;
                    }
                    if (set.contains(right))
                    {
                        ++count;
                        seen.add(right);
                        ++right;
                    }
                }
                if (count > max_seq)
                {
                    max_seq = count;
                }
            }
        }
        return max_seq;
    }
}
