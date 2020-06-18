public class Merge_Two_Sorted_Lists {

/*
Rating: Easy

Merge two sorted linked lists and return
it as a new list. The new list should be
made by splicing together the nodes of the
first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4

*/

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // O(n) time where n is the length of the longest list
        // O(1) space excluding the space of the new list
        ListNode sentinel = new ListNode();
        ListNode iterator = sentinel;
        while(l1 != null || l2 != null)
        {
            ListNode new_node = new ListNode();
            if (l1 == null && l2 != null)
            {
                new_node.val = l2.val;
                l2 = l2.next;
            }
            else if (l1 != null && l2 == null)
            {
                new_node.val = l1.val;
                l1 = l1.next;
            }
            else
            {
                if (l1.val < l2.val)
                {
                    new_node.val = l1.val;
                    l1 = l1.next;
                }
                else
                {
                    new_node.val = l2.val;
                    l2 = l2.next;
                }
            }
            iterator.next = new_node;
            iterator = iterator.next;
        }
        return sentinel.next;
    }
}
