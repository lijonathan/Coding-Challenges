public class Meeting_Rooms {

/*
Rating: Easy

Given an array of meeting time intervals consisting
of start and end times [[s1,e1],[s2,e2],...] (si < ei),
determine if a person could attend all meetings.

Example 1:

Input: [[0,30],[5,10],[15,20]]
Output: false

Example 2:

Input: [[7,10],[2,4]]
Output: true
 */

/*
    Approach: Sort intervals by ascending start time. Any potential overlaps
    will now be adjacent. Iterate through the array and check for any overlaps with
    neighboring interavls.
*/


    // O(n log n) time complexity
    // O(n) space complexity
    // Runtime: 7 ms, faster than 27.60% of Java online submissions
    // Memory Usage: 40.2 MB, less than 22.20% of Java online submissions
    public void merge(int[][] intervals, int start, int mid, int end)
    {
        int[][] merge_help = new int[end - start + 1][2];
        int begin_i = start;
        int end_i = mid + 1;
        int index = 0;
        while (begin_i <= mid || end_i <= end)
        {
            if (begin_i > mid)
            {
                merge_help[index] = intervals[end_i];
                ++end_i;
            }
            else if (end_i > end)
            {
                merge_help[index] = intervals[begin_i];
                ++begin_i;
            }
            else if (intervals[begin_i][0] < intervals[end_i][0])
            {
                merge_help[index] = intervals[begin_i];
                ++begin_i;
            }
            else
            {
                merge_help[index] = intervals[end_i];
                ++end_i;
            }
            ++index;
        }
        index = 0;
        for (int i = start; i <= end; ++i)
        {
            intervals[i] = merge_help[index];
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
    public boolean canAttendMeetings(int[][] intervals) {

        mergeSort(intervals, 0, intervals.length - 1);
        for (int i = 1; i < intervals.length; ++i)
        {
            if (intervals[i][0] < intervals[i - 1][1])
            {
                return false;
            }
        }
        return true;
    }
}
