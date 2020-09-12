import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Word_Search_II {
/*
Rating: Hard

Given a 2D board and a list of words from the dictionary,
find all words in the board.

Each word must be constructed from letters of sequentially
adjacent cell, where "adjacent" cells are those horizontally
or vertically neighboring. The same letter cell may not be used
more than once in a word.



Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]



Note:

    All inputs are consist of lowercase letters a-z.
    The values of words are distinct.
*/

/*
    Approach: Make a trie of all the words in the list. Iterate through the word search
              and backtrack starting from the current point. If the current string is not
              a prefix in the trie, return from the current recursive call. Mark the
              current board with a '#' to prevent cycles and before returning, return it back
              to the original char.
*/

    // O(O(m(4⋅3^(n−1))) runtime, m = size of board, n = length of longest word
    // O(w) where w = words in the set
    // Runtime: 73 ms, faster than 34.23% of Java online submissions
    // Memory Usage: 52.6 MB, less than 17.14% of Java online submissions
    class Trie
    {
        class TrieNode
        {
            TrieNode[] children;
            public TrieNode()
            {
                children = new TrieNode[26];
            }
        }
        TrieNode start;
        public Trie()
        {
            this.start = new TrieNode();
        }
        public void insert(String s)
        {
            int length = s.length();
            TrieNode root = start;
            for (int i = 0; i < length; ++i)
            {
                char c = s.charAt(i);
                int index = c - 97;
                if (root.children[index] == null)
                {
                    root.children[index] = new TrieNode();
                }
                root = root.children[index];
            }
        }
        public boolean prefix(String s)
        {
            int length = s.length();
            TrieNode root = start;
            for (int i = 0; i < length; ++i)
            {
                char c = s.charAt(i);
                int index = c - 97;
                if (root.children[index] == null)
                {
                    return false;
                }
                root = root.children[index];
            }
            return true;
        }
    }
    public void backtrack(HashSet<String> ws, char[][] board, Trie t, ArrayList<String> found,
            int row, int col, String curr)
    {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
                board[row][col] == '#')
        {
            return;
        }
        else
        {
            String next = curr + board[row][col];
            char c = board[row][col];
            board[row][col] = '#';
            if (ws.contains(next))
            {
                found.add(next);
                ws.remove(next);
            }
            if (t.prefix(next))
            {
                backtrack(ws, board, t, found, row - 1, col, next);
                backtrack(ws, board, t, found, row + 1, col, next);
                backtrack(ws, board, t, found, row, col - 1, next);
                backtrack(ws, board, t, found, row, col + 1, next);
            }
            board[row][col] = c;
        }
    }
    public List<String> findWords(char[][] board, String[] words) {
        ArrayList<String> found = new ArrayList<String>();
        HashSet<String> word_set = new HashSet<String>();
        Trie t = new Trie();
        for (int i = 0; i < words.length; ++i)
        {
            word_set.add(words[i]);
            t.insert(words[i]);
        }
        for (int i = 0; i < board.length; ++i)
        {
            for (int k = 0; k < board[0].length; ++k)
            {
                backtrack(word_set, board, t, found, i, k, "");
            }
        }
        return found;
    }
}
