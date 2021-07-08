package heart;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * 画一个爱心
 * (x^2+9/4*y^2+z^2-1)^3-x^2*z^3-9/80*y^2*z^3==0
 * @author Jack Lee
 * @since 2021-06-24
 **/
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class Heart {
    public static void main(String[] args) {
        double x;
        double y;
        char[] s = new char[]{'I', ' ', 'l', 'o', 'v', 'e', ' ', 'y', 'o', 'u', '!'};
        int index;
        for (y = 1.3f; y > -1.1f; y -= 0.06f) {
            index = 0;
            for (x = -1.1f; x <= 1.1f; x += 0.025f) {
                double result = x * x + pow((5.0 * y / 4.0 - sqrt(abs(x))), 2);
                if (result <= 1) {
                    System.out.print((s[index]));
                    index = (index + 1) % 11;
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println(" ");
        }
    }
}