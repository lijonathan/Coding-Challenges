public class Reverse_Integer {

/*
Rating: Easy

Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321

Example 2:

Input: -123
Output: -321

Example 3:

Input: 120
Output: 21

Note:
Assume we are dealing with an environment which could only
store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1].
For the purpose of this problem, assume that your function returns 0 when
the reversed integer overflows.
*/

/*
    Approach: Take the absolute value of the number. Edge Case - If the value is the minimum
              integer value, -2^32 absolute value will still be negative, it will overflow if
              reversed, return 0.

              Otherwise, pick each digit off from the last significant digit one by one. Do this by
              % 10. Remove the LSD by dividing by 10 after each loop iteration.
              Add that to the current reversed value and multiply the reversed value by 10.

              To check for overflow, divide by 10 after each multiple and subtract what was added on
              to see if they equal to previous value before the operation was performed.
*/


    // O(1) time, 10 digits maximum for max 32 bit integer value
    // O(1) space
    // Runtime: 1 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 36.6 MB, less than 61.46% of Java online submissions
    public int reverse(int x) {
        int reverse = 0;
        int negative = x < 0 ? -1 : 1;
        x = Math.abs(x);
        if (x < 0)
        {
            return 0;
        }
        while (x > 0)
        {
            int prev = reverse;
            reverse = reverse * 10;
            if (reverse / 10 != prev)
            {
                return 0;
            }
            int pop = x % 10;
            x = x / 10;

            prev = reverse;
            reverse += pop;
            if (reverse - pop != prev)
            {
                return 0;
            }
        }
        return reverse * negative;
    }
}
