import java.util.PriorityQueue;

public class Meeting_Rooms_II {

/*
Rating: Medium

Given an array of meeting time intervals consisting of start
and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum
number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2

Example 2:

Input: [[7,10],[2,4]]
Output: 1
*/

/*
    Approach: Sort the meeting time intervals array by increasing start time. 
              Add the first interval to a minheap ordered by end time. Iterate
              through the array and if the top of the minheap end time is <= the start
              time of the current interval, pop off the root. Always add the current interval
              end time to the minheap. After iteration, the size of the minheap is the number of maximum
              rooms needed.
*/

    // O(n log n) runtime
    // O(n) space
    // Runtime: 9ms beats 26.23% of Java online submissions
    // Memory Usage: 40 MB beats 28.65% of Java online submissions
    public void merge(int[][] intervals, int start, int mid, int end)
    {
        int s_i = start;
        int e_i = mid + 1;
        int[][] buf = new int[end - start + 1][2];
        int index = 0;
        while (s_i <= mid || e_i <= end)
        {
            if (s_i > mid)
            {
                buf[index] = intervals[e_i];
                ++e_i;
            }
            else if (e_i > end)
            {
                buf[index] = intervals[s_i];
                ++s_i;
            }
            else if (intervals[s_i][0] < intervals[e_i][0])
            {
                buf[index] = intervals[s_i];
                ++s_i;
            }
            else if (intervals[e_i][0] <= intervals[s_i][0])
            {
                buf[index] = intervals[e_i];
                ++e_i;
            }
            ++index;
        }
        index = 0;
        for (int i = start; i <= end; ++i)
        {
            intervals[i] = buf[index];
            ++index;
        }
    }

    public void mergeSort(int[][] intervals, int start, int end)
    {
        if (start < end)
        {
            int mid = (start + end) / 2;
            mergeSort(intervals, start, mid);
            mergeSort(intervals, mid + 1, end);
            merge(intervals, start, mid, end);
        }
    }

    public int minMeetingRooms(int[][] intervals) {

        if (intervals.length == 0)
        {
            return 0;
        }
        // minimum number of conference rooms = largest number of overlapped times at once
        mergeSort(intervals, 0, intervals.length - 1); // sorted by increasing start time

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; ++i)
        {
            if (pq.peek() <= intervals[i][0])
            {
                pq.remove();
            }
            pq.add(intervals[i][1]);
        }
        return pq.size();
    }
}
