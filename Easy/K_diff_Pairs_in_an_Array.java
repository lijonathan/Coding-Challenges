import java.util.ArrayList;
import java.util.HashSet;

public class K_diff_Pairs_in_an_Array {

/*
Rating: Easy

Given an array of integers and an integer k, you need to find the number
of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j),
where i and j are both numbers in the array and their absolute difference is k.

Example 1:

Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.

Example 2:

Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).

Example 3:

Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).

Note:

    The pairs (i, j) and (j, i) count as the same pair.
    The length of the array won't exceed 10,000.
    All the integers in the given input belong to the range: [-1e7, 1e7].
*/

/*
    Approach: HashSet of Integers and HashSet of ArrayLists which are seen pairs.
              Iterate through the array and get the number that is larger than the
              current number by k and the number that is smaller than the current number by
              k. Make an arraylist of size two with the smaller number first. If it is not in the
              HashSet of pairs, put it in. Add the current number to the set of integers for seen numbers.

              Return the size of the pairs hashset.
*/

    // O(n) runtime
    // O(n) space
    // Runtime: 11 ms, faster than 53.37% of Java online submissions
    // Memory Usage: 41.2MB
    public int findPairs(int[] nums, int k) {
        if (k < 0)
        {
            return 0;
        }
        HashSet<ArrayList<Integer>> pairs = new HashSet<ArrayList<Integer>>();
        HashSet<Integer> hs = new HashSet<Integer>();
        for (int i = 0; i < nums.length; ++i)
        {
            int upper = nums[i] + k;
            int lower = nums[i] - k;
            if (hs.contains(upper))
            {
                ArrayList<Integer> al = new ArrayList<Integer>();
                al.add(nums[i]);
                al.add(upper);
                pairs.add(al);
            }
            if (hs.contains(lower))
            {
                ArrayList<Integer> al = new ArrayList<Integer>();
                al.add(lower);
                al.add(nums[i]);
                pairs.add(al);
            }
            hs.add(nums[i]);
        }
        return pairs.size();
    }
}
