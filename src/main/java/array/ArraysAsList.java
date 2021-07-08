package array;

import java.util.Arrays;
import java.util.List;

/**
 * 解析《阿里巴巴开发手册》中Arrays.asList的问题
 *
 * @author Jack Lee
 * @since 2021-06-22
 **/
public class ArraysAsList {
    public static void main(String[] args) {
        // 返回的是Arrays的一个内部类ArrayList
        List<Integer> statusList = Arrays.asList(1, 2);
        System.out.println(statusList);
        System.out.println(statusList.contains(1));
        System.out.println(statusList.contains(3));
        // 会抛出：java.lang.UnsupportedOperationException，没有实现add()方法
        //noinspection AlibabaUnsupportedExceptionWithModifyAsList
        statusList.add(3);
        System.out.println(statusList.contains(3));
    }
}
