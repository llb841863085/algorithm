package array;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList中的subList方法
 * subList返回的是List的一个视图（SubList）：new SubList(this, 0, fromIndex, toIndex)
 *
 * @author Jack Lee
 * @since 2021-06-28
 **/
public class ArrayListSubList {
    public static void main(String[] args) {
        // 基本使用
        List<String> bookList = new ArrayList<>();
        bookList.add("面上头条快手阿里");
        bookList.add("升职加薪");
        bookList.add("当上CTO");
        bookList.add("迎娶白富美");
        bookList.add("走向人生巅峰");
        // subList返回的是List中索引从fromIndex（包含）到toIndex（不包含）的元素集合
        List<String> lqcBookList = bookList.subList(3, 5);
        System.out.println(bookList);
        System.out.println(lqcBookList);
        // 因为SubList是List的一个视图，指向的内存地址一样，所以修改SubList里面的记录会直接影响到List
        lqcBookList.set(0, "AAABBB");
        System.out.println(bookList);
        System.out.println(lqcBookList);

        lqcBookList.add("cccddd");
        System.out.println(bookList);
        System.out.println(lqcBookList);

        lqcBookList.add(1, "000111");
        System.out.println(bookList);
        System.out.println(lqcBookList);

        lqcBookList.remove(1);
        System.out.println(bookList);
        System.out.println(lqcBookList);

        modifyElement(bookList);

        addElement(bookList);
    }

    /**
     * 修改节点数据，正常执行
     *
     * @param subList 子列表
     */
    private static void modifyElement(List<String> subList) {
        // 修改List
        List<String> lBookList = subList.subList(2, 5);
        System.out.println("修改前：");
        System.out.println("bookList：" + subList);
        System.out.println("lBookList：" + lBookList);
        // 修改原集合的值
        subList.set(2, "CEO也行");
        System.out.println("修改后：");
        System.out.println("bookList：" + subList);
        System.out.println("lBookList：" + lBookList);
    }

    /**
     * 增加节点会报异常
     *
     * @param subList 子列表
     */
    private static void addElement(List<String> subList) {
        List<String> lBookList = subList.subList(2, 5);
        System.out.println("修改前：");
        System.out.println("bookList：" + subList);
        System.out.println("lBookList：" + lBookList);
        //原始集合添加一项
        //noinspection AlibabaConcurrentExceptionWithModifyOriginSubList
        subList.add("想想有点小激动");
        System.out.println("修改后：");
        System.out.println("bookList：" + subList);
        // 会抛异常：Exception in thread "main" java.util.ConcurrentModificationException
        System.out.println("lBookList：" + lBookList);
    }
}
