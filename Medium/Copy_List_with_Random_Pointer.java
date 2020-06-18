public class Copy_List_with_Random_Pointer {

/*
Rating: Medium

A linked list is given such that each node contains
an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

The Linked List is represented in the input/output as
a list of n nodes. Each node is represented as a
pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1)
where random pointer points to, or null if it does not point to any node.

 

Example 1:

Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:

Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Example 3:

Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]

Example 4:

Input: head = []
Output: []
Explanation: Given linked list is empty (null pointer), so return null.

 

Constraints:

    -10000 <= Node.val <= 10000
    Node.random is null or pointing to a node in the linked list.
    Number of Nodes will not exceed 1000.


*/
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    // O(n) time, O(1) space, no extra space other than new list
    public Node copyRandomList(Node head) {
        // no space needed if interleaved list o1 -> n1 -> o2 -> n2
        // need to do original before new in order to get o1.random.next for
        // new random
        if (head == null)
        {
            return null;
        }
        Node sentinel = new Node(-1);
        Node iter = sentinel;
        iter.next = head;
        Node head_marker = head;

        while (head != null) // first pass copy of the list and interleave
        { // need to interleave original first then new i.e. o1 -> n1 -> o2 -> n2 ...
            Node copy = new Node(head.val);
            iter = head.next;
            head.next = copy;
            copy.next = iter;
            head = iter;
        }
        head = head_marker;
        iter = sentinel.next;
        int count = 1;
        Node random = null;
        // update random pointers
        while (iter != null) // orig -> new -> orig -> new ....
        {
            if (count % 2 != 0)
            {
                // on an original node
                random = iter.random;
            }
            else
            {
                if (random != null)
                {
                    random = random.next;
                }
                iter.random = random;
            }
            iter = iter.next;
            count += 1;
        }
        iter = sentinel.next;
        sentinel.next = sentinel.next.next;
        Node next;
        // cannot seperate lists at same time as getting new random pointers!!
        // random pointer may point to earlier in list!
        while (iter != null)
        {
            next = iter.next;
            if (next != null)
            {
                iter.next = next.next;
            }
            iter = next;
        }
        return sentinel.next;
    }
}
