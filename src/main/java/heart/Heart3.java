package heart;

/**
 * Heart3
 *
 * @author Jack Lee
 * @since 2021-07-08
 **/
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class Heart3 {
    public static void main(String[] args) {
        float x;
        float y;
        float a;
        // 这里的参数皆可以调，条件就是看着顺眼
        for (y = 1.3f; y > -1.1f; y -= 0.06f) {
            for (x = -1.2f; x <= 1.2f; x += 0.025f) {
                a = x * x + y * y - 1;
                System.out.print(a * a * a - x * x * y * y * y <= 0.0f ? ' ' : "$");
            }
            System.out.println();
        }
    }
}
