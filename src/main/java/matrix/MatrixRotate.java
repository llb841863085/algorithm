package matrix;

import java.util.Arrays;

/**
 * 数组的水平翻转
 * <p>
 * LeetCode的48题就是这样的题目：有一个N*N整数矩阵，请编写一个算法，将矩阵顺时针旋转90度。
 * 给定一个N*N的矩阵，和矩阵的阶数N，请返回旋转后的NxN矩阵，保证N小于等于300。
 * 1 2 3                       7 4 1
 * 4 5 6     顺时针转90度之后     8 5 2
 * 7 8 9                       9 6 3
 *
 * @author Jack Lee
 * @since 2021-06-29
 **/
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class MatrixRotate {
    public static void main(String[] args) {
        int[][] matrix0 = init();
        System.out.println(Arrays.deepToString(rotateMatrix0(matrix0, matrix0.length)));

        int[][] matrix1 = init();
        System.out.println(Arrays.deepToString(rotateMatrix1(matrix1, matrix1.length)));

        int[][] matrix2 = init();
        System.out.println(Arrays.deepToString(rotateMatrix2(matrix2, matrix2.length)));
    }

    private static int[][] init() {
        return new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    }

    /**
     * 一层一层循环，坐标变换
     *
     * @param mat 矩阵
     * @param n   阶数
     * @return 翻转后矩阵（顺时针90度）
     */
    public static int[][] rotateMatrix0(int[][] mat, int n) {
        int tR = 0;
        int tC = 0;
        int dR = n - 1;
        int dC = n - 1;
        while (tR < dR) {
            for (int i = 0; i < dC - tC; i++) {
                int tmp = mat[tR][tC + i];
                mat[tR][tC + i] = mat[dR - i][tC];
                mat[dR - i][tC] = mat[dR][dC - i];
                mat[dR][dC - i] = mat[tR + i][dC];
                mat[tR + i][dC] = tmp;
            }
            tR++;
            tC++;
            dR--;
            dC--;
        }
        return mat;
    }

    /**
     * 根据数学规律进行
     *
     * @param mat 矩阵
     * @param n   阶数
     * @return 翻转后矩阵（顺时针90度）
     */
    public static int[][] rotateMatrix1(int[][] mat, int n) {
        int[][] temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[j][n - 1 - i] = mat[i][j];
            }
        }
        return temp;
    }

    /**
     * 两次翻转法
     * <p>
     * 先沿右上-左下的对角线翻转，再沿水平中线上下翻转即：对于函数中的一个点，先以y=x为轴作对称，然后以x轴作对称，则相当于该点顺时针旋转90°。
     * 所以对于图像来说每个点顺时针旋转了，则图也旋转了。所以可以将图以对角线作对称，然后以中间的横线作对称。
     *
     * @param mat 矩阵
     * @param n   阶数
     * @return 翻转后矩阵（顺时针90度）
     */
    public static int[][] rotateMatrix2(int[][] mat, int n) {
        // 对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        // 以横轴翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[i][n - 1 - j];
                mat[i][n - 1 - j] = temp;
            }
        }
        return mat;
    }
}
