public class Game_of_Life {

/*
Rating: Medium

According to the Wikipedia's article: "The Game of Life, also known simply as Life,
is a cellular automaton devised by the British mathematician John Horton Conway in
1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the
following four rules (taken from the above Wikipedia article):

    Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by over-population..
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board
given its current state. The next state is created by applying the above rules
simultaneously to every cell in the current state, where births and deaths occur
simultaneously.

Example:

Input: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]

Follow up:

    Could you solve it in-place? Remember that the board needs to
	be updated at the same time: You cannot update some cells first
	and then use their updated values to update other cells.
	
	
    In this question, we represent the board using a 2D array.
	In principle, the board is infinite, which would cause problems
	when the active area encroaches the border of the array.
	How would you address these problems?

*/

    public void gameOfLife(int[][] board) {
        // 0 = dead was dead
        // 1 = alive was alive
        // 2 = alive was dead
        // 3 = dead was alive
        // O(n) time where n = # cells on board
        // O(1) space, in place

        for(int i = 0; i < board.length; ++i)
        {
            for(int k = 0; k < board[0].length; ++k)
            {
                int number_neighbors = 0;
                if (i - 1 >= 0) //top
                {
                    if(board[i - 1][k] == 1 ||
                       board[i - 1][k] == 3)
                    {
                        ++number_neighbors;
                    }
                }
                if(i - 1 >= 0 && k + 1 < board[0].length) //top-right
                {
                    if(board[i - 1][k + 1] == 1 ||
                       board[i - 1][k + 1] == 3)
                    {
                        ++number_neighbors;
                    }
                }
                if(k + 1 < board[0].length) //right
                {
                    if(board[i][k + 1] == 1 ||
                       board[i][k + 1] == 3)
                    {
                        ++number_neighbors;
                    }
                }
                if(i + 1 < board.length && k + 1 < board[0].length) //bottom-right
                {
                    if(board[i + 1][k + 1] == 1 ||
                       board[i + 1][k + 1] == 3)
                    {
                        ++number_neighbors;
                    }
                }
                if (i + 1 < board.length) //bottom
                {
                    if(board[i + 1][k] == 1 ||
                       board[i + 1][k] == 3)
                    {
                        ++number_neighbors;
                    }
                }
                if (i + 1 < board.length && k - 1 >= 0) //bottom-left
                {
                    if(board[i + 1][k - 1] == 1 ||
                       board[i + 1][k - 1] == 3)
                    {
                        ++number_neighbors;
                    }
                }
                if(k - 1 >= 0) // left
                {
                    if(board[i][k - 1] == 1 ||
                       board[i][k - 1] == 3)
                    {
                        ++number_neighbors;
                    }
                }
                if (i - 1 >= 0 && k - 1 >= 0)
                {
                    if(board[i - 1][k - 1] == 1 ||
                       board[i - 1][k - 1] == 3)
                    {
                        ++number_neighbors;
                    }
                }
                
                if (number_neighbors < 2 && board[i][k] == 1) //condition 1
                {
                    
                    board[i][k] = 3;
                }
                else if (number_neighbors == 2 || //condition 2
                         number_neighbors == 3 && board[i][k] == 1)
                {
                     ; // do nothing
                }
                else if (number_neighbors > 3 && //condition 3
                        board[i][k] == 1)
                {
                    board[i][k] = 3;
                }
                else if (number_neighbors == 3 && board[i][k] == 0) //condition 4
                {
                    board[i][k] = 2;
                }
            }
        }
        for(int i = 0; i < board.length; ++i)
        {
            for(int k = 0; k < board[0].length; ++k)
            {
                if(board[i][k] == 2)
                {
                    board[i][k] = 1;
                }
                if(board[i][k] == 3)
                {
                    board[i][k] = 0;
                }
            }
        }
        return;
    }
}
