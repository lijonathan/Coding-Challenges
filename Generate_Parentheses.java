import java.util.ArrayList;

public class Generate_Parentheses {

/*
Rating: Medium

Given n pairs of parentheses, write a function
to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]


*/

    public void generate(int n, int l_num, int r_num, ArrayList<String> ans,
            String s)
    {
        if (r_num == n)
        {
            ans.add(s);
            return;
        }
        else if (r_num == l_num)
        {
            generate(n, l_num + 1, r_num, ans, s + "(");
        }
        else
        {
            if (l_num < n)
            {
                generate(n, l_num + 1, r_num, ans, s + "(");
            }
            generate(n, l_num, r_num + 1, ans, s + ")");
        }
        return;
    }

    public ArrayList<String> generateParenthesis(int n)
    {
        ArrayList<String> ans = new ArrayList<String>();
        generate(n, 0, 0, ans, "");
        return ans;
    }
}
