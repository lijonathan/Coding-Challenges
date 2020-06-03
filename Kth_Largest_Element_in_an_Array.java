class Kth_Largest_Element_in_an_Array {
/*

Rating: Medium

Find the kth largest element in an unsorted array.
Note that it is the kth largest element in the sorted order,
not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.

*/

    private class minHeap
    {
        int[] heap;
        int count;
        public minHeap(int k)
        {
            heap = new int[k + 1];
            count = 0;
        }
        private void swap(int i_1, int i_2)
        {
            int temp = heap[i_1];
            heap[i_1] = heap[i_2];
            heap[i_2] = temp;
        }
        private void bubble_up()
        {
            int curr_i = count;
            while (curr_i / 2 > 0)
            {
                int p_i = curr_i / 2;
                if (heap[p_i] > heap[curr_i]) // min heap so swap is parent is larger
                {
                    swap(curr_i, p_i);
                }
                else
                {
                    break;
                }
                curr_i = p_i;
            }
        }
        private void heapify()
        {
            int curr_i = 1;
            while (curr_i * 2 <= count)
            {
                int l_i = curr_i * 2;
                int r_i = curr_i * 2 + 1;
                int s_i;
                if (r_i <= count)
                {
                    s_i = (heap[l_i] < heap[r_i]) ? l_i : r_i;
                }
                else
                {
                    s_i = l_i;
                }
                if (heap[s_i] < heap[curr_i])
                {
                    swap(s_i, curr_i);
                }
                else
                {
                    break;
                }
                curr_i = s_i;
            }
        }
        public void insert(int x)
        {
            if (count < heap.length - 1)
            {
                ++count;
                heap[count] = x;
                bubble_up();
            }
            else
            {
                if (heap[1] < x)
                {
                    heap[1] = x;
                    heapify();
                }
            }
        }
        
        public int remove()
        {
            return heap[1];
        }
        
    }
    
    public int findKthLargest(int[] nums, int k) {
        // sort (n log n) , space O(n)
        // min heap (n log k) , space O(k)
        minHeap mh = new minHeap(k);
        for (int i = 0; i < nums.length; ++i)
        {
            mh.insert(nums[i]);
        }
        return mh.remove();
    }
}
