import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Word_Break_II {
    
/*
Rating: Hard

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.

Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

Example 1:

Input:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
    [
        "cats and dog",
        "cat sand dog"
    ]

Example 2:

Input:
    s = "pineapplepenapple"
    wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
    [
        "pine apple pen apple",
        "pineapple pen apple",
        "pine applepen apple"
    ]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:

Input:
    s = "catsandog"
    wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
    []
*/
    
/*
    Approach: First check that each character in the string s occurs in the wordDict. <-- Key performance

              Backtracking -- Starting at index 0, iterate through the wordDict and check if the substring
              of the current index to index equal to the wordDict word size. If so, recurse forward to that index
              and repeat the process while adding the string to the StringBuffer.

              If the index equals the length of the string, add the string to the answer list. After each recursive call returns
              the first time, remove the current word from the end of the StringBuffer.
*/


    // O(2^n) runtime, each recursive call branches two recursive calls. At worst case, each string in the
    // wordDict is length 1 so there are n traverses for the length of the input string, and each one branches twice
    // O(n) space for the maximum depth of the recursive call stack
    // Runtime: 4 ms, faster than 96.24% of Java online submissions
    // Memory Usage: 39.4 MB, less than 92.75% of Java online submissions
    public boolean check_valid(String s, HashSet<String> wordDict)
    {
        boolean[] char_set = new boolean[26];
        for (String curr : wordDict)
        {
            for (int j = 0; j < curr.length(); ++j)
            {
                char_set[curr.charAt(j) - 97] = true;
            }
        }
        for (int i = 0; i < s.length(); ++i)
        {
            if (!char_set[s.charAt(i) - 97])
            {
                return false;
            }
        }
        return true;
    }
    public void backtrack(ArrayList<String> sentences, HashSet<String> wordDict,
            StringBuffer sb, String s, int idx, int spaces, HashSet<Integer> dead_i)
    {
        if (idx == s.length())
        {
            sentences.add(sb.toString().substring(0, sb.length() - 1));
        }
        else
        {
            boolean dead = true;
            for (String word  : wordDict)
            {
                int next_i = idx + word.length();
                if (next_i <= s.length() && !dead_i.contains(next_i))
                {
                    String substring = s.substring(idx, next_i);
                    if (substring.equals(word))
                    {
                        dead = false;
                        sb.append(word);
                        sb.append(" ");
                        backtrack(sentences, wordDict, sb, s, next_i, spaces + 1, dead_i);
                        sb.delete(idx + spaces, sb.length());
                    }
                }
            }
            if (dead)
            {
                dead_i.add(idx);
            }
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        ArrayList<String> sentences = new ArrayList<String>();
        HashSet<String> hs = new HashSet<String>(wordDict);
        if (!check_valid(s, hs))
        {
            return sentences;
        }
        backtrack(sentences, hs, new StringBuffer(), s, 0, 0,
                new HashSet<Integer>());
        return sentences;
    }
}
