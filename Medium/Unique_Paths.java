public class Unique_Paths {
/*
Rating: Medium

A robot is located at the top-left corner of a m x n grid (marked 'Start'
in the diagram below).

The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid (marked
'Finish' in the diagram below).

How many possible unique paths are there?

Above is a 7 x 3 grid. How many possible unique paths are there?

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right

Example 2:

Input: m = 7, n = 3
Output: 28

Constraints:

    1 <= m, n <= 100
    It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
*/

/*
    Approach:   Dynamic Programming -- tabulation
                Create a matrix that is the dimension mxn.

                Initialize the first row and first column to 1.

                Iterate through the grid starting after the first column and first row,
                set the current grid value to the sum of the cell directly above and the cell
                directly to the left.

                Return the dp array value in the bottom right.
*/

    // O(m * n) time
    // O(m * n) space
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 36.5 MB, less than 45.15% of Java online submissions
    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];
        for (int i = 0 ; i < m; ++i)
        {
            grid[i][0] = 1;
        }
        for (int i = 0 ; i < n; ++i)
        {
            grid[0][i] = 1;
        }
        for (int i = 1; i < m; ++i)
        {
            for (int k = 1; k < n; ++k)
            {
                grid[i][k] = grid[i][k - 1] + grid[i - 1][k];
            }
        }
        return grid[m - 1][n - 1];
    }
}
