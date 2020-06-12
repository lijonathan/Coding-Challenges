class Two_Sum_II_Input_array_is_sorted {

/*
Rating: Easy

Given an array of integers that is already sorted in ascending order,
find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that
they add up to the target, where index1 must be less than index2.

Note:

    Your returned answers (both index1 and index2) are not zero-based.
    You may assume that each input would have exactly one solution and you
    may not use the same element twice.

Example:

Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.


*/
    /*
    Approach: Two Pointer
    Two pointer approach for finding a target sum in a sorted array.
    Left pointer at start, right pointer at end. If sum less than target,
    increment left. If sum is greater than target, decrement right.
     */
    public int[] twoSum(int[] numbers, int target) {
        // O(n) time, O(1) space
        // Runtime:  0 ms, faster than 100.00% of Java online submissions
        // Memory Usage: 39.6 MB, less than 46.46% of Java online submissions
        int[] ans = new int[2];
        int l_i = 0;
        int r_i = numbers.length - 1;
        while (l_i < r_i)
        {
            int sum = numbers[l_i] + numbers[r_i];
            if (sum == target)
            {
                ans[0] = l_i + 1;
                ans[1] = r_i + 1;
                return ans;
            }
            else if (sum < target)
            {
                ++l_i;
            }
            else
            {
                --r_i;
            }
        }
        return ans;
    }
}
