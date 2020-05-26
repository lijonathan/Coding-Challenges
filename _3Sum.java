import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;

class _3Sum {

/*
Rating: Medium

Given an array nums of n integers, are there elements a, b, c in
nums such that a + b + c = 0? Find all unique triplets in the array
which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

*/

    public void merge(int[] nums, int start, int mid, int end)
    {
        int[] merge_arr = new int[end - start + 1];
        int l_inc = start;
        int r_inc = mid + 1;
        int index = 0;
        while (l_inc <= mid || r_inc <= end)
        {
            if (l_inc > mid)
            {
                merge_arr[index] = nums[r_inc];
                ++r_inc;
            }
            else if (r_inc > end)
            {
                merge_arr[index] = nums[l_inc];
                ++l_inc;
            }
            else if (nums[l_inc] < nums[r_inc])
            {
                merge_arr[index] = nums[l_inc];
                ++l_inc;
            }
            else
            {
                merge_arr[index] = nums[r_inc];
                ++r_inc;
            }
            ++index;
        }
        index = 0;
        for (int i = start; i <= end; ++i)
        {
            nums[i] = merge_arr[index];
            ++index;
        }
    }
    
    public void mergeSort(int[] nums, int l, int r)
    {
        if (l < r)
        {
            int mid = (l + r) / 2;
            mergeSort(nums, l, mid);
            mergeSort(nums, mid + 1, r);
            merge(nums, l, mid, r);
        }
    }
    
    public List<List<Integer>> threeSum(int[] nums) {
        // O(n^2) time, O(n) space
        ArrayList<List<Integer>> ans = new ArrayList<List<Integer>>();
        mergeSort(nums, 0, nums.length - 1);
        HashSet<ArrayList<Integer>> triples = new HashSet<ArrayList<Integer>>();
        // Key: two pointer technique for finding a desired sum in a sorted array
        // start left pointer, right pointer, if sum > desired, decrement right pointer
        // if sum < desired, increment left pointer
        for(int i = 0; i < nums.length; ++i)
        {
            if(i - 1 >= 0 && nums[i - 1] == nums[i])
            {
                continue;
            }
            int needed = nums[i] * -1;
            int end = nums.length - 1;
            int start = i + 1;
            while (start < end)
            {
                int sum = nums[start] + nums[end];
                if (sum > needed)
                {
                    --end;
                }
                else if (sum < needed)
                {
                    ++start;
                }
                else
                {
                    ArrayList<Integer> entry = new ArrayList<Integer>();
                    entry.add(nums[i]);
                    entry.add(nums[start]);
                    entry.add(nums[end]);
                    if (!triples.contains(entry))
                    {
                        triples.add(entry);
                        ans.add(entry);
                    }
                    ++start;
                }
            }
        }
        
        return ans;
    }
}
