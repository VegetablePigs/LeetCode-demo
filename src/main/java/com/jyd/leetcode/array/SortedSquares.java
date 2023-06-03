package com.jyd.leetcode.array;

import java.util.Arrays; /**
 * @Author: jyd
 * @Date: 2023/06/04 00:00
 * @Company: 广州风雨雷科技有限公司
 * @Version: 1.0
 * @Description:
 **/
public class SortedSquares {

    public static void main (String[] args) {
        int[] nums = {-4,-1,0,3,10};
        SortedSquares solution = new SortedSquares();
        int[] result = solution.sortedSquares(nums);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 有序数组的平方
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int index = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                result[index--] = nums[left] * nums[left];
                left++;
            } else {
                result[index--] = nums[right] * nums[right];
                right--;
            }
        }
        return result;
    }
}