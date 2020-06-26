public class Longest_Palindromic_Substring {

/*
Rating: Medium

Given a string s, find the longest palindromic
substring in s. You may assume that the maximum
length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:

Input: "cbbd"
Output: "bb"
*/

/*
    Approach: 2n - 1 possible centers for the palindrome string. n characters, and n - 1
              for palindromes that start in the spaces between the characters. 

              Iterate through all 2n - 1 different potential centers, and check if they are
              possible palindromes by expanding in both the left and right directions from the
              center. Keep track of the largest palindrome.
*/


    // O(n^2) runtime
    // O(1) space
    // Runtime: 25ms beats 62.85% of Java online submissions
    // Memory Usage: 37.3 MB beats 98.68% of Java online submissions
    public String longestPalindrome(String s) {
        // brute force, O(n^3) check every string starting at each index
        // the check will take n time with n^2 iterations
        // --> middle points of the palindrome --> 2n - 1 different middle points
        // check string = n time --> O(n^2) time overall
        if (s.length() == 0)
        {
            return "";
        }
        int max = 0;
        int l_i = 0;
        int r_i = 0;
        int length = s.length();
        for (int i = 0; i < length * 2 - 1; ++i)
        {
            int count = 0;
            int left;
            int right;
            if (i % 2 == 0) // on an actual character
            {
                left = i / 2;
                right = i / 2;
            }
            else    // in between characters, left and right start at +/- 1 indexes
            {       // left i / 2 already takes the floor so -1 index is implicitly factored in
                left = i / 2;
                right = i / 2 + 1;
            }
            while (left >= 0 && right < length)
            {
                if (s.charAt(left) == s.charAt(right))
                {
                    if (right - left + 1 > max)
                    {
                        max = right - left + 1;
                        l_i = left;
                        r_i = right;
                    }
                    --left;
                    ++right;
                }
                else
                {
                    break;
                }
            }
        }
        return s.substring(l_i, r_i + 1);
    }
}
