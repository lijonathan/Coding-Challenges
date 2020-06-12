import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class Top_K_Frequent_Elements {

/*
Rating: Medium

Given a non-empty array of integers, return the
k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:

Input: nums = [1], k = 1
Output: [1]

Note:

    You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
    Your algorithm's time complexity must be better than O(n log n),
    where n is the array's size.
    It's guaranteed that the answer is unique, in other words
    the set of the top k frequent elements is unique.
    You can return the answer in any order.
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
        public int val;
        public int count;
        public Pair(int val, int count)
        {
            this.val = val;
            this.count = count;
        }
    }
    
    private class min_compare implements Comparator<Pair>
    {
        public int compare(Pair x1, Pair x2)
        {
            return (x1.count - x2.count); // first arg in compare method is what
        }                                 // gets compared in relation to second
    }                                     // negative if first object is lower priority
                                          // positive if it is higher priority
                                          // 0 if equal priority
    public int[] topKFrequent(int[] nums, int k) {
        // O(n log k) runtime
        // O(n) space for HashMap
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new min_compare());
        HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i)
        {
            Integer count = counts.get(nums[i]);
            if (count == null)
            {
                count = 0;
            }
            counts.put(nums[i], count + 1);
        }
        for (Integer i: counts.keySet())
        {
            Pair p = new Pair(i, counts.get(i));
            if (pq.size() < k)
            {
                pq.add(p);
            }
            else
            {
                if (pq.peek().count < p.count)
                {
                    pq.poll();
                    pq.add(p);
                }
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i)
        {
            ans[i] = pq.poll().val;
        }
        return ans;
    }
}
