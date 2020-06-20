public class Decode_Ways {

/*
Rating: Medium

A message containing letters from A-Z is being
encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26

Given a non-empty string containing only digits,
determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/

/*
    Approach: Using a sliding window of length 2 because you can either encode 1 digit or 2 digits.
    If the current digit is '0', then it cannot be encoded with 1 digit. If the past two digits are
    not >= 10 && <= 26, then it cannot be encoded with two digits.

    If i is even, if the current char is not 0, set it equal to the number of ways at i - 1, which is
    window[1]. (window[0] = window[1]). Then check for two characters, if they are >= 10 && <= 26, then
    increment window[0] by what was originally in window[0].

    The same applies for window[1] for when i is odd. 
    If s.length is even, return window[1], otherwise return window[0].
*/

    // O(n) runtime, n = length of s
    // O(1) space
    // Runtime: 1 ms, faster than 97.78% of Java online submissions
    // Memory Usage: 38.2 MB, less than 55.46% of Java online submissions
    public int numDecodings(String s) {
        int[] window = new int[2];
        int length = s.length();
        if (s.charAt(0) == '0')
        {
            return 0;
        }
        window[0] = 1;
        if (length >= 2 && Integer.parseInt(s.substring(0, 2)) >= 10 &&
                 Integer.parseInt(s.substring(0, 2)) <= 26)
        {
            window[1] = 1;
        }
        if (length >= 2 && s.charAt(1) != '0')
        {
            ++window[1];
        }
        
        for (int i = 2; i < length; ++i)
        {
            char i_1 = s.charAt(i);
            int i_2 = Integer.parseInt(s.substring(i - 1, i + 1));
            int orig_val;
            if (i % 2 == 0) // odd window[0]
            {
                orig_val = window[0];
                window[0] = 0;
                if (i_1 != '0')
                {
                    window[0] = window[1];
                }
                if (i_2 >= 10 && i_2 <= 26)
                {
                    window[0] += orig_val;
                }
            }
            else
            {
                orig_val = window[1];
                window[1] = 0;
                if (i_1 != '0')
                {
                    window[1] = window[0];
                }
                if (i_2 >= 10 && i_2 <= 26)
                {
                    window[1] += orig_val;
                }
            }
            if (window[0] == 0 && window[1] == 0)
            {
                return 0;
            }
        }
        if (s.length() % 2 == 0)
        {
            return window[1];
        }
        else
        {
            return window[0];
        }
    }
}
