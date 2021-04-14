package configuration;

import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主备选举测试逻辑
 * @author plusman
 * @since 2021/4/12 2:52 PM
 */
@Slf4j
public class LeaderFollowerElectionConfiguration {
    public static void main(String[] args) {
        CompetitorConfiguration competitor1 = new CompetitorConfiguration("C1");
        CompetitorConfiguration competitor2 = new CompetitorConfiguration("c2");
    
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(() -> {
            try {
                competitor1.run();
            } catch (NacosException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        executorService.execute(() -> {
            try {
                competitor2.run();
            } catch (NacosException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
