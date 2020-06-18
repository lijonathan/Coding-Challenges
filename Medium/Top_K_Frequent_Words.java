import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class Top_K_Frequent_Words {

/*
Rating: Medium

Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest.
If two words have the same frequency, then the word with the lower alphabetical
order comes first.

Example 1:

Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.

Example 2:

Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.

Note:

    You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
    Input words contain only lowercase letters.

Follow up:

    Try to solve it in O(n log k) time and O(n) extra space.

*/
    /*
    Approach: MinHeap

    Using Java builtin priority queue instead of making own heap.

    Keys: compare method is overriden. First element is compared to second. Negative
    if first has lower priority, positive if higher, 0 if same. 

     Priority Queue Class Javadoc: The head of this queue is the least element with
    respect to the specified ordering.
    
     */
    private class Pair
    {
        public int count;
        public String word;
        public Pair(String word, int count)
        {
            this.word = word;
            this.count = count;
        }
    }
    private class minimum implements Comparator<Pair>
    {
        public int compare(Pair i1, Pair i2)
        {
            return i1.count - i2.count;
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        //  O(n log k) --> min heap --> least frequent at top
        // O(n + k) space --> HashMap of counts
        // Runtime: 6ms faster than 40.66% of Java submissions
        // Memory Usage: 39.4 MB, less than 89.47% of Java submissions
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new minimum());
        for (int i = 0; i < words.length; ++i)
        {
            Integer count = counts.get(words[i]);
            if (count == null)
            {
                count = 0;
            }
            counts.put(words[i], count + 1);
        }
        for (String s: counts.keySet())
        {
            Integer count = counts.get(s);
            if (pq.size() < k)
            {
                Pair p = new Pair(s, count);
                pq.add(p);
            }
            else
            {
                if (pq.peek().count < count)
                {
                    Pair p = new Pair(s, count);
                    pq.poll();
                    pq.add(p);
                }
            }
        }
        ArrayList<String> ans = new ArrayList<String>();
        int first_count = pq.peek().count;
        ArrayList<String> ties = new ArrayList<String>();
        // dealing with ties is the trickiest part, remove all elements that have
        // least number of counts in priority queue
        while(pq.peek() != null && pq.peek().count == first_count)
        {
            pq.remove();
        }
        // add all potential ties for least counts that can still be part of
        // k into a ties arraylist
        for (String s: counts.keySet())
        {
            if (counts.get(s) == first_count)
            {
                ties.add(s);
            }
        }
        Collections.sort(ties);
        // pop off all elements in groups of same counts
        while (pq.size() > 0)
        {
            int curr_count = pq.peek().count;
            ArrayList<String> curr_ties = new ArrayList<String>();
            while (pq.peek() != null && pq.peek().count == curr_count)
            {
                curr_ties.add(pq.poll().word);
            }
            Collections.sort(curr_ties);
            // reverse ties because ans will be reversed later
            Collections.reverse(curr_ties);
            for (int i = 0; i < curr_ties.size(); ++i)
            {
                ans.add(curr_ties.get(i));
            }
        }
        // min heap pops off elements starting at smallest count, need to reverse
        Collections.reverse(ans);
        int stagger = ans.size();
        // add in ties for least amount that will make the list
        for (int i = stagger; i < k && (i - stagger) < ties.size(); ++i)
        {
            ans.add(ties.get(i - stagger));
        }
        return ans;
    }
}
