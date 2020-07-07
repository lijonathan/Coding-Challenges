public class Valid_Palindrome {
/*
Rating: Easy

Given a string, determine if it is a palindrome,
considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem,
we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true

Example 2:

Input: "race a car"
Output: false

 

Constraints:

    s consists only of printable ASCII characters.
*/

/*
    Approach: Pointer at end and start of string. If a character at one pointer is
              not a valid characer, move it towards the center by 1. If both are valid, check
              for equality and continue moving inward.
*/

    // O(n) time
    // O(1) space
    // Runtime: 4ms beats 52.41% of Java online submissions
    // Memory Usage: 40.2MB beats 34.75% of Java online submissions
    public boolean isPalindrome(String s) {
        int l_index = 0;
        int r_index = s.length() - 1;
        while (l_index < r_index)
        {
            Character l = Character.toLowerCase(s.charAt(l_index));
            Character r = Character.toLowerCase(s.charAt(r_index));
            boolean l_valid = true;
            boolean r_valid = true;
            if (!((l >= 97 && l <= 122) || (l >= 48 && l <= 57)))
            {
                ++l_index;
                l_valid = false;
            }
            if (!((r >= 97 && r <= 122) || (r >= 48 && r <= 57)))
            {
                --r_index;
                r_valid = false;
            }
            if (r_valid && l_valid && l != r)
            {
                return false;
            }
            else if (r_valid && l_valid && l == r)
            {
                ++l_index;
                --r_index;
            }
        }
        return true;
    }
}
