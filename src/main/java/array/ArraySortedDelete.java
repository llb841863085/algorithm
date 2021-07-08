package array;

import java.util.Arrays;

/**
 * 删除有序数组的三道题
 *
 * @author Jack Lee
 * @since 2021-06-20
 **/
public class ArraySortedDelete {
    public static void main(String[] args) {
        int[] init = init();
        System.out.println(removeElement(init, 1));
        init = init();
        System.out.println(removeDuplicates1(init));
        init = init();
        System.out.println(removeDuplicates2(init));
    }

    /**
     * leetcode27. 移除元素
     * 给你一个数组 nums1 和一个值 val，你需要原地移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * <p>
     * 要求：不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 示例1：
     * 输入：nums1 = [3,2,2,3], val = 3
     * 输出：2, nums1 = [2,2]
     * 示例2:
     * 输入：nums1 = [0,1,2,2,3,0,4,2], val = 2
     * 输出：5, nums1 = [0,1,4,0,3]
     *
     * @param nums 数组
     * @param val  待删除的元素
     * @return 数组的长度
     */
    public static int removeElement(int[] nums, int val) {
        int[] nums1 = Arrays.copyOf(nums, nums.length);
        int validLength = 0;
        for (int num : nums1) {
            if (num != val) {
                nums1[validLength] = num;
                validLength++;
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOf(nums1, validLength)));
        // 交换移除。
        // 思路就是我们还是从两端开始向中间遍历，left遇到num[i]=val的时候停下来，右侧继续。
        // 当右侧遇到num[j]!=val的位置的时候，将num[j]交换或者直接覆盖num[i]。之后i继续向右走。
        int[] nums2 = Arrays.copyOf(nums, nums.length);
        int currentValidLength = nums1.length;
        int i = 0;
        while (i < currentValidLength) {
            if (nums2[i] == val) {
                nums2[i] = nums2[currentValidLength - 1];
                currentValidLength--;
            } else {
                i++;
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOf(nums2, validLength)));
        return currentValidLength;
    }

    /**
     * leetcode26. 删除有序数组中的重复项
     * <p>
     * 给你一个有序数组 nums ，请你原地删除重复出现的元素，使每个元素只出现一次，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组 并在使用O(1)额外空间的条件下完成。
     * <p>
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2]
     * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为1,2。不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums 数组
     * @return 新长度
     */
    public static int removeDuplicates1(int[] nums) {
        int n = nums.length;
        //j用来标记有效位
        int j = 0;
        // 使用双指针最方便，一个指针负责数组遍历，一个指向有效数组的最后一个位置。当两个指针的值不一样时，才将指向有效位的向下移动。
        for (int i = 0; i < n; i++) {
            if (nums[i] != nums[j]) {
                // 因为元素的位置：有序数组<=有序重复数组
                // 所以可以直接根据索引赋值
                nums[++j] = nums[i];
            }
        }
        return j + 1;
    }

    /**
     * leetcode80. 删除有序数组中的重复项 II
     * 给你一个有序数组 nums ，请你原地删除重复出现的元素，使每个元素最多出现两次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     *
     * @param nums 数组
     * @return 长度
     */
    public static int removeDuplicates2(int[] nums) {
        return process(nums, 2);
    }

    private static int process(int[] nums, int k) {
        int u = 0;
        for (int x : nums) {
            // 下标u-k，标识上一个值对应的索引
            if (u < k || nums[u - k] != x) {
                nums[u++] = x;
            }
        }
        return u;
    }

    private static int[] init() {
        return new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    }
}
