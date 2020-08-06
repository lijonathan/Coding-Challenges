import java.util.ArrayList;

public class Design_HashMap {

/*
Rating: Easy

Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

    put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
    get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
    remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.


Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);          
hashMap.put(2, 2);         
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1 
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found) 


Note:

    All keys and values will be in the range of [0, 1000000].
    The number of operations will be in the range of [1, 10000].
    Please do not use the built-in HashMap library.

*/

/*
    Approach: Create an array of a size that is large and a prime number. Hash each key value
              by taking the mod of it and the size of the array to find the index. If multiple
              values Hash to the same index for collisions, create an ArrayList in that bucket
              to hold each value(chaining). To find a value in a chained bucket, iterate through
              the list at that index.
*/

    // O(1) time average, get, remove, put, O(n) time worst case if all collide
    // O(n) where n is the size of the table
    // Runtime: 12 ms, faster than 96.10% of Java online submissions
    // Memory Usage: 44.7 MB, less than 39.45% of Java online submissions
    class MyHashMap {

        private class list
        {
            ArrayList<Pair> bucket;
            public list()
            {
                bucket = new ArrayList<Pair>();
            }
        }
        private class Pair
        {
            int key;
            int val;
            public Pair(int key, int val)
            {
                this.key = key;
                this.val = val;
            }
        }

        /** Initialize your data structure here. */
        list[] table;
        public MyHashMap() {
            table = new list[7919];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int hash = key % 7919;
            if (table[hash] == null)
            {
                list l = new list();
                Pair p = new Pair(key, value);
                l.bucket.add(p);
                table[hash] = l;
            }
            else
            {
                int size = table[hash].bucket.size();
                ArrayList<Pair> bucket = table[hash].bucket;
                for (int i = 0; i < size; ++i)
                {
                    if (bucket.get(i).key == key)
                    {
                        bucket.get(i).val = value;
                        return;
                    }
                }
                bucket.add(new Pair(key, value));
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int hash = key % 7919;
            if (table[hash] == null)
            {
                return -1;
            }
            else
            {
                ArrayList<Pair> bucket = table[hash].bucket;
                int size = bucket.size();
                for (int i = 0; i < size; ++i)
                {
                    if (bucket.get(i).key == key)
                    {
                        return bucket.get(i).val;
                    }
                }
                return -1;
            }
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int hash = key % 7919;
            if (table[hash] != null)
            {
                ArrayList<Pair> bucket = table[hash].bucket;
                int size = bucket.size();
                for (int i = 0; i < size; ++i)
                {
                    if (bucket.get(i).key == key)
                    {
                        Pair p = bucket.get(bucket.size() - 1);
                        bucket.set(i, p);
                        bucket.remove(size - 1);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Your MyHashMap object will be instantiated and called as such:
     * MyHashMap obj = new MyHashMap();
     * obj.put(key,value);
     * int param_2 = obj.get(key);
     * obj.remove(key);
     */
}
