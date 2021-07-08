package matrix;

import java.util.Arrays;

/**
 * 矩阵置零的三种解法
 * <p>
 * leetcode73，给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。
 * <p>
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 * <p>
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 * @author Jack Lee
 * @since 2021-06-29
 **/
public class MatrixSetZero {
    public static void main(String[] args) {
        int[][] matrix0 = init();
        System.out.println("setZeroes init：\n" + Arrays.deepToString(matrix0).replace("],", "\n").replace(",", "\t").replaceAll("(\\[| \\[|])", ""));
        setZeroes0(matrix0);
        System.out.println("setZeroes0：\n" + Arrays.deepToString(matrix0).replace("],", "\n").replace(",", "\t").replaceAll("(\\[| \\[|])", ""));
        int[][] matrix1 = init();
        setZeroes1(matrix1);
        System.out.println("setZeroes1：\n" + Arrays.deepToString(matrix1).replace("],", "\n").replace(",", "\t").replaceAll("(\\[| \\[|])", ""));
        int[][] matrix2 = init();
        setZeroes2(matrix2);
        System.out.println("setZeroes2：\n" + Arrays.deepToString(matrix2).replace("],", "\n").replace(",", "\t").replaceAll("(\\[| \\[|])", ""));
    }

    /**
     * 使用数组标记
     * <p>
     * 我们首先遍历该数组一次，如果某个元素为 00，那么就将该元素所在的行和列所对应标记数组的位置置为 true。
     * 最后我们再次遍历该数组，用标记数组更新原数组即可。
     *
     * @param matrix 矩阵
     */
    public static void setZeroes0(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
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
     * 使用两个标记变量
     * 我们可以用矩阵的第一行和第一列代替方法一中的两个标记数组，以达到 O(1)O(1) 的额外空间。
     * 但这样会导致原数组的第一行和第一列被修改，无法记录它们是否原本包含 00。
     * 因此我们需要额外使用两个标记变量分别记录第一行和第一列是否原本包含 00。
     * <p>
     * 在实际代码中，我们首先预处理出两个标记变量，接着使用其他行与列去处理第一行与第一列，
     * 然后反过来使用第一行与第一列去更新其他行与列，
     * 最后使用两个标记变量更新第一行与第一列即可。
     *
     * @param matrix 矩阵
     */
    public static void setZeroes1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean flagCol0 = false;
        boolean flagRow0 = false;
        // 先记录第一列和第一行是否有零值，后面会修改第一行和第一列的值
        for (int[] ints : matrix) {
            if (ints[0] == 0) {
                flagCol0 = true;
                break;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                flagRow0 = true;
                break;
            }
        }
        // 如果出现零值，修改第一列和第一行对应位置的数据为零值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 如果第一列或第一行出现零值，将对应的行列元素设置为零值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 如果第一列或者第一行有零值，设置对应的行列所有元素为零值
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
     * 使用一个标记变量
     * 只使用一个标记变量记录第一列是否原本存在 00。
     * 这样，第一列的第一个元素即可以标记第一行是否出现 00。
     * 但为了防止每一列的第一个元素被提前更新，我们需要从最后一行开始，倒序地处理矩阵元素。
     *
     * @param matrix 矩阵
     */
    public static void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean flagCol0 = false;
        //这里主要处理第一列的情况
        for (int i = 0; i < m; i++) {
            // 只要第一列中某个位置出现了0，则第一列最后就会被置0
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
            // 判断每一行是否出现了0，有则将该该元素对应的行和列位置设置为0
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // 将第一行是否有0存到matrx[0][0]里了
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            // 根据第一列和第一行的是否为零值，设置其行或列元素的零值
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            // 如果初始矩阵第一列有零值，则设置第一列为零值
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }
    }

    private static int[][] init() {
        return new int[][]{{1, 2, 0, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}, {0, 18, 19, 20}};
    }
}