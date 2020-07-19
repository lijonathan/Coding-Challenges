public class Decode_String {
/*
Rating: Medium

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the
encoded_string inside the square brackets is being
repeated exactly k times. Note that k is guaranteed
to be a positive integer.

You may assume that the input string is always valid;
No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does
not contain any digits and that digits are only for those
repeat numbers, k. For example, there won't be input like 3a or 2[4].

Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"

Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"

Example 4:

Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
*/

/*
    Approach: Function to iterate starting from input index. If a string is a number, get the number of copies
              and recurse on the index of the start of the inner string(past the first '['). The call returns the string
              that has been copied the appropriate amount of times. Append that string to the current call stack's
              StringBuffer and increment the iterating index past the entire string that was repeated (use the counts of the
              number of left and right brackets, increment left for each '[' and decrement for each ']'. Break out when the
              left count is zero).

              Repeat the string the appropriate number of times and return that string.
*/

    // O(n) runtime, traverses the string
    // O(n) space for the recursive stack and the new strings
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 37.1 MB, less than 90.72% of Java online submissions
    public String decode(String s, int index, int copies)
    {
        int length = s.length();
        if (index < length)
        {
            StringBuffer sb = new StringBuffer();
            for (int k = index; k < length && s.charAt(k) != ']'; ++k)
            {
                Character c = s.charAt(k);
                if (!Character.isDigit(c))
                {
                    sb.append(Character.toString(c));
                }
                else
                {
                    StringBuffer copies_number = new StringBuffer();
                    Character digit = c;
                    int digit_index = k;
                    while(Character.isDigit(digit))
                    {
                        copies_number.append(Character.toString(digit));
                        ++digit_index;
                        digit = s.charAt(digit_index);
                    }
                    sb.append(decode(s, digit_index + 1, Integer.parseInt(copies_number.toString())));

                    int left_bracket = 1;
                    ++digit_index;
                    while(left_bracket > 0)
                    {
                        if (s.charAt(digit_index) == '[')
                        {
                            ++left_bracket;
                        }
                        else if (s.charAt(digit_index) == ']')
                        {
                            --left_bracket;
                        }
                        ++digit_index;
                    }
                    k = digit_index - 1;
                }
            }
            String s_copy = sb.toString();
            for (int i = 0; i < copies - 1; ++i)
            {
                sb.append(s_copy);
            }
            return sb.toString();
        }
        else
        {
            return "";
        }

    }
    public String decodeString(String s) {
        // recursive
        return decode(s, 0, 1);
    }
}
