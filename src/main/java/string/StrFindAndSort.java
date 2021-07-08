package string;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 字符串s2中出现的字符串s1中的字符的字典排序
 *
 * s1=cfba
 * s2=gfabexf
 * 输出：abf
 *
 * @author Jack Lee
 * @since 2021-06-20
 **/
public class StrFindAndSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        if (s1 == null || s2 == null) {
            System.out.print("");
            System.exit(0);
        }
        // 对s1排序
        char[] chs1 = s1.toCharArray();
        Arrays.sort(chs1);
        StringBuilder result = new StringBuilder();
        for (char c : chs1) {
            if (result.toString().indexOf(c) < 0 && s2.indexOf(c) > -1) {
                result.append(c);
            }
        }
        System.out.println(result);
    }
}