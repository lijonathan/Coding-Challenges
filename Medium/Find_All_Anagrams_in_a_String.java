import java.util.ArrayList;
import java.util.List;

public class Find_All_Anagrams_in_a_String {
/*
Rating: Medium

Given a string s and a non-empty string p, find
all the start indices of p's anagrams in s.

Strings consists of lowercase English letters
only and the length of both strings s and p
will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/

/*
    Approach: Get the character counts of the substring and store it in a char array of
              length 26.

              Sliding window approach. Move the right pointer until the window length is equal to
              the substring length and track the character counts.

              Check for the anagram by comparing character counts in both 26 length count arrays.
              Move both the left and the right pointers right and recheck after each slide.
*/


    // O(n) runtime
    // O(1) space
    // Runtime: 13 ms, faster than 60.82% of Java online submissions
    // Memory Usage: 44.7MB less than 34.08% of Java online submissions
    public boolean check_anagram(int[] chars_orig, int[] chars_substring)
    {
        for (int i = 0; i < chars_orig.length; ++i)
        {
            if (chars_orig[i] != chars_substring[i])
            {
                return false;
            }
        }
        return true;
    }
    
    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        int[] chars = new int[26];
        int length = p.length();
        for (int i = 0; i < length; ++i)
        {
            ++chars[p.charAt(i) - 97];
        }
        int s_length = s.length();
        int l_index = 0;
        int r_index = 0;
        int[] substring_chars = new int[26];
        while (r_index - l_index + 1 <= length && r_index < s_length)
        {
            ++substring_chars[s.charAt(r_index) - 97];
            ++r_index;
        }
        --r_index;
        while (r_index < s_length && r_index >= 0)
        {
            if (check_anagram(chars, substring_chars))
            {
                indices.add(l_index);
            }
            --substring_chars[s.charAt(l_index) - 97];
            ++l_index;
            ++r_index;
            if (r_index >= s_length)
            {
                break;
            }
            ++substring_chars[s.charAt(r_index) - 97];
        }
        return indices;
    }
}
