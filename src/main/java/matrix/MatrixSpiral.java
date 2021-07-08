package matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 螺旋矩阵
 *
 * @author Jack Lee
 * @since 2021-06-28
 **/
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class MatrixSpiral {
    public static void main(String[] args) {
        // 1  2  3
        // 4  5  6
        // 7  8  9
        // 11 22 33
        int[][] matrix0 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {11, 22, 33}};
        // 输出：[1, 2, 3, 6, 9, 33, 22, 11, 7, 4, 5, 8]
        System.out.println(spiralOrder(matrix0));
        int[][] matrix1 = generateMatrix(5);
        for (int[] ints : matrix1) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * leetcode54 顺时针打印二维数组
     * 给定一个m*n大小的矩阵，按螺旋的顺序返回矩阵中的所有元素。
     *
     * @param matrix 数组
     * @return 序列
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        if (matrix.length == 0) {
            return res;
        }
        //定义四个坐标，表示每次循环的四个位置
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (top < (matrix.length + 1) / 2 && left < (matrix[0].length + 1) / 2) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            for (int i = top + 1; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            for (int i = right - 1; i >= left && top != bottom; i--) {
                res.add(matrix[bottom][i]);
            }
            for (int i = bottom - 1; i >= (top + 1) && left != right; i--) {
                res.add(matrix[i][left]);
            }
            top++;
            bottom--;
            left++;
            right--;
        }
        return res;
    }

    /**
     * 59. 螺旋矩阵II
     * 给你一个正整数n，生成一个包含1到n2所有元素，且元素按顺时针顺序螺旋排列的nxn正方形矩阵 matrix。
     *
     * @param n n阶矩阵
     * @return 数组
     */
    public static int[][] generateMatrix(int n) {
        int l = 0;
        int r = n - 1;
        int t = 0;
        int b = n - 1;
        int[][] matrix = new int[n][n];
        int num = 1;
        int tar = n * n;
        while (num <= tar) {
            // left to right.
            for (int i = l; i <= r; i++) {
                matrix[t][i] = num++;
            }
            t++;
            // top to bottom.
            for (int i = t; i <= b; i++) {
                matrix[i][r] = num++;
            }
            r--;
            // right to left.
            for (int i = r; i >= l; i--) {
                matrix[b][i] = num++;
            }
            b--;
            // bottom to top.
            for (int i = b; i >= t; i--) {
                matrix[i][l] = num++;
            }
            l++;
        }
        return matrix;
    }
}
