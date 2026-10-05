class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to minimize binary search steps
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int x = nums1.length;
        int y = nums2.length;
        int low = 0, high = x;
        
        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;
            
            // Handle edge cases when partition is at the extreme left or right
            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];
            
            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];
            
            // Check if we have found the correct partition
            if (maxX <= minY && maxY <= minX) {
                // If total number of elements is odd
                if ((x + y) % 2 == 1) {
                    return Math.max(maxX, maxY);
                }
                // If total number of elements is even
                return (Math.max(maxX, maxY) + Math.min(minX, minY)) / 2.0;
            } else if (maxX > minY) {
                // We are too far right on nums1, move left
                high = partitionX - 1;
            } else {
                // We are too far left on nums1, move right
                low = partitionX + 1;
            }
        }
        
        throw new IllegalArgumentException("Input arrays are not sorted or invalid.");
    }
}

