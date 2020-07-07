public class Valid_Palindrome_II {
/*
Rating: Easy

Given a non-empty string s, you may delete at most one character.
Judge whether you can make it a palindrome.

Example 1:

Input: "aba"
Output: True

Example 2:

Input: "abca"
Output: True
Explanation: You could delete the character 'c'.

Note:

    The string will only contain lowercase characters a-z.
	The maximum length of the string is 50000.
*/


/*
    Approach: Move pointers from either end checking to see if they match.
              If a character does not match, mark the increment flag as true,
              and move the left increment up in the first pair of pointers and the right
              increment down in the second pair of pointers. If neither pair match and the increment
              flag has already been set, return false.
*/

    // O(n) time
    // O(1) space
    // Runtime: 13ms beats 20.83% of Java online submissions
    // Memory Usage: 40.4MB beats 32.32% of Java online submissions
    public boolean validPalindrome(String s) {
        int l_index_1 = 0;
        int l_index_2 = 0;
        int r_index_1 = s.length() -  1;
        int r_index_2 = s.length() - 1;
        
        boolean increment = false;
        while(l_index_1 <= r_index_1 && l_index_2 <= r_index_2)
        {
            Character cl1 = s.charAt(l_index_1);
            Character cl2 = s.charAt(l_index_2);
            Character cr1 = s.charAt(r_index_1);
            Character cr2 = s.charAt(r_index_2);
            
            if (cl1 == cr1 && cl2 == cr2)
            {
                ++l_index_1;
                --r_index_1;
                ++l_index_2;
                --r_index_2;
            }
            else if (cl1 == cr1)
            {
                ++l_index_1;
                --r_index_1;
            }
            else if (cl2 == cr2)
            {
                ++l_index_2;
                --r_index_2;
            }
            if (cl1 != cr1 && !increment)
            {
                increment = true;
                ++l_index_1;
                --r_index_2;
            }
            else if (cl1 != cr1 && cl2 != cr2)
            {
                return false;
            }
        }
        return true;
    }
}
