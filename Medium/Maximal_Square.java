public class Maximal_Square {
/*
Rating: Medium

Given a 2D binary matrix filled with 0's and 1's, find the
largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
*/

/*
    Approach: Dynamic Programming - Create an array that is the size of the number of columns.

              If the previous row < 0 or previous column is < 0 and the
              current value is 1, set the current dp array index to 1.

              If the current value is zero set the dp of the column index to 0.

              Otherwise, save the current value of the dp[k]. This marks the spot that is directly
              above the current spot being looked at. prev = the spot to the top left of the current spot.

              Set the dp[k] to the min of the top left(prev), top(dp[k]), and left(dp[k - 1]) + 1.
              Set prev = temp.
*/

    // O(n * m) runtime, n = # of rows, m = # of cols
    // O(m) space
    // Runtime: 3 ms, faster than 98.91% of Java online submissions
    // Memory Usage: 43.3 MB, less than 43.62% of Java online submissions
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
        {
            return 0;
        }
        int[] dp = new int[matrix[0].length];
        int max = 0;
        int prev = 0;
        for (int i = 0; i < matrix.length; ++i)
        {
            for (int k = 0; k < matrix[0].length; ++k)
            {
                if (matrix[i][k] == '1')
                {
                    int temp = dp[k];
                    if (k - 1 < 0 || i - 1 < 0)
                    {
                        dp[k]= 1;
                    }
                    else
                    {
                        dp[k] = Math.min(Math.min(dp[k - 1], dp[k]), prev) + 1;
                    }
                    prev = temp;
                }
                else
                {
                    dp[k] = 0;
                }
                if (dp[k] > max)
                {
                    max = dp[k];
                }
            }
        }
        return max * max;
    }
}
