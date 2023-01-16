package Exercise2022.SearchSort;

/**
 * 4. Median of Two Sorted Arrays
 *
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 */
public class H_P4_MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2, int l1, int r1, int l2, int r2, int k) {
        int n = (r1 - l1) + (r2 - l2);
        int mid = n/2;
        if (k == mid) {
            if (n < 2) {

            }
        } if (k < mid) {
            r1 = (r1 - l1)/2;
            r2 = (r2 - l2)/2;
            return findMedianSortedArrays(nums1, nums2, l1, r1, l2, r2, k);
        }  else {
            l1 = (l1 - l1)/2;
            l2 = (l2 - l2)/2;
            return findMedianSortedArrays(nums1, nums2, l1, r1, l2, r2, k);
        }
    }
}
