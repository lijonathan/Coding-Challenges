public class Swap_Nodes_in_Pairs {

/*
Rating: Medium

Given a linked list, swap every two
adjacent nodes and return its head.

You may not modify the values in the
list's nodes, only nodes itself may be changed.

Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.
*/

/*
    Approach: Recursively traverse the list in pairs and swap in each recursive call.
    Return the head of the newly swapped pair(if only 1 remaining node, return that 1 node,
    if at end of the list, return null) and set the new tail's next of the swapped pair to
    the return value node of the recursive call.
*/


    // O(n) runtime
    // O(1) space excluding recursive call stack
    // Runtime: 0ms beats 100% of Java online submissions
    // Memory Usage: 37 MB beats 76.47% of Java online submissions

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode swap_adjacent(ListNode head)
    {
        if (head != null)
        {
            ListNode next = head.next;
            ListNode next_next = null;
            if (next != null)
            {
                next_next = next.next; // track the actual next node past current pair
                next.next = head; // set the second pair's next to head
            }
            head.next = swap_adjacent(next_next); // head(the swapped pair's new tail) next is the
            if (next == null)                     // return value of the next recursive call(the start of the swapped)
            {                                     // next pair
                return head; // only 1 node remaining at end, so return original without swapping modification
            }
            else
            {
                return next; // return new start of the swapped pair
            }
        }
        else // return null if no nodes remaining
        {
            return null;
        }

    }
    public ListNode swapPairs(ListNode head) {

        return swap_adjacent(head);
    }
}
