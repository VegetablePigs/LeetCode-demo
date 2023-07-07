package com.jyd.leetcode.array;

/**
 * @Author: jyd
 * @Date: 2023/07/07 14:19
 * @Version: 1.0
 * @Description: 长度最小的子数组
 **/

public class MinSubArrayLen {

    /*
    给定一个含有n个正整数的数组和一个正整数 target 。
    找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，
    并返回其长度。如果不存在符合条件的子数组，返回 0 。
    */

    /*
        示例 1：
      输入：target = 7, nums = [2,3,1,2,4,3]
      输出：2
      解释：子数组 [4,3] 是该条件下的长度最小的子数组。
        示例 2：
      输入：target = 4, nums = [1,4,4]
      输出：1
        示例 3：
        输入：target = 11, nums = [1,1,1,1,1,1,1,1]
        输出：0
     */

    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        MinSubArrayLen solution = new MinSubArrayLen();
        int result = solution.minSubArrayLen(7, nums);
        System.out.println(result);
    }

    private int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int result = Integer.MAX_VALUE;
        int sum = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                // 通过将 "right" 索引减去 "left" 索引并加上 1，计算当前子数组的长度。
                //   - "result" 变量用于跟踪目前为止找到的最小长度。
                //   - "Math.min()" 函数用于更新 "result" 变量，取当前 "result" 和计算出的长度中较小的值。
                result = Math.min(result, right - left + 1);
                // 从 "sum" 变量中减去 "nums" 中 "left" 索引处的值，并将 "left" 索引增加 1。
                //   - 这一步删除子数组的最左边的元素，以检查是否存在更短的子数组。
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }


}