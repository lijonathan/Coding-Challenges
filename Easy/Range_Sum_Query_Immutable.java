public class Range_Sum_Query_Immutable {
/*
Rating: Easy

Given an integer array nums, find the sum of the elements between
indices i and j (i ≤ j), inclusive.

Example:

Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3



Constraints:

    You may assume that the array does not change.
    There are many calls to sumRange function.
    0 <= nums.length <= 10^4
    -10^5 <= nums[i] <= 10^5
    0 <= i <= j < nums.length
*/

/*
    Approach: Replace the values in the input array with the prefix sum array of the sum
              of all values up to that index inclusive.

              Return the value at nums index start - 1 to nums[end]. Start - 1 because of
              inclusive sum so including the number at index i.
*/

    // O(n) runtime for constructor, O(1) for call fo sumRange
    // O(1) space, modified input array
    // Runtime: 6 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 42.3 MB, less than 79.21% of Java online submissions
    class NumArray {

        int[] nums;
        public NumArray(int[] nums) {
            this.nums = nums;
            int running_sum = 0;
            for (int i = 0; i < nums.length; ++i)
            {
                running_sum += nums[i];
                nums[i] = running_sum;
            }
        }
        public int sumRange(int i, int j) {
            int start = 0;
            if (i > 0)
            {
                start = nums[i - 1];
            }
            return nums[j] - start;
        }
    }

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(i,j);
     */
}
