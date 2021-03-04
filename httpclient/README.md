## Apache HttpClient 连接池案例
1. 运行步骤：进入 agent 子项目，运行 `mvn verify`，得到 `agent-1.0-SNAPSHOT-jar-with-dependencies.jar` 包的绝对路径
2. 编辑 `HttpClientDemo` 程序的启动参数，添加 `-javaagent:/xxx/xxxx/agent-1.0-SNAPSHOT-jar-with-dependencies.jar`，agnet 路径为 1 中得到的绝对路径。
3. 在 IDEA 中调试启动 `HttpClientDemo`，可同时在 agent 和 example 中的打断点观察。

样例代码的输出如下 ：
```shell
15:06:40.725 [main] INFO cn.plusman.poc.httpclient.agent.SimpleHttpClientAgent - In premain method
15:06:40.962 [main] INFO cn.plusman.poc.httpclient.example.HttpClientDemo - enter 0
15:06:41.221 [main] INFO cn.plusman.poc.httpclient.agent.BasicHttpResponseInterceptor - Found org/apache/http/message/BasicHttpResponse
15:06:41.271 [main] INFO cn.plusman.poc.httpclient.example.HttpClientDemo - count: 1 finished, status HTTP/1.1 200 OK
15:06:41.271 [main] INFO cn.plusman.poc.httpclient.example.HttpClientDemo - enter 1
15:06:43.281 [main] ERROR cn.plusman.poc.httpclient.example.HttpClientDemo - catch connection timeout
org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
	at org.apache.http.impl.conn.PoolingHttpClientConnectionManager.leaseConnection(PoolingHttpClientConnectionManager.java:316)
	at org.apache.http.impl.conn.PoolingHttpClientConnectionManager$1.get(PoolingHttpClientConnectionManager.java:282)
	at org.apache.http.impl.execchain.MainClientExec.execute(MainClientExec.java:190)
	at org.apache.http.impl.execchain.ProtocolExec.execute(ProtocolExec.java:186)
	at org.apache.http.impl.execchain.RetryExec.execute(RetryExec.java:89)
	at org.apache.http.impl.execchain.RedirectExec.execute(RedirectExec.java:110)
	at org.apache.http.impl.client.InternalHttpClient.doExecute(InternalHttpClient.java:185)
	at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:83)
	at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:108)
	at cn.plusman.poc.httpclient.example.HttpClientDemo.postDemo(HttpClientDemo.java:44)
	at cn.plusman.poc.httpclient.example.HttpClientDemo.main(HttpClientDemo.java:59)
```