class Container_With_Most_Water {
/*

Rating: Medium

Given n non-negative integers a1, a2, ..., an ,
where each represents a point at coordinate (i, ai). n
vertical lines are drawn such that the two endpoints of
line i is at (i, ai) and (i, 0). Find two lines, which
together with x-axis forms a container, such that the container
contains the most water.

Note: You may not slant the container and n is at least 2.

 
The above vertical lines are represented by
array [1,8,6,2,5,4,8,3,7]. In this case, the max
area of water (blue section) the container can contain is 49.

Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
*/

    public int maxArea(int[] height) {
        // two pointer approach one from right, one from left
        // O(n) time, O(1) space
        int l_index = 0;
        int r_index = height.length - 1;
        int max_area = Integer.MIN_VALUE;
        while (l_index < r_index)
        {
            // PROOF: We starts with the widest container, l = 0 and r = n - 1.
            // Let's say the left one is shorter: h[l] < h[r]. Then, this is
            // already the largest container the left one can possibly form.
            // There's no need to consider it again. Therefore, we just throw
            // it away and start again with l = 1 and r = n -1.
            int area = (r_index - l_index) * Math.min(height[r_index], height[l_index]);
            if (area > max_area)
            {
                max_area = area;
            }
            // shift the pointer with the smaller height inward
            if (height[l_index] > height[r_index])
            {
                --r_index;
            }
            else
            {
                ++l_index;
            }
        }
        return max_area;
    }
}
