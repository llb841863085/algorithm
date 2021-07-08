import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 内存分配
 * 总内存：100
 * 操作数：2~100
 * 分配内存：REQUEST=20，成功返回内存首地址；失败返回error；分配内存为0也返回error；分配地址从小到大，释放后可再次分配
 * 释放内存：RELEASE=20，只能释放已占有的内存；整片释放，释放不存在的内存返回error
 *
 * @author Jack Lee
 * @since 2021-06-20
 **/
public class MemRequestAndRelease {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        // 已分配
        Map<Integer, Integer> usedMap = new HashMap<>(128);
        // 未分配
        Map<Integer, Integer> availableMap = new HashMap<>(128);
        availableMap.put(0, 100);
        for (int i = 0; i < count; i++) {
            String command = in.nextLine();
            boolean check = command == null || (!command.startsWith("REQUEST=") && !command.startsWith("RELEASE="));
            if (check) {
                System.out.println("error");
                continue;
            }
            if (command.startsWith("REQUEST=")) {
                int size = Integer.parseInt(command.split("=")[1]);
                if (size == 0) {
                    System.out.println("error");
                }
                boolean result = false;
                for (Map.Entry<Integer, Integer> entry : availableMap.entrySet()) {
                    int key = entry.getKey();
                    int value = entry.getValue();
                    if (value == size) {
                        System.out.println(key);
                        availableMap.remove(key);
                        usedMap.put(key, value);
                        result = true;
                    } else if (value > size) {
                        System.out.println(key);
                        availableMap.remove(key);
                        availableMap.put(key + size, value - size);
                        usedMap.put(key, value);
                        result = true;
                    }
                    if (result) {
                        break;
                    }
                }
                if (!result) {
                    System.out.println("error");
                }
            } else if (command.startsWith("RELEASE=")) {
                int index = Integer.parseInt(command.split("=")[1]);
                if (!usedMap.containsKey(index)) {
                    System.out.println("error");
                }
                for (Map.Entry<Integer, Integer> entry : usedMap.entrySet()) {
                    int key = entry.getKey();
                    int value = entry.getValue();
                    if (key == index) {
                        usedMap.remove(key);
                        // 合并连续内存
                        if (availableMap.containsKey(key + value)) {
                            availableMap.put(key, value + availableMap.get(key + value));
                            availableMap.remove(key + value);
                        } else {
                            availableMap.put(key, value);
                        }
                    }
                }
            }
        }
    }
}