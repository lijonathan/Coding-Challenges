public class Minimum_Window_Substring {

/*
Rating: Hard

Given a string S and a string T, find the
minimum window in S which will contain all
the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Note:

    If there is no such window in S that covers all characters
    in T, return the empty string "".
    If there is such window, you are guaranteed that there will
    always be only one unique minimum window in S.
*/

    /* Approach:
     Sliding window -- Use arrays that represent characters in the ASCII set
     and keep counts of current window character counts and substring character
     counts
     */
    private boolean check_equal(int[] curr, int[] t_count)
    {
        for (int i = 0; i < curr.length; ++i)
        {
            if (t_count[i] > curr[i])
            {
                return false;
            }
        }
        return true;
    }
    // Runtime: O(52n) 52 characters for upper and lowercase letters
    // Space: O(1) -- 2 arrays of length 52
    /* Runtime: 13 ms, faster than 50.65% of Java online submissions for Minimum Window Substring. */
    /* Memory Usage: 39.4 MB, less than 89.24% of Java online submissions for Minimum Window Substring. */
    public String minWindow(String s, String t) {
        // sliding window
        // verify T through array representing counts of ASCII values
        int[] t_count = new int[52];
        int[] s_count = new int[52];
        for (int i = 0; i < t.length(); ++i)
        {
            Character c = t.charAt(i);
            if (c >= 65 && c <= 90)
            {
                t_count[c - 65] = t_count[c - 65] + 1;
            }
            else if (c >= 97 && c <= 122)
            {
                t_count[c - 97 + 26] = t_count[c - 97 + 26] + 1;
            }
        }
        int min_l = 0;
        int min_r = Integer.MAX_VALUE;
        int l_index = 0;
        int r_index = 0;
        int length = s.length();
        while (r_index < length)
        {
            if (!check_equal(s_count, t_count)) // move right pointer forward until
            {                                   // the subset string contains all chars
                Character c = s.charAt(r_index);
                if (c >= 65 && c <= 90)
                {
                    s_count[c - 65] = s_count[c - 65] + 1;
                }
                else if (c >= 97 && c <= 122)
                {
                    s_count[c - 97 + 26] = s_count[c - 97 + 26] + 1;
                }

                ++r_index;
            }
            else    // move left pointer forward until substring no longer contains all chars
            {
                if (r_index - l_index < min_r - min_l)
                {// as left pointer moves forward, check for update to size of min window
                    min_r = r_index;
                    min_l = l_index;
                }
                Character c = s.charAt(l_index);
                if (c >= 65 && c <= 90)
                {
                    s_count[c - 65] = s_count[c - 65] - 1;
                    if (s_count[c - 65] < 0)
                    {
                        s_count[c - 65] = 0;
                    }
                }
                else if (c >= 97 && c <= 122)
                {
                    s_count[c - 97 + 26] = s_count[c - 97 + 26] - 1;
                    if (s_count[c - 97 + 26] < 0)
                    {
                        s_count[c - 97 + 26] = 0;
                    }
                }
                ++l_index;
            }
        }
        // right pointer has reached the end, case where last character makes
        // a valid substring. Move left up updating the size until it is not a valid substring
        // If last character does not make a valid substring, then this while loop never enters
        while(check_equal(s_count, t_count))
        {
            if (r_index - l_index < min_r - min_l)
            {
                min_r = r_index;
                min_l = l_index;
            }
            Character c = s.charAt(l_index);
            if (c >= 65 && c <= 90)
            {
                s_count[c - 65] = s_count[c - 65] - 1;
                if (s_count[c - 65] < 0)
                {
                    s_count[c - 65] = 0;
                }
            }
            else if (c >= 97 && c <= 122)
            {
                s_count[c - 97 + 26] = s_count[c - 97 + 26] - 1;
                if (s_count[c - 97 + 26] < 0)
                {
                    s_count[c - 97 + 26] = 0;
                }
            }
            ++l_index;
        }
        if(min_r - min_l == Integer.MAX_VALUE)
        {
            return "";
        }
        else
        {
            return s.substring(min_l, min_r);
        }
    }
}
