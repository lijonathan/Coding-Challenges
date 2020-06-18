import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Word_Ladder {

/*
Rating: Medium

Given two words (beginWord and endWord), and a dictionary's
word list, find the length of shortest transformation sequence
from beginWord to endWord, such that:

    Only one letter can be changed at a time.
    Each transformed word must exist in the word list.

Note:

    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.

Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

*/

    /* Approach: BFS. Change each letter in word one letter at a time through all
     * possible combinations of a-z. If new word is in wordList, add to queue.
     * Keep track of chain of words to reach current word and when endWord is found,
     * return the chain length that led to it. Only need to see if current word has been
     * seen before, no need to compare length because by nature BFS reaches elements
     * in the shortest possible order.
     */

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // O(n) * (m^2) where m is the avg length of each word. n because BFS
        // through the entire wordList(only words in wordList get added to the queue) and
        // iterate through length of each word to replace each letter. Strings are immuatable
        // so creating new string takes m time.
        
        // O(m^2) * (n) n words = n hashmap entries. Each hashmap entry has a key of length
        // m and the list of length m(m is avg length)
        
        // Runtime: 202ms beats 28.07% of Java online submissions
        // Memory Usage: 43.8MB beats 36.80% of Java online submissions
        // memory and runtime are asymptotically in line
        HashSet<String> hs = new HashSet<String>(wordList);
        if (!hs.contains(endWord))
        {
            return 0;
        }
        // looks like BFS problem
        HashMap<String, ArrayList<String>> links = new HashMap<String, ArrayList<String>>();
        ArrayList<String> first_link = new ArrayList<String>();
        first_link.add(beginWord);
        links.put(beginWord, first_link);
        ArrayDeque<String> queue = new ArrayDeque<String>();
        queue.add(beginWord);
        while(!queue.isEmpty())
        {
            String curr = queue.remove();
            for (int i = 0; i < curr.length(); ++i)
            {
                for (int k = 97; k <= 122; ++k)
                {
                    StringBuilder sb = new StringBuilder(curr);
                    sb.replace(i, i + 1, Character.toString((char) k));
                    String replaced = sb.toString();
                    if (hs.contains(replaced))
                    {
                        ArrayList<String> prev_link = links.get(curr);
                        if (curr.equals(endWord))
                        {
                            return prev_link.size();
                        }
                        ArrayList<String> curr_link = links.get(replaced);
                        if (curr_link == null)
                        {
                            curr_link = new ArrayList<String>(prev_link);
                            curr_link.add(replaced);
                            links.put(replaced, curr_link);
                            queue.add(replaced);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
