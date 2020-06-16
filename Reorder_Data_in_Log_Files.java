import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Reorder_Data_in_Log_Files {

/*
 Rating: Easy
 
 You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.
Then, either:
    Each word after the identifier will consist only of lowercase letters, or;
    Each word after the identifier will consist only of digits.

We will call these two varieties of logs letter-logs and digit-logs.
It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.
The letter-logs are ordered lexicographically ignoring identifier, with the
identifier used in case of ties.  The digit-logs should be put in their
original order.

Return the final order of the logs.

 

Example 1:

Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]

Constraints:

    0 <= logs.length <= 100
    3 <= logs[i].length <= 100
    logs[i] is guaranteed to have an identifier, and a word after the identifier.
 
 */

    // O(n log n) runtime --> n is the number of characters in logs
    // O(n) space --> sorting takes up n space
    // Runtime: 13 ms, faster than 18.69% of Java online submissions
    // Memory Usage: 39.4 MB, less than 95.95% of Java online submissions
    public String[] reorderLogFiles(String[] logs) {
        String[] ordered = new String[logs.length];
        HashSet<Integer> number_indices = new HashSet<Integer>();
        HashMap<String, ArrayList<String>> letter_log = new HashMap<String, ArrayList<String>>();
        int count = 0;
        for (int i = 0; i < ordered.length; ++i)
        {
            String[] data = logs[i].split(" ", 2);
            boolean digits = true;
            for (int k = 0; k < data[1].length(); ++k)
            {
                if (data[1].charAt(k) != ' ')
                {
                    if (data[1].charAt(k) < 48 || data[1].charAt(k) > 57)
                    {
                        digits = false;
                        break;
                    }
                }
            }
            if (!digits)
            {
                ordered[count] = data[1];
                ++count;
                ArrayList<String> log = letter_log.get(data[1]);
                if (log == null)
                {
                    log = new ArrayList<String>();
                }
                log.add(data[0]);
                letter_log.put(data[1], log);
            }
            else
            {
                number_indices.add(i);
            }
        }
        Arrays.sort(ordered, 0, count);
        for(int i = 0; i < count; ++i)
        {
            ArrayList<String> prefixes = letter_log.get(ordered[i]);
            if (prefixes.size() == 1)
            {
                ordered[i] = prefixes.get(0) + " " + ordered[i];
            }
            else
            {
                Collections.sort(prefixes);
                for (int k = 0; k < prefixes.size(); ++k)
                {
                    ordered[i + k] = prefixes.get(k) + " " + ordered[i + k];
                }
                i = i + prefixes.size() - 1;
            }
        }
        for (int i = 0; i < logs.length; ++i)
        {
            if (number_indices.contains(i))
            {
                ordered[count] = logs[i];
                ++count;
            }
        }
        return ordered;
    }
}
