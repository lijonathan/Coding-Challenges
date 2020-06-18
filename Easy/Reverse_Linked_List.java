public class Reverse_Linked_List {

/*
Rating: Easy

Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL

Follow up:

A linked list can be reversed either
iteratively or recursively.
Could you implement both?


*/
    //O(n) time to traverse list, O(1) space excluding recursive call space
    public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private void traversal(ListNode sentinel, ListNode head)
    {
        // always move the next node as the next of
        // sentinel. Update current node's next to sentinel next
        // same as iterative, except traversal through list is
        // recursive
        if(head != null)
        {
            ListNode iter = head.next;
            head.next = sentinel.next;
            sentinel.next = head;
            traversal(sentinel, iter);
        }
    }
    public ListNode reverseList(ListNode head)
    {
        ListNode sentinel = new ListNode();
        traversal(sentinel, head);
        return sentinel.next;
    }
    public ListNode iterreverseList(ListNode head) {
        ListNode sentinel = new ListNode();
        ListNode iterator;
        while(head != null)
        {   // always move the next node as the next of
            // sentinel. Update current node's next to sentinel next
            iterator = head.next;
            head.next = sentinel.next;
            sentinel.next = head;
            head = iterator;
        }
        return sentinel.next;
    }
}
