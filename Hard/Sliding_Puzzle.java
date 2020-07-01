import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Sliding_Puzzle {
/*
Rating: Hard

On a 2x3 board, there are 5 tiles represented
by the integers 1 through 5, and an empty square
represented by 0.

A move consists of choosing 0 and a 4-directionally
adjacent number and swapping it.

The state of the board is solved if and only
if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of
moves required so that the state of the board is solved.
If it is impossible for the state of the board to be
solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.

Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.

Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]

Input: board = [[3,2,4],[1,5,0]]
Output: 14

Note:

    board will be a 2 x 3 array as described above.
    board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
*/

/*
    Approach: BFS, expand out from all states that can be reached from the current state.
              Keep a HashMap of the number of steps to each state. The HashMap keys also
              serve as a set for all previously visited states that do not get readded into
              the queue.

              Converting states of the board to states that can be Hashed and manipulated
              was the trickiest part. Converted the state into a 1D arraylist
*/


    // O((row# * col#)! *row# * col#) runtime, the total possible number of states
    // multiplied by the time it takes to copy each state into a new state and hashinng it/
    // putting it into the queue
    // O((row# * col#)! *row# * col#) for the queue which will need to hold an upper bound
    // of the possible number of states, and the size of each state in the queue is r * c

    // Runtime: 6 ms, faster than 84.39% of Java online submissions
    // Memory Usage: 38.7 MB, less than 93.36% of Java online submissions
    public void convert_coords(ArrayList<Integer> state, int[] coords)
    {
        int zero_pos = 0;
        int length = state.size();
        for (int i = 0; i < length; ++i)
        {
            if (state.get(i) == 0)
            {
                zero_pos = i;
                break;
            }
        }
        coords[0] = zero_pos % 3;
        coords[1] = zero_pos / 3;
    }

    public boolean finished(ArrayList<Integer> state)
    {
        return state.get(0) == 1 && state.get(1) == 2 && state.get(2) == 3
                && state.get(3) == 4 && state.get(4) == 5;
    }

    public int slidingPuzzle(int[][] board) {
        HashMap<ArrayList<Integer>, Integer> visited = new HashMap<ArrayList<Integer>, Integer>();
        ArrayList<Integer> entry = new ArrayList<Integer>();
        int[] coords = new int[2];
        for (int i = 0; i < board.length; ++i)
        {
            for (int j = 0; j < board[0].length; ++j)
            {
                entry.add(board[i][j]);
            }
        }
        if (finished(entry))
        {
            return 0;
        }
        visited.put(entry, 0);
        ArrayDeque<ArrayList<Integer>> queue = new ArrayDeque<ArrayList<Integer>>();
        queue.add(entry);
        while(!queue.isEmpty())
        {
            entry = queue.remove();
            convert_coords(entry, coords);
            int x = coords[0];
            int y = coords[1];
            int index = x + y * 3;
            int moves = visited.get(entry);
            if (x - 1 >= 0) // swap left
            {
                ArrayList<Integer> input = new ArrayList<Integer>(entry);
                input.set(index, input.get(index - 1));
                input.set(index - 1, 0);
                if (!visited.containsKey(input))
                {
                    if (finished(input))
                    {
                        return moves + 1;
                    }
                    queue.add(input);
                    visited.put(input, moves + 1);
                }
            }
            if (x + 1 < 3) // swap right
            {
                ArrayList<Integer> input = new ArrayList<Integer>(entry);
                input.set(index, input.get(index + 1));
                input.set(index + 1, 0);
                if (!visited.containsKey(input))
                {
                    if (finished(input))
                    {
                        return moves + 1;
                    }
                    queue.add(input);
                    visited.put(input, moves + 1);
                }
            }
            if (y - 1 >= 0) // swap up
            {
                ArrayList<Integer> input = new ArrayList<Integer>(entry);
                input.set(index, input.get(index - 3));
                input.set(index - 3, 0);
                if (!visited.containsKey(input))
                {
                    if (finished(input))
                    {
                        return moves + 1;
                    }
                    queue.add(input);
                    visited.put(input, moves + 1);
                }
            }
            if (y + 1 < 2) // swap down
            {
                ArrayList<Integer> input = new ArrayList<Integer>(entry);
                input.set(index, input.get(index + 3));
                input.set(index + 3, 0);
                if (!visited.containsKey(input))
                {
                    if (finished(input))
                    {
                        return moves + 1;
                    }
                    queue.add(input);
                    visited.put(input, moves + 1);
                }
            }
        }
        return -1;
    }
}
