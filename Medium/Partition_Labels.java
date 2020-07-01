import java.util.ArrayList;
import java.util.List;

public class Partition_Labels {
/*
Rating: Medium

A string S of lowercase English letters is given.
We want to partition this string into as many parts
as possible so that each letter appears in at most
one part, and return a list of integers representing
the size of these parts.

Example 1:

Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect,
because it splits S into less parts.

Note:

    S will have length in range [1, 500].
    S will consist of lowercase English letters ('a' to 'z') only.
*/

/*
    Approach: Make intervals for the leftmost position of a character and rightmost
              position of each character. Sort the intervals by ascending left position.
              Merge overlapping intervals which are now contiguous, and return the length of
              each merged interval.
*/
    // O(n) time, constant factors are 26, log 26
    // O(1) space, constant factor of 26 for number of letters
    // Runtime: 4 ms, faster than 50.28% of Java online submissions
    // Memory Usage: 39 MB, less than 42.26% of Java online submissions
    public void merge(int[][] intervals, int start, int mid, int end)
    {
        int[][] buffer = new int[end - start + 1][2];
        int s_index = start;
        int e_index = mid + 1;
        int counter = 0;
        while(s_index <= mid || e_index <= end)
        {
            if (s_index > mid)
            {
                buffer[counter] = intervals[e_index];
                ++e_index;
            }
            else if (e_index > end)
            {
                buffer[counter] = intervals[s_index];
                ++s_index;
            }
            else if (intervals[s_index][0] <= intervals[e_index][0])
            {
                buffer[counter] = intervals[s_index];
                ++s_index;
            }
            else
            {
                buffer[counter] = intervals[e_index];
                ++e_index;
            }
            ++counter;
        }
        counter = 0;
        for (int i = start; i <= end; ++i)
        {
            intervals[i] = buffer[counter];
            ++counter;
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
    public List<Integer> partitionLabels(String S) {
        int[] letter_start = new int[26];
        for (int j = 0; j < 26; ++j)
        {
            letter_start[j] = -1;
        }
        int counter = 0;
        int l = S.length();
        for (int i = 0; i < l; ++i)
        {
            int letter = S.charAt(i) - 97;
            if (letter_start[letter] == -1)
            {
                letter_start[letter] = i;
                ++counter;
            }
        }
        
        int[][] intervals = new int[counter][2];
        counter = 0;
        for (int k = l - 1; k >= 0; --k)
        {
            int letter = S.charAt(k) - 97;
            if (letter_start[letter] != -1)
            {
                intervals[counter][0] = letter_start[letter];
                intervals[counter][1] = k;
                letter_start[letter] = -1;
                ++counter;
            }
        }
        mergeSort(intervals, 0, intervals.length - 1);
        for (int m = 0; m < intervals.length; ++m) // merge overlapped intervals
        {
            int iter = m + 1;
            while(iter < intervals.length && intervals[iter][0] < intervals[m][1])
            {
                intervals[m][1] = Math.max(intervals[m][1], intervals[iter][1]);
                intervals[iter] = null;
                ++iter;
            }
            m = iter - 1;
        }
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < intervals.length; ++i)
        {
            if (intervals[i] != null)
            {
                ans.add(intervals[i][1] - intervals[i][0] + 1);
            }
        }
        return ans;
    }
}
