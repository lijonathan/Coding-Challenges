import java.util.ArrayList;

public class Design_HashSet {

/*
Rating: Easy

Design a HashSet without using any built-in hash table libraries.

To be specific, your design should include these functions:

    add(value): Insert a value into the HashSet. 
    contains(value) : Return whether the value exists in the HashSet or not.
    remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.


Example:

MyHashSet hashSet = new MyHashSet();
hashSet.add(1);         
hashSet.add(2);         
hashSet.contains(1);    // returns true
hashSet.contains(3);    // returns false (not found)
hashSet.add(2);          
hashSet.contains(2);    // returns true
hashSet.remove(2);          
hashSet.contains(2);    // returns false (already removed)


Note:

    All values will be in the range of [0, 1000000].
    The number of operations will be in the range of [1, 10000].
    Please do not use the built-in HashSet library.
    
*/

/*
    Approach: Array of size large prime number. Hash each element by taking the mod of the key and
              the size of the array. If collisions occur, use an arraylist to chain elements that hash
              to the same index. Iterate through the arraylist to find the appropriate element.
*/

    // O(N/K) runtime, where N = # of buckets, K = # of elements, --> evenly distributed hashing
    // O(N + K)
    // Runtime: 11 ms, faster than 89.01% of Java online submissions
    // Memory Usage: 46.1 MB, less than 72.48% of Java online submissions
    class MyHashSet {

        /** Initialize your data structure here. */
        private class list
        {
            ArrayList<Integer> al;
            public list()
            {
                al = new ArrayList<Integer>();
            }
        }
        list[] table;
        public MyHashSet() {
            table = new list[7919];
        }
        
        public void add(int key) {
            int hash = key % 7919;
            if (table[hash] == null)
            {
                list l = new list();
                l.al.add(key);
                table[hash] = l;
            }
            else
            {
                list l = table[hash];
                int size = l.al.size();
                for (int i = 0; i < size; ++i)
                {
                    if (l.al.get(i) == key)
                    {
                        return;
                    }
                }
                l.al.add(key);
            }
        }
        
        public void remove(int key) {
            int hash = key % 7919;
            list l = table[hash];
            if (l != null)
            {
                int size = l.al.size();
                for (int i = 0; i < size; ++i)
                {
                    if (l.al.get(i) == key)
                    {
                        l.al.set(i, l.al.get(size - 1));
                        l.al.remove(size - 1);
                        if (size == 1)
                        {
                            table[hash] = null;
                        }
                        return;
                    }
                }
            }
        }
        
        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            int hash = key % 7919;
            if (table[hash] == null)
            {
                return false;
            }
            else
            {
                ArrayList<Integer> al = table[hash].al;
                int size = al.size();
                for (int i = 0; i < size; ++i)
                {
                    if (al.get(i) == key)
                    {
                        return true;
                    }
                }
                return false;
            }
        }
    }

    /**
     * Your MyHashSet object will be instantiated and called as such:
     * MyHashSet obj = new MyHashSet();
     * obj.add(key);
     * obj.remove(key);
     * boolean param_3 = obj.contains(key);
     */
}
