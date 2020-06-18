import java.util.HashMap;

class LRUCache {

/*
Rating: Medium

Design and implement a data structure for Least Recently Used (LRU) cache.
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the
key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present.
When the cache reached its capacity, it should invalidate the least recently
used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 // capacity // );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4


*/

/*

    Approach: Map of key value pairs for O(1) access for get.
    Doubley linked list of Nodes that contain keys that are in descending
    order of least recently used, head = most recently used.

    Map of key to node for O(1) access to ListNodes for restructuring of
    the list when a get or put operation occurs
*/
    private class ListNode
    {
        int val;
        ListNode next;
        ListNode prev;
        public ListNode(int val)
        {
            this.val = val;
        }
    }

    ListNode head;
    ListNode tail;
    HashMap<Integer, ListNode> lru;
    HashMap<Integer, Integer> map;
    int capacity;
    // O(1) put and O(1) get runtime
    // O(k) space, k = capacity
    // Runtime: 14ms beats 56.59% of Java online submissions
    // Memory Usage: 47.7MB beats 53.08% of Java online submissions
    public LRUCache(int capacity) {
        lru = new HashMap<Integer, ListNode>();
        map = new HashMap<Integer, Integer>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (capacity == 0)
        {
            return -1;
        }
        if (map.containsKey(key))
        {
            int val = map.get(key);
            ListNode node = lru.get(key);
            if(tail == head) // one node
            {
                return val;
            }
            else if (tail == node) // node is last but tail and head are not equal
            {
                tail.prev.next = null;
                tail.next = head;
                head.prev = tail;
                head = tail;
                tail = tail.prev;
                head.prev = null;
            }
            else if (head == node) // node is first and head and tail are not equal
            {
                ; // do nothing
            }
            else // somewhere in the middle
            {
                ListNode prev = node.prev;
                ListNode next = node.next;
                if(prev != null)
                {
                    prev.next = next;
                }
                if (next != null)
                {
                    next.prev = prev;
                }
                node.next = head;
                node.prev = null;
                head.prev = node;
                head = node;
            }
            return val;
        }
        else
        {
            return -1;
        }
    }

    public void put(int key, int value) {

        if (capacity == 0)
        {
            return;
        }
        ListNode curr_node = lru.get(key);
        if (curr_node == null) // not exist
        {
            ListNode new_node = new ListNode(key);
            if (map.size() < capacity) // under capacity
            {
                new_node.next = head;
                if (head != null)
                {
                    head.prev = new_node;
                    new_node.next = head;
                    head = new_node;
                }
                else
                {
                    head = new_node;
                    tail = new_node;
                }
            }
            else                       // at or above capacity
            {
                map.remove(tail.val);
                lru.remove(tail.val);
                if (tail == head)
                {
                    tail = new_node;
                    head = new_node;
                }
                else
                {
                    tail = tail.prev;
                    tail.next = null;
                    head.prev = new_node;
                    new_node.next = head;
                    head = new_node;
                }
            }
            map.put(key, value);
            lru.put(key, new_node);
        }
        else // key already exists
        {
            if (head != curr_node) // if head is current node, no modifications
            {                      // to the list is needed
                ListNode prev = curr_node.prev;
                ListNode next = curr_node.next;
                if (prev != null)
                {
                    prev.next = next;
                }
                if (next != null)
                {
                    next.prev = prev;
                }
                if (tail == curr_node)
                {
                    tail = tail.prev;
                    if(tail == null)
                    {
                        tail = curr_node;
                    }
                }
                head.prev = curr_node;
                curr_node.next = head;
                curr_node.prev = null;
                head = curr_node;
            }
            map.put(key, value);
        }
    }
}
