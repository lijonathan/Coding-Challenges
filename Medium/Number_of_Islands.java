import java.util.ArrayDeque;

public class Number_of_Islands {

/*
Rating: Medium

Given a 2d grid map of '1's (land) and '0's (water),
count the number of islands. An island is surrounded
by water and is formed by connecting adjacent lands
horizontally or vertically. You may assume all four edges
of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3

*/

    public void bfs(char[][] grid, int y, int x)
    {
        ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
        dq.offerLast(x);
        dq.offerLast(y);
        grid[y][x] = '0';
        while(dq.size() > 0)
        {
            int horiz = dq.removeFirst();
            int vert = dq.removeFirst();
            // set a space to marked when it is added into the
            // queue, not after it is popped to prevent duplicate
            // checking of spaces
            if(vert - 1 >= 0)
            {
                if(grid[vert - 1][horiz] == '1')
                {
                    dq.offerLast(horiz);
                    dq.offerLast(vert - 1);
                    grid[vert - 1][horiz] = '0';
                }
            }
            if (vert + 1 < grid.length)
            {
                if (grid[vert + 1][horiz] == '1')
                {
                    dq.offerLast(horiz);
                    dq.offerLast(vert + 1);
                    grid[vert + 1][horiz] = '0';
                }
            }
            if(horiz - 1 >= 0)
            {
                if (grid[vert][horiz - 1] == '1')
                {
                    dq.offerLast(horiz - 1);
                    dq.offerLast(vert);
                    grid[vert][horiz - 1] = '0';
                }
            }
            if (horiz + 1 < grid[0].length)
            {
                if (grid[vert][horiz + 1] == '1')
                {
                    dq.offerLast(horiz + 1);
                    dq.offerLast(vert);
                    grid[vert][horiz + 1] = '0';
                }
            }
        }
        return;
    }
    public int numIslands(char[][] grid) {
        // O(mn) m = # rows, n = # cols
        // O(min(m, n)) size of the queue if everything
        // is land
        int num_islands = 0;
        for(int i = 0; i < grid.length; ++i)
        {
            for(int k = 0; k < grid[0].length; ++k)
            {
                if(grid[i][k] == '1')
                {
                    bfs(grid, i, k);
                    num_islands += 1;
                }
            }
        }
        return num_islands;
    }
}
