import java.util.HashSet;

class Merge_Intervals {
/*

Rating: Medium

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
*/



/*Approach: sort the intervals by ascending begin time. All overlapped intervals
will then be consecutive. Merge the intervals O(n) time */

    private void merge(int[][] intervals, int start, int mid, int end)
    {
        int l_i = start;
        int r_i = mid + 1;
        int[][] buff = new int[end - start + 1][2];
        for (int i = 0; i < buff.length; ++i)
        {
            if (l_i <= mid && r_i > end)
            {
                buff[i] = intervals[l_i];
                ++l_i;
            }
            else if (r_i <= end && l_i > mid)
            {
                buff[i] = intervals[r_i];
                ++r_i;            
            }
            else if (intervals[l_i][0] <= intervals[r_i][0])
            {
                buff[i] = intervals[l_i];
                ++l_i;
            }
            else //if (intervals[r_i][0] < intervals[l_i][0])
            {
                buff[i] = intervals[r_i];
                ++r_i;
            }
        }
        int index = 0;
        for (int k = start; k <= end; ++k)
        {
            intervals[k] = buff[index];
            ++index;
        }
        
    }
    private void mergeSort(int[][] intervals, int start, int end)
    {
        if (start < end)
        {
            int mid = (end + start) / 2;
            mergeSort(intervals, start, mid);
            mergeSort(intervals, mid + 1, end);
            merge(intervals, start, mid, end);
        }
    }
    private boolean overlap(int[] i1, int[] i2)
    {
        return (i1[0] <= i2[1] && i1[1] >= i2[1]) || (i1[1] >= i2[0] && i1[0] <= i2[0])
            || (i1[0] >= i2[0] && i1[1] <= i2[1]) || (i1[0] <= i2[0] && i1[1] >= i2[1]);
    }
    private int[] merge(int[] i1, int[] i2)
    {
        int[] merged = new int[2];
        merged[0] = Math.min(i1[0], i2[0]);
        merged[1] = Math.max(i1[1], i2[1]);
        return merged;
    }
    public int[][] merge(int[][] intervals) {
        // O(n log n) time --> mergesort dominates
        // O(n) space --> mergesort array, HashSet
        mergeSort(intervals, 0, intervals.length - 1);
        HashSet<Integer> void_indexes = new HashSet<Integer>();
        for (int i = 0; i < intervals.length - 1; ++i)
        {
            int merge_count = 0;
            for (int k = i + 1; k < intervals.length; ++k)
            {
                if(overlap(intervals[i], intervals[k]))
                {
                    void_indexes.add(k);
                    intervals[i] = merge(intervals[i], intervals[k]);
                    ++merge_count;
                }
            }
            i = i + merge_count;
        }
        int[][] merged_intervals = new int[intervals.length - void_indexes.size()][2];
        int index = 0;
        for (int i = 0; i < intervals.length; ++i)
        {
            if (!void_indexes.contains(i))
            {
                merged_intervals[index] = intervals[i];
                ++index;
            }
        }
        return merged_intervals;
    }
}
