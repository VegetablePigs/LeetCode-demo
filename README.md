## 笔记

记录自己做题的思路和解法

### 做题顺序
数组-> 链表-> 哈希表->字符串->栈与队列->树->回溯->贪心->动态规划->图->其他

### 1. 数组
#### 1.1 二分查找 &#x2714;
* 704 &#x2714;
  * 注意：二分查找的边界条件，以及mid的取值
  * 写二分法，区间的定义一般为两种，左闭右闭即[left, right]，或者左闭右开即[left, right)。
  * 前提是数组为有序数组，同时题目还强调数组中无重复元素，因为一旦有重复元素，使用二分查找法返回的元素下标可能不是唯一的，这些都是使用二分法的前提条件，当大家看到题目描述满足如上条件的时候，可要想一想是不是可以用二分法了。
```java
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
```
#### 1.2 移除元素
* 27 
  * 注意：