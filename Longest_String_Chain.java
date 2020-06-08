import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Longest_String_Chain {

/*
Rating: Medium

Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only
if we can add exactly one letter anywhere in word1 to make it
equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with
k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor
of word_3, and so on.

Return the longest possible length of a word chain with words chosen
from the given list of words.

 

Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".

Note:

    1 <= words.length <= 1000
    1 <= words[i].length <= 16
    words[i] only consists of English lowercase letters.

*/

    /*
     Approach: Dynamic Programming - Sort by ascending string length.
     
     For each string, iterate through the characters and see if the word sans
     current character has been seen before. If so, add it to a sequence chain with
     a HashMap of latest string to the subsequence, if not, add a new entry of that
     word and a sequence of one. Return length of longest sequence chain.
     */
    public void merge(String[] words, int start, int mid, int end)
    {
        int start_i = start;
        int end_i = mid + 1;
        String[] buffer = new String[end - start + 1];
        int index = 0;
        while (start_i <= mid || end_i <= end)
        {
            if (start_i > mid)
            {
                buffer[index] = words[end_i];
                ++end_i;
            }
            else if (end_i > end)
            {
                buffer[index] = words[start_i];
                ++start_i;
            }
            else if (words[start_i].length() <= words[end_i].length())
            {
                buffer[index] = words[start_i];
                ++start_i;
            }
            else
            {
                buffer[index] = words[end_i];
                ++end_i;
            }
            ++index;
        }
        index = 0;
        for (int i = start; i <= end; ++i)
        {
            words[i] = buffer[index];
            ++index;
        }
    }
    public void mergeSort(String[] words, int start, int end)
    {
        if (start < end)
        {
            int mid = (start + end) / 2;
            mergeSort(words, start, mid);
            mergeSort(words, mid + 1, end);
            merge(words, start, mid, end);
        }
    }
    public void split(HashMap<Integer, HashSet<String>> length_map,
                      HashMap<String, ArrayList<String>> sequences, String s)
    {
        for (int i = 0; i < s.length(); ++i)
        {
            // length_map for quick lookup of all strings seen with length of s.length
            // - 1
            String shorter = s.substring(0, i) + s.substring(i + 1, s.length());
            if (length_map.containsKey(shorter.length()) &&
               length_map.get(shorter.length()).contains(shorter))
            {
                HashSet<String> entry = length_map.get(s.length());
                if (entry == null)
                {
                    entry = new HashSet<String>();
                }
                entry.add(s);
                length_map.put(s.length(), entry);
                // add new arraylist of current sequence to sequences map
                ArrayList<String> curr_sequence = sequences.get(shorter);
                ArrayList<String> new_seq = new ArrayList<String>(curr_sequence);
                new_seq.add(s);
                sequences.put(s, new_seq);
                return;
            }
        }
        HashSet<String> new_entry = length_map.get(s.length());
        if (new_entry == null)
        {
            new_entry = new HashSet<String>();
        }
        new_entry.add(s);
        length_map.put(s.length(), new_entry);
        ArrayList<String> new_al = new ArrayList<String>();
        new_al.add(s);
        sequences.put(s, new_al);
    }
    public int longestStrChain(String[] words) {
        // Runtime: 28 ms, faster than 87.48% of Java online submissions
        // Memory Usage: 39.8 MB, less than 44.34% of Java online submissions
        // nlogn sort + n * min(u,p) where u is avg length of word, p is the avg length of 
        // sequence
        // O(n * min(u, p)) runtime
        // memory: length_map -> O(n * 2) memory, sequences O(n * (n-1)) --> O(n^2)
        if (words.length == 0)
        {
            return 0;
        }
        mergeSort(words, 0, words.length - 1);
        HashMap<Integer, HashSet<String>> length_map = new HashMap<Integer, HashSet<String>>(); 
        HashMap<String, ArrayList<String>> sequences = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < words.length; ++i)
        {
            split(length_map, sequences, words[i]);
        }
        int max_length = Integer.MIN_VALUE;
        for (String s: sequences.keySet())
        {
            if (sequences.get(s).size() > max_length)
            {
                max_length = sequences.get(s).size();
            }
        }
        return max_length;
    }
}
