import java.util.ArrayList;

public class Add_Bold_Tag_in_String {
/*
Rating: Medium

Given a string s and a list of strings dict, you need to add
a closed pair of bold tag <b> and </b> to wrap the substrings
in s that exist in dict. If two such substrings overlap, you
need to wrap them together by only one pair of closed bold tag.
Also, if two substrings wrapped by bold tags are consecutive,
you need to combine them.

Example 1:

Input: 
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"



Example 2:

Input: 
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"

Constraints:

    The given dict won't contain duplicates, and its length won't exceed 100.
    All the strings in input have length in range [1, 1000].
*/

/*
    Approach: Trie of all the strings within the dictionary with a marker for
              if the current node is an ending point(string exist in the dictionary).

              Iterate through the string and at each index, check for the furthest index
              that makes a substring from the current index to the furthest index.

              Make a list of intervals and then merge those intervals together.

              For each interval in the string, add the tag.
*/

    // O(n * w) where n = length of s, w is length of all strings in dict
    // O(w) space for the Trie
    // Runtime: 23 ms, faster than 40.31% of Java online submissions
    // Memory Usage: 64.9 MB, less than 8.33% of Java online submissions
    class Trie
    {
        class TrieNode
        {
            TrieNode[] children;
            boolean end;
            public TrieNode()
            {
                children = new TrieNode[62];
                end = false;
            }
        }
        TrieNode start;
        public Trie()
        {
            start = new TrieNode();
        }
        public void insert(String s)
        {
            TrieNode root = start;
            int length = s.length();
            for (int i = 0; i < length; ++i)
            {
                char c = s.charAt(i);
                if (c >= 48 && c <= 57)
                {
                    if (root.children[c - 48] == null)
                    {
                        root.children[c - 48] = new TrieNode();
                    }
                    root = root.children[c - 48];
                }
                else if (c >= 65 && c <= 90)
                {
                    if (root.children[c - 65 + 10] == null)
                    {
                        root.children[c - 65 + 10] = new TrieNode();
                    }
                    root = root.children[c - 65 + 10];
                }
                else if (c >= 97)
                {
                    if (root.children[c - 97 + 36] == null)
                    {
                        root.children[c - 97 + 36] = new TrieNode();
                    }
                    root = root.children[c - 97 + 36];
                }
            }
            root.end = true;
        }
        public int ends(String s, int i)
        {
            int length = s.length();
            TrieNode root = start;
            int last = -1;
            for (int k = i; k < length; ++k)
            {
                char c = s.charAt(k);
                int index = c - 48;
                if (c >= 65 && c <= 90)
                {
                    index = c - 65 + 10;
                }
                else if (c >= 97)
                {
                    index = c - 97 + 36;
                }
                root = root.children[index];
                if (root == null)
                {
                    return last == -1 ? -1 : last;
                }
                if (root.end)
                {
                    last = k;
                }
            }
            return last;
        }
    }
    public String addBoldTag(String s, String[] dict) {
        Trie t = new Trie();
        for (int i = 0; i < dict.length; ++i)
        {
            t.insert(dict[i]);
        }
        int length = s.length();
        ArrayList<Integer[]> intervals = new ArrayList<Integer[]>();
        for (int k = 0; k < length; ++k)
        {
            int ends = t.ends(s, k);
            if (ends > -1 && intervals.size() > 0 && k - 1 <= intervals.get(intervals.size() - 1)[1])
            {
                intervals.get(intervals.size() - 1)[1] = Math.max(intervals.get(intervals.size() - 1)[1], ends);
            }
            else if (ends > -1)
            {
                Integer[] new_int = new Integer[2];
                new_int[0] = k;
                new_int[1] = ends;
                intervals.add(new_int);
            }
        }

        StringBuffer sb = new StringBuffer();
        int intervals_size = intervals.size();
        int prev_end = -1;
        for (int i = 0; i < intervals_size; ++i)
        {
            if (intervals.get(i) == null)
            {
                continue;
            }
            int start = intervals.get(i)[0];
            int end = intervals.get(i)[1];
            if (prev_end < start - 1)
            {
                sb.append(s.substring(prev_end + 1, start));
            }
            prev_end = end;
            sb.append("<b>");
            sb.append(s.substring(start, end + 1));
            sb.append("</b>");
        }
        sb.append(s.substring(prev_end + 1, length));
        return sb.toString();
    }
}
