public class Search_a_2D_Matrix {

/*
Rating: Medium
 
Write an efficient algorithm that searches for a value in an
m x n matrix. This matrix has the following properties:

    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer
    of the previous row.

Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true

Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
*/
    
/*
    Approach: Implicitly convert entire 2D array into 1D sorted array by convering row/col
    to index and vice versa. Run Binary Search
*/

    // O(log mn) runtime where m is the number of rows, n number of columns
    // O(log mn) space for recursive calls, excluding stack space O(1) space

    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 39.1 MB, less than 78.84% of Java online submissions
    public boolean binarySearch(int[][] arr, int start, int end, int target)
    {
        if (start <= end)
        {
            int mid = (start + end) / 2;
            int row = mid / arr[0].length; // this is calculation for row
            int col = mid % arr[0].length; // convert calculation for column
            if (arr[row][col] == target)
            {
                return true;
            }
            else if (arr[row][col] < target)
            {
                return binarySearch(arr, mid + 1, end, target);
            }
            else
            {
                return binarySearch(arr, start, mid - 1, target);
            }
        }
        else
        {
            return false;
        }
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)
        {
            return false;
        }
        else if (matrix[0].length == 0)
        {
            return false;
        }

        int length = matrix[0].length * matrix.length;
        return binarySearch(matrix, 0, length - 1, target);
    }
}
