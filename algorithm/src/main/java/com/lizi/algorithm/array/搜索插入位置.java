package com.lizi.algorithm.array;

/**
 * ClassName: 搜索插入位置
 * Description:
 *
 * @Author 染染熊
 * @Version 1.0
 * @Create 2025/8/17 16:34
搜索插入位置
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

请必须使用时间复杂度为 O(log n) 的算法。

 

示例 1:

输入: nums = [1,3,5,6], target = 5
输出: 2
示例 2:

输入: nums = [1,3,5,6], target = 2
输出: 1
示例 3:

输入: nums = [1,3,5,6], target = 7
输出: 4
 

提示:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums 为 无重复元素 的 升序 排列数组
-104 <= target <= 104
相关标签

Java



作者：LeetCode
链接：https://leetcode.cn/leetbook/read/array-and-string/cxqdh/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 搜索插入位置 {

    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        // 判断数组是否为空
        if (nums.length == 0) {
            return 0;
        }
        // 记录左索引
        int left = 0;
        // 记录右索引
        int right = nums.length - 1;
        // 循环查找
        while (left <= right) {
            // 计算中间索引
            int mid = left + (right - left) / 2;
            // 判断中间索引的值是否等于目标值
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) { // 如果中间索引小于目标值，改变左索引继续查找
                left = mid + 1;
            } else { // 如果中间索引大于目标值，改变右索引继续查找
                right = mid - 1;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 7;
        System.out.println(new 搜索插入位置().searchInsert(nums, target));

    }
}
