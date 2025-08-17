package com.lizi.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * ClassName: DCccC
 * Description:
 *
 * @Author 染染熊
 * @Version 1.0
 * @Create 2025/8/17 14:51
合并区间
以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

 

示例 1：

输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2：

输入：intervals = [[1,4],[4,5]]
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 

提示：

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
相关标签
 */
public class 合并区间 {

    /**
     * 自己编写
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }

        // 1. 按区间起点升序排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // 2. 存放合并结果
        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0]; // 当前合并区间
        merged.add(current);

        // 3. 遍历区间
        for (int[] interval : intervals) {
            int currentEnd = current[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            if (nextStart <= currentEnd) {
                // 重叠：更新当前区间的结束位置
                current[1] = Math.max(currentEnd, nextEnd);
            } else {
                // 不重叠：开始新的区间
                current = interval;
                merged.add(current);
            }
        }
//        merged.stream().forEach(a -> System.out.println(Arrays.toString(a)));

        // 4. 转为二维数组输出
        return merged.toArray(new int[merged.size()][]);
    }


    /**
     * 力扣官网题解
     * @param intervals
     * @return
     */
    public int[][] mergeLK(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    // 测试
    public static void main(String[] args) {
        int[][] intervals1 = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println(Arrays.deepToString(merge(intervals1)));
        // 输出 [[1, 6], [8, 10], [15, 18]]

        int[][] intervals2 = {{1,4},{4,5}};
        System.out.println(Arrays.deepToString(merge(intervals2)));
        // 输出 [[1, 5]]
    }









}
