public class Move_Zeroes {
/*
Rating: Easy

Given an array nums, write a function to move all 0's
to the end of it while maintaining the relative order
of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:

    You must do this in-place without making a copy of the array.
    Minimize the total number of operations.
*/

/*
    Approach: Count the number of zeros. Move all nonzeros to the front.
              Zero out the number of counted zeros starting from the back.
              Total # of operations = n
*/

    // O(n) runtime
    // O(1) space
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 39.8 MB, less than 57.83% of Java online submissions
    public void moveZeroes(int[] nums) {
        int num_zeros = 0;
        for (int i = 0; i < nums.length; ++i)
        {
            if (nums[i] == 0)
            {
                ++num_zeros;
            }
        }
        int index = 0;
        for (int i = 0; i < nums.length; ++i)
        {
            if (nums[i] != 0)
            {
                nums[index] = nums[i];
                ++index;
            }
        }
        while(num_zeros > 0)
        {
            nums[nums.length - num_zeros] = 0;
            --num_zeros;
        }
    }
}
