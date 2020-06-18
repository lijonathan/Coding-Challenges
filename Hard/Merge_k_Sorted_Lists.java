class Merge_k_Sorted_Lists {
/*

Rating: Hard

Merge k sorted linked lists and return it as
one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6

*/
     // Definition for singly-linked list.
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
    
    private class minHeap
    {
        ListNode[] arr;
        int count;
        public minHeap(int size)
        {
            arr = new ListNode[size + 1];
            count = 0;
        }
        private boolean is_less(ListNode p, ListNode q)
        {
            return p.val < q.val;   
        }
        private void swap(int curr_i, int s_i)
        {
            ListNode s_p = arr[curr_i];
            arr[curr_i] = arr[s_i];
            arr[s_i] = s_p;
        }
        private void bubble_down()
        {
            int curr_i = 1;
            while (curr_i < count)
            {
                int l_i = curr_i * 2;
                int r_i = curr_i * 2 + 1;
                if (l_i <= count && is_less(arr[l_i], arr[curr_i]) &&
                   (r_i > count || is_less(arr[l_i], arr[r_i])))
                {
                    swap(curr_i, l_i);
                    curr_i = l_i;
                }
                else if (r_i <= count && is_less(arr[r_i], arr[curr_i]))
                {
                    swap(curr_i, r_i);
                    curr_i = r_i;
                }
                else
                {
                    break;
                }
            }
        }
        public ListNode remove()
        {
            if (count > 0)
            {
                ListNode smallest = arr[1];
                arr[1] = arr[count];
                arr[count] = null;
                --count;
                bubble_down();
                return smallest;
            }
            else
            {
                return null;
            }
        }
        private void bubble_up()
        {
            int curr_i = count;
            while (curr_i > 1)
            {
                int par_i = curr_i / 2;
                if (is_less(arr[curr_i], arr[par_i]))
                {
                    swap(curr_i, par_i);
                    curr_i = par_i;
                }
                else
                {
                    break;
                }
            }
        }
        public boolean insert(ListNode entry)
        {
            if (entry == null)
            {
                return false;
            }
            if (count < arr.length - 1)
            {
                ++count;
                arr[count] = entry;
                bubble_up();
                return true;
            }
            return false;
        }
        public boolean is_empty()
        {
            return count == 0;
        }
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        // O(n log k) runtime, where n = total # of nodes, k = # of lists
        // O(k) space for the MinHeap
        minHeap mh = new minHeap(lists.length);
        for(int i = 0; i < lists.length; ++i)
        {
            mh.insert(lists[i]);
        }
        ListNode sentinel = new ListNode();
        ListNode it = sentinel;
        // no new list created, just modified the original lists
        // no extra space
        while (!mh.is_empty())
        {
            ListNode popped = mh.remove();
            it.next = popped;
            it = it.next;
            popped = popped.next;
            if (popped != null)
            {
                mh.insert(popped);
            }
        }
        return sentinel.next;
    }
}
