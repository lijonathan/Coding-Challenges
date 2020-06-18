import java.util.HashSet;

class Word_Break {

/*
Rating: Medium

Given a non-empty string s and a dictionary wordDict
containing a list of non-empty words, determine if s
can be segmented into a space-separated sequence of one or more dictionary words.

Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.

Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false




*/
    /*
    Approach: Memoize by creating a set of visited indices starting from iteration
              from the left.
              If the substring from the start of s, 0, to the current index is in the 
              wordDict, if it is, add the current index to the index hash set.
              At an index where it is not a substring from start of s
              to current index, first see if the current index - length of word in wordDict has
              been visited, then iterate through the list of words in wordDict and see if
              the substring from the current index - length of word in wordDict to current
              index is in the wordDict. If it is, add the current index to the index hashset.
              
              If the final index s.length() - 1 is in the index hashset, it can be made,
              otherwise it cannot.
    */
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<Integer> indexes = new HashSet<Integer>();
        HashSet<String> hs = new HashSet<String>(wordDict);
        // add each substring of s into set for constant lookup
        // O(n * p) where n is the number of chars in s and p is the number of
        // words in wordDict
        // space O(n). The number of visited indices will not be larger than the number of
        // indices in s
        int length = s.length();
        for (int i = 0; i < length; ++i)
        {
            if (hs.contains(s.substring(0, i + 1)))
            {
                indexes.add(i);
            }
            else
            {
                /****Was originally memoizing/iterating over all indexes that had been seen
                seeing if any 
                Iterator it = indexes.iterator();
                while (it.hasNext())
                {
                    Integer start = (Integer) it.next();
                    String substring = s.substring(start + 1, i + 1);
                    if (hs.contains(substring))
                    {
                        indexes.add(i);
                        break;
                    }
                }
                substring from that index to current was in set. Faster to iterate over all
                strings in hashset because more likely to have much fewer in set than the
                set of indexes that have been visited.
                **/
                for (String d_s: hs)
                {
                    if (i - d_s.length() + 1 >= 0 && indexes.contains(i - d_s.length())
                        && s.substring(i - d_s.length() + 1, i + 1).equals(d_s))
                    {
                        indexes.add(i);
                    }
                }
            }
        }
        if (indexes.contains(s.length() - 1))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
