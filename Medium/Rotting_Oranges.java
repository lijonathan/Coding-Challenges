import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {

/*
Rating: Medium

In a given grid, each cell can have one of three values:

    the value 0 representing an empty cell;
    the value 1 representing a fresh orange;
    the value 2 representing a rotten orange.

Every minute, any fresh orange that is adjacent (4-directionally) to
a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell
has a fresh orange.  If this is impossible, return -1 instead.

 

Example 1:

Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is
never rotten, because rotting only happens 4-directionally.

Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.

 

Note:

    1 <= grid.length <= 10
    1 <= grid[0].length <= 10
    grid[i][j] is only 0, 1, or 2.
*/

    public boolean fresh(int[][] grid, int x, int y)
    {
        return grid[y][x] != 2 && grid[y][x] != 0;
    }
    
    private boolean valid_pt(int[][] grid, int x, int y)
    {
        return x >= 0 && x < grid[0].length && y >= 0 && y < grid.length;
    }
    
    private boolean increment(int[][] grid, int x, int y, int n_val)
    {
        if (grid[y][x] > n_val || grid[y][x] == 1)
        {
            grid[y][x] = n_val;
            return true;
        }
        return false;
    }
    public int orangesRotting(int[][] grid) {
        // O(n) time where n is the number of cells in grid, O(n) space for the Queue
        // O(2n) each point has two values for the coordinates represented by ArrayList
        int no_fresh = 0;
        ArrayDeque<ArrayList<Integer>> queue = new ArrayDeque<ArrayList<Integer>>();
        // add all rotted oranges into queue
        for (int i = 0; i < grid.length; ++i)
        {
            for(int k = 0; k < grid[0].length; ++k)
            {
                if(grid[i][k] == 2)
                {
                    ArrayList<Integer> coords_rot = new ArrayList<Integer>();
                    coords_rot.add(k);
                    coords_rot.add(i);
                    queue.add(coords_rot);
                }
                if (grid[i][k] == 1)
                {
                    no_fresh++;
                }
            }
        }
        if (no_fresh == 0)
        {
            return 0;
        }
        // KEY: BFS out from each rotten orange. BFS naturally occurs in rounds, so each orange that gets
        // popped out from the front of the queue has the same distance from a rotting orange in a
        // particular round
        ArrayList<Integer> curr;
        while (queue.size() > 0)
        {
            curr = queue.remove();
            int x = curr.get(0);
            int y = curr.get(1);
            ArrayList<Integer> entry;
            if (valid_pt(grid, x - 1, y) && fresh(grid, x - 1, y)) // left
            {
                if(increment(grid, x - 1, y, grid[y][x] + 2))
                {
                    entry = new ArrayList<Integer>();
                    entry.add(x - 1);
                    entry.add(y);
                    queue.add(entry);
                }
            }
            if (valid_pt(grid, x, y - 1) && fresh(grid, x, y - 1)) // top
            {
                if(increment(grid, x, y - 1, grid[y][x] + 2))
                {
                    entry = new ArrayList<Integer>();
                    entry.add(x);
                    entry.add(y - 1);
                    queue.add(entry);
                }
            }
            if (valid_pt(grid, x + 1, y) && fresh(grid, x + 1, y)) // right
            {
                if(increment(grid, x + 1, y, grid[y][x] + 2))
                {
                    entry = new ArrayList<Integer>();
                    entry.add(x + 1);
                    entry.add(y);
                    queue.add(entry);
                }
            }
            if (valid_pt(grid, x, y + 1) && fresh(grid, x, y + 1)) // bottom
            {
                if(increment(grid, x, y + 1, grid[y][x] + 2))
                {
                    entry = new ArrayList<Integer>();
                    entry.add(x);
                    entry.add(y + 1);
                    queue.add(entry);
                }
            }
        }
        // find largest value of rotten orange, which signifies largest time to rot
        int min = 0;
        for (int i = 0; i < grid.length; ++i)
        {
            for (int k = 0; k < grid[0].length; ++k)
            {
                if (grid[i][k] == 1)
                {
                    return -1;
                }
                if (grid[i][k] > min && grid[i][k] != 2)
                {
                    min = grid[i][k];
                }
            }
        }
        // calculate time to rot. 
        return (min - 2) / 2;       
    }
}
