package array;

/**
 * 动态数组
 * <p>
 * 动态数组的特点是：可随机存储，可以自动调整大小等。动态扩容和缩容是其最重要的特征。
 *
 * @author Jack Lee
 * @since 2021-06-29
 **/
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class ArrayCustom<E> {
    public static void main(String[] args) {
        ArrayCustom<Integer> arr = new ArrayCustom<>();
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);
        System.out.println("arr.getSize()：" + arr.getSize());
        System.out.println("arr.getCapacity()：" + arr.getCapacity());
        System.out.println("arr.isEmpty()：" + arr.isEmpty());
        System.out.println("arr.get(1)：" + arr.get(1));

        arr.set(5, 55);
        System.out.println("arr.set(5, 55)：" + arr);
        System.out.println("arr.contains(55)：" + arr.contains(55));

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        System.out.println(arr.removeFirst());
        System.out.println(arr);

        System.out.println(arr.removeLast());
        System.out.println(arr);
    }


    private E[] data;
    private int size;

    /**
     * 构造函数，传入数组的容量capacity构造Array
     */
    public ArrayCustom(int capacity) {
        //noinspection unchecked
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参数的构造函数，默认数组的容量capacity=10
     */
    public ArrayCustom() {
        this(10);
    }

    /**
     * 获取数组中的元素个数
     *
     * @return 元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组的容量
     *
     * @return 数组容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 返回数组是否为空
     *
     * @return 是否为空数组
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向所有元素后添加一个新元素,O(1)
     *
     * @param e 需添加的元素
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 在所有元素前添加一个新元素,O(1)
     *
     * @param e 需添加的元素
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在第index个位置插入一个新元素e,O(n/2)=O(n)
     *
     * @param index 添加位置
     * @param e     添加的元素
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed!,Require index >= 0 and index <= size.");
        }
        if (size == data.length) {
            resize(2 * data.length);
        }
        if (size - index >= 0) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }
        data[index] = e;
        size++;
    }

    /**
     * 获取index索引位置的元素,O(1)
     *
     * @param index 元素的索引
     * @return 查找的元素
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed.Index is illegal.");
        }
        return data[index];
    }

    /**
     * 修改index索引位置的元素为e,O(1)
     *
     * @param index 元素的索引
     * @param e     需设置的元素
     */
    void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed.Index is illegal.");
        }
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素e,O(n)
     *
     * @param e 元素
     * @return 是否包含
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引，如果不存在元素e，则返回-1,O(n)
     *
     * @param e 待查找元素
     * @return 元素的索引
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从数组中删除index位置的元素, 返回删除的元素,O(n)
     *
     * @param index 元素的索引
     * @return 删除的元素
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed.Index is illegal.");
        }
        E ret = data[index];
        if (size - index + 1 >= 0) {
            System.arraycopy(data, index + 1, data, index + 1 - 1, size - index - 1);
        }
        size--;
        //loitering objects != memory leak
        data[size] = null;

        if (size == data.length / 2) {
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 从数组中删除第一个元素, 返回删除的元素
     *
     * @return 删除的元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 从数组中删除最后一个元素, 返回删除的元素
     *
     * @return 删除的元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从数组中删除元素e
     *
     * @param e 待删除的元素
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\t", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(",");
            }
        }
        res.append(']');
        return res.toString();
    }

    /**
     * 将数组空间的容量变成newCapacity大小
     *
     * @param newCapacity 新的容量
     */
    private void resize(int newCapacity) {
        //noinspection unchecked
        E[] newData = (E[]) new Object[newCapacity];
        if (size >= 0) {
            System.arraycopy(data, 0, newData, 0, size);
        }
        data = newData;
    }
}
