import java.util.HashMap;

public class Longest_Substring_with_At_Most_Two_Distinct_Characters {
/*
Rating: Medium

Given a string s, find the length of the longest substring t
that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.

Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
*/

/*
    Approach: Sliding window with two pointers approach. Increment right pointer until the
              end of the string. Keep a Hashmap of characters and their respective counts.
              If the number of unique characters between the left and right pointer is greater
              than two, increment the left pointer until there are <= 2 unique characters in the
              window. Track the largest window.
*/

    // O(n) runtime
    // O(26) space for HashMap of character counts
    // Runtime: 5 ms, faster than 69.34% of Java online submissions
    // Memory Usage: 39.4 MB, less than 65.22% of Java online submissions
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        HashMap<Character, Integer> chars = new HashMap<Character, Integer>();
        int l_ptr = 0;
        int r_ptr = 0;
        int max_length = 0;
        int length = s.length();
        while(r_ptr < length)
        {
            Character c = s.charAt(r_ptr);
            Integer count = chars.get(c);
            if (count == null)
            {
                count = 0;
            }
            chars.put(c, count + 1);
            ++r_ptr;
            if (chars.size() <= 2 && r_ptr - l_ptr > max_length)
            {
                max_length = r_ptr - l_ptr;
            }
            while(chars.size() > 2 && l_ptr < r_ptr)
            {
                Character l_c = s.charAt(l_ptr);
                count = chars.get(l_c);
                count = count - 1;
                if (count == 0)
                {
                    chars.remove(l_c);
                }
                else
                {
                    chars.put(l_c, count);
                }
                ++l_ptr;
            }
        }
        return max_length;
    }
}
