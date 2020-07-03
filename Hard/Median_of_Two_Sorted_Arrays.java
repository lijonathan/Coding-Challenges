public class Median_of_Two_Sorted_Arrays {
/*
Rating: Hard

There are two sorted arrays nums1 and nums2
of size m and n respectively.

Find the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0

Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
*/

/*
    Approach: First choose the smaller of the two arrays. This will be the one to split/binary
              search across.

              Idea -- continuously split the smaller array and then split the second array at a point
              to ensure that number of elements in the left halves is equal to or one greater than the
              number of elements in the right half.

              Find the partition point of the smaller array. The second array gets partitioned such that
              the number of elements in left part of the first array and the left part of the second array
              are equal or +1 to the sum of the elements in the right parts.

              Note: the mid numbers do not represent indices. For the left part, it is mid - 1 but the right
              part is mid.

              The correct partition is found if n1_left <= n2_right && n2_left <= n1_right. This means the 
              split has occurred directly at the center of what would have been the combined array.

              If the total number of elements is even --> return the avg(max(n1_left, n2_left), min(n1_right, n2_right))

              Total number of elements is odd --> return max(n1_left, n2_left)

              If the condition for finding the partition was not met:

                If max of left side of nums1 is greater than the min of the right side of nums2,
                right = mid - 1 and recalculate partition

                If max of left side of nums2 is less than min of right side of nums1
                left = mid + 1 and recalculate partition.

              If the loop exits, then the input arrays were not sorted per problem specification

*/

    // O(log(min(m, n))) time
    // O(1) space
    // Runtime: 2 ms, faster than 99.83% of Java online submissions
    // Memory Usage: 40.5 MB, less than 55.36% of Java online submissions
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
        {
            return findMedianSortedArrays(nums2, nums1); // ensure nums1 not larger than nums2
        }

        int start = 0;
        int end = nums1.length;
        while(start <= end)
        {
            int mid_1 = (start + end) / 2;
            int mid_2 = (nums1.length + nums2.length + 1) / 2 - mid_1; // + 1 handles both even and
            // always takes the ceiling on odd numbers
            int left_n1 = mid_1 > 0 ? nums1[mid_1 - 1] : Integer.MIN_VALUE; // if nothing left of partition, set to min value
            int right_n1 = mid_1 < nums1.length ? nums1[mid_1] : Integer.MAX_VALUE; // if nothing right of partition, set to max value

            int left_n2 = mid_2 > 0 ? nums2[mid_2 - 1] : Integer.MIN_VALUE;
            int right_n2 = mid_2 < nums2.length ? nums2[mid_2] : Integer.MAX_VALUE;
            if (left_n1 <= right_n2 && left_n2 <= right_n1)
            {
                return (nums1.length + nums2.length) % 2 == 0 ?
                        (Math.max(left_n1, left_n2) + Math.min(right_n1, right_n2)) / 2.0 :
                            Math.max(left_n1, left_n2);
            }
            else if (left_n1 < right_n2) // can move right
            {
                start = mid_1 + 1;
            }
            else
            {
                end = mid_1 - 1;
            }
        }
        return Integer.MIN_VALUE;
    }
}
