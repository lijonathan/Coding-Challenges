public class Fibonacci_Number {

/*
Rating: Easy

The Fibonacci numbers, commonly denoted F(n) form a sequence,
called the Fibonacci sequence, such that each number is the sum
of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), for N > 1.

Given N, calculate F(N).

Example 1:

Input: 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.

Example 2:

Input: 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.

Example 3:

Input: 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.

Note:

0 ≤ N ≤ 30.
*/

/*
    Approach: Tabulation -- bottom up dynamic programming.

    Sliding window of size 2 from 0 to n calculating the Fibonnaci sequence. Store 0th and 1st
    Fibonnacci numbers first. The next value is always the sum of the previous two. So alternate,
    if i is even put it into window[0], otherwise into window[1].
*/


    // O(n) runtime
    // O(1) space
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 35.9 MB, less than 81.95% of Java online submissions
    public int fib(int N) {
        int[] window = new int[2];
        window[0] = 0;
        window[1] = 1;
        for (int i = 2; i <= N; ++i)
        {
            int sum = window[0] + window[1];
            if (i % 2 == 0)
            {
                window[0] = sum;
            }
            else
            {
                window[1] = sum;
            }
        }
        return N % 2 == 0 ? window[0] : window[1];
    }
}
