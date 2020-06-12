class Word_Search {

/*
Rating: Medium

Given a 2D board and a word, find
if the word exists in the grid.

The word can be constructed from letters
of sequentially adjacent cell, where "adjacent"
cells are those horizontally or vertically
neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.

 

Constraints:

    board and word consists only of lowercase and uppercase English letters.
    1 <= board.length <= 200
    1 <= board[i].length <= 200
    1 <= word.length <= 10^3

*/
    /*
    Approach: Backtracking:
    https://leetcode.com/explore/learn/card/recursion-ii/472/backtracking/2793/
    At each board position, trigger a DFS. Use a visited board to track if a particular
    spot has already been visited in a partiular DFS. Prior to returning the value,
    reset that spot to unvisited. <-- Key to backtracking

     */
    private boolean backtrack(char[][] board, String word, int count,
            boolean[][] visited, int x, int y)
    {
        if (x < 0 || x >= board[0].length || y < 0 || y >= board.length) // order of if else matters
        {
            return false;
        }
        else if (word.charAt(count) != board[y][x])
        {
            return false;
        }
        else if (visited[y][x])
        {
            return false;
        }
        else if (count == word.length() - 1) // need this to occur after all failure cases have been checked
        {
            return word.charAt(count) == board[y][x];
        }
        else
        {
            visited[y][x] = true; // key lines here, this is where mark visited path
            boolean return_val = backtrack(board, word, count + 1, visited, x - 1, y) ||
                    backtrack(board, word, count + 1, visited, x + 1, y) ||
                    backtrack(board, word, count + 1, visited, x, y - 1) ||
                    backtrack(board, word, count + 1, visited, x, y + 1);
            visited[y][x] = false; // reset visited spot to unvisited before returning
            return return_val;     // up the recursion tree. Therefore, cannot directly return the recursive call
        }
    }
    public boolean exist(char[][] board, String word) {
        // dfs on each letter that has starting letter
        // Time 5ms beat 82.79% of Java submissions
        // Space 41.6 MB beats 44.21% of Java submissions
        // O(n * L^4) where L is the length of word. Raised to the fourth
        // because recursive call branches in 4 directions per word position
        // O(n) space for the backtracking board
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i)
        {
            for (int k = 0; k < board[0].length; ++k)
            {
                if (backtrack(board, word, 0, visited, k, i))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
