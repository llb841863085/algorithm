package matrix;

import java.util.Arrays;

/**
 * 搜索二维矩阵
 *
 * @author Jack Lee
 * @since 2021-06-28
 **/
public class MatrixSearch {
    public static void main(String[] args) {
        // 1  2  8  10
        // 3  5  11 19
        // 4  6  12 20
        // 7  8  13 21
        // 9  10 14 22
        int[][] matrix = {{1, 2, 8, 10}, {3, 5, 11, 19}, {4, 6, 12, 20}, {7, 8, 13, 21}, {9, 10, 14, 22}};
        System.out.println(Arrays.toString(findElement(matrix, 9)));
    }

    /**
     * 从矩阵中搜索值
     *
     * @param matrix 矩阵
     * @param x      搜索值
     * @return 值的下标元组
     */
    public static int[] findElement(int[][] matrix, int x) {
        int[] res = new int[2];
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] > x) {
                col--;
            } else if (matrix[row][col] < x) {
                row++;
            } else {
                res[0] = row;
                res[1] = col;
                break;
            }
        }
        return res;
    }
}
