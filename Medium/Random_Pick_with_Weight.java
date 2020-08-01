import java.util.Random;

public class Random_Pick_with_Weight {

/*
Rating: Medium

Given an array w of positive integers, where w[i] describes the weight of
index i(0-indexed), write a function pickIndex which randomly picks an
index in proportion to its weight.

For example, given an input list of values w = [2, 8], when we pick up
a number out of it, the chance is that 8 times out of 10 we should pick
the number 1 as the answer since it's the second element of the array (w[1] = 8).



Example 1:

Input
["Solution","pickIndex"]
[[[1]],[]]
Output
[null,0]

Explanation
Solution solution = new Solution([1]);
solution.pickIndex(); // return 0. Since there is only one single element
                      // on the array the only option is to return the first element.

Example 2:

Input
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output
[null,1,1,1,1,0]

Explanation
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It's returning the second element (index = 1) 
                      // that has probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It's returning the first element
                      // (index = 0) that has probability of 1/4.

Since this is a randomization problem, multiple answers are allowed
so the following outputs can be considered correct :
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.



Constraints:

    1 <= w.length <= 10000
    1 <= w[i] <= 10^5
    pickIndex will be called at most 10000 times.
*/

/*
    Approach: Construct a prefixes sum array where each index of the array is the cumulative
              sum of the weights array through that particular index.

              To pick an index, generate a random value between the total sum and 1 inclusive.
              Binary Search through the prefixes array until the index is found where the random
              value is less than the element in the value in the current index of the prefixes sum
              array but greater than the value in the previous index.
*/


    // O(n) space for the prefixes array where n = # of values in w
    // O(n) time for the constructor to construct the prefixes array
    // O(logn) for pickIndex through binarySearch
    // Runtime: 21 ms, faster than 84.56% of Java online submissions
    // Memory Usage: 43.4 MB, less than 81.27% of Java online submissions
    class Solution {
        
        Random r;
        int total;
        int[] prefixes;
        public Solution(int[] w) {
            total = 0;
            prefixes = new int[w.length];
            for (int i = 0; i < w.length; ++i)
            {
                total += w[i];
                prefixes[i] = total;
            }
            r = new Random();
        }
        
        public int bSearch(int target)
        {
            int low = 0;
            int high = prefixes.length - 1;
            while(low <= high)
            {
                int mid = (low + high) / 2;
                if (prefixes[mid] == target)
                {
                    return mid;
                }
                else if (prefixes[mid] > target)
                {
                    if (mid == 0)
                    {
                        return 0;
                    }
                    else if (prefixes[mid - 1] < target)
                    {
                        return mid;
                    }
                    else if (prefixes[mid - 1] == target)
                    {
                        return mid - 1;
                    }
                    else
                    {
                        high = mid - 1;
                    }
                }
                else
                {
                    if (mid == prefixes.length - 1)
                    {
                        return mid;
                    }
                    low = mid + 1;
                }
            }
            return -1;
        }
        
        public int pickIndex() {
            int r_val = r.nextInt(total);
            r_val += 1;
            int val = bSearch(r_val);
            return val;
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(w);
     * int param_1 = obj.pickIndex();
     */
}
