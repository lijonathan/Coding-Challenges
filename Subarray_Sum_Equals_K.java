import java.util.HashMap;

public class Subarray_Sum_Equals_K {

/*
Rating: Medium

Given an array of integers and an integer k,
you need to find the total number of continuous
subarrays whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2

 

Constraints:

    The length of the array is in range [1, 20,000].
    The range of numbers in the array is [-1000, 1000]
	and the range of the integer k is [-1e7, 1e7].


*/
	public int subarraySum(int[] nums, int k) {
        //brute force, O(n^2) try every possible subarray
        // O(n) runtime - 1 pass over array,
		// O(n) space for every sum starting from left
        HashMap<Integer, Integer> sum_counts = new HashMap<Integer, Integer>();
        
        int total_arrays = 0;
        int curr_sum = 0;
        sum_counts.put(0, 1);
        // put 0 into HashMap so if current subarray from 0 to index, = k
        for(int j = 0; j < nums.length; ++j)
        {	
        	// Sum from the left side, 0 index
            curr_sum += nums[j];
            // count how many subarrays to left index j have difference
            // between k and current sum, add those to total
            int diff = curr_sum - k;
            if (sum_counts.containsKey(diff))
            {
                total_arrays += sum_counts.get(diff);
            }
            // add counter of current sum into HashMap
            if(sum_counts.containsKey(curr_sum))
            {
                int counts = sum_counts.get(curr_sum) + 1;
                sum_counts.put(curr_sum, counts);
            }
            else
            {
                sum_counts.put(curr_sum, 1);
            }
            

        }
        return total_arrays;
    }
}
