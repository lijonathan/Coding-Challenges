import java.util.HashMap;
import java.util.HashSet;

public class String_Transforms_Into_Another_String {

/*
Rating: Hard

Given two strings str1 and str2 of the same length, determine
whether you can transform str1 into str2 by doing zero or more conversions.

In one conversion you can convert all occurrences of one
character in str1 to any other lowercase English character.

Return true if and only if you can transform str1 into str2.

 

Example 1:

Input: str1 = "aabcc", str2 = "ccdee"
Output: true
Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'.
Note that the order of conversions matter.

Example 2:

Input: str1 = "leetcode", str2 = "codeleet"
Output: false
Explanation: There is no way to transform str1 to str2.

Note:

    1 <= str1.length == str2.length <= 10^4
    Both str1 and str2 contain only lowercase English letters.
*/

/*
    Approach: Iterate through the strings and have a mapping from the the character
              in the first string to the corresponding character in the second string at
              the same index. If at any point, a character from the first string is mapped
              to two different characters in the second string, return false.

              If both strings have every single character, then return false. This means
              that there is no character to serve as a "buffer" in the first string, but
              it only matters if the second string also has all the characters otherwise the first string
              can use the character not present in the second string as the buffer.

              Iterate through the mappings and if any character gets mapped to itself and
              the second string has all the characters, return false. The strings are already
              established to not be the same, therefore if a character is mapped to itself, that means
              that those characters cannot be changed.

              Since the second string has a full character set, there is no buffer character
              that can be used to transform characters.

              i.e.
              a cannot be changed, and any time a character gets changed, now two positions have the same
              character when the other string only has 1 of each character.
              acdefg....yzb
              abcdef....xyz
*/


    // O(n) runtime
    // O(1) space
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 39.4 MB, less than 85.54% of Java online submissions
    public boolean canConvert(String str1, String str2) {
        if(str1.equals(str2))
        {
            return true;
        }
        HashMap<Character, Character> char_map = new HashMap<Character, Character>();
        HashSet<Character> mapped_to = new HashSet<Character>();
        int length = str1.length();
        for (int i = 0; i < length; ++i)
        {
            Character from_c = str1.charAt(i);
            Character to_c = str2.charAt(i);
            mapped_to.add(to_c);
            if (char_map.get(from_c) == null)
            {
                char_map.put(from_c, to_c);
            }
            else
            {
                Character prev_mapped = char_map.get(from_c);
                if (prev_mapped != to_c)
                {
                    return false;
                }
            }
        }
        int char_counts = char_map.size();
        if (char_counts == 26 && mapped_to.size() == 26)
        {
            return false;
        }
        for(Character c: char_map.keySet())
        {
            Character c2 = char_map.get(c);
            if (char_map.get(c2) == c && mapped_to.size() == 26)
            {
                return false;
            }
        }
        return true;
    }
}
