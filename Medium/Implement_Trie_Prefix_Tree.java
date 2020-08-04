public class Implement_Trie_Prefix_Tree {

/*
Rating: Medium

Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true

Note:

    You may assume that all inputs are consist of lowercase letters a-z.
    All inputs are guaranteed to be non-empty strings.
*/

/*
    Approach: Create a class for a TrieNode. Each TrieNode has potentially 26 children, so an array of length 26
              that holds a TrieNode if that particular letter is a child of the current node. Defaults to all nulls.
              A boolean field to determine if the current TrieNode is the termination of a word that has been inserted
              into the Trie, or if it is just a prefix.

              To insert a string, iterate down the Trie tree through the TrieNodes, creating new TrieNode leaves where
              necessary. At the last letter, set terminate to true. Search iterates down the tree and checks if the letters
              exist in the Trie path. The last letter check should see if the terminate flag is set.

              Checking a prefix iterates down the tree to check if the prefix path exists.

*/

    // O(n) runtime for insert, n = length of insert string, O(n) runtime for search, O(n) for startsWith
    // O(n) space for insert, O(1) for search, startsWith
    // Runtime: 33 ms, faster than 70.41% of Java online submissions
    // Memory Usage: 49 MB, less than 65.07% of Java online submissions
    class Trie {

        private class TrieNode
        {
            TrieNode[] children;
            boolean terminate;
            public TrieNode()
            {
                children = new TrieNode[26];
                terminate = false;
            }
        }

        /** Initialize your data structure here. */
        TrieNode starts;
        public Trie() {
            starts = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode root = starts;
            for (int i = 0; i < word.length(); ++i)
            {
                Character c = word.charAt(i);
                if (root.children[c - 97] == null)
                {
                    root.children[c - 97] = new TrieNode();
                    root = root.children[c - 97];

                }
                else
                {
                    root = root.children[c - 97];
                }
                if (i == word.length() - 1)
                {
                    root.terminate = true;
                }
            }
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode root = starts;
            for (int i = 0; i < word.length(); ++i)
            {
                root = root.children[word.charAt(i) - 97];
                if (root == null)
                {
                    return false;
                }
            }
            return root.terminate;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode root = starts;
            for (int i = 0; i < prefix.length(); ++i)
            {
                root = root.children[prefix.charAt(i) - 97];
                if (root == null)
                {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
}
