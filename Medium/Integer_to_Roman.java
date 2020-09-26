public class Integer_to_Roman {
/*
Rating: Medium

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

For example, two is written as II in Roman numeral, just two one's added together.
Twelve is written as, XII, which is simply X + II. The number twenty seven is written
as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right.
However, the numeral for four is not IIII. Instead, the number four is written
as IV. Because the one is before the five we subtract it making four.
The same principle applies to the number nine, which is written as IX.
There are six instances where subtraction is used:

    I can be placed before V (5) and X (10) to make 4 and 9. 
    X can be placed before L (50) and C (100) to make 40 and 90. 
    C can be placed before D (500) and M (1000) to make 400 and 900.

Given an integer, convert it to a roman numeral. Input is guaranteed to
be within the range from 1 to 3999.

Example 1:

Input: 3
Output: "III"

Example 2:

Input: 4
Output: "IV"

Example 3:

Input: 9
Output: "IX"

Example 4:

Input: 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.

Example 5:

Input: 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
*/

/*
    Approach: Similar to Integer to English Word, continually get the current magnitude and the number
              of times that magnitude happens. After making the current magnitude's string, reduce
              the number by 1 magnitude and repeat.
*/

    // O(1) runtime
    // O(1) space
    // Runtime: 4 ms, faster than 92.12% of Java online submissions
    // Memory Usage: 39.2 MB, less than 77.94% of Java online submissions
    public String intToRoman(int num) {
        StringBuffer sb = new StringBuffer();
        int thousands = num / 1000;
        for (int i = 0; i < thousands; ++i)
        {
            sb.append("M");
        }
        num = num % 1000;
        if (num >= 900)
        {
            sb.append("C");
            sb.append("M");
        }
        else if (num >= 500)
        {
            int hundreds = (num - 500) / 100;
            sb.append("D");
            for (int i = 0; i < hundreds; ++i)
            {
                sb.append("C");
            }
        }
        else if (num >= 400)
        {
            sb.append("C");
            sb.append("D");
        }
        else if (num >= 100)
        {
            int hundreds = num / 100;
            for (int i = 0; i < hundreds; ++i)
            {
                sb.append("C");
            }
        }
        num = num % 100;
        if (num >= 90)
        {
            sb.append("X");
            sb.append("C");
        }
        else if (num >= 50)
        {
            int tens = num / 10 - 5;
            sb.append("L");
            for (int i = 0; i < tens; ++i)
            {
                sb.append("X");
            }
        }
        else if (num >= 40)
        {
            sb.append("X");
            sb.append("L");
        }
        else
        {
            int tens = num / 10;
            for (int i = 0; i < tens; ++i)
            {
                sb.append("X");
            }
        }
        num = num % 10;
        if (num >= 9)
        {
            sb.append("I");
            sb.append("X");
        }
        else if (num >= 5)
        {
            int ones = num - 5;
            sb.append("V");
            for (int i = 0; i < ones; ++i)
            {
                sb.append("I");
            }
        }
        else if (num >= 4)
        {
            sb.append("I");
            sb.append("V");
        }
        else
        {
            int ones = num;
            for (int i = 0; i < ones; ++i)
            {
                sb.append("I");
            }
        }
        return sb.toString();
    }
}
