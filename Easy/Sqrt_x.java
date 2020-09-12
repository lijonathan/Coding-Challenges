public class Sqrt_x {
/*
Rating: Easy

Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed
to be a non-negative integer.

Since the return type is an integer, the decimal digits are
truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2

Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
*/

/*
	Approach: Use binary search to find the sqrt of the target number.
*/

    // O(log x) runtime
    // O(1) space
    // Runtime: 1 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 36.8 MB, less than 70.30% of Java online submissions
    public int mySqrt(int x) {
        double x_ = (double) x;
        double low = 0.0;
        double high = x_;
        while(low <= high)
        {
            double mid = (double) (int) ((low + high) / 2);
            double prod = mid * mid;
            if (prod == x_)
            {
                return (int) mid;
            }
            else if (prod < x_)
            {
                double greater = (mid + 1.0) * (mid + 1.0);
                if (greater > x_)
                {
                    return (int) mid;
                }
                else if (greater == x_)
                {
                    return (int) (mid + 1.0);
                }
                low = mid + 1.0;
            }
            else
            {
                double less = (mid - 1.0) * (mid - 1.0);
                if (less <= x_)
                {
                    return (int) (mid - 1.0);
                }
                high = mid - 1.0;
            }
        }
        return -1;
    }
}
