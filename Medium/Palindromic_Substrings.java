public class Palindromic_Substrings {
/*
Rating: Medium

Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as
different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

Note:

    The input string length won't exceed 1000.
*/

/*
    Approach: Iterate through potential centers of palindromic substrings. Centers either
              lie directly on a character or in between two characters. There are 2n - 1
              such centers.

              If the index is odd, the center is in between two characters. l_ptr = i / 2,  r_ptr = i / 2 + 1.
              If the index is even, the center is on a character and l_ptr = r_ptr = i / 2.

              Expand left and right away from the center and count the number of palindromic substrings.
*/

    // O(n^2) runtime
    // O(1) space
    // Runtime: 3 ms, faster than 79.33% of Java online submissions
    // Memory Usage: 37.7 MB, less than 69.96% of Java online submissions
    public int countSubstrings(String s) {
        int iterations = s.length() * 2 - 1;
        int length = s.length();
        int num = 0;
        for (int i = 0; i < iterations; ++i)
        {
            int l_ptr = i / 2;
            int r_ptr = i / 2;
            if (i % 2 == 1)
            {
                r_ptr += 1;
            }
            while(l_ptr >= 0 && r_ptr < length)
            {
                if (s.charAt(l_ptr) == s.charAt(r_ptr))
                {
                    --l_ptr;
                    ++r_ptr;
                }
                else
                {
                    break;
                }
                ++num;
            }
        }
        return num;
    }
}
