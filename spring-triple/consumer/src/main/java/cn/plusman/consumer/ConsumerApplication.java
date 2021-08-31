package cn.plusman.consumer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

import cn.plusman.api.GreeterService;
import cn.plusman.api.hello.HelloReply;
import cn.plusman.api.hello.HelloRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author plusman
 * @since 2021/8/30 10:46 PM
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "cn.plusman")
@Service
public class ConsumerApplication {
    @DubboReference
    private GreeterService greeterService;
    
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);

        ConsumerApplication application = context.getBean(ConsumerApplication.class);

        application.doSayHello();
    }

    public void doSayHello() {
        try {
            String  reply = greeterService.sayHello("Hello");
            System.out.println("Reply: " +  reply);
            TimeUnit.SECONDS.sleep(1);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
