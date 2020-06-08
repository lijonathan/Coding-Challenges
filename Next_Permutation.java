class Next_Permutation {

/*
Rating: Medium

Implement next permutation, which rearranges numbers
into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it
as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its
corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1


*/
    /*
    Approach: Starting from the right, keep moving left until the number at index is 
              less than the number to the right of it --> everything to the right of the
              given index is therefore already in descending order.
              
              Find the smallest number to the right of the given index that is larger
              than the number currently found. Swap the two numbers.
              
              Reverse numbers to the right of the given index.
              
    */
    public void nextPermutation(int[] nums) {
        // constant extra memory
        // in place
        // -> rules out any type of sorting
        // O(n) time, O(1) space
        // Runtime 0ms, beats 100.00% of Java submissions
        // Memory 39.5 MB beats 80.36% of Java submissions
        for (int i = nums.length - 2; i >= 0; --i)
        {
            if (nums[i] < nums[i + 1])
            {
                int smallest_i = i;
                for (int k = i + 1; k < nums.length; ++k)
                {
                    if (nums[k] > nums[i] && nums[k] <= nums[smallest_i] //<= instead of < so take rightmost element possible
                            || smallest_i == i)
                    {
                        smallest_i = k;
                    }
                }
                int swap = nums[i];
                nums[i] = nums[smallest_i];
                nums[smallest_i] = swap;
                int mid = (i + nums.length - 1) / 2;
                int index = 0;
                for (int j = i + 1; j <= mid; ++j)
                {
                    swap = nums[nums.length - 1 - index];
                    nums[nums.length - 1 - index] = nums[j];
                    nums[j] = swap;
                    ++index;
                }
                return;
            }
        }
        int mid = (nums.length - 1) / 2;
        for (int i = 0; i <= mid; ++i)
        {
            int temp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
        }
    }
}
