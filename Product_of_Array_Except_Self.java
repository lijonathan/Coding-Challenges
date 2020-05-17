
public class Product_of_Array_Except_Self {

/*
Rating: Medium

Given an array nums of n integers where n > 1,
return an array output such that output[i] is equal
to the product of all the elements of nums except nums[i].
Example:
Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements
of any prefix or suffix of the array (including the whole array)
fits in a 32 bit integer.
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity?
(The output array does not count as extra space for the purpose of space complexity analysis.)
*/
    
    public int[] productExceptSelf(int[] nums) {
        // O(n) run-time, O(1) space
        int[] ans = new int[nums.length];
        ans[0] = 1;
        // Build array of product of everything to the left
        // of current index
        for(int i = 1; i < ans.length; ++i)
        {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        int R = 1;
        // product of everything starting from the right
        // multiply that product with current left product array value
        for(int k = nums.length - 2; k >= 0; --k)
        {
            R = R * nums[k + 1];
            ans[k] = ans[k] * R;
        }
        return ans;
        
        
    }
}
