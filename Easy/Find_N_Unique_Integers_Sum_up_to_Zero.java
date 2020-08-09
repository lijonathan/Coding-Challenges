public class Find_N_Unique_Integers_Sum_up_to_Zero {

/*
Rating: Easy

Given an integer n, return any array containing n unique
integers such that they add up to 0. 

Example 1:

Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].

Example 2:

Input: n = 3
Output: [-1,0,1]

Example 3:

Input: n = 1
Output: [0]

Constraints:

    1 <= n <= 1000
*/

/*
    Approach: Single loop from 0 to n - 2 inclusive. i increments by 2 each time.
              Set index i to i and index i + 1 to the negative of i + 1. If it is an odd number
              set the last index value to zero.
*/


    // O(n) runtime
    // O(1) space excluding the output array
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 37.8 MB, less than 53.31% of Java online submissions
    public int[] sumZero(int n) {
        int[] sum_zero = new int[n];
        for (int i = 0; i < n - 1; i = i + 2)
        {
            sum_zero[i] = i + 1;
            if (n > 1)
            {
                sum_zero[i + 1] = -(i + 1);
            }
        }
        if (n % 2 != 0)
        {
            sum_zero[sum_zero.length - 1] = 0;
        }
        return sum_zero;
    }
}
