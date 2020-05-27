class K_Closest_Points_to_Origin {
/*

Rating: Medium

We have a list of points on the plane.
Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane
is the Euclidean distance.)

You may return the answer in any order.
The answer is guaranteed to be unique (except
for the order that it is in.)

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)


Note:

    1 <= K <= points.length <= 10000
    -10000 < points[i][0] < 10000
    -10000 < points[i][1] < 10000

 */
/*****************************************************************
 
 Solution 1: Max Heap -- O(k) space, O(n log k) runtime where n is the number of
             points in the array and k is the number of closest points
 
 *********************************************************************/
    private class MaxHeap
    {
        int[][] heap;
        int max_size;
        int count;
        private int distance_measure(int buffer[])
        {
            return buffer[0] * buffer[0] + buffer[1] * buffer[1];
        }
        public MaxHeap(int K)
        {
            heap = new int[K + 1][2];
            max_size = K;
            count = 0;
        }
        
        private void bubble_up()
        {
            int curr_i = count;
            int parent_i = count / 2;
            int[] swap = new int[2];
            while (parent_i > 0)
            {
                int dst_c = distance_measure(heap[curr_i]);
                int dst_p = distance_measure(heap[parent_i]);
                if (dst_p < dst_c)
                {
                    swap[0] = heap[parent_i][0];
                    swap[1] = heap[parent_i][1];
                    heap[parent_i][0] = heap[curr_i][0];
                    heap[parent_i][1] = heap[curr_i][1];
                    heap[curr_i][0] = swap[0];
                    heap[curr_i][1] = swap[1];
                    curr_i = parent_i;
                    parent_i = curr_i / 2;
                }
                else
                {
                    break;
                }
            }
        }

        private void heapify()
        {
            int curr_i = 1;
            int l_i = curr_i * 2;
            int r_i = curr_i * 2 + 1;
            int[] swap = new int[2];
            while (l_i <= count)
            {
                int dst_c = distance_measure(heap[curr_i]);
                int dst_l = Integer.MIN_VALUE;
                int dst_r = Integer.MIN_VALUE;
                if (l_i <= count)
                {
                    dst_l = distance_measure(heap[l_i]);
                }
                if (r_i <= count)
                {
                    dst_r = distance_measure(heap[r_i]);
                }
                if (dst_c < dst_l && dst_l > dst_r) 
                // This condition was wrong! not dst_l < dst_r!!
                // swap with the larger child!
                {
                    swap[0] = heap[curr_i][0];
                    swap[1] = heap[curr_i][1];
                    heap[curr_i][0] = heap[l_i][0];
                    heap[curr_i][1] = heap[l_i][1];
                    heap[l_i][0] = swap[0];
                    heap[l_i][1] = swap[1];
                    curr_i = l_i;
                }
                else if (dst_c < dst_r)
                {
                    swap[0] = heap[curr_i][0];
                    swap[1] = heap[curr_i][1];
                    heap[curr_i][0] = heap[r_i][0];
                    heap[curr_i][1] = heap[r_i][1];
                    heap[r_i][0] = swap[0];
                    heap[r_i][1] = swap[1];
                    curr_i = r_i;
                }
                else
                {
                    break;
                }
                l_i = curr_i * 2;
                r_i = curr_i * 2 + 1;
            }
        }
        public boolean add(int[] point)
        {
            if (count < max_size)
            {
                ++count;
                heap[count] = point;
                bubble_up();
                return true;
            }
            else if (distance_measure(heap[1]) > distance_measure(point))
            {
                heap[1][0] = point[0];
                heap[1][1] = point[1];
                heapify();
                return true;
            }
            else
            {
                return false;
            }
        }
        public int[] remove()
        {
            if (count == 0)
            {
                return null;
            }
            int[] root  = new int[2];
            root[0] = heap[1][0];
            root[1] = heap[1][1];
            if (count == 1)
            {
                --count;
                return root;
            }
            heap[1][0] = heap[count][0];
            heap[1][1] = heap[count][1];
            heap[count] = null;
            --count;
            heapify();
            return root;
        }
    }
     
    
    public int[][] kClosest_MaxHeap(int[][] points, int K) {
        int[][] closest = new int[K][2];
        MaxHeap mh = new MaxHeap(K);
        for(int i = 0; i < points.length; ++i)
        {
            mh.add(points[i]);
        }
        for(int m = 0; m < K; ++m)
        {
            closest[K - 1 - m] = mh.remove();
        }
        return closest;
    }
    
    /*****************************************************************
    
    Solution 2: Mergesort and return k Closest
                O(n log n) time, O(n) space where n is the number of points
    
    *********************************************************************/
    private int distance_measure(int buffer[])
    {
        return buffer[0] * buffer[0] + buffer[1] * buffer[1];
    }
    private void merge(int[][] points, int start, int mid, int end)
    {
        int[][] buffer = new int[end - start + 1][2];
        int l_iter = start;
        int r_iter = mid + 1;
        int index = 0;
        while (l_iter <= mid || r_iter <= end)
        {
            if (l_iter > mid)
            {
                buffer[index][0] = points[r_iter][0];
                buffer[index][1] = points[r_iter][1];
                ++r_iter;
            }
            else if (r_iter > end)
            {
                buffer[index][0] = points[l_iter][0];
                buffer[index][1] = points[l_iter][1];
                ++l_iter;
            }
            else if (distance_measure(points[l_iter]) < distance_measure(points[r_iter]))
            {
                buffer[index][0] = points[l_iter][0];
                buffer[index][1] = points[l_iter][1];
                ++l_iter;
            }
            else
            {
                buffer[index][0] = points[r_iter][0];
                buffer[index][1] = points[r_iter][1];
                ++r_iter;
            }
            ++index;
        }
        index = 0;
        for (int i = start; i <= end; ++i)
        {
            points[i][0] = buffer[index][0];
            points[i][1] = buffer[index][1];
            ++index;
        }
    }
    private void merge_sort(int[][] points, int start, int end)
    {
        if (start < end)
        {
            int mid = (start + end) / 2;
            merge_sort(points, start, mid);
            merge_sort(points, mid + 1, end);
            merge(points, start, mid, end);
        }
    }
    public int[][] kClosest_mergeSort(int[][] points, int K) {
        int[][] closest = new int[K][2];
        merge_sort(points, 0, points.length - 1);
        for (int i = 0; i < K; ++i)
        {
            closest[i][0] = points[i][0];
            closest[i][1] = points[i][1];
        }
        return closest;
    }

}
