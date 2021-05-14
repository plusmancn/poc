package naming;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author plusman
 * @since 2021/4/13 12:14 PM
 */
public class Playground {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("serverAddr", "127.0.0.1");
    
        try {
            NamingService naming = NamingFactory.createNamingService(properties);
            
            Instance instance = new Instance();
            instance.setIp("192.168.31.95");
            instance.setPort(8080);
            instance.setWeight(1);
            instance.setClusterName("public");
            
            naming.registerInstance("nacos.test.3", "public", instance);
            
            // naming.registerInstance("nacos.test.3", "2.2.2.2", 9999, "DEFAULT");
            List<Instance> instanceList = naming.getAllInstances("providers:org.apache.dubbo.demo.DemoServic");
            
            System.in.read();
            
            System.out.println(instanceList);
        } catch (NacosException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
