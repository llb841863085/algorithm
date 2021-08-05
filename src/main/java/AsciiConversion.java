import java.util.Arrays;

/**
 * 与进制有关的两道题
 *
 * @author Jack Lee
 * @since 2021-06-20
 **/
public class AsciiConversion {
    /**
     * 因为要考虑到 余数 > 9 的情况，2<=N<=16.
     */
    private static final String[] F = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(plusOne(new int[]{4, 3, 2, 1})));
        System.out.println(Arrays.toString(plusOne(new int[]{0})));
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 3, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9, 9})));

        System.out.println(solve(1234, 15));
        System.out.println(solve(4321, 13));
    }

    /**
     * leetcode66题：加一
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位，数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * 示例 1：
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     * 解释：输入数组表示数字 123。
     * 示例 2：
     * 输入：digits = [4,3,2,1]
     * 输出：[4,3,2,2]
     * 解释：输入数组表示数字 4321。
     * 示例 3：
     * 输入：digits = [0]
     * 输出：[1]
     * 限制条件：
     * 1 <= digits.length <= 100
     * 0 <= digits[i] <= 9
     *
     * @param digits 数字数组
     * @return 加一后的数字数组
     */
    public static int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            // 先加1
            digits[i]++;
            // 得到下标i的值，对10取模
            digits[i] %= 10;
            // 如果不为0，表示加1前不是9，直接返回数组；否则需要进位，继续执行判断。
            if (digits[i] != 0) {
                return digits;
            }
        }
        // 如果执行到此处，表示元素组所有的位置都是9，需要进位
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 进制转换
     * 1、定义大小为16的数组F，保存的是2到16的各个进制的值对应的标记，这样赋值时只计算下标，不必考虑不同进制的转换关系了
     * 2、使用StringBuffer完成数组转置等功能，如果不记得这个方法，工作量直接飙升
     * 3、通过一个flag来判断正数还是负数
     *
     * @param m 十进制数M，M是32位整数
     * @param n 转换的进制数N，2<=n<=16
     * @return 转换后的字符串
     */
    public static String solve(int m, int n) {
        boolean flag = false;
        if (m < 0) {
            flag = true;
            m *= -1;
        }
        StringBuilder sb = new StringBuilder();
        int temp;
        while (m != 0) {
            temp = m % n;
            //技巧一：通过数组F[]解决了大量繁琐的不同进制之间映射的问题
            sb.append(F[temp]);
            m = m / n;
        }
        //技巧二：使用StringBuffer的reverse()方法，让原本麻烦的转置瞬间美好
        sb.reverse();
        //技巧三：最后处理正负，不要从一开始就揉在一起。
        return (flag ? "-" : "") + sb;
    }
}
