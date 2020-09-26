import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Reorganize_String {
/*
Rating: Medium

Given a string S, check if the letters can be rearranged so that two
characters that are adjacent to each other are not the same.

If possible, output any possible result. If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"

Example 2:

Input: S = "aaab"
Output: ""

Note:

    S will consist of lowercase letters and have length in range [1, 500].
*/

/*
    Approach: Idea -- Need to always try to use up the character with the most occurrences
                      as quickly as possible. 

                      Simulate building the string below.

                      Create Pair with character and count of character remaining. Create pairs
                      of chars with their initial counts and input them into a MaxHeap compared based
                      on highest counts at the top.

                      Continually pop off the PriorityQueue and append the char of the current Pair onto
                      the StringBuffer. Have a pair that tracks the previously popped off Pair (need to hold each
                      pair that gets popped off for one extra loop iteration before adding it back into the PriorityQueue
                      as to prevent two chars that are the same ending up next to each other).

                      On each iteration after popping off the queue, if the previous Pair count is not
                      0, add it back into the queue. Decrement the count of the current pair that got popped off and
                      then set the prev tracker q to the pair that just got popped off. Append the char of the current
                      Pair to the string.

                      After the queue is empty, if the remaining Pair's char count is greater than 0 and
                      if the char is the same as the last char in the buffer, return "".

                      Return the built out string if the above conditions are not met.
*/

    // O(n log A) where A is the size of character set, n is size of S
    // O(A) space
    // Runtime: 3 ms, faster than 61.62% of Java online submissions
    // Memory Usage: 37.6 MB, less than 68.88% of Java online submissions
    class Pair
    {
        char c;
        int count;
        public Pair(char c, int count)
        {
            this.c = c;
            this.count = count;
        }
    }
    class PairComparator implements Comparator<Pair>
    {
        public int compare(Pair p, Pair q)
        {
            return q.count - p.count;
        }
    }
    public String reorganizeString(String S) {
        if (S == null || S.length() < 2)
        {
            return S;
        }
        PriorityQueue<Pair> pq = new PriorityQueue(new PairComparator());
        HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
        int length = S.length();
        for (int i = 0; i < length; ++i)
        {
            char c = S.charAt(i);
            Integer cnt = counts.get(c);
            if (cnt == null)
            {
                cnt = 0;
            }
            counts.put(c, cnt + 1);
        }
        for (Character c : counts.keySet())
        {
            Pair p = new Pair(c, counts.get(c));
            pq.offer(p);
        }
        StringBuffer sb = new StringBuffer();
        Pair q = null;
        while(pq.size() >= 0)
        {
            if (pq.size() == 0) 
            {
                break;
            }
            Pair p = pq.remove();
            if (q != null && q.count != 0)
            {
                pq.offer(q);
            }
            q = p;
            sb.append(p.c);
            p.count -= 1;
        }
        if (q.count > 0 && q.c == sb.charAt(sb.length() - 1))
        {
            return "";
        }
        return sb.toString();
    }
}
