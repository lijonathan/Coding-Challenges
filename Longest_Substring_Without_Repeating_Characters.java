import java.util.HashSet;

public class Longest_Substring_Without_Repeating_Characters {

/*
Rating: Medium

Given a string, find the length of the longest substring
without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 

Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke"
             is a subsequence and not a substring.
*/

    public int lengthOfLongestSubstring(String s) {
        // sliding window
        // O(n) time -- 2n, if the left side of the window catches up
        // to the right
        // O(k) space -- where k is the min between the character subset
        // and the size of the string
        HashSet<Character> hs = new HashSet<Character>();
        int max_length = 0;
        int left = 0;
        int right = 0;
        int length = s.length();
        while(right < length)
        {
            Character c = s.charAt(right);
            if (hs.contains(c))
            {
                while(hs.contains(c))
                {
                    hs.remove(s.charAt(left));
                    ++left;
                }
            }
            else
            {
                hs.add(c);
                ++right;
                if(right - left > max_length)
                {
                    max_length = right - left;
                }
            }
        }
        return max_length;
    }
}
