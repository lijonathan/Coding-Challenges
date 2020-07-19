public class Add_Two_Numbers_II {
/*
Rating: Medium

You are given two non-empty linked lists representing two
non-negative integers. The most significant digit comes first
and each of their nodes contain a single digit. Add the two
numbers and return it as a linked list.

You may assume the two numbers do not contain any leading
zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words,
reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
*/

/*
    Approach: Find the length of both lists and make a deep copy of the larger list.
              Increment an iterator ListNode to the spot in the longer list where the
              two lists have equal length as well as an additional marker for the node prior
              to the start of the sublist. This will be for in case of a carry bit after
              adding the shorter list and the longer list's sublist. Recursively add the
              longer list's sublist and the shorter list whilst tracking the carry.

              If there is an extra carry after the addition of the lists, recursively add
              that carry from the spot of the longer list's sublist.
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
    // O(1) space excluding creation of new list, if recursive call stack is counted, O(n) space
    // Runtime: 2 ms, faster than 99.14% of Java online submissions
    // Memory Usage: 39.7 MB, less than 60.40% of Java online submissions
    public int add(ListNode long_l, ListNode short_l)
    {
        if (long_l != null && short_l != null)
        {
            int sum = long_l.val + short_l.val + add(long_l.next, short_l.next);
            int carry = sum > 9 ? 1 : 0;
            sum = carry == 1 ? sum - 10 : sum;
            long_l.val = sum;
            return carry;
        }
        else
        {
            return 0;
        }
    }
    public int carry_back(ListNode it, int index, int curr_index)
    {
        if (curr_index == index)
        {
            it.val = it.val + 1;
            int carry = it.val > 9 ? 1 : 0;
            it.val = carry == 1 ? it.val - 10 : it.val;
            return carry;
        }
        else if (curr_index >= 0)
        {
            it.val = it.val + carry_back(it.next, index, curr_index + 1);
            int carry = it.val > 9 ? 1 : 0;
            it.val = carry == 1 ? it.val - 10 : it.val;
            return carry;
        }
        else
        {
            return 1;
        }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l1_length = 0;
        int l2_length = 0;
        ListNode it = l1;
        while(it != null)
        {
            ++l1_length;
            it = it.next;
        }
        it = l2;
        while(it != null)
        {
            ++l2_length;
            it = it.next;
        }
        ListNode sentinel = new ListNode(-1);
        it = sentinel;
        ListNode long_it = null;
        ListNode short_it = null;
        if (l1_length > l2_length)
        {
            long_it = l1;
            short_it = l2;
        }
        else
        {
            long_it = l2;
            short_it = l1;
        }
        while(long_it != null)
        {
            it.next = new ListNode(long_it.val);
            it = it.next;
            long_it = long_it.next;
        }
        // unreversed long and short lists marked -- long list is a deep copy
        int diff = Math.abs(l1_length - l2_length);
        it = sentinel.next;
        ListNode carry = sentinel;
        for (int i = 0; i < diff; ++i)
        {
            it = it.next;
            carry = carry.next;
        }
        int carry_int = add(it, short_it);
        if (carry_int == 1)
        {
            if (diff > 0)
            {
                carry_int = carry_back(sentinel.next, diff - 1, 0);
            }
        }
        if (carry_int == 1)
        {
            ListNode head = new ListNode(1);
            head.next = sentinel.next;
            sentinel.next = head;
        }
        return sentinel.next;
    }
}
