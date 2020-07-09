import java.util.ArrayList;
import java.util.List;

public class Restore_IP_Addresses {
/*
Rating: Medium

Given a string containing only digits, restore it
by returning all possible valid IP address combinations.

A valid IP address consists of exactly four integers
(each integer is between 0 and 255) separated by single points.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
*/

    /*
    Approach: Recurse through the string and track the current index. Append the current
              substring of index, index + 1 character after, and index + 2 characters after,
              checking the string for validity -- does not extend past end of the string and
              does not start with '0' and <= 255.

              Also track the number of "sections", the sections between the ".". Once the section count
              hits 5, starting at 1, it is the base case.

              Backtrack after each recursive call by removing what was added i.e. 1 character,
              and adding 2 characters and recursing and so on.
    
    */

    // O(n) time
    // O(1) space
    // Runtime: 2ms beats 87.18% of Java online submissions
    // Memory Usage: 39.5MB beats 72.97% of Java online submissions
    public void generate(String s, ArrayList<String> ips, StringBuffer sb, int idx,
            int sections)
    {
        if (idx == s.length() && sections == 5)
        {
            ips.add(sb.toString().substring(0, sb.length() - 1));
        }
        else if (sections >= 5)
        {
            return;
        }
        else if (idx < s.length())
        {
            String s1 = s.substring(idx, idx + 1);

            sb.append(s1);
            sb.append(".");
            generate(s, ips, sb, idx + 1, sections + 1);
            sb.delete(sb.length() - 2, sb.length());

            if (idx + 1 < s.length())
            {
                String s2 = s.substring(idx, idx + 2);
                if (s2.charAt(0) != '0')
                {
                    sb.append(s2);
                    sb.append(".");
                    generate(s, ips, sb, idx + 2, sections + 1);
                    sb.delete(sb.length() - 3, sb.length());
                }
            }
            if (idx + 2 < s.length())
            {
                String s3 = s.substring(idx, idx + 3);
                if (s3.charAt(0) != '0')
                {
                    if (Integer.parseInt(s3) <= 255)
                    {
                        sb.append(s3);
                        sb.append(".");
                        generate(s, ips, sb, idx + 3, sections + 1);
                        sb.delete(sb.length() - 4, sb.length());
                    }
                }
            }
        }
    }
    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> ips = new ArrayList<String>();
        generate(s, ips, new StringBuffer(), 0, 1);
        return ips;
    }
}
