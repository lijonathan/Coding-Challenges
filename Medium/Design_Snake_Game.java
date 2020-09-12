import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class Design_Snake_Game {
/*
Rating: Medium

Design a Snake game that is played on a device with
screen size = width x height. Play the game online
if you are not familiar with the game.

The snake is initially positioned at the top left
corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order.
When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example,
the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that
it will not appear on a block occupied by the snake.

Example:

Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that,
the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
*/

/*
    Approach: Keep an index tracker of the food. Use ArrayLists of ints of size 2
              to track the x,y coordinates of a body position. 

              A move in the snake only appends a new position at the front of the head
              and removes the tail. Use an ArrayDeque for the snake position and a HashSet of
              the body positions.

              If a food is consumed, add the new position to the front of the Deque without removing
              the tail.

*/

    // O(1) runtime
    // O(n) space where n = size of food
    // Runtime: 60 ms, faster than 61.03% of Java online submissions
    // Memory Usage: 45.7 MB, less than 67.97% of Java online submissions
    class SnakeGame {

        /** Initialize your data structure here.
            @param width - screen width
            @param height - screen height 
            @param food - A list of food positions
            E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
        int width;
        int height;
        int[][] food;
        int food_index;
        final String UP;
        final String LEFT;
        final String RIGHT;
        final String DOWN;
        ArrayDeque<ArrayList<Integer>> body;
        HashSet<ArrayList<Integer>> body_parts;
        ArrayList<Integer> new_pos;
        int head_x;
        int head_y;
        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;
            food_index = 0;
            UP = "U";
            LEFT = "L";
            RIGHT = "R";
            DOWN = "D";
            body = new ArrayDeque<ArrayList<Integer>>();
            body_parts = new HashSet<ArrayList<Integer>>();
            new_pos = new ArrayList<Integer>();
            new_pos.add(0);
            new_pos.add(0);
            body.add(new ArrayList<Integer>(new_pos));
            body_parts.add(body.peek());
            head_x = 0;
            head_y = 0;
        }

        private boolean valid_move(String direction)
        {
            return direction.equals(UP) || direction.equals(LEFT) || direction.equals(RIGHT)
                    || direction.equals(DOWN);
        }

        private boolean out_of_bounds(String direction)
        {
            if (direction.equals(UP))
            {
                return head_y - 1 < 0;
            }
            else if (direction.equals(DOWN))
            {
                return head_y + 1 >= height;
            }
            else if (direction.equals(LEFT))
            {
                return head_x - 1 < 0;
            }
            else
            {
                return head_x + 1 >= width;
            }
        }
        private boolean hit_self(String direction)
        {
            if (direction.equals(UP))
            {
                head_y -= 1;
            }
            else if (direction.equals(DOWN))
            {
                head_y += 1;
            }
            else if (direction.equals(LEFT))
            {
                head_x -= 1;
            }
            else
            {
                head_x += 1;
            }
            new_pos.set(0, head_x);
            new_pos.set(1, head_y);
            ArrayList<Integer> last = body.peekLast();
            if (head_x == last.get(0) && head_y == last.get(1))
            {
                return false;
            }
            return body_parts.contains(new_pos);
        }
        private int move_conditions(String direction)
        {
            ArrayDeque<ArrayList<Integer>> deque = this.body;
            if (hit_self(direction))
            {
                return -1;
            }
            else
            {
                if (food_index < food.length && head_x == food[food_index][1] && head_y == food[food_index][0])
                {
                    body.offerFirst(new ArrayList<Integer>(new_pos));
                    body_parts.add(new ArrayList<Integer>(new_pos));
                    ++food_index;
                    return body_parts.size() - 1;
                }
                else
                {
                    ArrayList<Integer> back = body.pollLast();
                    body_parts.remove(back);
                    back.set(0, new_pos.get(0));
                    back.set(1, new_pos.get(1));
                    body.offerFirst(back);
                    body_parts.add(new ArrayList<Integer>(back));
                    return body_parts.size() - 1;
                }
            }
        }

        /** Moves the snake.
            @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
            @return The game's score after the move. Return -1 if game over. 
            Game over when snake crosses the screen boundary or bites its body. */
        public int move(String direction) {
            if (!valid_move(direction))
            {
                return -1;
            }
            else if (out_of_bounds(direction))
            {
                return -1;
            }
            else
            {
                return move_conditions(direction);
            }
        }
    }

    /**
     * Your SnakeGame object will be instantiated and called as such:
     * SnakeGame obj = new SnakeGame(width, height, food);
     * int param_1 = obj.move(direction);
     */
}
