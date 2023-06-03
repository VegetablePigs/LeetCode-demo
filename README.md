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
  * 注意：要知道数组的元素在内存地址中是连续的，不能单独删除数组中的某个元素，只能覆盖。
  ##### 1.2.1 暴力法
  这个题目暴力的解法就是两层for循环，一个for循环遍历数组元素 ，第二个for循环更新数组。  
    时间复杂度：O(n^2)
    空间复杂度：O(1)
    ```java
    public int removeElement(int[] nums, int val) {
        int size = nums.size();
        for (int i = 0; i < size; i++) {
            if (nums[i] == val) { // 发现需要移除的元素，就将数组集体向前移动一位
                for (int j = i + 1; j < size; j++) {
                    nums[j - 1] = nums[j];
                }
                i--; // 因为下标i以后的数值都向前移动了一位，所以i也向前移动一位
                size--; // 此时数组的大小-1
            }
        }
        return size;
    }
    ```
    ##### 1.2.2 双指针法 &#x2714;
    双指针法（快慢指针法）：通过一个快慢指针在一个for循环下完成两个for循环的工作。  
    定义快慢指针
    * 快指针：寻找新数组的元素 ，新数组就是不含有目标元素的数组
    * 慢指针：指向更新 新数组下标的位置
    双指针法（快慢指针法）在数组和链表的操作中是非常常见的，很多考察数组、链表、字符串等操作的面试题，都使用双指针法。  
  
注意这些实现方法并没有改变元素的相对位置！  
时间复杂度：O(n)  
空间复杂度：O(1)  

```java
public int removeElement(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
        if (val != nums[fastIndex]) {
        nums[slowIndex++] = nums[fastIndex];
        }
        }
        return slowIndex;
        }
```
```java
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
```
#### 1.3 有序数组的平方 lc-977
* 977
  * 注意：数组是有序的，所以平方后的数组也是有序的，所以可以使用双指针法，从两端开始遍历，比较两端的平方值，将较大的值放入新数组的末尾。
  * 时间复杂度：O(n)
  * 空间复杂度：O(n)  
  
##### 1.3.1 双指针法  
i指向起始位置，j指向终止位置。  
定义一个新数组result，和A数组一样的大小，让k指向result数组终止位置。  
如果A[i] * A[i] < A[j] * A[j] 那么result[k--] = A[j] * A[j]; 。  
如果A[i] * A[i] >= A[j] * A[j] 那么result[k--] = A[i] * A[i]; 。  

```java
public int[] sortedSquares(int[] nums) {
        int right = nums.length - 1;
        int left = 0;
        int[] result = new int[nums.length];
        int index = result.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                // 正数的相对位置是不变的， 需要调整的是负数平方后的相对位置
                result[index--] = nums[left] * nums[left];
                ++left;
            } else {
                result[index--] = nums[right] * nums[right];
                --right;
            }
        }
        return result;
    }
```


  
