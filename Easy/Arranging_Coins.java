public class Arranging_Coins {
/*
Rating: Easy

You have a total of n coins that you want to form in a staircase shape,
where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.

Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
*/

/*
    Approach: 

    sum i = 1 to i = x: x(x + 1) / 2

    (x^2 + x) / 2 = n
    2n = x(x + 1)

    --Completing the Square
    (x + 1/2)^2 - 1/4 <= 2n

    x = floor(sqrt(2n + 1/4) - 1/2)
*/

    // O(1) time
    // O(1) space
    // Runtime: 1 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 36.8 MB, less than 57.85% of Java online submissions
    public int arrangeCoins(int n) {
        /*
        int num_rows = 0;
        int counter = 1;
        while(n > 0)
        {
            n -= counter;
            if (n >= 0)
            {
                ++num_rows;
            }
            ++counter;
        }*/
        // x * (x + 1) / 2 = n
        // .5x^2 + .5x - n = 0
        return (int) (Math.sqrt(2.0 * (double) n + 0.25) - 0.5); 
    }
}
