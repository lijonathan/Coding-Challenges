public class Longest_Mountain_in_Array {
/*
Rating: Medium

Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:

    B.length >= 3
    There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]

(Note that B could be any subarray of A, including the entire array A.)

Given an array A of integers, return the length of the longest mountain. 

Return 0 if there is no mountain.

Example 1:

Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.

Example 2:

Input: [2,2,2]
Output: 0
Explanation: There is no mountain.

Note:

    0 <= A.length <= 10000
    0 <= A[i] <= 10000

Follow up:

    Can you solve it using only one pass?
    Can you solve it in O(1) space?
*/

/*
    Approach: Iterate through the array and track if the current is greater than the previous and
              it is not downhill, or if current is less than previous and downhill.
              Within the loop, mark a downhill flag once the current is less than the previous.

              Special condition, the next start may be the current end so if ptr is > 0 and the previous
              is less than the current, back the pointer up one.
*/

    // O(n) time, single pass
    // O(1) space
    // Runtime: 3 ms, faster than 55.70% of Java online submissions
    // Memory Usage: 40.3 MB, less than 88.86% of Java online submissions
    public int longestMountain(int[] A) {
        int ptr = 0;
        int longest = 0;
        while(ptr < A.length)
        {
            int counter = 1;
            boolean downhill = false;
            if (ptr > 0 && A[ptr - 1] < A[ptr])
            {
                ptr = ptr - 1;
            }
            while(ptr < A.length && 
                    (((counter == 1 || A[ptr] > A[ptr - 1]) && !downhill) || (downhill && A[ptr] < A[ptr - 1])))
            {
                if (counter > longest && downhill)
                {
                    longest = counter;
                }
                ++counter;
                ++ptr;
                if (counter > 2 && ptr < A.length && A[ptr - 1] > A[ptr])
                {
                    downhill = true;
                }
            }
        }
        return longest >= 3 ? longest : 0;
    }
}
