import java.util.Stack;

public class Valid_Parentheses {

/*
Rating: Easy

Given a string containing just the characters
(', ')', '{', '}', '[' and ']',
determine if the input string is valid.

An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.

Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true

Example 2:

Input: "()[]{}"
Output: true

Example 3:

Input: "(]"
Output: false

Example 4:

Input: "([)]"
Output: false

Example 5:

Input: "{[]}"
Output: true

*/

    public boolean isValid(String s) {
        // O(n) space for stack
        // O(n) time
        // n is the number of characters in s
        Stack stack = new Stack();
        for (int i = 0; i < s.length(); ++i)
        {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[')
            {
                stack.push(c);
            }
            else
            {
                if (stack.empty())
                {
                    return false;
                }
                char p = (char) stack.pop();
                if (c == ')' && p != '(')
                {
                    return false;
                }
                if (c == ']' && p != '[')
                {
                    return false;
                }
                if (c == '}' && p != '{')
                {
                    return false;
                }
            }
        }
        if (stack.empty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
