public class Max_Area_of_Island {
/*
Rating: Medium

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
(representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no
island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]

Given the above grid, return 6. Note the answer is not 11, because
the island must be connected 4-directionally.

Example 2:

[[0,0,0,0,0,0,0,0]]

Given the above grid, return 0.

Note: The length of each dimension in the given grid does not exceed 50.
*/

/*
    Approach: DFS every time an island is hit, flipping all 1's to 0's as they are visited
              in the island and counting up the area of the island. Return the largest island.
*/


    // O(n) runtime where n = # of cells in the grid
    // O(n) space for the recursive stack
    // Runtime: 2 ms, faster than 99.76% of Java online submissions
    // Memory Usage: 39.5 MB, less than 96.32% of Java online submissions
    public int dfs(int[][] grid, int x, int y)
    {
        if (x >= 0 & x < grid[0].length && y >= 0 && y < grid.length && grid[y][x] == 1)
        {
            grid[y][x] = 0;
            int area = dfs(grid, x - 1, y) + dfs(grid, x + 1, y) + dfs(grid, x, y - 1) + dfs(grid, x, y + 1) + 1;
            return area;
        }
        else
        {
            return 0;
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; ++i)
        {
            for (int k = 0; k < grid[0].length; ++k)
            {
                if (grid[i][k] == 1)
                {
                    int area = dfs(grid, k, i);
                    if (area > max)
                    {
                        max = area;
                    }
                }
            }
        }
        return max;
    }
}
