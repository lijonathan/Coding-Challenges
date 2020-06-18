import java.util.Comparator;
import java.util.PriorityQueue;

public class Find_Median_from_Data_Stream {

/*
Rating: Hard

Median is the middle value in an ordered integer list.
If the size of the list is even, there is no middle value.
So the median is the mean of the two middle value.
For example,

[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

    void addNum(int num) - Add a integer number from the
                           data stream to the data structure.
    double findMedian() - Return the median of all elements so far.



Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2



Follow up:

    If all integer numbers from the stream are between 0 and 100,
    how would you optimize it?
    If 99% of all integer numbers from the stream are between 0 and
    100, how would you optimize it?
    --> optimize by keeping track of number of numbers above 100 and below 0.
    Since vast majority of numbers are in between 0 and 100, the median will be as well.

    Have an array of size 100 that tracks the counts of numbers between 0 and 100.
    Factor in the counts above and below the range to find the Median.
*/

    /* Approach:
    Have two heaps, one min heap and one max heap. Keep both heaps balanced with each
    other (none has a size greater than the other + 1). The median will always be at the
    root of the heaps.
     */
    class MedianFinder {

        // O(n log n) time for insert and find median.
        // O(n) space for the heaps
        
        // Runtime: 46 ms, faster than 75.70% of Java online submissions
        // Memory Usage: 50.1 MB, less than 92.80% of Java online submissions
        private class max_comparator implements Comparator<Integer>
        {
            public int compare(Integer x, Integer y)
            {
                return (x - y) * -1;
            }
        }
        /** initialize your data structure here. */
        PriorityQueue<Integer> pq_left;
        PriorityQueue<Integer> pq_right;
        public MedianFinder() {
            pq_left = new PriorityQueue<Integer>(new max_comparator());
            pq_right = new PriorityQueue<Integer>();
        }

        public void addNum(int num) {
            if (pq_left.peek() != null && pq_right.peek() != null)
            {
                if (pq_right.peek() < num)
                {
                    pq_right.add(num);
                }
                else
                {
                    pq_left.add(num);
                }
            }
            else if (pq_left.peek() == null)
            {
                pq_left.add(num);
            }
            else
            {
                pq_right.add(num);
            }

            if (pq_left.size() < pq_right.size() - 1)
            {
                pq_left.add(pq_right.poll());
            }
            else if (pq_right.size() < pq_left.size() - 1)
            {
                pq_right.add(pq_left.poll());
            }
            if (pq_left.peek() != null && pq_right.peek() != null)
            {
                if (pq_left.peek() > pq_right.peek())
                {
                    pq_left.add(pq_right.poll());
                    pq_right.add(pq_left.poll());
                }
            }
        }

        public double findMedian() {
            int median_index = (int) Math.round((pq_left.size() + pq_right.size()) / 2.0);
            if ((pq_left.size() + pq_right.size()) % 2 != 0)
            {
                if (pq_left.size() == median_index)
                {
                    return pq_left.peek();
                }
                else
                {
                    return pq_right.peek();
                }
            }
            else
            {
                return ((double) pq_left.peek() + (double) pq_right.peek()) / 2.0;
            }
        }
    }

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */
}
