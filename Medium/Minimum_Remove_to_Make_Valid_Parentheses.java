import java.util.HashSet;

class Minimum_Remove_to_Make_Valid_Parentheses {
/*

Rating: Medium

Given a string s of '(' , ')' and lowercase English characters. 

Your task is to remove the minimum number of parentheses
( '(' or ')', in any positions ) so that the resulting parentheses
string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

    It is the empty string, contains only lowercase characters, or
    It can be written as AB (A concatenated with B), where A and B are
    valid strings, or
    It can be written as (A), where A is a valid string.

 

Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"

 

Constraints:

    1 <= s.length <= 10^5
    s[i] is one of  '(' , ')' and lowercase English letters.


*/
    
    public String minRemoveToMakeValid(String s) {
        // O(n) time, O(n) space
        HashSet<Integer> extras = new HashSet<Integer>();
        StringBuffer sb = new StringBuffer();
        int l_count = 0; 
        for (int i = 0; i < s.length(); ++i)
        {
            Character c = s.charAt(i);
            if (c == '(')
            {
                ++l_count;
            }
            else if (c == ')')
            {
                if (l_count > 0)
                {
                    --l_count;
                }
                else
                {
                    extras.add(i);
                }
            }
        }
        int r_count = 0;
        for (int i = s.length() - 1; i >= 0; --i)
        {
            Character c = s.charAt(i);
            if (c == ')')
            {
                ++r_count;
            }
            else if (c == '(')
            {
                if (r_count > 0)
                {
                    --r_count;
                }
                else
                {
                    extras.add(i);
                }
            }
        }
        for (int i = 0; i < s.length(); ++i)
        {
            if (!extras.contains(i))
            {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
