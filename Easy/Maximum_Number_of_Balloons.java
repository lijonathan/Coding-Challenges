public class Maximum_Number_of_Balloons {
/*
Rating: Easy

Given a string text, you want to use the characters
of text to form as many instances of the word "balloon"
as possible.

You can use each character in text at most once. Return
the maximum number of instances that can be formed.

Example 1:

Input: text = "nlaebolko"
Output: 1

Example 2:

Input: text = "loonbalxballpoon"
Output: 2

Example 3:

Input: text = "leetcode"
Output: 0

Constraints:

    1 <= text.length <= 10^4
    text consists of lower case English letters only.
*/

/*
    Approach: Add up counts of the characters in text. Then subtract the characters needed
              for "balloon" and increment the count. Repeat while all characters needed are
              greater than 0.
*/

    // O(n) runtime, n is the length of the input string
    // O(1) space --> 26 length array for character set
    // Runtime: 1ms beats 100.00% of Java online submissions
    // Memory Usage: 37.8MB beats 85.90% of Java online submissions
    public int maxNumberOfBalloons(String text) {
        int[] chars = new int[26];
        int length = text.length();
        for (int i = 0; i < length; ++i)
        {
            chars[text.charAt(i) - 97]++;
        }
        int count = 0;
        while(chars[1] > 0 && chars[0] > 0 && chars[11] > 0 &&
             chars[14] > 0 && chars[13] > 0)
        {
            chars[1] -= 1;
            chars[0] -= 1;
            chars[11] -= 2;
            chars[14] -= 2;
            chars[13] -= 1;
            if (chars[1] >= 0 && chars[0] >= 0 && chars[11] >= 0 &&
             chars[14] >= 0 && chars[13] >= 0)
            {
                ++count;
            }
        }
        return count;
    }
}
