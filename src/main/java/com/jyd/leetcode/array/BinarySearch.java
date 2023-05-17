package com.jyd.leetcode.array;

/**
 * @Author: jyd
 * @Date: 2023/05/10 15:41
 * @Company: 广州风雨雷科技有限公司
 * @Version: 1.0
 * @Desc:
 **/
public class BinarySearch {
    /**
     * 二分查找 leetcode 704
     * 写二分法，区间的定义一般为两种，左闭右闭即[left, right]，或者左闭右开即[left, right)。
     * 这道题目的前提是数组为有序数组，同时题目还强调数组中无重复元素，因为一旦有重复元素，使用二分查找法返回的元素下标可能不是唯一的，这些都是使用二分法的前提条件，当大家看到题目描述满足如上条件的时候，可要想一想是不是可以用二分法了
     * @param args
     */

    public static void main (String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int target = 10;
        BinarySearch solution = new BinarySearch();
        int result = solution.search(nums, target);
        System.out.println(result);
    }
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 二分查找 定义左闭右开区间 [left, right)
        while (left <= right){
            int mid = left + (right - left) / 2; // 防止溢出 等同于(left + right) / 2
            if (nums[mid] == target) {
                // 找到了
                return mid;
            } else if (nums[mid] < target) {
                // target 在右区间，所以[mid + 1, right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                // target 在左区间，所以[left, mid - 1]
                right = mid - 1;
            }
        }
        // 左闭右闭区间 [left, right]
        while (left < right){
            int middle = left + ((right - left) >> 1);
            if (nums[middle] == target) {
                // 找到了
                return middle;
            } else if (nums[middle] > target){
                right = middle; // target 在左区间，在[left, middle)中
            } else if (nums[middle] < target){
                left = middle + 1; // target 在右区间，在[middle + 1, right)中
            }
        }
        return -1;
    }

    // 暴力解法
    private static int ordinary (int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                // 找到了
                return i;
            }
        }
        return -1;
    }
}
