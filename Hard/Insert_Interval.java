public class Insert_Interval {
/*
Rating: Hard

Given a set of non-overlapping intervals, insert a new interval into
the intervals (merge if necessary).

You may assume that the intervals were initially sorted according
to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
*/

/*
    Approach: Create a new n+1x2 array and insert the newInterval at the end.
              Move the new interval left until all intervals are sorted by start time.

              Merge all adjacent overlapping intervals. Set intervals that got merged into
              another interval to null. Count the number of remaining intervals and create
              a new list of that size, copy the intervals into it and return it.
*/

    // O(n) time where n = # of intervals
    // O(n) space
    // Runtime: 1 ms, faster than 98.83% of Java online submissions
    // Memory Usage: 41.3 MB, less than 97.98% of Java online submissions
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0 && newInterval == null)
        {
            return null;
        }
        else if (newInterval == null)
        {
            return intervals;
        }
        else if (intervals == null || intervals.length == 0)
        {
            int[][] combined = new int[1][2];
            combined[0] = newInterval;
            return combined;
        }
        int[][] all_intervals = new int[intervals.length + 1][2];
        for (int i = 0; i < intervals.length; ++i)
        {
            all_intervals[i][0] = intervals[i][0];
            all_intervals[i][1] = intervals[i][1];
        }
        all_intervals[intervals.length][0] = newInterval[0];
        all_intervals[intervals.length][1] = newInterval[1];
        int index = intervals.length;
        int s = newInterval[0];
        int e = newInterval[1];
        while(index > 0 && all_intervals[index][0] < all_intervals[index - 1][0])
        {
            all_intervals[index][0] = all_intervals[index - 1][0];
            all_intervals[index][1] = all_intervals[index - 1][1];

            all_intervals[index - 1][0] = s;
            all_intervals[index - 1][1] = e;
            --index;
        }
        int merge_count = 0;
        for (int i = 0; i < all_intervals.length; ++i)
        {
            if (all_intervals[i] == null)
            {
                continue;
            }
            int next = i + 1;
            while(next < all_intervals.length && all_intervals[i][1] >= all_intervals[next][0])
            {
                all_intervals[i][1] = Math.max(all_intervals[i][1], all_intervals[next][1]);
                ++merge_count;
                all_intervals[next] = null;
                ++next;
            }
        }
        int[][] merged = new int[all_intervals.length - merge_count][2];
        index = 0;
        for (int i = 0; i < all_intervals.length; ++i)
        {
            if (all_intervals[i] != null)
            {
                merged[index] = all_intervals[i];
                ++index;
            }
        }
        return merged;
    }
}
