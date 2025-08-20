package com.lizi.algorithm.array;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: 零矩阵
 * Description:
 *
 * @Author 染染熊
 * @Version 1.0
 * @Create 2025/8/19 22:46
零矩阵
编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。

 

示例 1：

输入：
[
[1,1,1],
[1,0,1],
[1,1,1]
]
输出：
[
[1,0,1],
[0,0,0],
[1,0,1]
]
示例 2：

输入：
[
[0,1,2,0],
[3,4,5,2],
[1,3,1,5]
]
输出：
[
[0,0,0,0],
[0,4,5,0],
[0,3,1,0]
]
相关标签

Java



作者：LeetCode
链接：https://leetcode.cn/leetbook/read/array-and-string/ciekh/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 零矩阵 {


    /**
     * 方法1：
     * @param matrix
     */
    public static void setZeroes1(int[][] matrix) {

        List<AbstractMap.SimpleEntry<Integer, Integer>> list = new ArrayList<>();
        // 先获取所有为0的索引
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                if (matrix[i][j] == 0) {
                    list.add(new AbstractMap.SimpleEntry<>(i, j));
                }
        }

        // 遍历所有为0的索引，将对应行和列都置为0：
        for (AbstractMap.SimpleEntry<Integer, Integer> entry : list) {
            int i = entry.getKey();
            int j = entry.getValue();
            for (int k = 0; k < matrix[i].length; k++) {
                matrix[i][k] = 0;
            }
            for (int k = 0; k < matrix.length; k++) {
                matrix[k][j] = 0;
            }
        }
    }

    /**
     * 方法2：力扣官网方法，类似上面的setZeroes1方法
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }


    /**
     * 方法3：优化方法2，不需要额外的空间，将第一行和第一列作为记录的数组标记，第一行和第一列需要最后进行处理
     * @param matrix
     */
    public static void setZeroes3(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false, flagRow0 = false;
        // 记录第一列是否存在0
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
        }
        // 记录第一行是否存在0
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                flagRow0 = true;
            }
        }
        // 遍历除了第一行和第一列，并将为0的第一行和第一列置为0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 遍历除了第一行和第一列，判断对应第一行和第一列是否为0，将对应行和列都置为0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 最后处理第一行和第一列
        if (flagCol0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (flagRow0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }


    /**
     * 方法4：优化方法3，不需要额外的空间，将第一列作为记录的数组标记，第一列需要最后进行处理
     * @param matrix
     */
    public static void setZeroes4(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 用于记录第一列是否为0标识
        boolean flagCol0 = false;
        // 遍历，记录第一列是否存在0，并判断每一行，并将为0的 第一行和第一列置为0
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 倒序遍历：判断每个位置的第一行和第一类是否为0，为0，将其设置为0；并判断第一列是否存在0，存在则，设置对应第一列为0
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }
    }





    // 测试
    public static void main(String[] args) {
        int[][] matrix = {
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };
        setZeroes4(matrix);

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    } //





}
