class Search_in_Rotated_Sorted_Array {

/*
Rating: Medium

Suppose an array sorted in ascending order is
rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found
in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
*/

    public int binarySearch(int[] nums, int target, int lower, int upper)
    {
        int mid = (lower + upper) / 2;
        if (lower < upper)
        {
            if (nums[mid] == target)
            {
                return mid;
            }
            else if (nums[mid] > target)
            {
                return binarySearch(nums, target, lower, mid - 1);
            }
            else
            {
                return binarySearch(nums, target, mid + 1, upper);
            }
        }
        else if (lower == upper && nums[lower] == target)
        {
            return lower;
        }
        else
        {
            return -1;
        }
    }
    
    public int find_pivot(int[] nums, int lower, int upper)
    {
        int mid = (upper + lower) / 2;
        if (nums[mid] > nums[0]) // pivot to the right
        {
            return find_pivot(nums, mid + 1, upper);
        }
        else if (nums[mid] <= nums[0]) // pivot to the left or current
        {
            if (nums[mid] == nums[0]) //check for pivot is first index
            {
                return 0;
            }
            else
            {
                if (nums[mid - 1] > nums[mid])
                {
                    return mid;
                }
                else
                {
                    return find_pivot(nums, lower, mid);
                }
            }
        }
        else
        {
            return -1;
        }
    }
    public int search(int[] nums, int target) {
        // O(log n) time, O(1) space
        // edge case
        if (nums.length == 0)
        {
            return -1;
        }
        // edge case
        else if (nums.length == 1)
        {
            return nums[0] == target ? 0 : -1;
        }
        // edge case
        else if (nums.length == 2)
        {
            if (nums[0] == target)
            {
                return 0;
            }
            else if (nums[1] == target)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        // array is not rotated
        else if (nums[nums.length - 1] > nums[0])
        {
            return binarySearch(nums, target, 0, nums.length - 1);
        }
        else
        {   // find the pivot point
            int pivot = find_pivot(nums, 0, nums.length - 1);
            if (nums[0] > target) // point is pre-pivot
            {
                return binarySearch(nums, target, pivot, nums.length - 1);
            }
            else // point is post pivot
            {
                return binarySearch(nums, target, 0, pivot - 1);
            }
        }
    }
}
