import java.util.HashSet;

public class Contains_Duplicate {
/*
Rating: Easy

Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice
in the array, and it should return false if every element is distinct.

Example 1:

Input: [1,2,3,1]
Output: true

Example 2:

Input: [1,2,3,4]
Output: false

Example 3:

Input: [1,1,1,3,3,4,3,2,4,2]
Output: true
*/

/*
    Approach: Iterate through the array and use a set to track the values. If a value
              is in the set, return true.
*/

    // O(n) runtime
    // O(n) space
    // Runtime: 6 ms, faster than 64.73% of Java online submissions
    // Memory Usage: 45.7 MB, less than 55.69% of Java online submissions
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; ++i)
        {
            if (set.contains(nums[i]))
            {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}
