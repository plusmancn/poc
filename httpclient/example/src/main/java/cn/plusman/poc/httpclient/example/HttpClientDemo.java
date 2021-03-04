package cn.plusman.poc.httpclient.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author plusman
 * @since 2021/3/4 2:32 PM
 */
@Slf4j
public class HttpClientDemo {
    final static CloseableHttpClient httpClient;
    static {
        RequestConfig requestConfig = RequestConfig.custom()
            // 从连接池获取连接的等待时间
            .setConnectionRequestTimeout(2000)
            .setConnectTimeout(5000)
            .setSocketTimeout(5000)
            .build();
        
        httpClient = HttpClientBuilder
            .create()
            .setDefaultRequestConfig(requestConfig)
            .setMaxConnTotal(1)
            .build();
    }
    
    private static int count  = 0;
    public static void postDemo() throws IOException {
        log.info("enter {}", count++);
        
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        
        // 关闭资源释放
        // 进入 InternalHttpClient.doExecute
        CloseableHttpResponse response = httpClient.execute(httpGet);
        
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity, "UTF-8");
        log.info("count: {} finished, status {}", count, response.getStatusLine());
    }
    
    public static void main(String[] args) {
        try {
            postDemo();
        } catch (IOException e) {
            log.error("catch connection timeout", e);
        }
        
        try {
            postDemo();
        } catch (IOException e) {
            log.error("catch connection timeout", e);
        }
    }
}
