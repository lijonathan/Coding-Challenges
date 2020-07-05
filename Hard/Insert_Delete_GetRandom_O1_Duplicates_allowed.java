import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Insert_Delete_GetRandom_O1_Duplicates_allowed {
/*
Rating: Hard

Design a data structure that supports all following operations in average O(1) time.
Note: Duplicate elements are allowed.

    insert(val): Inserts an item val to the collection.
    remove(val): Removes an item val from the collection if present.
    getRandom: Returns a random element from current collection of elements.
    The probability of each element being returned is linearly related to the
    number of same value the collection contains.

Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
*/

/*
    Approach: Arraylist to store all the values. HashMap of values to indices that values are at, HashSet.

              Remove swaps the first index found for that element with the last element and deletes the last
              element. Both hashsets for both values get updated.

              Insert goes onto the end of the arraylist with the index updated in the hashmap.

              Random class to return a random value amongst the arraylist.
*/


    // O(n) space where n = # of elements in set
    // O(1) time on average -- amortized constant time for arraylist
    // Runtime: 10ms beats 94.08% of Java online submissions
    // Memory Usage: 46.3MB beats 64.19% of Java online submissions

    /** Initialize your data structure here. */
    HashMap<Integer, HashSet<Integer>> n_to_index;
    ArrayList<Integer> nums;
    boolean ret_val;
    Random r;
    HashSet<Integer> insert;
    HashSet<Integer> last_hs;
    int last_val;
    public Insert_Delete_GetRandom_O1_Duplicates_allowed() {
        
        n_to_index = new HashMap<Integer, HashSet<Integer>>();
        nums = new ArrayList<Integer>();
        r = new Random();
        insert = null;
        last_hs = null;
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        
        insert = n_to_index.get(val);
        ret_val = false;
        if (insert == null)
        {
            insert = new HashSet<Integer>();
            n_to_index.put(val, insert);
            ret_val = true;
        }
        nums.add(val);
        insert.add(nums.size() - 1);
        return ret_val;        
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!n_to_index.containsKey(val))
        {
            return false;
        }
        insert = n_to_index.get(val);
        int index = insert.iterator().next();
        insert.remove(index);
        if (index == nums.size() - 1)
        {
            nums.remove(nums.size() - 1);
            if (insert.isEmpty())
            {
                n_to_index.remove(val);
                return true;
            }
        }
        else
        {
            int last_val = nums.get(nums.size() - 1);
            last_hs = n_to_index.get(last_val);
            last_hs.remove(nums.size() - 1);
            last_hs.add(index);

            nums.set(index, last_val);
            nums.remove(nums.size() - 1);

            if (insert.isEmpty())
            {
                n_to_index.remove(val);
            }
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        if (nums.size() == 0)
        {
            return -1;
        }
        else
        {
            return nums.get(r.nextInt(nums.size()));
        }
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
