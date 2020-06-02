import java.util.HashMap;

class Palindrome_Permutation {
/*

Rating: Easy

Given a string, determine if a permutation of the string could form a palindrome.

Example 1:

Input: "code"
Output: false

Example 2:

Input: "aab"
Output: true

Example 3:

Input: "carerac"
Output: true

*/

    // O(n) time, O(character set) space
    public boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i)
        {
            Character c = s.charAt(i);
            Integer count = counts.get(c);
            if (count == null){
                counts.put(c, 1);
            }
            else
            {
                counts.put(c, count + 1);
            }
        }
        boolean odd = false;
        // allowed at most 1 character with an odd count
        for (Character key: counts.keySet())
        {
            if (counts.get(key) % 2 != 0)
            {
                if (!odd)
                {
                    odd = true;
                }
                else
                {
                    return false;
                }
            }
        }
        return true;
    }
}
