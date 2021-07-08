package matrix;

/**
 * 二维数组选路径
 * 一个机器人在m×n大小的地图的左上角（起点）。
 * 机器人每次向下或向右移动。
 * 机器人要到达地图的右下角（终点）。
 * 可以有多少种不同的路径从起点走到终点？
 * 备注：m和n小于等于100,并保证计算结果在int范围内。
 * 起点：（0，0） 终点：（m，n）
 *
 * @author Jack Lee
 * @since 2021-06-29
 **/
public class MatrixUniqePath {
    public static void main(String[] args) {
        System.out.println(getUniquePaths(1, 1));
        System.out.println(getUniquePaths(2, 2));
        System.out.println(getUniquePaths(3, 3));
        System.out.println(getUniquePaths(3, 4));
        System.out.println(getUniquePaths(2, 4));
        System.out.println(getUniquePaths(5, 10));
    }

    public static int getUniquePaths(int m, int n) {
        return search(m, n);
    }

    public static int search(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        return search(m - 1, n) + search(m, n - 1);
    }
}
