package string;

import java.util.Scanner;

/**
 * 求字符串中数字的最小和
 *
 * aa12bb-34dd12 ==> 1+2-34+1+2=-28
 *
 * @author Jack Lee
 * @since 2021-06-20
 **/
public class StrMinSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String src = in.nextLine();
        if (src == null) {
            System.out.print(0);
            System.exit(0);
        }
        int sum = 0;
        int currentNum = 0;
        // 正负标记，默认正号
        boolean flag = true;
        for (int i = 0; i < src.length(); i++) {
            char ch = src.charAt(i);
            //字母或空格
            boolean check = (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == ' ' || ch == '+';
            if (check) {
                sum += -1 * currentNum;
                flag = true;
                currentNum = 0;
            }
            // 数字
            else if (ch >= '0' && ch <= '9') {
                if (flag) {
                    sum += ch - 48;
                } else {
                    currentNum = currentNum * 10 + ch - 48;
                }
            }
            // 负号
            else {
                sum += -1 * currentNum;
                flag = false;
                currentNum = 0;
            }
        }
        System.out.println(sum + -1 * currentNum);
    }
}