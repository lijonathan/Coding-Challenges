import java.util.LinkedList;

public class Sliding_Window_Maximum {
/*
Rating: Hard

Given an array nums, there is a sliding window of size k which
is moving from the very left of the array to the very right.
You can only see the k numbers in the window. Each time the
sliding window moves right by one position. Return the max sliding window.

Follow up:
Could you solve it in linear time?

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7



Constraints:

    1 <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
    1 <= k <= nums.length
*/

/*
    Approach: Initialize a queue with the first window. While the queue is not empty and the current
              value is greater than the value at the index that the back of the queue contains, remove
              the element at the back of the queue. Then add the current index into the back of the queue.

              Iterate through the array starting past the first window. At each iteration, clean the front
              of the queue by removing indexes from the front of the array that are before the start of the current
              window. (Note: The indexes in the queue are naturally in ascending order by nature of how they are added in).

              Clean the back of the queue by continually removing elements from the back of the queue whose
              indexes contain values smaller than or equal to the current value.

              Add the current index into the back of the queue and take the index that is
              at the front of the queue as the current window's maximum.
*/


    // O(n) runtime
    // O(k) space for the queue, queue always cleans up indices that are not in window, exclude output array
    // Runtime: 9 ms, faster than 88.20% of Java online submissions
    // Memory Usage: 53.7 MB, less than 42.09% of Java online submissions
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0)
        {
            return new int[0];
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int[] maximums = new int[nums.length - k + 1];
        for (int i = 0; i < k; ++i)
        {
            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i])
            {
                queue.removeLast();
            }
            queue.add(i);
        }
        int index = 0;
        maximums[index] = nums[queue.peek()];
        ++index;
        for (int i = k;  i < nums.length; ++i)
        {
            while(!queue.isEmpty() && queue.peek() < i - k + 1) // remove indices out of range of current window
            {
                queue.remove();
            }
            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i])
            {
                queue.removeLast();
            }
            queue.add(i);
            maximums[index] = nums[queue.peek()];
            ++index;
        }
        return maximums;
    }
}
