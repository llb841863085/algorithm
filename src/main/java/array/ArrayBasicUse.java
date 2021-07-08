package array;

import java.util.Arrays;

/**
 * 数组的基本使用
 *
 * @author Jack Lee
 * @since 2021-06-20
 **/
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class ArrayBasicUse {
    public static void main(String[] args) {
        // 创建和初始化
        int[] arrInit = new int[10];
        for (int i = 0; i < arrInit.length; i++) {
            arrInit[i] = i;
        }
        System.out.println(Arrays.toString(arrInit));
        int[] arrInit2 = new int[]{0, 1, 2, 3, 5, 6, 8};
        System.out.println(Arrays.toString(arrInit2));

        // 查找
        int[] arrFind = new int[10];
        int sizeFind = 10;
        for (int i = 0; i < sizeFind; i++) {
            arrFind[i] = i;
        }
        int a = findByIndex(arrFind, sizeFind, 5);
        System.out.println("5号位置的元素为：" + a);
        int b = findByElement(arrFind, 10, 7);
        System.out.println("元素7所在的位置为：" + b);

        // 添加
        int[] arrAdd = new int[10];
        int sizeAdd = 9;
        for (int i = 0; i < sizeAdd; i++) {
            arrAdd[i] = i;
        }
        add(arrAdd, 9, 5, 55);
        System.out.println(Arrays.toString(arrAdd));
        System.out.println("插入的位置：" + add(arrAdd, sizeAdd, -9));
        for (int i = 0; i < 10; i++) {
            System.out.print(arrAdd[i] + " ");
        }
        System.out.println();

        // 删除
        int[] arrDelete = new int[10];
        int sizeDelete = 9;
        for (int i = 0; i < sizeDelete; i++) {
            arrDelete[i] = i;
        }
        System.out.println("删除的元素为：" + remove(arrDelete, sizeDelete, 3));
        for (int i = 0; i < 10; i++) {
            System.out.print(arrDelete[i] + " ");
        }
        System.out.println();
        int[] arrDelete2 = new int[10];
        int sizeDelete2 = 10;
        for (int i = 0; i < sizeDelete2; i++) {
            arrDelete2[i] = i;
        }
        removeElement(arrDelete2, 10, 4);
        for (int i = 0; i < 9; i++) {
            System.out.print(arrDelete2[i] + " ");
        }
    }

    /**
     * 根据索引位置查找
     *
     * @param arr   数组
     * @param size  已经存放的元素容量
     * @param index 查找的位置
     * @return 查找到的元素
     */
    public static int findByIndex(int[] arr, int size, int index) {
        if (index < 0 || index > size - 1) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index < size.");
        }
        return arr[index];
    }

    /**
     * 查找目标元素所在的位置
     *
     * @param arr  数组
     * @param size 已经存放的元素容量
     * @param key  待查找的元素
     * @return 查找到的元素索引
     */
    public static int findByElement(int[] arr, int size, int key) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 在指定位置添加元素
     *
     * @param arr   数组
     * @param size  已经存放元素的数量
     * @param index 插入的位置
     * @param key   插入的元素
     */
    public static void add(int[] arr, int size, int index, int key) {
        if (size >= arr.length) {
            throw new IllegalArgumentException("Add failed. array is full.");
        }
        if (index < 0 || index > arr.length) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index < arr.length.");
        }
        if (size - index >= 0) {
            System.arraycopy(arr, index, arr, index + 1, size - index);
        }
        arr[index] = key;
        size++;
        System.out.println("插入后的大小：" + size);
    }


    /**
     * 将给定的元素插入到有序数组的对应位置中
     *
     * @param arr     数组
     * @param size    数组已经存储的元素数量
     * @param element 待插入的元素
     * @return 插入的位置
     */
    public static int add(int[] arr, int size, int element) {
        if (size == arr.length) {
            throw new IllegalArgumentException("Add failed. Array is full.");
        }
        int index = -1;
        //找到新元素的插入位置
        for (int i = 0; i < size; i++) {
            if (element < arr[i]) {
                index = i;
                break;
            }
        }
        //元素后移
        if (arr.length - 1 - index >= 0) {
            System.arraycopy(arr, index, arr, index + 1, arr.length - 1 - index);
        }
        //插入数据
        arr[index] = element;
        return index;
    }

    /**
     * 根据索引位置删除一个元素
     *
     * @param arr   数组
     * @param size  数组元素数量
     * @param index 删除位置
     * @return 删除的元素
     */
    public static int remove(int[] arr, int size, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        int ret = arr[index];
        if (size - index + 1 >= 0) {
            System.arraycopy(arr, index + 1, arr, index + 1 - 1, size - index + 1);
        }
        size--;
        System.out.println("删除后的容量为：" + size);
        return ret;
    }

    /**
     * 从数组中删除元素
     *
     * @param arr  数组
     * @param size 数组已有元素数量
     * @param key  待删除的元素
     */
    public static void removeElement(int[] arr, int size, int key) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arr[i] == key) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            if (size - index + 1 >= 0) {
                System.arraycopy(arr, index + 1, arr, index + 1 - 1, size - index - 1);
            }
            size--;
            System.out.println("删除后的容量为：" + size);
        }
    }
}
