public class Find_First_and_Last_Position_of_Element_in_Sorted_Array {
/*
Rating: Medium

Given an array of integers nums sorted in ascending order,
find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Constraints:

    0 <= nums.length <= 10^5
    -10^9 <= nums[i] <= 10^9
    nums is a non decreasing array.
    -10^9 <= target <= 10^9
*/

/*
    Approach: Binary Search for the start index of the target. If not found, return -1 and set
              the end position to -1 as well. Otherwise, binary search for the last position as well.
*/

    // O(log n) runtime
    // O(log n) space for recursive call stack
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 42.2MB less than 98.98% of Java online submissions
    public int bsearch_start(int[] nums, int target, int start, int end)
    {
        if (start > end)
        {
            return -1;
        }
        else
        {
            int mid = (start + end) / 2;
            if (nums[mid] == target)
            {
                if (mid == 0)
                {
                    return 0;
                }
                else if (nums[mid - 1] != target)
                {
                    return mid;
                }
                else
                {
                    return bsearch_start(nums, target, start, mid - 1);
                }
            }
            else if (nums[mid] < target)
            {
                return bsearch_start(nums, target, mid + 1, end);
            }
            else
            {
                return bsearch_start(nums, target, start, mid - 1);
            }
        }
    }
    public int bsearch_end(int[] nums, int target, int start, int end)
    {
        if (start > end)
        {
            return -1;
        }
        else
        {
            int mid = (start + end) / 2;
            if (nums[mid] == target)
            {
                if (mid == nums.length - 1)
                {
                    return mid;
                }
                else if (nums[mid + 1] != target)
                {
                    return mid;
                }
                else
                {
                    return bsearch_end(nums, target, mid + 1, end);
                }
            }
            else if (nums[mid] < target)
            {
                return bsearch_end(nums, target, mid + 1, end);
            }
            else
            {
                return bsearch_end(nums, target, start, mid - 1);
            }
        }
    }
    
    public int[] searchRange(int[] nums, int target) {
        // binary search
        int[] range = new int[2];
        range[0] = bsearch_start(nums, target, 0, nums.length - 1);
        if (range[0] == -1)
        {
            range[1] = -1;
        }
        else
        {
            range[1] = bsearch_end(nums, target, 0, nums.length - 1);
        }
        return range;
    }
}
