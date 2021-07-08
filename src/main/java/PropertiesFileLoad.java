import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件加载
 *
 * @author Jack Lee
 * @since 2021-06-21
 **/
public class PropertiesFileLoad {
    public static void main(String[] args) {
        Node node = new Node(1);
        // 方法1：获取文件路径，如果配置文件在jar里面的话，使用文件io流读取会提示找不到文件
        URL resourceUrl = node.getClass().getClassLoader().getResource("META-INF/spring.factories");
        if (resourceUrl == null) {
            System.exit(-1);
        }
        System.out.println("Resource：" + resourceUrl);
        //输出：Resource：
        // jar:file:/C:/Users/lilb3/.m2/repository/org/springframework/boot/spring-boot/2.5.1/spring-boot-2.5.1
        // .jar!/META-INF/spring.factories
        // 使用openStream() API来获取输入流
        try (InputStream in = resourceUrl.openStream()) {
            dealWithInputStream(in);
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }

        // 方法2：直接读取配置文件的流
        try (InputStream in = node.getClass().getClassLoader().getResourceAsStream("META-INF/spring.factories")) {
            if (in != null) {
                dealWithInputStream(in);
            }
        } catch (Exception e) {
            System.out.println("异常： " + e.getMessage());
        }

        // 方法3：参看SpringFactoriesLoader.loadSpringFactories()
        try {
            Enumeration<URL> urls = node.getClass().getClassLoader().getResources("META-INF/spring.factories");
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                UrlResource resource = new UrlResource(url);
                System.out.println("SpringFactoriesLoader.loadSpringFactories()：" + resource);
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                for (Map.Entry<?, ?> entry : properties.entrySet()) {
                    String factoryTypeName = ((String) entry.getKey()).trim();
                    String[] factoryImplementationNames =
                        StringUtils.commaDelimitedListToStringArray((String) entry.getValue());
                    for (String factoryImplementationName : factoryImplementationNames) {
                        System.out.println(factoryTypeName + ", " + factoryImplementationName.trim());
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("异常：" + ex.getMessage());
        }
    }

    private static void dealWithInputStream(InputStream in) throws IOException {
        byte[] bytes = new byte[1024];
        int length;
        int size = 0;
        while ((length = in.read(bytes)) != -1) {
            size += length;
            System.out.println("length： " + length + "，current size：" + size);
        }
    }

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
