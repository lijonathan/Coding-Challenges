public class Palindrome_Number {
/*
Rating: Easy

Determine whether an integer is a palindrome. An integer is
a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true

Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right
to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Follow up:
Coud you solve it without converting the integer to a string?
*/

/*
    Approach: Reverse the number by modding off the least significant digit by % 10.
              Multiply reverse by 10 and add the modded off digit. Check at the end
              if the reversed and the current are equal. Use a long for the reverse number
              to prevent overflow.
*/

    // O(1), max # of digits in int is number of digits in -2^32
    // O(1) space
    // Runtime: 6 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 38.6 MB, less than 91.52% of Java online submissions
    public boolean isPalindrome(int x) {
        if (x < 0 || (x > 0 && x % 10 == 0))
        {
            return false;
        }
        int mod = x;
        long reverse = 0;
        while(mod > 0)
        {
            reverse *= 10;
            int digit = mod % 10;
            mod = mod / 10;
            reverse += digit;
        }
        return (int) reverse == x;
    }
}
