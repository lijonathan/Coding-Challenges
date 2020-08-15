import java.util.List;

public class Leftmost_Column_with_at_Least_a_One {
/*
Rating: Medium

(This problem is an interactive problem.)

A binary matrix means that all elements are 0 or 1. For
each individual row of the matrix, this row is sorted
in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return
leftmost column index(0-indexed) with at least a 1 in it.
If such index doesn't exist, return -1.

You can't access the Binary Matrix directly.
You may only access the matrix using a BinaryMatrix interface:

    BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
    BinaryMatrix.dimensions() returns a list of 2 elements [rows, cols], which means the matrix is rows * cols.

Submissions making more than 1000 calls to BinaryMatrix.get
will be judged Wrong Answer.  Also, any solutions that attempt
to circumvent the judge will result in disqualification.

For custom testing purposes you're given the binary matrix mat as input in the following four examples.
You will not have access the binary matrix directly.


Example 1:

Input: mat = [[0,0],[1,1]]
Output: 0

Example 2:

Input: mat = [[0,0],[0,1]]
Output: 1

Example 3:

Input: mat = [[0,0],[0,0]]
Output: -1

Example 4:

Input: mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
Output: 1

Constraints:

    rows == mat.length
    cols == mat[i].length
    1 <= rows, cols <= 100
    mat[i][j] is either 0 or 1.
    mat[i] is sorted in a non-decreasing way.
*/

    // This is the BinaryMatrix's API interface.
    // You should not implement it, or speculate about its implementation
    // interface BinaryMatrix {
    // public int get(int row, int col) {}
    // public List<Integer> dimensions {}
    // };

/*
    Approach: Binary search each row for the first 1 in that row. Return the
              smallest column found.
*/

    // O(m * log(n)) runtime, m = # rows, n = # of cols
    // O(1) space
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 40.2 MB, less than 58.71% of Java online submissions
    public int binarySearch(BinaryMatrix bm, int row, int col)
    {
        int start = 0;
        int end = col - 1;
        while(start <= end)
        {
            int mid = (start + end) / 2;
            int val = bm.get(row, mid);
            if (val == 0)
            {
                start = mid + 1;
            }
            else
            {
                if (mid == 0)
                {
                    return 0;
                }
                else if (bm.get(row, mid - 1) == 0)
                {
                    return mid;
                }
                else
                {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimension = binaryMatrix.dimensions();
        int rows = dimension.get(0);
        int cols = dimension.get(1);
        // 100 x 100 maximum dimension
        // log2(100) = 6.6 -> 7 times maximum number of calls per row
        // 7 * 10 = 100 = 700
        int least = Integer.MAX_VALUE;
        for (int i = 0; i < rows; ++i)
        {
            int col = binarySearch(binaryMatrix, i, cols);
            if (col != -1 && col < least)
            {
                least = col;
            }
        }
        return least == Integer.MAX_VALUE ? -1 : least;
    }
}
