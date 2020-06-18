class Trapping_Rain_Water {
/*

Rating: Hard

Given n non-negative integers representing an
elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped.
Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

 */

//KEY : Two Pointer technique
    public int trap(int[] height) {
        // O(n) time, O(1) space
        // move left pointer to first non zero height
        // IDEA: move right pointer until either hits end or
        // is at least as tall as left pointer height
        // increment left to the right pointer and sum the
        // amount of water trapped at each spot as left
        // is incrementing
        int l_index = 0;
        int r_index = 0;

        // above idea flushed out below
        int total = 0;
        while(l_index < height.length - 1)
        {
            int l_height = height[l_index];
            if (l_height > 0)
            {
                r_index = l_index + 1;
                int t_index = r_index;
                // move right pointer until hits end or
                // a block as tall as left
                
                // if it is not as tall as left, keep track of
                // current tallest and keep moving right pointer right
                while(r_index < height.length)
                {
                    int r_height = height[r_index];
                    if (r_height >= height[t_index])
                    {
                        t_index = r_index;
                    }
                    if (r_height >= l_height)
                    {
                        break;
                    }
                    ++r_index;
                }
                // move right pointer to tallest index --> this is needed
                // because if right keeps moving and they are all shorter than left
                // we need to move it to the tallest one that was right of the left pointer
                r_index = t_index;
                int shortest = Math.min(height[l_index], height[r_index]);
                ++l_index;
                // sum water by incrementing left until it hits right pointer
                while (l_index < r_index)
                {
                    int spot_height = shortest - height[l_index];
                    total += spot_height;
                    ++l_index;
                }
            }
            // left pointer height is zero, keep moving right
            else
            {
                ++l_index;
            }
        }
        return total;
    }
}
