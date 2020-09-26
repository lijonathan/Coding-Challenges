public class Robot_Bounded_In_Circle {
/*
Rating: Medium

On an infinite plane, a robot initially stands at (0, 0) and faces north.
The robot can receive one of three instructions:

    "G": go straight 1 unit;
    "L": turn 90 degrees to the left;
    "R": turn 90 degress to the right.

The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the
robot never leaves the circle.


Example 1:

Input: "GGLLGG"
Output: true
Explanation: 
The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered
at the origin.

Example 2:

Input: "GG"
Output: false
Explanation: 
The robot moves north indefinitely.

Example 3:

Input: "GL"
Output: true
Explanation: 
The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...

Note:

    1 <= instructions.length <= 100
    instructions[i] is in {'G', 'L', 'R'}
*/

/*
    Approach: Key -- The robot must have zero net up direction and 0 net left direction.

              Using LG as the most basic instruction, it takes 4 turns to get it back to the original
              spot. --> 4 repetitions of the instructions.

              Track the left offset and the up offset of the robot. The current facing direction and any G
              instructions. At the end, for a circular path, the left offset and up offset must both be 0.
*/

    // O(n) runtime where n = length of instructions
    // O(1) space
    // Runtime: 1 ms, faster than 34.44% of Java online submissions
    // Memory Usage: 37.2 MB, less than 79.17% of Java online submissions
    public boolean isRobotBounded(String instructions) {
        int l_offset = 0;
        int u_offset = 0;
        int dir = 0;
        int rotation = 0;
        int length = instructions.length() * 4;
        int l = length / 4;
        for (int k = 0; k < length; ++k)
        {
            char mov = instructions.charAt(k % l);
            if (mov == 'L')
            {
                --rotation;
            }
            else if (mov == 'R')
            {
                ++rotation;
            }
            else if (mov == 'G')
            {
                dir = Math.abs(rotation) % 4;
                if (dir == 0)
                {
                    ++u_offset;
                }
                else if ((dir == 1 && rotation < 0) || (dir == 3 && rotation > 0))
                {
                    ++l_offset;
                }
                else if (dir == 2)
                {
                    --u_offset;
                }
                else if ((dir == 1 && rotation > 0) || (dir == 3 && rotation < 0))
                {
                    --l_offset;
                }
            }
        }
        return u_offset == 0 && l_offset == 0;
    }
}
