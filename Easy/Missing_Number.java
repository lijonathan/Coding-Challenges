public class Missing_Number {
/*
Rating: Easy

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2

Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8

Note:
Your algorithm should run in linear runtime complexity.
Could you implement it using only constant extra space complexity?
*/

/*
    Approach: Have a flag to check if the last number i.e. nums.length is present.
              Set the value of at the index where index = nums[i] to the negative of the value.
              If the value is 0, set it to min integer value.

              Check which index is still not negative, return that index.
*/

    // O(n) runtime
    // O(1) space
    // Runtime: 1 ms, faster than 59.20% of Java online submissions
    // Memory Usage: 40.2 MB, less than 58.95% of Java online submissions
    public int missingNumber(int[] nums) {
        boolean last = false;
        for (int i = 0; i < nums.length; ++i)
        {
            int x = Math.abs(nums[i]);
            if (x == nums.length)
            {
                last = true;
            }
            else
            {
                if (x == Integer.MIN_VALUE)
                {
                    x = 0;
                }
                nums[x] *= -1;
                if (nums[x] == 0)
                {
                    nums[x] = Integer.MIN_VALUE;
                }
            }
        }
        if (!last)
        {
            return nums.length;
        }
        for (int k = 0; k < nums.length; ++k)
        {
            if(nums[k] >= 0)
            {
                return k;
            }
        }
        return -1;
    }
}
