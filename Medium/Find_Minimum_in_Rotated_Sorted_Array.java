public class Find_Minimum_in_Rotated_Sorted_Array {
/*
Rating: Medium

Suppose an array sorted in ascending order is rotated at some pivot
unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1

Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
*/

/*
    Approach: Binary Search to find the pivot point. Compare to the value in the first index.
              If mid > nums[0], then still before the pivot point, shift to the right.
              If it is less than mid, to the right of pivot point, check if current is pivot,
              otherwise shift left.
*/

    // O(log n) runtime
    // O(1) space
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 39.5 MB, less than 38.35% of Java online submissions
    public int find_pivot(int[] nums, int start, int end)
    {
        while(start <= end)
        {
            int mid = (start + end) / 2;
            if (nums[mid] > nums[0])
            {
                if (mid == end)
                {
                    return 0;
                }
                start = mid + 1;
            }
            else if (nums[mid] < nums[0])
            {
                if (nums[mid - 1] > nums[mid])
                {
                    return mid;
                }
                end = mid - 1;
            }
            else
            {
                start = mid + 1;
            }
        }
        return -1;
    }
    public int findMin(int[] nums) {
        if (nums.length == 1)
        {
            return nums[0];
        }
        else if (nums[nums.length - 1] >= nums[0])
        {
            return nums[0];
        }
        return nums[find_pivot(nums, 0, nums.length - 1)];
    }
}
