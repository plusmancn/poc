package configuration;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author plusman
 * @since 2021/4/13 12:00 AM
 */
@Slf4j
public class CompetitorConfiguration {
    private String name;
    
    public CompetitorConfiguration(String name) {
        this.name = name;
    }
    
    public void run() throws NacosException, InterruptedException {
        String serverAddr = "127.0.0.1";
        // try get leader
        String dataId = "cn.plusman.nacos.explore.lfe::leader";
        String group = "LFE";
    
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 5000);
        log.info("「{}」before publish content: ", this.name, content);
        
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("「{}」receive: {}", name, configInfo);
            }
        
            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    
        boolean isPublishOk = configService.publishConfig(dataId, group, "hello nacos! by " + this.name);
        log.info("「{}」Publish result is: {}", this.name, isPublishOk);
    
        Thread.sleep(3000);
        content = configService.getConfig(dataId, group, 5000);
        log.info("「{}」config content is: {}", this.name, content);
        
        // Hold on
        Thread.sleep(4000);
        
        // boolean isRemoveOk = configService.removeConfig(dataId, group);
        // log.info("「{}」Remove Result is {}", this.name, isRemoveOk);
        // Thread.sleep(3000);
        //
        // content = configService.getConfig(dataId, group, 5000);
        // log.info("「{}」final content is: {}", this.name, content);
        // Thread.sleep(300000);
    }
}
