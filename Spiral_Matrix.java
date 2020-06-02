import java.awt.List;
import java.util.ArrayList;

class Spiral_Matrix {
/*

Rating: Medium

Given a matrix of m x n elements (m rows, n columns), return all
elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

*/

    public List<Integer> spiralOrder(int[][] matrix) {
        // O(n) time where n is the # of elements in matrix
        // O(1) space excluding the answer list
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (matrix.length == 0)
        {
            return ans;
        }
        // may not be square
        // always go around four sides
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (top <= bottom || left <= right)
        {
            // top
            int start;
            int end;
            if (top < bottom ||
                (top == bottom))
            {
                start = 0 + left;
                end = right;
                for (int i = start; i <= end; ++i)
                {
                    ans.add(matrix[top][i]);
                }
            }
            // right
            if (right > left - 1 || right == left)
            {
                start = 0 + top + 1;
                end = bottom;
                for (int k = start; k <= end; ++k)
                {
                    ans.add(matrix[k][right]);
                }
            }
            // bottom
            if (bottom > top)
            {
                start = right - 1;
                end = 0 + left;
                for (int b = start; b >= end; --b)
                {
                    ans.add(matrix[bottom][b]);
                }
            }
            // left
            if (left < right)
            {
                start = bottom - 1;
                end = 0 + top + 1;
                for (int l = start; l >= end; --l)
                {
                    ans.add(matrix[l][left]);
                }
            }
            ++ left;
            --right;
            ++top;
            --bottom;
        }
        return ans;
    }
}
