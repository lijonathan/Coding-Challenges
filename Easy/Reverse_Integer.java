import java.util.ArrayList;

class Reverse_Integer {
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
Assume we are dealing with an environment which could
only store integers within the 32-bit signed integer
range: [−231,  231 − 1]. For the purpose of this problem,
assume that your function returns 0 when the reversed integer
overflows.
*/

    public int reverse(int x) {
        ArrayList<Long> al = new ArrayList<Long>();
        boolean negative = (x < 0);
        long abs_x = Math.abs(x);
        long pow = 1;
        while (pow <= abs_x)
        {
            pow = pow * 10;
            long entry = (abs_x % pow) / (pow / 10);
            al.add(entry);
            abs_x = abs_x -  entry;
        }
        long sum = 0;
        pow = 1;
        for(int i = al.size() - 1; i >= 0; --i)
        {
            sum +=  pow * al.get(i);
            pow = pow * 10;
        }
        long max = (long) Integer.MAX_VALUE;
        if (sum > max && !negative)
        {
            return 0;
        }
        if (sum > max + 1 && negative)
        {
            return 0;
        }
        if (negative)
        {
            sum = sum * -1;
        }
        return (int) sum;
        
    }
}
