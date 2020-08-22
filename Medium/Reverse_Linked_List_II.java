public class Reverse_Linked_List_II {
/*
Rating: Medium

Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
*/


/*
    Approach: Create a sentinel class. iterate to the start index and track the node prior
              to the start index and the node at the beginning of the reverse index and
              reverse from start index to end index by attaching sentinel.next
              to current node and current node.next to sentinel.next. Set the node previous to the reverse
              index next pointer to sentinel.next and the original node at the start of the unreversed sequence
              to next pointer to what was originally after.
*/

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // O(n) runtime where n is the index of the reversed sequence
    // O(1) space
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 37.2 MB, less than 61.98% of Java online submissions
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode it = head;
        for (int i = 1; i < m - 1; ++i)
        {
            it = it.next; // node before start of reverse
        }
        ListNode prev = it;
        ListNode sentinel = new ListNode(0);
        if (m > 1)
        {
            it = it.next;
        }
        ListNode last = it;
        for (int i = m; i <= n; ++i)
        {
            ListNode next = it.next;
            it.next = sentinel.next;
            sentinel.next = it;
            it = next;
        }
        last.next = it;
        if (m == 1)
        {
            return sentinel.next;
        }
        prev.next = sentinel.next;
        return head;   
    }
}
