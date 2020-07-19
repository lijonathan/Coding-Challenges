public class Add_Binary {
/*
Rating: Easy

Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"

Example 2:

Input: a = "1010", b = "1011"
Output: "10101"



Constraints:

    Each string consists only of '0' or '1' characters.
    1 <= a.length, b.length <= 10^4
    Each string is either "0" or doesn't contain any leading zero.
*/


/*
    Approach: Iterate with pointers from the rightmost side of both strings. Have a carry bit
              flag and decrement both counters. If one counter has already reached the start of
              a string, just add 0 for that bitstring's position.
*/

    // O(n) time, n = length of longest string
    // O(1) space
    // Runtime: 2 ms, faster than 81.99% of Java online submissions
    // Memory Usage: 38.2 MB, less than 71.04% of Java online submissions
    public String addBinary(String a, String b) {
        int i_1 = a.length() - 1;
        int i_2 = b.length() - 1;
        StringBuffer sb = new StringBuffer();
        boolean carry = false;
        while(i_1 >= 0 || i_2 >= 0)
        {
            Character b_1 = '0';
            Character b_2 = '0';
            if (i_1 >= 0)
            {
                b_1 = a.charAt(i_1);
                --i_1;
            }
            if (i_2 >= 0)
            {
                b_2 = b.charAt(i_2);
                --i_2;
            }
            if (b_1 == '0' && b_2 == '0')
            {
                if (!carry)
                {
                    sb.append("0");
                }
                else
                {
                    sb.append("1");
                    carry = false;
                }
            }
            else if (b_1 == '1' && b_2 == '1')
            {
                if (!carry)
                {
                    carry = true;
                    sb.append("0");
                }
                else
                {
                    sb.append("1");
                }
            }
            else if (!(b_1 == '1' && b_2 == '1') && !(b_1 == '0' && b_2 == '0'))
            {
                if (!carry)
                {
                    sb.append("1");
                }
                else
                {
                    sb.append("0");
                }
            }
        }
        if (carry)
        {
            sb.append("1");
        }
        return sb.reverse().toString();
    }
}
