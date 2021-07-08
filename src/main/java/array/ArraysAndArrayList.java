package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Java中的Array、Arrays和ArrayList
 *
 * @author Jack Lee
 * @since 2021-06-21
 **/
public class ArraysAndArrayList {

    public static void main(String[] args) {
        // ArrayList继承自 AbstractList，实现了 List 接口。
        // 底层基于数组实现容量大小动态变化,允许 null 的存在。
        // 同时还实现了 RandomAccess、Cloneable、Serializable 接口，所以ArrayList 是支持快速访问、复制、序列化的。
        // 支持动态扩容等特性
        List<Integer> list = new ArrayList<>(1);
        list.add(1);
        list.add(2);
        System.out.println(list);
        Integer[] array = list.toArray(new Integer[0]);
        System.out.println(Arrays.toString(array));

        // Array.sort(Object[] array) 对数组按照升序排序
        int[] nums = {2, 5, 0, 4, 6, -10};
        Arrays.sort(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();
        // Arrays.sort(Object[] array, int from, int to) 对数组元素指定范围进行排序,从起始位置到结束位置，取头不取尾
        nums = new int[]{2, 5, 0, 4, 1, -10};
        //对前四位元素进行排序
        Arrays.sort(nums, 0, 4);
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();
        // Arrays.fill(Object[] array,Object object) 可以为数组元素填充相同的值
        nums = new int[]{2, 5, 0, 4, 1, -10};
        Arrays.fill(nums, 1);
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();
        // Arrays.fill(Object[] array,int from,int to,Object object) 对数组的部分元素填充一个值,从起始位置到结束位置，取头不取尾
        nums = new int[]{2, 5, 0, 4, 1, -10};
        //对数组元素下标2到4的元素赋值为3
        Arrays.fill(nums, 2, 5, 3);
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();
        // Arrays.toString(Object[] array) 返回数组的字符串形式
        nums = new int[]{2, 5, 0, 4, 1, -10};
        System.out.println(Arrays.toString(nums));
        // Arrays.deepToString(Object arrays) 返回多维数组的字符串形式
        int[][] nums2 = {{1, 2}, {3, 4}};
        System.out.println(Arrays.deepToString(nums2));
    }

}
