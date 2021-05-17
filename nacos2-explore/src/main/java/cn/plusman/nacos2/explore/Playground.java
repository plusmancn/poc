package cn.plusman.nacos2.explore;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author plusman
 * @since 2021/5/17 12:11 PM
 */
@Slf4j
public class Playground {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, "127.0.0.1:8850");
        properties.setProperty(PropertyKeyConst.NAMING_CACHE_REGISTRY_DIR, "v2xCache");
        
        try {
            NamingService namingService = NamingFactory.createNamingService(properties);
            
            Instance instance = new Instance();
            instance.setIp("192.168.31.95");
            instance.setPort(8080);
            instance.setWeight(1);
            instance.setClusterName("public");
    
            namingService.registerInstance("nacos.test.3", "public", instance);
    
            System.in.read();
    
            List<Instance> instanceList = namingService.getAllInstances("nacos.test.3", "public");
            System.out.println(instanceList);
        } catch (NacosException | IOException e) {
            log.error("CatchError", e);
        }
    }
}
