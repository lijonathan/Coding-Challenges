public class Interval_List_Intersections {
/*
Rating: Medium

Given two lists of closed intervals, each list of intervals is
pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the
set of real numbers x with a <= x <= b.  The intersection of
two closed intervals is a set of real numbers that is either empty,
or can be represented as a closed interval.  For example,
the intersection of [1, 3] and [2, 4] is [2, 3].)

Example 1:

Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

Note:

    0 <= A.length < 1000
    0 <= B.length < 1000
    0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 */

/*
    Approach: First find the number of interlaps between the two lists.
              Intervals overlap if s1_s <= s2_s && s1_e >= 2_s or 
              s2_s <= s1_s && s2_e >= s1_s.

              Overlap:
              Increment the index in the array with the interval that 
              has a earlier end time.

              No Overlap:
              Increment the index in the array with the interval with the earlier
              start time.

              Merging Intervals:
              New overlap is the larger start time and earlier end time.
*/

    // O(n + m) runtime sum of length of both interval lists
    // O(n + m) space -- possible all intervals overlap with each other
    // Runtime: 7 ms, faster than 17.60% of Java online submissions
    // Memory Usage: 52.2 MB beats 20.53% of Java online submissions
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        
        int a_ptr = 0;
        int b_ptr = 0;
        int counter = 0;
        while(a_ptr < A.length && b_ptr < B.length)
        {
            if ((A[a_ptr][0] <= B[b_ptr][0] && A[a_ptr][1] >= B[b_ptr][0]) ||
                (B[b_ptr][0] <= A[a_ptr][0] && B[b_ptr][1] >= A[a_ptr][0]))
            {
                ++counter;
                if (A[a_ptr][1] < B[b_ptr][1])
                {
                    ++a_ptr;
                }
                else if (B[b_ptr][1] < A[a_ptr][1])
                {
                    ++b_ptr;
                }
                else
                {
                    ++a_ptr;
                    ++b_ptr;
                }
            }
            else if (A[a_ptr][0] < B[b_ptr][0])
            {
                ++a_ptr;
            }
            else if (B[b_ptr][0] < A[a_ptr][0])
            {
                ++b_ptr;
            }
        }
        int[][] intersections = new int[counter][2];
        a_ptr = 0;
        b_ptr = 0;
        counter = 0;
        while(a_ptr < A.length && b_ptr < B.length)
        {
            if ((A[a_ptr][0] <= B[b_ptr][0] && A[a_ptr][1] >= B[b_ptr][0]) ||
                (B[b_ptr][0] <= A[a_ptr][0] && B[b_ptr][1] >= A[a_ptr][0]))
            {
                intersections[counter][0] = Math.max(A[a_ptr][0], B[b_ptr][0]);
                intersections[counter][1] = Math.min(A[a_ptr][1], B[b_ptr][1]);;
                ++counter;
                if (A[a_ptr][1] < B[b_ptr][1])
                {
                    ++a_ptr;
                }
                else if (B[b_ptr][1] < A[a_ptr][1])
                {
                    ++b_ptr;
                }
                else
                {
                    ++a_ptr;
                    ++b_ptr;
                }
            }
            else if (A[a_ptr][0] < B[b_ptr][0])
            {
                ++a_ptr;
            }
            else if (B[b_ptr][0] < A[a_ptr][0])
            {
                ++b_ptr;
            } 
        }
        return intersections;
    }
}
