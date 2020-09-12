public class Palindrome_Linked_List {
/*
Rating: Easy

Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false

Example 2:

Input: 1->2->2->1
Output: true

Follow up:
Could you do it in O(n) time and O(1) space?
*/

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

/*
    Approach: Reverse the latter half of the linkedlist. Then compare the first half
              and the second half.
*/

    // O(n) runtime
    // O(1) space
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 42.4 MB, less than 68.41% of Java online submissions
    public boolean isPalindrome(ListNode head) {
        int count = 0;
        ListNode it = head;
        while(it != null)
        {
            it = it.next;
            ++count;
        }
        it = head;
        ListNode sentinel = new ListNode(-1);
        for (int i = 0; i < count / 2; ++i)
        {
            ListNode next = it.next;
            it.next = sentinel.next;
            sentinel.next = it;
            it = next;
        }
        ListNode latter = it;
        if (count % 2 != 0)
        {
            latter = latter.next;
        }
        it = sentinel.next;
        for (int i = 0; i < count / 2; ++i)
        {
            if (it.val != latter.val)
            {
                return false;
            }
            it = it.next;
            latter = latter.next;
        }
        return true;
    }
}
