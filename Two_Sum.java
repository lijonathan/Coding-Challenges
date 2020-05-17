import java.util.HashMap;

public class Two_Sum {

/*
Rating: Easy

Given an array of integers, return indices of the
two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution,
and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

*/
    
    public int[] twoSum(int[] nums, int target)
    {
        // O(n) time, O(n) space
        int[] solution = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; ++i)
        {
            int difference = target - nums[i];
            if(map.containsKey(difference))
            {
                solution[0] = i;
                solution[1] = map.get(difference);
            }
            else
            {
                map.put(nums[i], i);
            }
        }
        return solution;
    }
}
