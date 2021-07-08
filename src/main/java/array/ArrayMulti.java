package array;

import java.util.Arrays;

/**
 * java中的二维数组
 *
 * @author Jack Lee
 * @since 2021-06-23
 **/
public class ArrayMulti {
    public static void main(String[] args) {
        printSplit();
        testInit();
        printSplit();
        int[][] intA = {{1, 2}, {2, 3, 4}, {3, 4, 5, 6}};
        testSearch(intA);
        printSplit();
        testSearch2(intA);
        printSplit();
        testSearch3(intA);
        printSplit();
        testSaveObjectArray();
        printSplit();
        testArrayCopy();
        printSplit();
        testArrayCopy2();
        printSplit();
        testArrayCopy3();
        printSplit();
    }

    private static void printSplit() {
        System.out.println("\n=========================");
    }

    private static void printSplit2() {
        System.out.println("\n--------------------------");
    }

    private static void testArrayCopy() {
        // 地址的拷贝
        int[] array = {11, 22, 33, 44};
        int[] arrayB = new int[5];
        System.out.println("数组拷贝之前：" + Arrays.toString(arrayB));
        // 两个数组指向同一内存
        arrayB = array;
        System.out.println("数组拷贝之后：" + Arrays.toString(arrayB));
    }

    private static void testArrayCopy2() {
        // 地址的拷贝
        int[] array = {11, 22, 33, 44};
        int[] arrayB = new int[5];
        System.out.println("数组拷贝之前：" + Arrays.toString(arrayB));
        // 值的拷贝
        arrayB[0] = array[0];
        System.out.println("数组拷贝之后：" + Arrays.toString(arrayB));
    }

    private static void testArrayCopy3() {
        // 地址的拷贝
        int[] array = {11, 22, 33, 44};
        int[] arrayB = new int[5];
        // System.arraycopy
        System.out.println("数组拷贝之前：" + Arrays.toString(arrayB));
        //0       0       0       0       0
        //拷贝
        System.arraycopy(array, 0, arrayB, 1, 2);

        System.out.println("数组拷贝之后：" + Arrays.toString(arrayB));
        //0       11       22       0       0
    }

    /**
     * 对象数组的存储
     */
    private static void testSaveObjectArray() {
        //创建一个Person类型的数组，用于存储3个Person类型的对象
        Person[] pers = new Person[3];
        //创建Person类型的对象
        Person p1 = new Person("张三", 19, "男");
        //将p1对象存储到Person类型的数组中
        pers[0] = p1;

        pers[1] = new Person("李四", 20, "女");
        pers[2] = new Person("王五", 28, "男");

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < pers.length; i++) {
            System.out.println(pers[i]);
        }
    }

    /**
     * (1)普通for循环
     *
     * @param intA 数组
     */
    private static void testSearch(int[][] intA) {
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < intA.length; i++) {
            for (int j = 0; j < intA[i].length; j++) {
                System.out.print(intA[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * (2)加强for循环
     *
     * @param intA 数组
     */
    private static void testSearch2(int[][] intA) {
        for (int[] arr : intA) {
            for (int i : arr) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }

    /**
     * (3)普通与加强for循环的混搭
     *
     * @param intA 数组
     */
    private static void testSearch3(int[][] intA) {
        //加强for
        for (int[] arr : intA) {
            //noinspection ForLoopReplaceableByForEach
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + "\t");
            }
            System.out.println();
        }
        printSplit2();
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < intA.length; i++) {
            //加强for
            for (int j : intA[i]) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 初始化
     */
    private static void testInit() {
        //二维数组不是规则的矩阵
        int[][] intA = {{1, 2}, {2, 3, 4}, {3, 4, 5, 6}};
        //打印出来的都是地址
        System.out.println("intA: " + Arrays.toString(intA));
        //声明一个二维数组，用于存储3个一维数组，每一个一维数据存多少个数组，不知道
        int[][] intB = new int[3][];
        intB[0] = new int[3];
        intB[1] = new int[]{1, 2, 3, 4};
        intB[2] = new int[2];
        System.out.println("intB: " + Arrays.toString(intB));
        //声明一个二维数组，存储三个一维数组，每个一维数组的长度为4
        int[][] intC = new int[3][4];
        intC[0] = new int[]{1, 2, 3};
        System.out.println("intC[0]" + Arrays.toString(intC[0]));
        intC[1] = new int[]{11, 12, 13, 14};
        System.out.println("intC[1]" + Arrays.toString(intC[1]));
        // Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 4 "intC[2][4] = 5;"
        // 重新赋值，所以不会报错。  为啥定义的长度是4，这里设置了5个值都没报错呢？？？？？
        System.out.println("[3][4]" + Arrays.toString(intC[2]));
        intC[2] = new int[]{21, 22, 23, 24, 25};
        System.out.println("intC[2]" + Arrays.toString(intC[2]));
        System.out.println("intC: " + Arrays.toString(intC));
    }

    static class Person {
        private final String name;
        private final int age;
        private final String gender;

        public Person(String name, int age, String gender) {
            super();
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        //重写toString以打印想要的输出 否则只会打印对象的内存地址
        @Override
        public String toString() {
            return name + "\t" + age + "\t" + gender;
        }
    }
}
