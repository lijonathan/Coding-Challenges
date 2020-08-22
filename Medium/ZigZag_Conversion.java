public class ZigZag_Conversion {
/*
Rating: Medium

The string "PAYPALISHIRING" is written in a zigzag pattern on a given
number of rows like this: (you may want to display this pattern in a fixed
font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);

Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
*/

/*
    Approach: StringBuffer array that is the size of the number of rows. Iterate through the strings
              and simulate building out the string in the zigzag formation with a flag to see if it is
              iterating down or diagonally. If the flag goes from vertical to diagonal, set row = numRows - 2.
              Set row = 1 if it flips from diagonal to vertical.
*/


    // O(n) runtime where n = length of s
    // O(n) space
    // Runtime: 5 ms, faster than 82.04% of Java online submissions
    // Memory Usage: 40.1 MB, less than 63.56% of Java online submissions
    public String convert(String s, int numRows) {
        int length = s.length();
        if (numRows == 1 || length <= numRows)
        {
            return s;
        }
        StringBuffer[] sbs = new StringBuffer[numRows];
        int row = 0;
        boolean diag = false;
        for (int i = 0; i < length; ++i)
        {
            char c = s.charAt(i);
            if (!diag)
            {
                if (sbs[row] == null)
                {
                    sbs[row] = new StringBuffer();
                }
                sbs[row].append(c);
                ++row;
                if (row >= numRows)
                {
                    row = row - 2;
                    diag = true;
                }
            }
            else
            {
                sbs[row].append(c);
                --row;
                if (row < 0)
                {
                    row = 1;
                    diag = false;
                }
            }
        }
        StringBuffer sb = sbs[0];
        for (int i = 1; i < numRows && i < length; ++i)
        {
            sb.append(sbs[i].toString());
        }
        return sb.toString();
    }
}
