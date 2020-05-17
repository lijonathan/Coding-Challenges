public class Add_Two_Numbers {

/*
Rating: Medium

You are given two non-empty linked lists representing
two non-negative integers. The digits are stored in reverse order
and each of their nodes contain a single digit.
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero,
except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
*/

    public class ListNode {
              int val;
              ListNode next;
              ListNode() {}
              ListNode(int val) { this.val = val; }
              ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // O(n) runtime where n is the longest of the two lists, O(1) additional
        // space excluding the newly created list
        ListNode head = new ListNode();
        ListNode iterator = head;
        boolean carry = false;
        while(l1 != null || l2 != null)
        {
            int l1_val = 0;
            int l2_val = 0;
            if (l1 != null)
            {
                l1_val = l1.val;
            }
            if (l2 != null)
            {
                l2_val = l2.val;
            }
            int val = l1_val + l2_val;
            if (carry)
            {
                val += 1;
            }
            if (val > 9)
            {
                val = val - 10;
                carry = true;
            }
            else
            {
                carry = false;
            }
            ListNode curr = new ListNode(val);
            iterator.next = curr;
            iterator = iterator.next;
            if (l1 != null)
            {
                l1 = l1.next;
            }
            if (l2 != null)
            {
                l2 = l2.next;
            }
        }
        if (carry)
        {
            iterator.next = new ListNode(1);
        }
        return head.next;
    }
}
