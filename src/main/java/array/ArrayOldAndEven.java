package array;

import java.util.Arrays;

/**
 * 数组中使奇数都在偶数前面的三道题
 *
 * @author Jack Lee
 * @since 2021-06-20
 **/
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class ArrayOldAndEven {
    public static void main(String[] args) {
        int[] init = init();
        System.out.println(Arrays.toString(sortArrayByParity0(init)));
        init = init();
        System.out.println(Arrays.toString(sortArrayByParity1(init)));
        init = init();
        System.out.println(Arrays.toString(sortArrayByParity2(init)));
    }

    /**
     * 不考虑调整后元素的顺序
     *
     * @param nums 数组
     * @return 调整后的结果
     */
    public static int[] sortArrayByParity0(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 找到第一个偶数的下标
            while (nums[left] % 2 != 0 && left < right) {
                left++;
            }
            // 找到最后一个奇数的下标
            while (nums[right] % 2 == 0 && left < right) {
                right--;
            }
            // 交换奇数和偶数的位置
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
        return nums;
    }

    /**
     * 要求调整后的顺序仍与原始数组的顺序一致
     *
     * @param nums 数组
     * @return 调整后的结果
     */
    public static int[] sortArrayByParity1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            // 冒泡排序思想
            for (int j = 0; j < n - 1 - i; ++j) {
                // 相邻元素的四种情况：奇数奇数[不处理]、偶数偶数[不处理]、奇数偶数[不处理]、偶数奇数[交换位置]
                // 左边是偶数，右边是奇数的情况，需要交换位置。
                if ((nums[j] & 1) == 0 && (nums[j + 1] & 1) == 1) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
        return nums;
    }

    /**
     * leetcode的922题. 按奇偶排序数组 II
     * 给定一个非负整数数组A， A中一半整数是奇数，一半整数是偶数。
     * 对数组进行排序，以便当A[i]为奇数时，i也是奇数；当A[i]为偶数时， i也是偶数。
     * 你可以返回任何满足上述条件的数组作为答案。
     * 示例：
     * 输入：[4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     * 提示：
     * 2 <= A.length <= 20000
     * A.length % 2 == 0
     * 0 <= A[i] <= 1000
     * 如果采用双指针方式，与上面的第一题是一个思路，使用两个变量来标记，一个代表奇数位，一个偶数位。遍历到某个位置时，判断奇偶，如果是奇数时奇数标记加2，遇到偶数时，偶数标记位加2.
     *
     * @param nums 数组
     * @return 调整后的结果
     */
    public static int[] sortArrayByParity2(int[] nums) {
        int[] res = new int[nums.length];
        // 偶指针
        int i = 0;
        // 奇指针
        int j = 1;
        for (int num : nums) {
            // 偶数放到偶指针位置，同时偶指针指向下一个偶数下标，即+2
            if (num % 2 == 0) {
                res[i] = num;
                i += 2;
            }
            // 奇数放到奇指针位置，同时奇指针指向下一个奇数下标，即+2
            else {
                res[j] = num;
                j += 2;
            }
        }
        return res;
    }

    private static int[] init() {
        return new int[]{1, 2, 2, 4, 7, 6, 7, 3};
    }
}
