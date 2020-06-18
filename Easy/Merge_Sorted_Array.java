public class Merge_Sorted_Array {

/*
Rating: Easy

Given two sorted integer arrays nums1 and nums2,
merge nums2 into nums1 as one sorted array.

Note:

    The number of elements initialized in
    nums1 and nums2 are m and n respectively.
    You may assume that nums1 has enough space
    (size that is greater or equal to m + n) to
    hold additional elements from nums2.

Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

*/

/*
    Approach: Copy all of nums1 numbers in ascending order to the end of nums1 array. It is okay
    if the beginning of the copy overrides the end of the original since it is guarenteed nums1.length = m + n.
    Iterate through the copy of nums1 and nums2 and copy them in sorted order to the front of nums1.
*/
    // O(n + m) time
    // O(1) space
    // Runtime: 0ms beats 100.00% of Java online submissions
    // Memory Usage: 39.3MB beats 87.03% of Java online submissions
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = 0;
        int end = nums1.length - 1;
        int i, j = 0;
        for (i = m - 1; i >= 0; --i)
        {
            nums1[end] = nums1[i];
            --end;
        }
        i = end + 1;
        while (i < nums1.length || j < n)
        {
            if (i >= nums1.length)
            {
                nums1[index] = nums2[j];
                ++j;
            }
            else if (j >= n)
            {
                nums1[index] = nums1[i];
                ++i;
            }
            else if (nums1[i] <= nums2[j])
            {
                nums1[index] = nums1[i];
                ++i;
            }
            else
            {
                nums1[index] = nums2[j];
                ++j;
            }
            ++index;
        }
    }
}
