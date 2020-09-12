public class Remove_Nth_Node_From_End_of_List {
/*
Rating: Medium

Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?
*/

/*
    Approach: Iterate a farther pointer to the nth + 1 place in the list. If at any point it is null, then
              the head will be deleted and return head.next. Start a pointer at the start of the list and
              iterate both by 1 step until the farther pointer is null. Attach the slower pointer's next to 
              next.next and return the head.
*/

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    // O(n) runtime
    // O(1) space
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 37.4 MB, less than 90.83% of Java online submissions
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode hair = head;
        for (int i = 0; i < n + 1; ++i)
        {
            if (hair == null)
            {
                return head.next;
            }
            hair = hair.next;
        }
        ListNode it = head;
        while(hair != null)
        {
            it = it.next;
            hair = hair.next;
        }
        it.next = it.next.next;
        return head;
    }
}
