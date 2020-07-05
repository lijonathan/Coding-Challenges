import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Insert_Delete_GetRandom_O1 {
/*
Rating: Medium

Design a data structure that supports all following operations in average O(1) time.


    insert(val): Inserts an item val to the set if not already present.
    remove(val): Removes an item val from the set if present.
    getRandom: Returns a random element from current set of elements
               (it's guaranteed that at least one element exists when
               this method is called). Each element must have the same
               probability of being returned.

Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
*/

/*
    Approach: ArrayList to store all the values. HashMap of values to the index in the arraylist that
              the value is stored at. 

              Insert goes onto the end of the arraylist.

              Remove swaps the element with the last element of the arraylist and then removes the last
              element in the arraylist.

              Use Random class of Java to return a random integer between 0 inclusive
              and the length of the arraylist exclusive.
*/


    // O(n) space where n = # of elements in set
    // O(1) average time for insert, delete, getRandom -- insert is O(1) amortized time for arraylist
    // Runtime: 9 ms, faster than 49.24% of Java online submissions
    // Memory Usage: 44.4 MB, less than 62.62% of Java online submissions



    /** Initialize your data structure here. */
    
    ArrayList<Integer> values;
    HashMap<Integer, Integer> val_indices;
    Random r;
    Integer index;
    int last;
    public Insert_Delete_GetRandom_O1() {
        // avg means can use arraylist because amortized to O(1) time
        // hashmap of val to index
        values = new ArrayList<Integer>();
        val_indices = new HashMap<Integer, Integer>();
        r = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        
        index = val_indices.get(val);
        if (index == null)
        {
            values.add(val);
            index = values.size() - 1;
            val_indices.put(val, index);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        index = val_indices.get(val);
        if (index == null)
        {
            return false;
        }
        last = values.get(values.size() - 1);
        values.set(index, last);
        val_indices.put(last, index);
        values.remove(values.size() - 1);
        val_indices.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return values.get(this.r.nextInt(values.size()));
    }
}
