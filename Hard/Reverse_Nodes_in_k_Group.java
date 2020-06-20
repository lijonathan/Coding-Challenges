public class Reverse_Nodes_in_k_Group {

/*
Rating: Hard

Given a linked list, reverse the nodes of a linked list
k at a time and return its modified list.

k is a positive integer and is less than or equal to the
length of the linked list. If the number of nodes is not
a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

    Only constant extra memory is allowed.
    You may not alter the values in the list's nodes, only nodes
    itself may be changed.
*/

/*
    Approach: Reverse nodes in constant space with technique of appending next node always
              to the next of a sentinel.

              After each group reversal, return markers for the end of the new reversed group,
              the beginning of the newly reversed group, and the next node past the reversed group.
              Use those values to properly chain together the reversed groups with the rest of
              the list.
*/

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // O(n) runtime, n = # of nodes in list
    // O(1) space
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 39.6 MB, less than 64.48% of Java online submissions
    public ListNode[] reverse(ListNode curr, int k, ListNode[] markers)
    {
        markers[0] = curr; // end of reversed list
        ListNode sentinel = new ListNode(-1);
        for (int i = 0; i < k; ++i)
        {
            ListNode next = curr.next;
            curr.next = sentinel.next;
            sentinel.next = curr;
            curr = next;
        }
        markers[1] = sentinel.next; // beginning of reversed list
        markers[2] = curr; // start of unreversed next group
        return markers;
        
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        
        ListNode it = head;
        int count = 0;
        while (it != null)
        {
            ++count;
            it = it.next;
        }
        int groups = count / k;
        it = head;
        ListNode[] marker = new ListNode[3];
        ListNode prev_end = null;
        for (count = 0; count < groups; ++count)
        {
            marker = reverse(it, k, marker);
            if (prev_end == null)
            {
                prev_end = marker[0];
                head = marker[1];
            }
            // marker[0] = end of reversed list
            // marker[1] = beginning of reversed list
            // marker[2] = start of unreversed next group
            else
            {
                prev_end.next = marker[1];
                prev_end = marker[0];
            }
            it = marker[2];
        }
        marker[0].next = marker[2];
        return head;
    }
}
