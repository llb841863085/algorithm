package array;

import java.util.Arrays;

/**
 * 合并有序数组的两道题
 *
 * @author Jack Lee
 * @since 2021-06-20
 **/
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class ArrayMerge {

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < 5; i++) {
            arr[i] = i;
        }
        int[] arr0 = {1, 3, 5, 7, 9};
        merge(arr, 5, arr0, arr0.length);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = {1, 3, 5, 6};
        int[] arr2 = {1, 2, 5, 7};
        int[] arr3 = {3, 6, 9, 11};
        int[] arr4 = {5, 7, 13, 17};

        int[] merge1 = merge(arr1, arr2);
        int[] merge2 = merge(arr3, arr4);
        int[] merge = merge(merge1, merge2);
        System.out.println(Arrays.toString(merge));
    }

    /**
     * leetcode 88题 合并两个有序数组
     * 给定两个有序的整数数组A和B，元素个数分别为m和n，将B合并到A，假设A的空间足够。
     * <p>
     * 这个问题的关键点是将B合并到A的时候，A是有数据的，不能强行插入。如果从前向后插入，数组A后面的元素为反复移动，代价比较高。
     * 比较好的方式是从A数组的后面开始逐步向前移动。
     *
     * @param a 源数组A
     * @param m 源数组长度m
     * @param b 源数组B
     * @param n 源数组长度n
     */
    public static void merge(int[] a, int m, int[] b, int n) {
        int indexA = m - 1;
        int indexB = n - 1;
        int index = m + n - 1;
        // 从后往前将ab中最大的值放到a数组的末尾
        while (indexA >= 0 && indexB >= 0) {
            a[index--] = a[indexA] >= b[indexB] ? a[indexA--] : b[indexB--];
        }
        // 将b中未插入的数组插入到a的首部
        while (indexB >= 0) {
            a[index--] = b[indexB--];
        }
    }

    /**
     * 合并两个数组
     * 合并n个有序数组时，通过两两合并来完成。
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return 合并后的数组
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        int length1 = arr1.length;
        int length2 = arr2.length;

        int[] arr = new int[length1 + length2];
        // arr1的指针
        int j = 0;
        // arr2的指针
        int k = 0;
        // arr的指针
        int i = 0;
        while (j < length1 && k < length2) {
            if (arr1[j] <= arr2[k]) {
                arr[i] = arr1[j];
                i++;
                j++;
            } else {
                arr[i] = arr2[k];
                i++;
                k++;
            }
        }

        //把还没有进行合并的元素直接添加到新数组的后面
        while (j < length1) {
            arr[i] = arr1[j];
            i++;
            j++;
        }
        while (k < length2) {
            arr[i] = arr2[k];
            i++;
            k++;
        }

        return arr;
    }
}
