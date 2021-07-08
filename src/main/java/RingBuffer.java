/**
 * 环形缓冲器
 * 满和空的解决方法
 * （1）总是保持一个存储单元为空
 * （2）使用数据计数 [本类使用]
 * （3）镜像指示位
 *
 * @author Jack Lee
 * @since 2021-06-29
 **/
public class RingBuffer {
    public static void main(String[] args) {
        RingBuffer ringBuffer = new RingBuffer(5);
        System.out.println(ringBuffer.put("0"));
        System.out.println(ringBuffer.put("1"));
        System.out.println(ringBuffer.put("2"));
        System.out.println(ringBuffer.put("3"));
        System.out.println(ringBuffer.put("4"));
        System.out.println(ringBuffer.put("5"));
        System.out.println(ringBuffer.take());
        System.out.println(ringBuffer.take());
        System.out.println(ringBuffer.put("5"));
        ringBuffer.printRingBuffer();
        System.out.println(ringBuffer.take());
        System.out.println(ringBuffer.take());
        System.out.println(ringBuffer.take());
        System.out.println(ringBuffer.take());
        System.out.println(ringBuffer.take());
        ringBuffer.printRingBuffer();
        System.out.println(ringBuffer.put("5"));
        ringBuffer.printRingBuffer();
    }

    /**
     * ringBuffer的总长度，最大能够存储的成员数
     */
    private final int length;

    /**
     * ringBuffer内部存储的现有的成员数
     */
    private int size;

    /**
     * 写指针,代表下一次写操作执行的位置
     */
    private int writePos;

    /**
     * 读指针,代表下一次读操作执行的位置
     */
    private int readPos;

    /**
     * 放置成员的循环数组，长度为length
     */
    private final Object[] list;


    /**
     * ringBuffer的构造函数<br>
     * 初始化各种参数和object数组
     *
     * @param length ringBuffer的总长度，最大能够存储的成员数
     */
    public RingBuffer(int length) {
        this.length = length;
        list = new Object[length];
        size = 0;
        writePos = 0;
        readPos = 0;
    }

    /**
     * 显示ringBuffer已满，是否还能插入新的元素
     *
     * @return 如果数组已满，不能插入，返回true<br>如果能插入，返回false
     */
    public boolean isFull() {
        return size == length;
    }

    /**
     * 显示ringBuffer是否为空，是否还能取出元素
     *
     * @return 如果数组为空，不能取出元素，返回true <br> 如果能取出，返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在ringBuffer中插入一个元素，在写指针处插入元素
     *
     * @param object 被插入的元素
     * @return 如果成功插入，返回true<br> 如果队列已满，返回false
     */
    public boolean put(Object object) {
        if (isFull()) {
            //如果队列已满
            return false;
        }
        if (writePos == length - 1) {
            //如果这次写入的位置在队列的最后一个元素
            list[writePos] = object;
            //那么下次写入的位置为0
            writePos = 0;
        } else {
            //这次写入的位置不是队列最后一个元素，那么现在writePos处写入，然后++
            list[writePos++] = object;
        }
        //ringBuffer中的成员数增加
        //size的处理在最后操作，防止size增加了，读操作却没有读到
        size++;
        return true;
    }

    /**
     * 从ringBuffer中取出一个元素
     *
     * @return 如果队列为空，返回null <br> 否则，返回读指针对应的元素
     */
    public Object take() {
        if (isEmpty()) {
            return null;
        }
        Object result;
        if (readPos == length - 1) {
            //如果这次取出的位置在队列的最后一个元素
            result = list[readPos];
            //将对于位置的元素清空
            list[readPos] = null;
            readPos = 0;
        } else {
            result = list[readPos];
            list[readPos++] = null;
        }
        size--;
        return result;
    }

    public void printRingBuffer() {
        StringBuilder sb = new StringBuilder("开始打印环形缓冲区");
        sb.append("length=").append(length).append(", size=").append(size).append(", writePos=")
            .append(writePos).append(", readPos=").append(readPos);
        sb.append("环形缓冲区：");
        for (int i = 0; i < length; i++) {
            sb.append(i).append("=").append(list[i]).append("\t");
        }
        sb.append("\n打印环形缓冲区结束");
        System.out.println(sb.toString());
    }
}
