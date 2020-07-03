import java.util.ArrayList;
import java.util.List;

public class Find_All_Numbers_Disappeared_in_an_Array {
/*
Rating: Easy

Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array),
some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may
assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
*/

/*
    Approach: Multiply the index value of the number at the current index by -1.
              i.e. if a[i] = 3, a[3 - 1] *= -1. If the number is already negative,
              do nothing. If an array is not negative, the index number is a value that
              was not in the array.
*/

    // O(n) runtime
    // O(1) space excluding the returned array
    // Runtime: 5ms beats 8323% of Java online submissions
    // Memory Usage: 48.9MB beats 35.00% of Java online submissions
    public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; ++i)
        {
            if (nums[Math.abs(nums[i]) - 1] > 0)
            {
                nums[Math.abs(nums[i]) - 1] *= -1;
            }
        }
        for (int k = 0; k < nums.length; ++k)
        {
            if (nums[k] > 0)
            {
                ans.add(k + 1);
            }
        }
        return ans;
    }
}
