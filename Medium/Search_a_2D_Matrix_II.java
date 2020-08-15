public class Search_a_2D_Matrix_II {
/*
Rating: Medium

Write an efficient algorithm that searches for a value in an m x n matrix.
This matrix has the following properties:

    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.

Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

Given target = 5, return true.

Given target = 20, return false.
*/

/*
    Approach: Start at bottom left corner. Move up if current number is greater than target.
              Move right if current number is less than target.
*/

    // O(m + n) runtime, m = # of columns, n = # of rows
    // O(1) space
    // Runtime: 5 ms, faster than 73.92% of Java online submissions
    // Memory Usage: 45 MB, less than 75.01% of Java online submissions
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
        {
            return false;
        }
        int row = matrix.length - 1;
        int col = 0;
        while(col < matrix[0].length && row >= 0)
        {
            int val = matrix[row][col];
            if (val == target)
            {
                return true;
            }
            else if (val > target)
            {
                --row;
            }
            else
            {
                ++col;
            }
        }
        return false;
    }
}
