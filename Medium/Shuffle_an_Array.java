import java.util.ArrayList;
import java.util.Random;

public class Shuffle_an_Array {

/*
Rating: Medium

Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
*/

/*
    Approach: Have a reference to the original array and create a shuffled array that is the size
              of the input array. Create an ArrayList that is a copy at first of the original array.
              
              Build the shuffled array:
              Have a pointer to the end of the arraylist and continually randomly picking an index between the start
              and the end inclusive. Input the randomly picked index's value into the current index of
              the shuffled array. Swap the current value at the random index with the end index's value of the arraylist
              and decrement the end index and increment the insert index. Everything past the end index in the arraylist
              are values that have already been picked.
*/


    // O(n) time for the constructor, O(1) time for reset, O(n) time for shuffle
    // O(n) space
    // Runtime: 75 ms, faster than 94.13% of Java online submissions
    // Memory Usage: 47.4 MB, less than 88.14% of Java online submissions
    int[] nums;
    int[] shuffled;
    Random r;
    ArrayList<Integer> ar;
    public Shuffle_an_Array(int[] nums) {
        this.nums = nums;
        r = new Random();
        ar = new ArrayList<Integer>();
        shuffled = new int[nums.length];
        for (int i = 0; i < nums.length; ++i)
        {
            ar.add(nums[i]);
        }
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int end = nums.length;
        for (int i = 0; i < nums.length; ++i)
        {
            int select = r.nextInt(end);
            shuffled[i] = ar.get(select);
            int swap = ar.get(end - 1);
            ar.set(end - 1, ar.get(select));
            ar.set(select, swap);
            --end;
        }
        return shuffled;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
