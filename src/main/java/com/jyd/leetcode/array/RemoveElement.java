package com.jyd.leetcode.array;

public class RemoveElement {
    /**
     * 移除元素
     */

    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        RemoveElement solution = new RemoveElement();
        int result = solution.removeElement(nums, 3);
        System.out.println(result);
    }

    // 快慢指针
    public int removeElement(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (val != nums[fastIndex]) {
                nums[slowIndex++] = nums[fastIndex];
            }
        }
        return slowIndex;
    }

    /**
     * 相向双指针方法，基于元素顺序可以改变的题目描述改变了元素相对位置，确保了移动最少元素
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int removeElement2(int[] nums, int val) {
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        while(rightIndex >= 0 && nums[rightIndex] == val) {
            //将right移到从右数第一个值不为val的位置
            rightIndex--;
        }
        while(leftIndex <= rightIndex){
            if (nums[leftIndex] == val){
                //left位置的元素需要移除
                //将right位置的元素移到left（覆盖），right位置移除
                nums[leftIndex] = nums[rightIndex];
                rightIndex--;
            }
            leftIndex++;
            while(rightIndex >= 0 && nums[rightIndex] == val) {
                //将right移到从右数第一个值不为val的位置
                rightIndex--;
            }
        }
        return leftIndex;
    }
}
