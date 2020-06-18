class Minimum_Path_Sum {
/*

Rating: Medium

Given a m x n grid filled with non-negative numbers,
find a path from top left to bottom right which minimizes
the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.

*/
    
    public int minPathSum(int[][] grid) {
        // O(n) space where n is the size of grid
        // O(n) runtime where n is the number of cells in grid
        int[][] cost = new int[grid.length][grid[0].length];
        for(int i = 0; i < cost.length; ++i)
        {
            for (int k = 0; k < cost[0].length; ++k)
            {
                cost[i][k] = -1;
            }
        }
        
        cost[0][0] = grid[0][0];
        for (int i = 0; i < grid.length; ++i)
        {
            for (int k = 0; k < grid[0].length; ++k)
            {
                if (i - 1 >= 0)
                {
                    if (cost[i - 1][k] + grid[i][k] < cost[i][k]
                       || cost[i][k] == -1)
                    {
                        cost[i][k] = cost[i - 1][k] + grid[i][k];
                    }
                }
                if (k - 1 >= 0)
                {
                    if (cost[i][k - 1] + grid[i][k] < cost[i][k]
                       || cost[i][k] == -1)
                    {
                        cost[i][k] = cost[i][k - 1] + grid[i][k];
                    }
                }
            }
        }
        return cost[cost.length - 1][cost[0].length - 1];
    }
}
