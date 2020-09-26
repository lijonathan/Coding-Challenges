public class Is_Subsequence {
/*
Rating: Easy

Given a string s and a string t, check if s is subsequence of t.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence
of "abcde" while "aec" is not).

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you
want to check one by one to see if T has its subsequence. In this scenario,
how would you change your code?

Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true

Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false

Constraints:

    0 <= s.length <= 100
    0 <= t.length <= 10^4
    Both strings consists only of lowercase characters.
*/

/*
    Approach: Keep an index tracker for the characters that have been seen up to the current index in
              of s initialized at 0. Iterate through the characters of t and if the current char in t equals the char at
              index in s, increment index. If index reaches the length of s, return true.

              After the loop, return false.
*/

    // O(n) runtime where n = length of t
    // O(1) space
    // Runtime: 1 ms, faster than 83.74% of Java online submissions
    // Memory Usage: 37.3 MB, less than 81.34% of Java online submissions
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0)
        {
            return true;
        }
        int s_index = 0;
        for (int i = 0; i < t.length(); ++i)
        {
            if (s.charAt(s_index) == t.charAt(i))
            {
                ++s_index;
                if (s_index == s.length())
                {
                    return true;
                }
            }
        }
        return false;
    }
}
