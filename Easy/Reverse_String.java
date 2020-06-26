public class Reverse_String {

/*
Rating: Easy

Write a function that reverses a string.
The input string is given as an array of characters char[].

Do not allocate extra space for another array,
you must do this by modifying the input array
in-place with O(1) extra memory.

You may assume all the characters consist of
printable ascii characters.

 

Example 1:

Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]

Example 2:

Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
*/

/*
    Approach: Recursivly swapped left and right indexes moving towards the middle.
              It would have been better to do it iteratively, but this was recursive
              practice.
*/

    // O(n) runtime
    // O(1) space excluding recursive call stack
    // Runtime: 1ms beats 56.46% of Java online submissions
    // Memory Usage: 48 MB beats 19.51% of Java online submissions
    public void reverse(char[] s, int index)
    {
        if (index < s.length / 2)
        {
            char temp = s[index];
            s[index] = s[s.length - 1 - index];
            s[s.length - 1 - index] = temp;
            reverse(s, index + 1);
        }
    }
    public void reverseString(char[] s) {
        
        reverse(s, 0);
    }
}
