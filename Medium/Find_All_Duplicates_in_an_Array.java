import java.util.ArrayList;
import java.util.List;

public class Find_All_Duplicates_in_an_Array {
/*
Rating: Medium

Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array),
some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
*/

/*
    Approach: The number at an index is guarenteed to be between 1 and the size of the array
              therefore each value can be mapped to an index. The index number corresponds to a value
              present in the array, and multiply the value at that index by -1. i.e. if a[i] = 3, multiply
              a[3 - 1] by -1. The index number corresponds to a value found in the array. If an index value is
              negative, that value appeared once in the array, if it is positive, the index # appeared twice
              in the array.
*/
    
    // O(n) runtime
    // O(1) space excluding the answer array
    // Runtime: 5ms beats 95.10% of Java online submissions
    // Memory Usage: 48.7MB beats 46.15% of Java online submissions
    public List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        
        // if see, mark as negative --> twice appeared will be positive
        
        for (int i = 0; i < nums.length; ++i)
        {
            if (nums[Math.abs(nums[i]) - 1] < 0)
            {
                ans.add(Math.abs(nums[i]));
            }
            else
            {
                nums[Math.abs(nums[i]) - 1] *= -1;
            }
        }
        return ans;
    }
}
