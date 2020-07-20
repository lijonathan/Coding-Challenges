import java.util.HashMap;
import java.util.HashSet;

public class Design_Tic_Tac_Toe {
/*
Rating: Medium

Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:

    A move is guaranteed to be valid and is placed on an empty block.
    Once a winning condition is reached, no more moves is allowed.
    A player who succeeds in placing n of their marks in a horizontal,
    vertical, or diagonal row wins the game.

Example:

Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|

Follow up:
Could you do better than O(n2) per move() operation? 
*/

/*
    Approach: Two HashMaps, one for each player. The key is the row, and the value
              is a HashSet for each column in that row that the player has a piece on.

              To check for a winning state - Check is the HashSet of the row that the
              respective player made a move on equals the length of the board. Check
              if there are the same number of keys(rows) as n and then check if each HashSet
              has the column number. Check diagonals by iterating through the rows as well
              and checking for if they contain the col # that is the same as the row # and if
              they have the col # that is the board length minus the row number.
*/

    // O(n) runtime per move, check along a row, col, or along diagonal
    // O(n^2) space possible completely fill the board
    // Runtime: 6 ms, faster than 42.95% of Java online submissions
    // Memory Usage: 42.5 MB, less than 45.23% of Java online submissions

    /** Initialize your data structure here. */
    HashMap<Integer, HashSet<Integer>> player_1_r_c;
    HashMap<Integer, HashSet<Integer>> player_2_r_c;
    int n;
    public Design_Tic_Tac_Toe(int n) {
        player_1_r_c = new HashMap<Integer, HashSet<Integer>>();
        player_2_r_c = new HashMap<Integer, HashSet<Integer>>();
        this.n = n;
    }
    
    /*  Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        HashMap<Integer, HashSet<Integer>> positions = null;
        HashSet<Integer> player_rc = null;
        if (player == 1)
        {
            positions = player_1_r_c;
            player_rc = player_1_r_c.get(row);
        }
        else if (player == 2)
        {
            positions = player_2_r_c;
            player_rc = player_2_r_c.get(row);
        }
        if (player_rc == null)
        {
            player_rc = new HashSet<Integer>();
        }
        player_rc.add(col);
        if(player_rc.size() == n)
        {
            return player;
        }
        positions.put(row, player_rc);
        return check(player, player_rc, col, positions);
    }
    public int check(int player, HashSet<Integer> rc, int col, HashMap<Integer, HashSet<Integer>> positions)
    {
        if (positions.size() != n)
        {
            return 0;
        }
        else
        {
            boolean cols = true;
            boolean diag_l = true;
            boolean diag_r = true;
            for (Integer row : positions.keySet())
            {
                if (cols && !positions.get(row).contains(col))
                {
                    cols = false;
                }
                if (diag_l && !positions.get(row).contains(row))
                {
                    diag_l = false;
                }
                if (diag_r && !positions.get(row).contains(n - 1 - row))
                {
                    diag_r = false;
                }
                if (!cols && !diag_l && !diag_r)
                {
                    break;
                }
            }
            if (cols || diag_l || diag_r)
            {
                return player;
            }
            else
            {
                return 0;
            }
        }
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */

