package heart;

import javax.swing.*;
import java.awt.*;
/**
 * Heart2
 *
 * @author Jack Lee
 * @since 2021-07-08
 **/
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class Heart2 extends JFrame {
    private static final long serialVersionUID = -1284128891908775645L;

    /**
     * 定义加载窗口大小
     */
    public static final int GAME_WIDTH = 500;

    public static final int GAME_HEIGHT = 500;
    /**
     * 获取屏幕窗口大小
     */
    public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    public Heart2() {
        // 设置窗口标题
        this.setTitle("心形曲线");
        // 设置窗口初始位置
        this.setLocation((WIDTH - GAME_WIDTH) / 2, (HEIGHT - GAME_HEIGHT) / 2);
        // 设置窗口大小
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        // 设置背景色
        this.setBackground(Color.BLACK);
        // 设置窗口关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置窗口显示
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        double x;
        double y;
        double r;
        Image offScreen = createImage(GAME_WIDTH, GAME_HEIGHT);
        Graphics drawOffScreen = offScreen.getGraphics();
        for (int i = 0; i < 90; i++) {
            for (int j = 0; j < 90; j++) {
                r = Math.PI / 45 * i * (1 - Math.sin(Math.PI / 45 * j)) * 18;
                x = r * Math.cos(Math.PI / 45 * j) * Math.sin(Math.PI / 45 * i) + GAME_WIDTH / 2.0d;
                y = -r * Math.sin(Math.PI / 45 * j) + GAME_HEIGHT / 4.0d;
                //设置画笔颜色
                drawOffScreen.setColor(Color.PINK);
                // 绘制椭圆
                drawOffScreen.fillOval((int) x, (int) y, 2, 2);
            }
            // 生成图片
            g.drawImage(offScreen, 0, 0, this);
        }
    }

    public static void main(String[] args) {
        Heart2 demo = new Heart2();
        demo.setVisible(true);
    }
}